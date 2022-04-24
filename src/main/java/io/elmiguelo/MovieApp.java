package io.elmiguelo;

import io.elmiguelo.business.MovieCatalog;
import io.elmiguelo.business.MovieCatalogImpl;
import io.elmiguelo.data.DataAccessImpl;

import java.io.IOException;
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int op = -1;
        MovieCatalog catalog = new MovieCatalogImpl();
        String fileName = null;
        while (op!=0){
            System.out.println("Choose option: \n"
                + "1.-Start Catalog \n"
                + "2.-Add Movie \n"
                + "3.-Get Movies \n"
                + "4.-Find Movie \n"
                + "0.-End");
            op = Integer.parseInt(in.nextLine());

            switch (op){
                case 1:
                    catalog.createFile();
                    break;
                case 2:
                    System.out.println("Type in your Movie");
                    String movie = in.nextLine();
                    catalog.addMovie(movie);
                    break;
                case 3:
                    catalog.getMovies();
                    break;
                case 4:
                    System.out.println("Type in movie to find");
                    String toFind = in.nextLine();
                    catalog.movieSearch(toFind);
                    break;
                case 0:
                    System.out.println("Fare well!");
                    break;
            }
        }
    }
}
