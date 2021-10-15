package model;

import java.util.Iterator;
import java.util.LinkedList;

public class MovieList {
    private LinkedList<Movie> movieList;

    public MovieList() {
        movieList = new LinkedList<Movie>();
    }

    public void addMovie(Movie newMovie) {
        movieList.add(newMovie);
    }

    public void removeMovie(String title) {
        int index = getIndexOfMovie(title);
        movieList.remove(index);
    }

    public Movie getMovie(String title) {
        int index = getIndexOfMovie(title);
        return movieList.get(index);
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

    public boolean isEmpty() {
        return movieList.isEmpty();
    }

    @Override
    public String toString() {
        Iterator<Movie> iterator = movieList.iterator();
        String movieListString = iterator.next().toString();
        while (iterator.hasNext()) {
            movieListString += ", " + iterator.next().toString();
        }
        return movieListString;
    }

}
