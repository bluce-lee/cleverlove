package com.cleverlove.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final String LOG0_REGEX = "log0\\(\\d*\\);";

    public static int log0Num(CharSequence input) {
        Pattern pattern = Pattern.compile(LOG0_REGEX);
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
