package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    static Faker faker = new Faker(new Locale("en"));
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().safeEmailAddress();
    public String streetAddress = faker.address().streetAddress();
    public String gender = faker.options().option("Male", "Female", "Other");
    public String phoneNumber = faker.number().digits(10);
    public String day = String.valueOf(faker.number().numberBetween(1, 28));
    public String month = faker.options().option("January", "February", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December");
    public String year = String.valueOf(faker.number().numberBetween(1900, 2023));
    public String subject = faker.options().option("Maths", "Accounting", "Arts", "Social Studies",
        "Biology", "Physics", "Chemistry", "Computer Science", "Commerce",
        "Economics", "Civics", "Hindi", "English", "History");
    public String hobby = faker.options().option("Sports", "Reading", "Music");
    public String picturePath = "testImage.jpg";
    public String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    public String city = getCity(state);

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrit");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }
}
