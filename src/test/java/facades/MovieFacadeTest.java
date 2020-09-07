package facades;

import utils.EMF_Creator;
import entities.Movie;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    private static Movie m1, m2;
    
    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MovieFacade.getFacadeExample(emf);
       EntityManager em = emf.createEntityManager();
       
       m1 = new Movie("test1", "desc1", 2000);
       m2 = new Movie("test2", "desc1", 2010);
       
       try {
            em.getTransaction().begin();
            em.persist(m1);
            em.persist(m2);
            em.getTransaction().commit();
       } finally {
           em.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
    }

  
    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testAddMovie() {
        long before = facade.getMovieCount();
        facade.addMovie("test3", "desc3", 2020);
        long after = facade.getMovieCount();
        assertTrue (after > before);
        
    }

    @Test
    public void testGetMovieByID() {
        Movie test = facade.getMovieByID(m1.getId());
        assertEquals(test.getTitle(), m1.getTitle());
    }
    
    @Test
    public void testGetAllMovies() {
        int minimumSize = 2;
        assertTrue(facade.getAllMovies().size() >= minimumSize);
    }
    
//    @Test
//    public void testGetMoviesByReleaseYear() {
//        int expectedSize = 1;
//        assertEquals(expectedSize, facade.getMoviesByReleaseYear(2000).size());
//    }
    
    @Test
    public void testGetMovieCount() {
        // Greater than or equal to 2 because I don't know the order the tests are run in.
        int minimumSize = 2;
        assertTrue(facade.getMovieCount() >= minimumSize);
    }
    

}
