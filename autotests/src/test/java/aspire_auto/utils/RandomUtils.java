package aspire_auto.utils;

import com.github.javafaker.Faker;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomUtils {

    /**
     * You can use com.github.javafaker.Faker
     * to generate many Random Data types such as fullName, firstName, lastName, Address...
     */

    public static String getRandomName() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMhhmmss");
        String strDate = formatter.format(new Date());
        return strDate.concat(getRandomNumber());
    }

    /**
     * Generate Random number with 8 digits length.
     */
    public static String getRandomNumber() {
        Random random = new Random();
        return String.valueOf(10000000 + random.nextInt(90000000));
    }

    public static String getRandomFullName() {
        Faker faker = new Faker();
        return faker.name().fullName();
    }

    public static String getRandomEmail() {
        return "aspire_auto" + RandomUtils.getRandomName() + "@yopmail.com";
    }
}
