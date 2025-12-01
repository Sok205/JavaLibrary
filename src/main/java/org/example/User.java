package org.example;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private final String userId;
    private final String name;
    private final boolean isOnTime;
    private final List<LibraryItem> borrowedItems;

    public User(String userId, String name, boolean isOnTime) {
        this.userId = userId;
        this.name = name;
        this.isOnTime = isOnTime;
        this.borrowedItems = new ArrayList<>();
    }

    public abstract int getMaxBooks();

    public abstract int getMaxJournals();

    public abstract int getMaxFilms();

    public abstract int getJournalLoanPeriod();

    public void borrowItem(LibraryItem item) {
        borrowedItems.add(item);
    }

    public void returnItem(LibraryItem item) {
        borrowedItems.remove(item);
    }

    public boolean canBorrow(LibraryItem item) {
        if (item instanceof Book) {
            return countBorrowedBooks() < getMaxBooks();
        } else if (item instanceof Journal) {
            return countBorrowedJournals() < getMaxJournals();
        } else if (item instanceof Film) {
            return countBorrowedFilms() < getMaxFilms();
        }
        return false;
    }

    private long countBorrowedBooks() {
        return borrowedItems.stream().filter(item -> item instanceof Book).count();
    }

    private long countBorrowedJournals() {
        return borrowedItems.stream().filter(item -> item instanceof Journal).count();
    }

    private long countBorrowedFilms() {
        return borrowedItems.stream().filter(item -> item instanceof Film).count();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public boolean isOnTime() {
        return isOnTime;
    }

    public List<LibraryItem> getBorrowedItems() {
        return new ArrayList<>(borrowedItems);
    }
}
