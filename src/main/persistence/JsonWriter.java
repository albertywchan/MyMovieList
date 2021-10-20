package persistence;

import model.*;
import org.json.*;
import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    private FileWriter writer;

    public JsonWriter(String file) {
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

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
