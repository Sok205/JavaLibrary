package org.example;

public class Student extends User {
    private static final int MAX_BOOKS = 3;
    private static final int MAX_JOURNALS = 3;
    private static final int MAX_FILMS = 1;
    private static final int JOURNAL_LOAN_PERIOD = 3;

    public Student(String userId, String name, boolean isOnTime) {
        super(userId, name, isOnTime);
    }

    @Override
    public int getMaxBooks() {
        return MAX_BOOKS;
    }

    @Override
    public int getMaxJournals() {
        return MAX_JOURNALS;
    }

    @Override
    public int getMaxFilms() {
        return MAX_FILMS;
    }

    @Override
    public int getJournalLoanPeriod() {
        return JOURNAL_LOAN_PERIOD;
    }
}
