package com.appdevelopmentshop.validationp.conditions;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.appdevelopmentshop.validationp.BaseCondition;
import com.appdevelopmentshop.validationp.rules.Rule;

/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class CheckBoxCondition extends BaseCondition<CheckBox, View> {

    public CheckBoxCondition(CheckBox viewView, Rule... checkBoxRule) {
        super(viewView, null, checkBoxRule);
    }

    @Override
    protected void onError(View errorView, String msg) {

    }

    @Override
    protected void onSetNotifier(final ValidationNotifier notifier) {
        super.onSetNotifier(notifier);
        getTargetView().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                notifier.notifyNeedValidate();
            }
        });
    }

    @Override
    protected void onRemoveNotifier() {
        super.onRemoveNotifier();
        getTargetView().setOnCheckedChangeListener(null);
    }
}
