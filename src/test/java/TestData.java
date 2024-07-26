import com.github.javafaker.Faker;
import utils.RandomUtils;

import java.util.Locale;

public class TestData {

    static Faker faker = new Faker(Locale.of("en"));
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().safeEmailAddress();
    public String streetAddress = faker.address().streetAddress();
    public String gender = RandomUtils.getRandomGender();
    public String phoneNumber = faker.number().digits(10);
    public String day = String.valueOf(faker.number().numberBetween(1, 28));
    public String month = RandomUtils.getRandomMonth();
    public String year = String.valueOf(faker.number().numberBetween(1900, 2023));
    public String subject = RandomUtils.getRandomSubject();
    public String hobby = RandomUtils.getRandomHobby();
    public String picturePath = "testImage.jpg";
    public String state = RandomUtils.getRandomState();
    public String city = RandomUtils.getCity(state);
}
