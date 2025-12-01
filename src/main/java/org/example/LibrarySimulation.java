package org.example;

import java.util.List;
import java.util.Random;

public class LibrarySimulation {
    private static final double BOOK_BORROW_PROBABILITY = 0.05;
    private static final double JOURNAL_BORROW_PROBABILITY = 0.08;
    private static final double FILM_BORROW_PROBABILITY = 0.05;
    private static final double RETURN_PROBABILITY = 0.02;
    private static final int SIMULATION_DAYS = 365;

    private final Library library;
    private final Random random;
    private double totalFinesCollected;

    public LibrarySimulation(Library library) {
        this.library = library;
        this.random = new Random();
        this.totalFinesCollected = 0.0;
    }

    public void simulate() {
        for (int day = 1; day <= SIMULATION_DAYS; day++) {
            simulateDay(day);
        }
    }

    private void simulateDay(int currentDay) {
        for (User user : library.getUsers()) {
            handleBorrowAttempts(user, currentDay);
            handleReturnAttempts(user, currentDay);
            handleOnTimeForcedReturns(user, currentDay);
        }
    }

    private void handleBorrowAttempts(User user, int currentDay) {
        attemptBorrow(user, currentDay, Book.class, BOOK_BORROW_PROBABILITY);
        attemptBorrow(user, currentDay, Journal.class, JOURNAL_BORROW_PROBABILITY);
        attemptBorrow(user, currentDay, Film.class, FILM_BORROW_PROBABILITY);
    }

    private void attemptBorrow(User user, int currentDay,
                               Class<? extends LibraryItem> itemType,
                               double probability) {
        if (random.nextDouble() < probability) {
            List<LibraryItem> availableItems = library.getAvailableItemsByType(itemType);
            if (!availableItems.isEmpty()) {
                LibraryItem item = availableItems.get(random.nextInt(availableItems.size()));
                library.borrowItem(item, user, currentDay);
            }
        }
    }

    private void handleReturnAttempts(User user, int currentDay) {
        List<LibraryItem> borrowedItems = user.getBorrowedItems();
        for (LibraryItem item : borrowedItems) {
            if (random.nextDouble() < RETURN_PROBABILITY) {
                returnItemWithFine(item, user, currentDay);
            }
        }
    }

    private void handleOnTimeForcedReturns(User user, int currentDay) {
        if (!user.isOnTime()) {
            return;
        }
        List<LibraryItem> borrowedItems = user.getBorrowedItems();
        for (LibraryItem item : borrowedItems) {
            if (item.daysOverdue(currentDay) >= 0) {
                returnItemWithFine(item, user, currentDay);
            }
        }
    }

    private void returnItemWithFine(LibraryItem item, User user, int currentDay) {
        double fine = item.computeFine(currentDay);
        totalFinesCollected += fine;
        library.returnItem(item, user);
    }

    public double getTotalFinesCollected() {
        return totalFinesCollected;
    }
}
