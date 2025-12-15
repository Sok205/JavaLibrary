package org.example;

public final class Faculty extends User {
    private static final int JOURNAL_LOAN_PERIOD = 7;

    public Faculty(String userId, String name, boolean isOnTime) {
        super(userId, name, isOnTime);
    }

    @Override
    public int getMaxBooks() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxJournals() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getMaxFilms() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getJournalLoanPeriod() {
        return JOURNAL_LOAN_PERIOD;
    }
}
