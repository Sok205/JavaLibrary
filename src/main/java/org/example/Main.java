package org.example;

import java.util.Random;

public class Main {
    private static final int STUDENT_COUNT = 80;
    private static final int FACULTY_COUNT = 20;
    private static final int ON_TIME_COUNT = 67;
    private static final int BOOK_COUNT = 100;
    private static final int JOURNAL_COUNT = 50;
    private static final int FILM_COUNT = 50;

    public static void main(String[] args) {
        Library library = createLibrary();
        LibrarySimulation simulation = new LibrarySimulation(library);

        System.out.println("Starting library simulation for 365 days...");
        simulation.simulate();

        System.out.println("Simulation completed.");
        System.out.printf("Total fines collected: $%.2f%n", simulation.getTotalFinesCollected());
    }

    private static Library createLibrary() {
        Library library = new Library();
        Random random = new Random();

        populateUsers(library, random);
        populateBooks(library);
        populateJournals(library);
        populateFilms(library);

        return library;
    }

    private static void populateUsers(Library library, Random random) {
        boolean[] onTimeFlags = generateOnTimeFlags(random);
        int index = 0;

        for (int i = 0; i < STUDENT_COUNT; i++) {
            library.addUser(new Student("S" + i, "Student " + i, onTimeFlags[index++]));
        }

        for (int i = 0; i < FACULTY_COUNT; i++) {
            library.addUser(new Faculty("F" + i, "Faculty " + i, onTimeFlags[index++]));
        }
    }

    private static boolean[] generateOnTimeFlags(Random random) {
        boolean[] flags = new boolean[STUDENT_COUNT + FACULTY_COUNT];
        for (int i = 0; i < ON_TIME_COUNT; i++) {
            flags[i] = true;
        }
        shuffleArray(flags, random);
        return flags;
    }

    private static void shuffleArray(boolean[] array, Random random) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            boolean temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private static void populateBooks(Library library) {
        String[] genres = {"Fiction", "Non-Fiction", "Mystery", "Science Fiction", "Romance"};
        String[] publishers = {"Penguin", "HarperCollins", "Random House", "Simon & Schuster"};

        for (int i = 0; i < BOOK_COUNT; i++) {
            library.addItem(new Book(
                    "B" + i,
                    "Book Title " + i,
                    "Author " + (i % 20),
                    genres[i % genres.length],
                    publishers[i % publishers.length]
            ));
        }
    }

    private static void populateJournals(Library library) {
        String[] publishers = {"Elsevier", "Springer", "Wiley", "IEEE"};

        for (int i = 0; i < JOURNAL_COUNT; i++) {
            library.addItem(new Journal(
                    "J" + i,
                    "Journal Title " + i,
                    "2049-" + String.format("%04d", i),
                    publishers[i % publishers.length],
                    "Issue " + (i % 12),
                    "http://journal" + i + ".example.com"
            ));
        }
    }

    private static void populateFilms(Library library) {
        String[] genres = {"Action", "Comedy", "Drama", "Horror", "Sci-Fi"};
        String[] ratings = {"G", "PG", "PG-13", "R"};

        for (int i = 0; i < FILM_COUNT; i++) {
            library.addItem(new Film(
                    "F" + i,
                    "Film Title " + i,
                    genres[i % genres.length],
                    "Director " + (i % 15),
                    2000 + (i % 24),
                    90 + (i % 90),
                    ratings[i % ratings.length]
            ));
        }
    }
}