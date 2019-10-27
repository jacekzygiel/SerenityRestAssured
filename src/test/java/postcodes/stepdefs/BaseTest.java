package postcodes.stepdefs;

import cucumber.api.java.Before;

import java.io.IOException;
import java.util.*;

public class BaseTest {

    public static String BASE_URI = "http://api.postcodes.io/";

    @Before
    private static void setPropertiesFromFile() {
        Properties prop = new Properties();
        try {
            prop.load(BaseTest.class.getClassLoader().getResourceAsStream("config.properties"));
            BASE_URI = prop.getProperty("host.url");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final List<String> INVALID_POST_CODES_LIST =
            new ArrayList<>(Arrays.asList("123 ABC", "O2L5 51A", "XXX 222"));

    public static final List<String> VALID_POST_CODES_LIST =
            new ArrayList<>(Arrays.asList("B92 7BD", "M32 0JG", "NE30 1DP"));

    public String getRandomValidPostCode() {
        return VALID_POST_CODES_LIST.get(new Random().nextInt(VALID_POST_CODES_LIST.size()));
    }

    public String getRandomInvalidPostCode() {
        return INVALID_POST_CODES_LIST.get(new Random().nextInt(INVALID_POST_CODES_LIST.size()));
    }
}
