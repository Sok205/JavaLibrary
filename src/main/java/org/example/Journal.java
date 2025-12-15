package org.example;

import org.example.interfaces.Loanable;

public final class Journal extends LibraryItem implements Loanable {
    private static final double FINE_PER_DAY = 2.0;

    private final String eISSN;
    private final String publisher;
    private final String latestIssue;
    private final String journalURL;

    public Journal(String libraryId, String title, String eISSN, String publisher,
                   String latestIssue, String journalURL) {
        super(libraryId, title);
        this.eISSN = eISSN;
        this.publisher = publisher;
        this.latestIssue = latestIssue;
        this.journalURL = journalURL;
    }

    @Override
    public int getLoanPeriod(User user) {
        return user.getJournalLoanPeriod();
    }

    @Override
    public double getDailyOverdueFee() {
        return FINE_PER_DAY;
    }

    @Override
    public int getTypeOrder() {
        return 1;
    }

    @Override
    public Integer getYear() {
        return null;
    }

    @Override
    public boolean matches(String keyword) {
        String lower = keyword.toLowerCase();
        return getTitle().toLowerCase().contains(lower)
                || publisher.toLowerCase().contains(lower)
                || eISSN.toLowerCase().contains(lower)
                || latestIssue.toLowerCase().contains(lower)
                || journalURL.toLowerCase().contains(lower);
    }

    public String getEISSN() {
        return eISSN;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getLatestIssue() {
        return latestIssue;
    }

    public String getJournalURL() {
        return journalURL;
    }
}
