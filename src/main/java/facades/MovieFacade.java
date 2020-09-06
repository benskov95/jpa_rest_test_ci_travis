package facades;

import entities.Movie;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Movie("Bob's Revenge", "He's crazy", 2011));
        em.persist(new Movie("Autumn", "Very artsy and convoluted", 1973));
        em.persist(new Movie("Superguy", "He's like a guy but super", 2016));
        em.getTransaction().commit();
        em.close();
        
    }
    

    private MovieFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Movie addMovie(String title, String description, int releaseYear) {
        EntityManager em = getEntityManager();
        Movie m = new Movie(title, description, releaseYear);
        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return m;
        } finally {
            em.close();
        }
    }
    
    public Movie getMovieByID(int id) {
        EntityManager em = getEntityManager();
        try {
            Movie m = em.find(Movie.class, id);
            return m;
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getAllMovies() {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em.createQuery("SELECT m FROM Movie m", Movie.class);
        List<Movie> movies = query.getResultList();
        return movies;
        } finally {
            em.close();
        }
    }
    
    public List<Movie> getMoviesByReleaseYear(int year) {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em.createQuery("SELECT m FROM Movie m WHERE m.releaseYear = :year", Movie.class);
        query.setParameter("year", year);
        List<Movie> movies = query.getResultList();
        return movies;
        } finally {
            em.close();
        }
    }
    
    public long getMovieCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long movieCount = (long)em.createQuery("SELECT COUNT(m) FROM Movie m").getSingleResult();
            return movieCount;
        }finally{  
            em.close();
        }
        
    }

}
