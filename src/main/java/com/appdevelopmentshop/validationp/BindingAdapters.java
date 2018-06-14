package com.appdevelopmentshop.validationp;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.appdevelopmentshop.validationp.rules.Rule;


/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class BindingAdapters {

    @BindingAdapter(value = {"validator", "rules", "conditions"}, requireAll = false)
    public static  void bindValidator(final View view,
                                      Validator validator, Rule[] rules, BaseCondition conditions) {
        if(validator==null) return;

        if(view instanceof EditText){
            validator.addEditTextCondition((EditText) view, rules);
        } else  if(view instanceof TextInputLayout){
            validator.addTextInputLayoutCondition((TextInputLayout) view, rules);
        } else {
            validator.addCondition(conditions);
        }
    }
}