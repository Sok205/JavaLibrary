package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private final List<LibraryItem> items;
    private final List<User> users;

    public Library() {
        this.items = new ArrayList<>();
        this.users = new ArrayList<>();
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
}
