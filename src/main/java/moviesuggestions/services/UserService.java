package moviesuggestions.services;

import moviesuggestions.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired private RandomNumberGenerator randomNumberGenerator;

    public int getAge() {
        int slowDown = randomNumberGenerator.getRandomNumberInRange(0, 1);
        int age = randomNumberGenerator.getRandomNumberInRange(10, 20);
        try {
            if (slowDown == 0) {
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return age;
    }

}