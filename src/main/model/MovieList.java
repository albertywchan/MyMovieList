package model;

import java.util.Iterator;
import java.util.LinkedList;

// This class represents a list of movies
public class MovieList {
    private LinkedList<Movie> movieList;

    // EFFECTS:  movieList is set to an empty linked list
    public MovieList() {
        movieList = new LinkedList<>();
    }

    /* REQUIRES: movie object that is not null and movie title cannot be identical to one that is already in movieList
       MODIFIES: this
       EFFECTS:  newMovie is added to movieList
     */
    public void addMovie(Movie newMovie) {
        movieList.add(newMovie);
    }

    /* REQUIRES: movieList is not empty and the movie with the particular movie is in movieList
       MODIFIES: this
       EFFECTS:  movie with the particular title is removed from movieList
     */
    public Movie removeMovie(String title) {
        int index = getIndexOfMovie(title);
        return movieList.remove(index);
    }

    /* REQUIRES: movieList is not empty and the movie with the particular movie is in movieList
       EFFECTS:  returns a String representation of a particular movie including its rating and comment
     */
    public String getReview(String title) {
        int index = getIndexOfMovie(title);
        Movie movie = movieList.get(index);
        return (movie.toString() + "\n"
                + "Rating: " + movie.getRating() + "/5\n Comment: " + movie.getComment() + "\n");
    }

    /* REQUIRES: movieList is not empty, the movie with the particular movie is in movieList, rating is between 1 and 5
       EFFECTS:  rating and comment is set to the parameters for the particular movie
     */
    public void updateReview(String title, int rating, String comment) {
        int index = getIndexOfMovie(title);
        Movie movie = movieList.get(index);
        movie.setRating(rating);
        movie.setComment(comment);
    }

    /* EFFECTS:  returns the index of the particular movie if it is present in movieList
                 returns -1 otherwise
     */
    private int getIndexOfMovie(String title) {
        int index = 0;
        boolean found = false;
        for (Movie movie : movieList) {
            if (movie.getTitle().equals(title)) {
                found = true;
                break;
            }
            index++;
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    /* EFFECTS:  returns true if the particular movie is present in movieList
                 returns false otherwise
     */
    public boolean hasMovie(String title) {
        return getIndexOfMovie(title) != -1;
    }

    /* EFFECTS:  returns true if movieList is empty
                 returns false otherwise
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    // EFFECTS:  returns the number of movies currently in movieList
    public int length() {
        return movieList.size();
    }

    // EFFECTS: returns a String that lists out all the movie titles in movieList
    @Override
    public String toString() {
        String movieListString = "";
        if (!isEmpty()) {
            Iterator<Movie> iterator = movieList.iterator();
            movieListString = iterator.next().getTitle();
            while (iterator.hasNext()) {
                movieListString += ", " + iterator.next().getTitle();
            }
        }
        return movieListString;
    }
}
