package org.example;

import org.example.interfaces.OverdueObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Library {
    private final List<LibraryItem> items;
    private final List<User> users;
    private final List<OverdueObserver> observers;
    private final Set<String> notifiedOverdueItems;

    public Library() {
        this.observers = new ArrayList<>();
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
        this.notifiedOverdueItems = new HashSet<>();
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean borrowItem(LibraryItem item, User user, int currentDay) {
        if (!item.isAvailable()) {
            return false;
        }
        if (!user.canBorrow(item)) {
            return false;
        }
        item.borrow(user, currentDay);
        user.borrowItem(item);
        return true;
    }

    public void returnItem(LibraryItem item, User user) {
        notifiedOverdueItems.remove(item.getLibraryId());
        item.returnItem();
        user.returnItem(item);
    }

    public List<LibraryItem> getAvailableItems() {
        return items.stream()
                .filter(LibraryItem::isAvailable)
                .collect(Collectors.toList());
    }

    public List<LibraryItem> getAvailableItemsByType(Class<? extends LibraryItem> type) {
        return items.stream()
                .filter(LibraryItem::isAvailable)
                .filter(type::isInstance)
                .collect(Collectors.toList());
    }

    public List<LibraryItem> getItems() {
        return new ArrayList<>(items);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public List<LibraryItem> search(String keyword) {
        return items.stream()
                .filter(item -> item.matches(keyword))
                .collect(Collectors.toList());
    }

    public List<LibraryItem> getSortedCatalog() {
        List<LibraryItem> sorted = new ArrayList<>(items);
        Collections.sort(sorted);
        return sorted;
    }

    public void addObserver(OverdueObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(OverdueObserver observer) {
        this.observers.remove(observer);
    }

    public void checkOverdue(int currentDay) {
        for (LibraryItem item : items) {
            if (!item.isAvailable() && item.isOverdue(currentDay)) {
                if (!notifiedOverdueItems.contains(item.getLibraryId())) {
                    notifiedOverdueItems.add(item.getLibraryId());
                    int daysLate = item.daysOverdue(currentDay);
                    User borrower = item.getBorrower();
                    for (OverdueObserver observer : observers) {
                        observer.notifyOverdue(borrower, item, daysLate);
                    }
                }
            }
        }
    }
}
