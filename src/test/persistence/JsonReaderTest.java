package persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.MovieList;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        try {
            JsonReader jr = new JsonReader("./data/foo.json");
            MovieList ml = jr.parseMovieList();
            fail("IOException was expected.");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFile() {
        try {
            JsonReader jr = new JsonReader("./data/testReaderEmptyFile.json");
            MovieList ml = jr.parseMovieList();
            assertEquals(ml.getName(), "Test Movie List");
            assertTrue(ml.isEmpty());
        } catch (IOException e) {
            fail("IOException was not expected.");
        }
    }

    @Test
    void testReaderGeneralFile() {
        try {
            JsonReader jr = new JsonReader("./data/testReaderGeneralFile.json");
            MovieList ml = jr.parseMovieList();
            assertEquals(ml.getName(), "Test Movie List");
            assertFalse(ml.isEmpty());
            assertTrue(ml.hasMovie("Batman"));
            assertEquals(ml.getReview("Batman"), "Title: Batman\nGenre: Action\n"
                    + "Rating: 4/5\nComment: Excellent movie.\n");
        } catch (IOException e) {
            fail("IOException was not expected.");
        }
    }
}
