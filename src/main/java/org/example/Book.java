package org.example;

public class Book extends LibraryItem {
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
    public double getFinePerDay() {
        return FINE_PER_DAY;
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
