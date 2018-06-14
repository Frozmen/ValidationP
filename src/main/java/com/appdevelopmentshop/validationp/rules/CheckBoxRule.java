package com.appdevelopmentshop.validationp.rules;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class CheckBoxRule implements Rule<CheckBox> {

    private boolean checkedValidValue;
    private ValidationErrorListener errorListener;

    public CheckBoxRule(boolean checkedValidValue, ValidationErrorListener errorListener){
        this.checkedValidValue = checkedValidValue;
        this.errorListener = errorListener;
    }

    @Override
    public boolean isViewValid(CheckBox view) {
        boolean isValid = view.isChecked() == checkedValidValue;
        if(!isValid){
            errorListener.onError(view);
        }
        return isValid;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }

    public interface ValidationErrorListener extends Serializable {
        void onError(CheckBox checkBox);
    }
}
