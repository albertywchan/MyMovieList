package persistence;

import model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

// This class represents a reader that reads MovieList objects from JSON files
public class JsonReader {
    private JSONObject movieList;
    private JSONArray movies;

    /* EFFECTS:  constructs a reader from the given JSON file and reads the MovieList from it
                 catches IOException when an invalid file name is provided
     */
    public JsonReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            line = reader.readLine();
        }
        movieList = new JSONObject(builder.toString());
        movies = movieList.getJSONArray("movies");
        reader.close();
    }

    // EFFECTS:  returns a MovieList object with all the movies provided in the JSON file
    public MovieList parseMovieList() {
        String name = movieList.getString("name");
        MovieList movieList = new MovieList(name);
        parseMovies(movieList);
        return movieList;
    }

    /* MODIFIES: movieList
       EFFECTS:  parses all the movies provided in the JSON file and adds it to movieList
     */
    private void parseMovies(MovieList movieList) {
        for (Object objMovie : movies) {
            JSONObject jsonMovie = (JSONObject) objMovie;
            String title = jsonMovie.getString("title");
            String genre = jsonMovie.getString("genre");
            int rating = jsonMovie.getInt("rating");
            String comment = jsonMovie.getString("comment");
            Movie movie = new Movie(title, genre, rating, comment);
            movieList.addMovie(movie);
        }
    }
}
