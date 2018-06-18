package com.appdevelopmentshop.validationp;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appdevelopmentshop.validationp.conditions.CheckBoxCondition;
import com.appdevelopmentshop.validationp.rules.CheckBoxRule;
import com.appdevelopmentshop.validationp.rules.Rule;


/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class BindingAdapters {

    public static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter(value = {"validator", "conditions", "rules"}, requireAll = false)
    public static void bindValidator(final View view,
                                     Validator validator, BaseCondition conditions, Rule... rules) {
        if (validator == null){
            Log.i(TAG, "bindValidator: validator is null");
            return;
        }

        if (view instanceof EditText) {
            validator.addEditTextCondition((EditText) view, rules);
        } else if (view instanceof TextInputLayout) {
            validator.addTextInputLayoutCondition((TextInputLayout) view, rules);
        } else if (view instanceof CheckBox) {
            validator.addCheckBoxCondition((CheckBox) view, rules);
        } else {
            if (conditions != null) {
                validator.addCondition(conditions);
            } else {
                throw new IllegalStateException("You should provide a custom Condition for " + view.getClass());
            }
        }
    }
}
