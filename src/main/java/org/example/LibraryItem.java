package org.example;

public abstract class LibraryItem {
    private final String libraryId;
    private final String title;
    private Integer borrowDate;
    private User borrower;

    public LibraryItem(String libraryId, String title) {
        this.libraryId = libraryId;
        this.title = title;
    }

    public abstract int getLoanPeriod(User user);

    public abstract double getFinePerDay();

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
        return overdueDays * getFinePerDay();
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
