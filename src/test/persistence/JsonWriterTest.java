package persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieList ml = new MovieList("Test Movie List");
            JsonWriter jw = new JsonWriter("./data/\0foo.json");
            jw.saveMovieList(ml);
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFile() {
        try {
            // write
            MovieList ml1 = new MovieList("Test Movie List");
            JsonWriter jw = new JsonWriter("./data/testWriterEmptyFile.json");
            jw.saveMovieList(ml1);
            // read
            JsonReader jr = new JsonReader("./data/testWriterEmptyFile.json");
            MovieList ml2 = jr.parseMovieList();
            assertEquals(ml2.getName(), "Test Movie List");
            assertTrue(ml2.isEmpty());
        } catch (IOException e) {
            fail("IOException was not expected.");
        }
    }

    @Test
    void testWriterGeneralFile() {
        try {
            // write
            MovieList ml1 = new MovieList("Test Movie List");
            Movie m1 = new Movie("Batman", "Action", 4, "Excellent movie.");
            Movie m2 = new Movie("Finding Nemo", "Family", 3, "Great movie for children.");
            ml1.addMovie(m1);
            ml1.addMovie(m2);
            JsonWriter jw = new JsonWriter("./data/testWriterGeneralFile.json");
            jw.saveMovieList(ml1);
            // read
            JsonReader jr = new JsonReader("./data/testWriterGeneralFile.json");
            MovieList ml2 = jr.parseMovieList();
            assertEquals(ml2.getName(), "Test Movie List");
            assertFalse(ml2.isEmpty());
            assertTrue(ml2.hasMovie("Batman"));
            assertTrue(ml2.hasMovie("Finding Nemo"));
            assertEquals(ml2.getReview("Batman"), "Title: Batman\nGenre: Action\n"
                    + "Rating: 4/5\nComment: Excellent movie.\n");
            assertEquals(ml2.getReview("Finding Nemo"), "Title: Finding Nemo\nGenre: Family\n"
                    + "Rating: 3/5\nComment: Great movie for children.\n");
        } catch (IOException e) {
            fail("IOException was not expected.");
        }
    }
}
