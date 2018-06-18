package com.appdevelopmentshop.validationp;

import android.view.View;

/**
 * Created by Artem Sisetskyi on 6/7/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public interface ConditionValidChangeListener {
    void onConditionValidStateChange(BaseCondition<?, ?> condition);
}
