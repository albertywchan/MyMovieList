package model;

import static org.junit.jupiter.api.Assertions.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {
    Movie m1;
    Movie m2;
    Movie m3;
    Movie m4;

    @BeforeEach
    void runBefore() {
        m1 = new Movie("Batman", "Action", -1, "");
        m2 = new Movie("Finding Nemo", "Family", -1, "");
        m3 = new Movie("Saw", "Horror", -1, "");
        m4 = new Movie("Superbad", "Comedy", -1, "");
    }

    @Test
    void testTitles() {
        // Using getters with original values
        assertEquals(m1.getTitle(), "Batman");
        assertEquals(m2.getTitle(), "Finding Nemo");
        // Using setters with new values
        m1.setTitle("Spiderman");
        m2.setTitle("Frozen");
        // Using getters with new values
        assertNotEquals(m1.getTitle(),"Batman");
        assertNotEquals(m2.getTitle(), "Finding Nemo");
        assertEquals(m1.getTitle(),"Spiderman");
        assertEquals(m2.getTitle(),"Frozen");
    }

    @Test
    void testGenres() {
        // Using getters with original values
        assertEquals(m3.getGenre(), "Horror");
        assertEquals(m4.getGenre(), "Comedy");
        // Using setters with new values
        m3.setGenre("Thriller");
        m4.setGenre("Drama");
        // Using getters with new values
        assertNotEquals(m3.getGenre(), "Horror");
        assertNotEquals(m4.getGenre(), "Comedy");
        assertEquals(m3.getGenre(), "Thriller");
        assertEquals(m4.getGenre(), "Drama");
    }

    @Test
    void testRatings() {
        // Using getters with original values (default is -1)
        assertEquals(m1.getRating(), -1);
        assertEquals(m2.getRating(), -1);
        // Using setters with new values
        m1.setRating(4);
        m2.setRating(5);
        // Using getters with new values
        assertNotEquals(m1.getRating(), -1);
        assertNotEquals(m2.getRating(), -1);
        assertEquals(m1.getRating(), 4);
        assertEquals(m2.getRating(), 5);

    }

    @Test
    void testComments() {
        // Using getters with original values (default is "")
        assertEquals(m3.getComment(), "");
        assertEquals(m4.getComment(), "");
        // Using setters with new values
        m3.setComment("Very scary movie.");
        m4.setComment("Hilarious movie.");
        // Using getters with new values
        assertNotEquals(m3.getComment(), "Very scary");
        assertNotEquals(m4.getComment(), "Hilarious");
        assertEquals(m3.getComment(), "Very scary movie.");
        assertEquals(m4.getComment(), "Hilarious movie.");
    }

    @Test
    void testToString() {
        assertEquals(m1.toString(), "Title: Batman | Genre: Action");
        assertEquals(m2.toString(), "Title: Finding Nemo | Genre: Family");
        assertEquals(m3.toString(), "Title: Saw | Genre: Horror");
        assertEquals(m4.toString(), "Title: Superbad | Genre: Comedy");
    }
}
