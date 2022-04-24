package io.elmiguelo.business;

import io.elmiguelo.data.DataAccess;
import io.elmiguelo.data.DataAccessImpl;
import io.elmiguelo.domain.Movie;
import io.elmiguelo.exceptions.DataAccessException;
import io.elmiguelo.exceptions.DataReadException;
import io.elmiguelo.exceptions.DataWriteException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MovieCatalogImpl implements MovieCatalog {
    private final DataAccess access;

    public MovieCatalogImpl() {
        this.access = new DataAccessImpl();
    }

    @Override
    public void addMovie(String movieName) {
        Movie movie = new Movie(movieName);
        boolean fileExist = false;
        try {
            fileExist = this.access.fileExists(FILE_NAME);
            this.access.addMovie(movie,FILE_NAME,fileExist);
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMovies() {
        try {
            List movies = this.access.getAllMovies(FILE_NAME);
            movies.forEach(System.out::println);
        } catch (DataReadException e) {
            System.out.println("Error while getting Movies");
        }
    }

    @Override
    public void movieSearch(String movieName) {
        String rs = null;
        try {
            rs = this.access.find(FILE_NAME,movieName);
        } catch (DataReadException | FileNotFoundException e) {
            System.out.println("Error while getting Movie");
        }
        System.out.println(rs);
    }

    @Override
    public void createFile() {
        try {
            if(this.access.fileExists(FILE_NAME)){
                this.access.deleteFile(FILE_NAME);
                this.access.createFile(FILE_NAME);
            }else{
                this.access.createFile(FILE_NAME);
            }

        } catch (DataAccessException e) {
            System.out.println("Error while creating File " + e.getMessage());
        }
    }
}
