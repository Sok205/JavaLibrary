package org.example;

import org.example.interfaces.Searchable;

import java.util.Objects;

public abstract sealed class LibraryItem implements Searchable, Comparable<LibraryItem>
        permits Book, Journal, Film {
    private final String libraryId;
    private String title;
    private Integer borrowDate;
    private User borrower;

    public LibraryItem(String libraryId, String title) {
        this.libraryId = libraryId;
        this.title = title;
    }

    public abstract int getLoanPeriod(User user);

    public abstract double getDailyOverdueFee();

    public abstract int getTypeOrder();

    public abstract Integer getYear();

    public void borrow(User user, int currentDay) {
        this.borrower = user;
        this.borrowDate = currentDay;
    }

    public void returnItem() {
        this.borrower = null;
        this.borrowDate = null;
    }

    public boolean isAvailable() {
        return borrower == null;
    }

    public int daysOverdue(int currentDay) {
        if (borrowDate == null) {
            return 0;
        }
        int daysOnLoan = currentDay - borrowDate;
        int allowedDays = getLoanPeriod(borrower);
        return daysOnLoan - allowedDays;
    }

    public boolean isOverdue(int currentDay) {
        return daysOverdue(currentDay) > 0;
    }

    public double computeFine(int currentDay) {
        int overdueDays = daysOverdue(currentDay);
        if (overdueDays <= 0) {
            return 0.0;
        }
        return overdueDays * getDailyOverdueFee();
    }

    @Override
    public int compareTo(LibraryItem o) {
        if (!Objects.equals(this.title, o.title)) {
            return this.title.compareTo(o.title);
        }

        if (this.getTypeOrder() != o.getTypeOrder()) {
            return Integer.compare(this.getTypeOrder(), o.getTypeOrder());
        }

        Integer thisYear = this.getYear();
        Integer otherYear = o.getYear();
        if (thisYear != null && otherYear != null) {
            return Integer.compare(thisYear, otherYear);
        }
        if (thisYear != null) {
            return -1;
        }
        if (otherYear != null) {
            return 1;
        }

        return 0;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public String getTitle() {
        return title;
    }

    public Integer getBorrowDate() {
        return borrowDate;
    }

    public User getBorrower() {
        return borrower;
    }
}
