package com.appdevelopmentshop.validationp.rules;

import android.text.TextUtils;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class RequiredRule extends BaseTextRule {

    public RequiredRule(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public boolean isTextValid(String text) {
        return !TextUtils.isEmpty(text);
    }
}
