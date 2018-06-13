package com.appdevelopmentshop.validationp.conditions;

import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.appdevelopmentshop.validationp.BaseCondition;
import com.appdevelopmentshop.validationp.rules.Rule;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class EditTextCondition extends BaseCondition<EditText, EditText> {

    private TextWatcher validationNotifTextWatcher;

    public EditTextCondition(EditText viewView, Rule... rules){
        super(viewView, viewView, rules);
    }

    public EditTextCondition(@IdRes int editTextId,
                             Rule... rules){
        super(editTextId, editTextId, rules);
    }

    @Override
    protected void onError(EditText errorView, String msg) {
        errorView.setError(msg);
    }

    @Override
    protected void resetError(EditText errorView) {
        errorView.setError(null);
    }

    @Override
    protected void onSetNotifier() {
        super.onSetNotifier();
        validationNotifTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notifyNeedValidate();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        getTargetView().addTextChangedListener(validationNotifTextWatcher);
    }

    @Override
    protected void onRemoveNotifier() {
        getTargetView().removeTextChangedListener(validationNotifTextWatcher);
        validationNotifTextWatcher = null;
    }
}
