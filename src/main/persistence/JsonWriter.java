package persistence;

import model.*;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

// This class represents a reader that reads MovieList objects from JSON files
public class JsonWriter {
    private FileWriter writer;

    /* EFFECTS:  constructs a writer that will write to the given file
                 throws IOException if given file cannot be open
     */
    public JsonWriter(String fileName) {
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    // EFFECTS:  saves movieList as a JSON file
    public void saveMovieList(MovieList movieList) {
        JSONObject json = movieList.toJson();
        try {
            writer.write(json.toString());
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
