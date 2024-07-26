package utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static void main(String[] args) {
        System.out.println(getRandomString(10));
        System.out.println(getRandomEmail());
        System.out.println(getRandomAddress());
        System.out.println(getRandomInt(111, 99999999));
        System.out.println(getRandomPhone());
        System.out.println(getRandomGender());
        System.out.println(getRandomMonth());
    }

    public static String getRandomString(int len) {
//        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@qa.guru";
    }

    public static String getRandomAddress() {
        return getRandomString(10) + " " + getRandomString(10) + " " + getRandomString(10);
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // +3(263)253-66-12
    public static String getRandomPhone() {
        return String.format("+%s(%s)%s-%s-%s", getRandomInt(1, 9), getRandomInt(111, 999),
                getRandomInt(111, 999), getRandomInt(11, 99), getRandomInt(11, 99));
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};

        return getRandomItemFromArray(genders);
    }

    public static String getRandomItemFromArray(String[] array) {
        int index = getRandomInt(0, array.length - 1);

        return array[index];
    }

    public static String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        return getRandomItemFromArray(months);
    }

    public static String getRandomHobby() {
        String[] hobby = {"Sports", "Reading", "Music"};

        return getRandomItemFromArray(hobby);
    }

    public static String getRandomSubject() {
        String[] subject = {"Maths", "Accounting", "Arts", "Social Studies",
                "Biology", "Physics", "Chemistry", "Computer Science", "Commerce",
                "Economics", "Civics", "Hindi", "English", "History"};

        return getRandomItemFromArray(subject);
    }

    public static String getRandomState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};

        return getRandomItemFromArray(state);
    }

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> getRandomItemFromArray(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh" -> getRandomItemFromArray(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana" -> getRandomItemFromArray(new String[]{"Karnal", "Panipat"});
            case "Rajasthan" -> getRandomItemFromArray(new String[]{"Jaipur", "Jaiselmer"});
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }
}
