package model;

import static org.junit.jupiter.api.Assertions.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieListTest {
    MovieList l1;
    MovieList l2;

    @BeforeEach
    void runBefore() {
        l1 = new MovieList("l1");
        l2 = new MovieList("l2");
        Movie m1 = new Movie("Batman", "Action", -1, "");
        Movie m2 = new Movie("Finding Nemo", "Family", -1, "");
        Movie m3 = new Movie("Saw", "Horror", -1, "");
        Movie m4 = new Movie("Superbad", "Comedy", -1, "");
        l1.addMovie(m1);
        l1.addMovie(m2);
        l2.addMovie(m3);
        l2.addMovie(m4);
    }

    @Test
    void testAddAndRemove() {
        assertEquals(l1.length(), 2);
        assertEquals(l2.length(), 2);
        Movie m1 = new Movie("Spiderman", "Action", -1, "");
        Movie m2 = new Movie("Frozen", "Family", -1 ,"");
        l1.addMovie(m1);
        l1.addMovie(m2);
        l2.removeMovie("Saw");
        l2.removeMovie("Superbad");
        assertEquals(l1.length(), 4);
        assertEquals(l2.length(), 0);
    }

    @Test
    void testReviews() {
        assertEquals(l1.getReview("Batman"), "Title: Batman | Genre: Action\n"
                + "Rating: -1/5\n Comment: \n");
        assertEquals(l1.getReview("Finding Nemo"), "Title: Finding Nemo | Genre: Family\n"
                + "Rating: -1/5\n Comment: \n");
        l1.updateReview("Batman", 4, "Excellent movie.");
        l1.updateReview("Finding Nemo", 3, "Great movie for children.");
        assertEquals(l1.getReview("Batman"), "Title: Batman | Genre: Action\n"
                + "Rating: 4/5\n Comment: Excellent movie.\n");
        assertEquals(l1.getReview("Finding Nemo"), "Title: Finding Nemo | Genre: Family\n"
                + "Rating: 3/5\n Comment: Great movie for children.\n");
    }

    @Test
    void testHasMovie() {
        assertFalse(l1.hasMovie("Spiderman"));
        assertTrue(l1.hasMovie("Batman"));
        assertFalse(l2.hasMovie("Saw 2"));
        assertTrue(l2.hasMovie("Saw"));
    }

    @Test
    void testIsEmpty() {
        assertFalse(l1.isEmpty());
        l1.removeMovie("Batman");
        assertFalse(l1.isEmpty());
        l1.removeMovie("Finding Nemo");
        assertTrue(l1.isEmpty());
    }

    @Test
    void testToString() {
        assertEquals(l1.toString(), "Batman, Finding Nemo");
        assertEquals(l2.toString(), "Saw, Superbad");
        Movie m1 = new Movie("Spiderman", "Action", -1, "");
        Movie m2 = new Movie("Frozen", "Family", -1, "");
        l1.addMovie(m1);
        l1.addMovie(m2);
        l2.removeMovie("Saw");
        l2.removeMovie("Superbad");
        assertEquals(l1.toString(), "Batman, Finding Nemo, Spiderman, Frozen");
        assertEquals(l2.toString(), "");
    }
}
