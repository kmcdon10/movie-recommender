package moviesuggestions.services;

import moviesuggestions.App;
import moviesuggestions.utils.RandomNumberGenerator;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = App.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks private UserService underTest;
    @Mock private RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);

    @Test
    public void testGetAgeReturnsValueGivenByRandomNumberGenerator() {
        doReturn(12).when(randomNumberGenerator).getRandomNumberInRange(10, 20);
        assertEquals(12, underTest.getAge());
    }

    @Test
    public void getGetAge() {
        doReturn(20).when(randomNumberGenerator).getRandomNumberInRange(10, 20);
        assertEquals(20, underTest.getAge());
    }
}
