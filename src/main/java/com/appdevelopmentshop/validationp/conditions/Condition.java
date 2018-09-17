package com.appdevelopmentshop.validationp.conditions;

import com.appdevelopmentshop.validationp.ConditionValidChangeListener;

/**
 * Created by Artem Sisetskyi on 6/21/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public interface Condition extends ValidationNotifier {

    void setAutoValidatable(boolean isEnable);

    void setErrorShowing(boolean isNeedShow);

    boolean validate();

    boolean isValid();

    void setValidChangeListener(ConditionValidChangeListener listener);
}
