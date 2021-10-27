package persistence;

import model.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

// This class represents a reader that reads MovieList objects from JSON files
public class JsonWriter {
    private static final int INDENT_FACTOR = 2;
    private FileWriter writer;

    /* EFFECTS:  constructs a writer that will write to the given file
                 throws IOException if given file cannot be open
     */
    public JsonWriter(String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }

    // EFFECTS:  saves movieList as a JSON file
    public void saveMovieList(MovieList movieList) throws IOException {
        JSONObject json = movieList.toJson();
        writer.write(json.toString(INDENT_FACTOR));
        writer.close();
    }
}
