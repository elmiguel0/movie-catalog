package io.elmiguelo.data;

import io.elmiguelo.domain.Movie;
import io.elmiguelo.exceptions.DataAccessException;
import io.elmiguelo.exceptions.DataReadException;
import io.elmiguelo.exceptions.DataWriteException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DataAccess {
    String FILE_PATH = "src/main/resources/";
    boolean  fileExists(String fineName) throws DataAccessException;
    List<Movie> getAllMovies(String fileName) throws DataReadException;
    void addMovie(Movie movie,String fileName,boolean add) throws DataWriteException, IOException;
    String find(String fileName, String movieName) throws DataReadException, FileNotFoundException;

    void createFile(String fileName) throws DataAccessException;
    void deleteFile(String fileNAme) throws DataAccessException;
}
