package moviesuggestions.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import moviesuggestions.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired private RandomNumberGenerator randomNumberGenerator;

    @HystrixCommand()
    public int getAge() {
        return randomNumberGenerator.getRandomNumberInRange(10, 20);
    }

}