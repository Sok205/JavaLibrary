package org.example.interfaces;

import org.example.LibraryItem;
import org.example.User;

public interface OverdueObserver {
    void notifyOverdue(User user, LibraryItem item, int daysLate);
}
