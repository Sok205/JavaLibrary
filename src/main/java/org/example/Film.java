package org.example;

public class Film extends LibraryItem {
    private static final int LOAN_PERIOD_DAYS = 2;
    private static final double FINE_PER_DAY = 5.0;

    private final String genre;
    private final String director;
    private final Integer year;
    private final Integer runtime;
    private final String rating;

    public Film(String libraryId, String title, String genre, String director,
                Integer year, Integer runtime, String rating) {
        super(libraryId, title);
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.runtime = runtime;
        this.rating = rating;
    }

    @Override
    public int getLoanPeriod(User user) {
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public double getFinePerDay() {
        return FINE_PER_DAY;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getRating() {
        return rating;
    }
}
