package com.appdevelopmentshop.validationp.conditions;

/**
 * Created by Artem Sisetskyi on 6/14/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */

/**
 * Need call notifyNeedValidate() when you want trigger validation
 */
public interface ValidationNotifier {
    void notifyNeedValidate();
}
