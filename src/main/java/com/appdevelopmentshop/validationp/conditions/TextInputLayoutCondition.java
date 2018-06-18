package com.appdevelopmentshop.validationp.conditions;

import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
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
public class TextInputLayoutCondition extends BaseCondition<EditText, TextInputLayout> {

    private TextWatcher validationNotifTextWatcher;

    public TextInputLayoutCondition(TextInputLayout viewView, Rule... rules){
        super(viewView.getEditText(), viewView, rules);
    }

    @Override
    protected void onError(TextInputLayout errorView, String msg) {
        errorView.setError(msg);
    }

    @Override
    protected void resetError(TextInputLayout errorView) {
        errorView.setError(null);
    }

    @Override
    protected void onSetNotifier(final ValidationNotifier notifier) {
        validationNotifTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                notifier.notifyNeedValidate();
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
