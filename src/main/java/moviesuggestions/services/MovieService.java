package moviesuggestions.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired private UserService userService;

    private final static ArrayList<String> kidsList = new ArrayList<String>() {{
        add("Shrek");
        add("Coco");
        add("The Incredibles");
    }};
    private final static ArrayList<String> teenList = new ArrayList<String>() {{
        add("The Avengers");
        add("The Dark Knight");
        add("Inception");
    }};
    private final static ArrayList<String> adultList = new ArrayList<String>() {{
        add("The Godfather");
        add("Deadpool");
        add("Saving Private Ryan");
    }};

    @HystrixCommand(fallbackMethod = "getKidsMovies",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            })
    public List<String> getRecommendedMovies() {
        int age = userService.getAge();
        if (age <= 13) {
            return kidsList;
        } else if (age <= 17) {
            return teenList;
        } else {
            return adultList;
        }
    }

    public List<String> getKidsMovies() {
        return kidsList;
    }

}
