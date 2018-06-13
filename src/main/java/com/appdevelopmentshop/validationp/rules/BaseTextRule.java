package com.appdevelopmentshop.validationp.rules;

import android.widget.TextView;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public abstract class BaseTextRule implements Rule<TextView> {

    private final String errorMsg;

    public BaseTextRule(String errorMsg){
        this.errorMsg = errorMsg;
    }

    @Override
    public boolean isViewValid(TextView editText) {
        return isTextValid(editText.getText().toString());
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }


    public abstract boolean isTextValid(String text);
}
