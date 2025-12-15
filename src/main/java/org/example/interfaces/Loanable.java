package org.example.interfaces;

import org.example.User;

public interface Loanable {
    int getLoanPeriod(User user);
    double getDailyOverdueFee();
}
