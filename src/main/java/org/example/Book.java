package org.example;

import org.example.interfaces.Loanable;

public final class Book extends LibraryItem implements Loanable {
    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY = 0.5;

    private final String author;
    private final String genre;
    private final String publisher;

    public Book(String libraryId, String title, String author, String genre, String publisher) {
        super(libraryId, title);
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    @Override
    public int getLoanPeriod(User user) {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public double getDailyOverdueFee() {
        return FINE_PER_DAY;
    }

    @Override
    public int getTypeOrder() {
        return 0;
    }

    @Override
    public Integer getYear() {
        return null;
    }

    @Override
    public boolean matches(String keyword) {
        String lower = keyword.toLowerCase();
        return getTitle().toLowerCase().contains(lower)
                || author.toLowerCase().contains(lower)
                || genre.toLowerCase().contains(lower)
                || publisher.toLowerCase().contains(lower);
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }
}
