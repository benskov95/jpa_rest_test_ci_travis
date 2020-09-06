package dto;

import entities.Movie;


public class MovieDTO {
    
    private String title;
    private String description;

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
    }
    
    
}
