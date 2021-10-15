package model;

import java.util.Iterator;
import java.util.LinkedList;

public class MovieList {
    private LinkedList<Movie> movieList;

    public MovieList() {
        movieList = new LinkedList<>();
    }

    public void addMovie(Movie newMovie) {
        movieList.add(newMovie);
    }

    public Movie removeMovie(String title) {
        int index = getIndexOfMovie(title);
        return movieList.remove(index);
    }

    public void getReview(String title) {
        int index = getIndexOfMovie(title);
        Movie movie = movieList.get(index);
        System.out.println(movie.toString() + "\n"
                + "Rating: " + movie.getRating() + "\n"
                + "Comment: " + movie.getComment());

    }

    public void updateReview(String title, int rating, String comment) {
        int index = getIndexOfMovie(title);
        Movie movie = movieList.get(index);
        movie.setRating(rating);
        movie.setComment(comment);
    }

    public int getIndexOfMovie(String title) {
        int index = 0;
        boolean found = false;
        for (Movie movie: movieList) {
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

    public boolean hasMovie(String title) {
        return getIndexOfMovie(title) != -1;
    }

    public boolean isEmpty() {
        return movieList.isEmpty();
    }

    @Override
    public String toString() {
        Iterator<Movie> iterator = movieList.iterator();
        String movieListString = iterator.next().getTitle();
        while (iterator.hasNext()) {
            movieListString += ", " + iterator.next().getTitle();
        }
        return movieListString;
    }

}
