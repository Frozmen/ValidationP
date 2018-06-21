package com.appdevelopmentshop.validationp;

import android.util.Log;
import android.view.View;

import com.appdevelopmentshop.validationp.conditions.Condition;
import com.appdevelopmentshop.validationp.conditions.ValidationNotifier;
import com.appdevelopmentshop.validationp.rules.Rule;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public abstract class BaseCondition<T extends View, E extends View>
        implements Condition, ValidationNotifier {

    public static final String TAG = BaseCondition.class.getSimpleName();

    private transient T   targetView;

    private transient E   errorView;

    private       ConditionValidChangeListener validatorStrategy;
    private final List<? extends Rule<T>>      rules;

    private boolean isAutoValidatable;
    private boolean isConditionValid;

    public BaseCondition(T viewView, E errorView, Rule<T>... rules) {
        this.targetView = viewView;
        this.errorView = errorView;
        this.rules = Arrays.asList(rules);
    }

    @Override
    public void setValidChangeListener(ConditionValidChangeListener rootViewProvider) {
        this.validatorStrategy = rootViewProvider;
    }

    @Override
    public boolean validate() {
       boolean isValid = true;
        for (Rule rule : rules) {
            if (!rule.isViewValid(getTargetView())) {
                isValid = false;
                onError(errorView, rule.getErrorMsg());
                break;
            }
        }
        if (isValid) {
            resetError(errorView);
        }

        //need compare with prev state to notify if it changed
        if (isConditionValid && !isValid) {
            isConditionValid = false;
            validatorStrategy.onConditionValidStateChange(this);
        }
        if (!isConditionValid && isValid) {
            isConditionValid = true;
            validatorStrategy.onConditionValidStateChange(this);
        }

        return isValid;
    }

    @Override
    public void setAutoValidatable(boolean isEnable) {
        this.isAutoValidatable = isEnable;
        if (isAutoValidatable) {
            onSetNotifier(this);
        } else {
            onRemoveNotifier();
        }
    }

    @Override
    public boolean isValid() {
        return isConditionValid;
    }

    protected T getTargetView(){
        if (targetView == null) {
                throw new IllegalStateException("Validatable view is null");
        }
        return targetView;
    }

    protected abstract void onError(E errorView, String msg);

    protected void resetError(E errorView) {
        //stubb
        Log.i(TAG, "resetError: there is no overwrite resetError() for " + getClass().getSimpleName());
    }

    protected void onSetNotifier(ValidationNotifier notifier) {
        //stubb
        Log.i(TAG, "onSetNotifier: there is no overwrite onSetNotifier() for " + getClass().getSimpleName());

    }

    //TODO are we really need to un subscribe notifier from view?
    protected void onRemoveNotifier() {
        //stubb
        Log.i(TAG, "onRemoveNotifier: there is no overwrite onRemoveNotifier() for " + getClass().getSimpleName());
    }

    @Override
    public void notifyNeedValidate() {
        validate();
    }

}
