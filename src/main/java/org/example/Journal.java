package org.example;

public class Journal extends LibraryItem {
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
    public double getFinePerDay() {
        return FINE_PER_DAY;
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
