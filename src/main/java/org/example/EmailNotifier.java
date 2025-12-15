package org.example;

import org.example.interfaces.OverdueObserver;

public final class EmailNotifier implements OverdueObserver {

    @Override
    public void notifyOverdue(User user, LibraryItem item, int daysLate) {
        System.out.println("Email: " + user.getName() + " (" + user.getUserId() + ") has "
                + item.getTitle() + " overdue by " + daysLate + " days");
    }
}
