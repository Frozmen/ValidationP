package com.appdevelopmentshop.validationp.rules;

import android.view.View;

import java.io.Serializable;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public interface Rule<T extends View> extends Serializable {
     boolean isViewValid(T view);

    String getErrorMsg();
}
