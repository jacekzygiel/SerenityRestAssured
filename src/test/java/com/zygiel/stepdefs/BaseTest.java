package com.zygiel.stepdefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BaseTest {

    public static String BASE_URI = "http://api.postcodes.io/";

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
