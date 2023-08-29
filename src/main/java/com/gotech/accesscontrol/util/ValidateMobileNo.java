package com.gotech.accesscontrol.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidateMobileNo {
    private static final Pattern pattern = Pattern.compile("^(\\+91[\\-\\s]?)?[0]?(91)?[789]\\d{9}$");

    public boolean isValidMobileNo(String contactNo) {
        Matcher matcher = pattern.matcher(contactNo);
        return matcher.matches();
    }
}