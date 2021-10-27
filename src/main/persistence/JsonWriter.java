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
    public JsonWriter(String fileName) {
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println(fileName + " file was not found. Please use a different file name.");
        }
    }

    // EFFECTS:  saves movieList as a JSON file
    public void saveMovieList(MovieList movieList) {
        JSONObject json = movieList.toJson();
        try {
            writer.write(json.toString(INDENT_FACTOR));
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to save. Please use a different file name.");
        }
    }
}
