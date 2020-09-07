package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.MovieDTO;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(); 
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    
    @GET
    @Path("/allmovies")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllMovies() {
        List<MovieDTO> wrapped = new ArrayList<>();
        try {
            List<Movie> movies = FACADE.getAllMovies();
            for (Movie m : movies) {
                wrapped.add(new MovieDTO(m));
            }
            String jsonString = GSON.toJson(wrapped);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
    
    @GET
    @Path("/by_id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMovieByID(@PathParam("id") int id) {
        try {
            Movie movie = FACADE.getMovieByID(id);
            MovieDTO movieDTO = new MovieDTO(movie);
            String jsonString = GSON.toJson(movieDTO);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: The movie with the specified ID (" + id + ") does not exist.";
        }
    }
    
    @GET
    @Path("/by_year/{year}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getMoviesByReleaseYear(@PathParam("year") int year) {
        List<MovieDTO> wrapped = new ArrayList<>();
        try {
            List<Movie> movies = FACADE.getMoviesByReleaseYear(year, FACADE);
            for (Movie m : movies) {
                wrapped.add(new MovieDTO(m));
            }
            String jsonString = GSON.toJson(wrapped);
            if (movies.isEmpty()) {
                return "ERROR: There are no movies with the specified year (" + year + ").";
            }
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Make sure to type a year (use numbers).";
        } 
    }
    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
}
