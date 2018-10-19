package moviesuggestions.utils;

import moviesuggestions.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = App.class)
@RunWith(MockitoJUnitRunner.class)
public class RandomNumberGeneratorTest {

    @InjectMocks private RandomNumberGenerator underTest;

    @Test
    public void testRandomNumberGenerator() {
        int actual = underTest.getRandomNumberInRange(0, 1);
        assertTrue(actual == 0 || actual == 1);

        int actual2 = underTest.getRandomNumberInRange(5, 10);
        assertTrue(actual2 >= 5);
        assertTrue(actual2 <= 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomNumberGenerator_throwsErrorWhenMinGreaterThanMax() {
        underTest.getRandomNumberInRange(1, 0);
    }

}
