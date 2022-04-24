package io.elmiguelo.data;

import io.elmiguelo.domain.Movie;
import io.elmiguelo.exceptions.DataAccessException;
import io.elmiguelo.exceptions.DataReadException;
import io.elmiguelo.exceptions.DataWriteException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccessImpl implements DataAccess {


    @Override
    public boolean fileExists(String fileName) throws DataAccessException {
        return new File(FILE_PATH + fileName).exists();
    }

    @Override
    public List getAllMovies(String fileNAme) throws DataReadException {
        File file = new File(FILE_PATH + fileNAme);
        List list = new ArrayList();
        try{
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = read.readLine();
            while(line!=null){
                Movie  movie = new Movie(line);
                list.add(movie);
                line = read.readLine();
            }
            read.close();
        } catch (FileNotFoundException e) {
            throw new DataReadException("Exception while loading Movies: " + e.getMessage());
        } catch (IOException e) {
            throw new DataReadException("Exception while loading Movies: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void addMovie(Movie movie, String fileName, boolean add) throws DataWriteException {
        File file = new File(FILE_PATH + fileName);
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file,true));
            writer.println(movie.toString());
            writer.close();
            System.out.println("movie " + movie + "was added.");
        } catch (IOException e) {
            throw new DataWriteException("Exception while writing Movie: " + e.getMessage());
        }
    }

    @Override
    public String find(String fileName, String find) throws DataReadException {
        File file = new File(FILE_PATH + fileName);
        String rs = null;
        try {
            BufferedReader read = new BufferedReader(new FileReader(file));
            String movie = null;
            movie = read.readLine();
            int index = 1;
            while (movie!=null){
                if (find!=null && find.equalsIgnoreCase(movie)){
                    rs = "Movie " + movie + " found on index " + index;
                    break;
                }
                movie = read.readLine();
                index++;
            }
            read.close();
        } catch (FileNotFoundException e) {
            throw new DataReadException("Exception while looking for Movie: " + e.getMessage());
        } catch (IOException e) {
            throw new DataReadException("Exception while looking for Movie: " + e.getMessage());
        }
        return rs;
    }

    @Override
    public void createFile(String fileName) throws DataAccessException{
        File file = new File(FILE_PATH + fileName);
        try  {
            PrintWriter writer = new PrintWriter(file);
            writer.close();
            System.out.println("File was Created");
        } catch (FileNotFoundException e) {
            throw new DataAccessException("Exception while creating File: " + e.getMessage());
        }
    }

    @Override
    public void deleteFile(String fileName) {
        File file = new File(FILE_PATH + fileName);
        if(!file.exists()){
            System.out.println("file name: " + fileName + " does not exist.");
        }
        file.delete();
    }
}
