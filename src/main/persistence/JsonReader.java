package persistence;

import model.*;
import org.json.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private BufferedReader reader;
    private StringBuilder builder;
    private JSONObject movieList;
    private JSONArray movies;

    public JsonReader(String file) {
        try {
            reader = new BufferedReader(new FileReader(file));
            builder = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                line = reader.readLine();
            }
            movieList = new JSONObject(builder.toString());
            movies = movieList.getJSONArray("movies");
            reader.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    public MovieList parseMovieList() {
        String name = movieList.getString("name");
        MovieList movieList = new MovieList(name);
        parseMovies(movieList);
        return movieList;
    }

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
