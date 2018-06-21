package com.appdevelopmentshop.validationp;

import android.databinding.BindingAdapter;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.appdevelopmentshop.validationp.conditions.CheckBoxCondition;
import com.appdevelopmentshop.validationp.conditions.Condition;
import com.appdevelopmentshop.validationp.rules.CheckBoxRule;
import com.appdevelopmentshop.validationp.rules.Rule;


/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class BindingAdapters {

    public static final String TAG = BindingAdapters.class.getSimpleName();

    @BindingAdapter(value = {"validator", "rules"}, requireAll = false)
    public static void _bindValidatorRules(final View view,
                                     Validator validator,  Rule... rules) {
        if (validator == null) {
            Log.i(TAG, "_bindValidatorRules: validator is null");
            return;
        }

        Condition cond = ConditionFabric.getCondition(view, rules);
        if (cond == null) {
            Log.i(TAG, "_bindValidatorRules: there is no default condition for view " + view.getClass());
            //todo remove in prod
            throw new IllegalStateException("_bindValidatorRules: there is no condition for view " + view.getClass());
        }
        validator.addCondition(cond);
    }

    @BindingAdapter(value = {"validator", "conditions"}, requireAll = false)
    public static void _bindValidatorCondition(final View view,
                                     Validator validator, Condition conditions) {
        if (validator == null || conditions == null) {
            Log.i(TAG, "_bindValidatorCondition: validator or condition is null");
            return;
        }
        validator.addCondition(conditions);
    }
}
