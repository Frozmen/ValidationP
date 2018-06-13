package com.appdevelopmentshop.validationp;

import android.view.View;

/**
 * Created by Artem Sisetskyi on 6/7/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public interface ValidatorStrategy {
    View getRoot();
    void onConditionValidStateChange(BaseCondition<?, ?> condition);
}
