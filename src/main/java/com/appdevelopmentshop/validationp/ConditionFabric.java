package com.appdevelopmentshop.validationp;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import com.appdevelopmentshop.validationp.conditions.CheckBoxCondition;
import com.appdevelopmentshop.validationp.conditions.Condition;
import com.appdevelopmentshop.validationp.conditions.EditTextCondition;
import com.appdevelopmentshop.validationp.conditions.TextInputLayoutCondition;
import com.appdevelopmentshop.validationp.rules.Rule;

/**
 * Created by Artem Sisetskyi on 6/21/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
//todo later will be full fabric with adding condition on runtime
@Deprecated
public class ConditionFabric {

    public static Condition getCondition(View view, Rule... rules) {
        if (view instanceof EditText) {
            return new EditTextCondition((EditText) view, rules);
        } else if (view instanceof TextInputLayout) {
            return new TextInputLayoutCondition((TextInputLayout) view, rules);
        } else if (view instanceof CheckBox) {
            return new CheckBoxCondition((CheckBox) view, rules);
        }
        return null;
    }
}
