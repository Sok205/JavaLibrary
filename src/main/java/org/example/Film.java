package org.example;

import org.example.interfaces.Loanable;

public final class Film extends LibraryItem implements Loanable {
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
    public double getDailyOverdueFee() {
        return FINE_PER_DAY;
    }

    @Override
    public int getTypeOrder() {
        return 2;
    }

    @Override
    public Integer getYear() {
        return year;
    }

    @Override
    public boolean matches(String keyword) {
        String lower = keyword.toLowerCase();
        if (getTitle().toLowerCase().contains(lower)) return true;
        if (genre.toLowerCase().contains(lower)) return true;
        if (director.toLowerCase().contains(lower)) return true;
        if (rating.toLowerCase().contains(lower)) return true;
        if (year != null && String.valueOf(year).contains(lower)) return true;
        return false;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getRating() {
        return rating;
    }
}
