package moviesuggestions.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import moviesuggestions.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired private UserService userService;
//    @Autowired private RandomNumberGenerator randomNumberGenerator;

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

//    @HystrixCommand(fallbackMethod = "getKidsMovies")
    public List<String> getRecommendedMovies() {
        return getMovies();
    }

    @HystrixCommand(fallbackMethod = "getKidsMovies")
    private List<String> getMovies() {
        String resp = userService.getAge();
        int age;
        try {
            age = Integer.parseInt(resp);
        } catch (NumberFormatException e) {
            age = 1;
        }
        System.out.println("age: " + age);
        if (age <= 13) {
            return kidsList;
        } else if (age <= 17) {
            return teenList;
        } else {
            return adultList;
        }
    }

    public List<String> getKidsMovies() {
        System.out.println("using fallback method");
        return kidsList;
    }

}
