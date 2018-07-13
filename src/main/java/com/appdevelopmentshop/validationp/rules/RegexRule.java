package com.appdevelopmentshop.validationp.rules;

import java.util.regex.Pattern;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class RegexRule extends BaseTextRule {
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    private final Pattern pattern;

    public RegexRule(String regex, String errorMsg) {
        super(errorMsg);
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isTextValid(String text) {
        return pattern.matcher(text).matches();
    }
}
