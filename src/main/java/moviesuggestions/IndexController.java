package moviesuggestions;

import moviesuggestions.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/")
public class IndexController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<String> index() {
        return movieService.getRecommendedMovies();
    }

}