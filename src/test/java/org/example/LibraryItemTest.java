package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryItemTest {
    private Library library;
    private Student student;
    private Faculty faculty;
    private Book book;
    private Journal journalForStudent;
    private Journal journalForFaculty;
    private Film film;

    @BeforeEach
    void setUp() {
        library = new Library();
        student = new Student("S1", "Test Student", true);
        faculty = new Faculty("F1", "Test Faculty", true);

        book = new Book("B1", "Test Book", "Test Author", "Fiction", "Test Publisher");
        journalForStudent = new Journal("J1", "Test Journal", "1234-5678", "Test Publisher", "Issue 1", "http://test.com");
        journalForFaculty = new Journal("J2", "Test Journal 2", "1234-5679", "Test Publisher", "Issue 2", "http://test2.com");
        film = new Film("F1", "Test Film", "Action", "Test Director", 2020, 120, "PG-13");

        library.addItem(book);
        library.addItem(journalForStudent);
        library.addItem(journalForFaculty);
        library.addItem(film);
        library.addUser(student);
        library.addUser(faculty);
    }

    @Test
    void testBookDaysOverdue_NotOverdue() {
        library.borrowItem(book, student, 1);
        assertEquals(-10, book.daysOverdue(5));
    }

    @Test
    void testBookDaysOverdue_Overdue() {
        library.borrowItem(book, student, 1);
        assertEquals(1, book.daysOverdue(16));
    }

    @Test
    void testBookDaysOverdue_ExactlyOnTime() {
        library.borrowItem(book, student, 1);
        assertEquals(0, book.daysOverdue(15));
    }

    @Test
    void testBookIsOverdue_NotOverdue() {
        library.borrowItem(book, student, 1);
        assertFalse(book.isOverdue(10));
    }

    @Test
    void testBookIsOverdue_Overdue() {
        library.borrowItem(book, student, 1);
        assertTrue(book.isOverdue(16));
    }

    @Test
    void testBookIsOverdue_ExactlyOnTime() {
        library.borrowItem(book, student, 1);
        assertFalse(book.isOverdue(15));
    }

    @Test
    void testBookComputeFine_NotOverdue() {
        library.borrowItem(book, student, 1);
        assertEquals(0.0, book.computeFine(10), 0.01);
    }

    @Test
    void testBookComputeFine_Overdue() {
        library.borrowItem(book, student, 1);
        assertEquals(2.5, book.computeFine(20), 0.01);
    }

    @Test
    void testJournalDaysOverdue_Student() {
        library.borrowItem(journalForStudent, student, 1);
        assertEquals(2, journalForStudent.daysOverdue(6));
    }

    @Test
    void testJournalDaysOverdue_Faculty() {
        library.borrowItem(journalForFaculty, faculty, 1);
        assertEquals(-2, journalForFaculty.daysOverdue(6));
    }

    @Test
    void testJournalIsOverdue_Student() {
        library.borrowItem(journalForStudent, student, 1);
        assertTrue(journalForStudent.isOverdue(5));
    }

    @Test
    void testJournalIsOverdue_Faculty() {
        library.borrowItem(journalForFaculty, faculty, 1);
        assertFalse(journalForFaculty.isOverdue(6));
    }

    @Test
    void testJournalComputeFine_Student() {
        library.borrowItem(journalForStudent, student, 1);
        assertEquals(6.0, journalForStudent.computeFine(7), 0.01);
    }

    @Test
    void testJournalComputeFine_Faculty() {
        library.borrowItem(journalForFaculty, faculty, 1);
        assertEquals(0.0, journalForFaculty.computeFine(6), 0.01);
    }

    @Test
    void testFilmDaysOverdue_NotOverdue() {
        library.borrowItem(film, student, 1);
        assertEquals(-1, film.daysOverdue(2));
    }

    @Test
    void testFilmDaysOverdue_Overdue() {
        library.borrowItem(film, student, 1);
        assertEquals(2, film.daysOverdue(5));
    }

    @Test
    void testFilmIsOverdue_NotOverdue() {
        library.borrowItem(film, student, 1);
        assertFalse(film.isOverdue(2));
    }

    @Test
    void testFilmIsOverdue_Overdue() {
        library.borrowItem(film, student, 1);
        assertTrue(film.isOverdue(4));
    }

    @Test
    void testFilmComputeFine_NotOverdue() {
        library.borrowItem(film, student, 1);
        assertEquals(0.0, film.computeFine(2), 0.01);
    }

    @Test
    void testFilmComputeFine_Overdue() {
        library.borrowItem(film, student, 1);
        assertEquals(10.0, film.computeFine(5), 0.01);
    }
}
