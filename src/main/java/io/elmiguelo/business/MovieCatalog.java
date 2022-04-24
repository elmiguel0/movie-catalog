package io.elmiguelo.business;

public interface MovieCatalog {
    String FILE_NAME = "movies.txt";
    void addMovie(String MovieName);
    void getMovies();
    void movieSearch(String movieName);
    void createFile();
}
