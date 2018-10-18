package moviesuggestions.services;

import moviesuggestions.App;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = App.class)
@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @InjectMocks private MovieService underTest;
    @Mock private UserService userService = mock(UserService.class);
//    @Autowired private RandomNumberGenerator randomNumberGenerator;

    private final static ArrayList<String> kidsMovies = new ArrayList<String>() {{
        add("Shrek");
        add("Coco");
        add("The Incredibles");
    }};
    private final static ArrayList<String> teenMovies = new ArrayList<String>() {{
        add("The Avengers");
        add("The Dark Knight");
        add("Inception");
    }};
    private final static ArrayList<String> adultMovies = new ArrayList<String>() {{
        add("The Godfather");
        add("Deadpool");
        add("Saving Private Ryan");
    }};


    @Test
    public void testGetRecommendedMovies() {
        doReturn("19").when(userService).getAge();
        List<String> movies = underTest.getRecommendedMovies();
        assertEquals(adultMovies.get(0), movies.get(0));
    }

    @Test
    public void testGetRecommendedMoviesForTeens() {
        doReturn("15").when(userService).getAge();
        List<String> movies = underTest.getRecommendedMovies();
        assertEquals(teenMovies.get(0), movies.get(0));
    }

    @Test
    public void testGetRecommendedMoviesForKids() {
        doReturn("12").when(userService).getAge();
        List<String> movies = underTest.getRecommendedMovies();
        assertEquals(kidsMovies.get(0), movies.get(0));
    }

    @Ignore
    @Test
    public void testGetRecommendedMoviesWhenUserServiceThrowsException() {
//        when(userService.getAge()).thenThrow(new RuntimeException());
//        doThrow(new ArithmeticException()).when(userService).getAge();

        List<String> movies = underTest.getRecommendedMovies();
        assertEquals(kidsMovies.get(0), movies.get(0));
    }
}
