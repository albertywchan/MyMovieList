# My Personal Project

## MyMovieList

**MyMovieList** is an application that helps users keep track of movies they have seen. With this app, users will be 
able to create a watchlist of movies they would like to see. In addition, users will also be able to leave reviews of 
movies they have previously watched. Reviews will include a rating on a scale of one to five stars and a comment about 
what they thought about the movie.

This application will cater towards those who enjoy watching movies and enjoy providing recommendations to others. My
main inspiration for this app came from websites such as *Goodreads* and *MyDramaList*. *Goodreads* allows users to create
lists of books they intend on reading as well as leaving reviews for books they have already read. *MyDramaList* has
similar features for television shows. As someone who enjoys watching new movies as well as re-watching old movies, an
app such as **MyMovieList** would be very useful to have.

## User Stories

- As a user, I want to be able to view the list of movies on my watchlist
- As a user, I want to be able to add a movie to my watchlist
- As a user, I want to be able to remove a movie to my watchlist 
- As a user, I want to be able to view the list of movies I have reviewed
- As a user, I want to be able to write a review (rating & comment)
- As a user, I want to be able to read my previous reviews
- As a user, I want to be able to update my previous reviews
- As a user, I want to be able to load existing watchlists and reviews
- As a user, I want to be able to save my current watchlist and reviews

## Phase 4: Task 2
```
Sun Nov 21 20:49:49 PST 2021
Watchlist has been created
Sun Nov 21 20:49:49 PST 2021
Reviews has been created
Sun Nov 21 20:50:23 PST 2021
Watchlist has been created
Sun Nov 21 20:50:23 PST 2021
Batman has been added to Watchlist
Sun Nov 21 20:50:23 PST 2021
Finding Nemo has been added to Watchlist
Sun Nov 21 20:50:23 PST 2021
Superbad has been added to Watchlist
Sun Nov 21 20:50:23 PST 2021
The Conjuring has been added to Watchlist
Sun Nov 21 20:51:38 PST 2021
Superbad has been removed from Watchlist
Sun Nov 21 20:51:38 PST 2021
Superbad has been added to Reviews
Sun Nov 21 20:51:38 PST 2021
Review for Superbad has been updated
```

## Phase 4: Task 3

- Implement a bi-directional relationship between Movie and MovieList since each Movie should 
belong to only one MovieList
- Implement a class hierarchy for movies i.e. have Movie as a parent class and WatchlistMovie and ReviewedMovie
as subclasses
- Separate the MyMovieListGUI class into multiple components i.e. use one class for the Buttons panel, use another
class for the Watchlist panel, and another class for the Reviews panel
