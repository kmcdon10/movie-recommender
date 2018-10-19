package moviesuggestions.services;

import moviesuggestions.App;
import moviesuggestions.utils.RandomNumberGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.stubbing.answers.AnswersWithDelay;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = App.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class MovieServiceTest {

    @InjectMocks private MovieService underTest;
    @Mock private UserService userService = mock(UserService.class);
    @Mock private RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);

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
        doReturn(19).when(userService).getAge();
        List<String> actualMovies = underTest.getRecommendedMovies();
        assertEquals(adultMovies, actualMovies);
    }

    @Test
    public void testGetRecommendedMoviesForTeens() {
        doReturn(15).when(userService).getAge();
        List<String> actual = underTest.getRecommendedMovies();
        assertEquals(teenMovies, actual);
    }

    @Test
    public void testGetRecommendedMoviesForKids() {
        doReturn(12).when(userService).getAge();
        List<String> actual = underTest.getRecommendedMovies();
        assertEquals(kidsMovies, actual);
    }

    @Test
    public void testGetRecommendedMoviesWhenUserServiceThrowsException() {
        when(randomNumberGenerator.getRandomNumberInRange(anyInt(), anyInt())).thenThrow(new IllegalArgumentException("illegal argument"));

        List<String> actualMovies = underTest.getRecommendedMovies();
        assertEquals(kidsMovies, actualMovies);
    }

    @Test
    public void testGetRecommendedMoviesWhenUserServiceTimesOut() {
        when(randomNumberGenerator.getRandomNumberInRange(anyInt(), anyInt())).thenAnswer(new AnswersWithDelay(150, new Returns(20)));

        List<String> actualMovies = underTest.getRecommendedMovies();
        assertEquals(kidsMovies, actualMovies);
    }

    @Test
    public void testGetMoviesFallback() {
        List<String> actualMovies = underTest.getKidsMovies();
        assertEquals(kidsMovies, actualMovies);
    }
}
