package com.appdevelopmentshop.validationp;

import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;

import com.appdevelopmentshop.validationp.conditions.ValidationNotifier;
import com.appdevelopmentshop.validationp.rules.Rule;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public abstract class BaseCondition<T extends View, E extends View>
        implements ValidationNotifier{

    public static final String TAG = BaseCondition.class.getSimpleName();

    private           int viewId;
    private transient T   targetView;
    private           int errorViewId;
    private transient E   errorView;

    private       ValidatorStrategy       validatorStrategy;
    private final List<? extends Rule<T>> rules;

    private boolean isAutoValidatable;
    private boolean isConditionValid;

    public BaseCondition(@IdRes int viewId, @IdRes int errorId, Rule<T>... rules) {
        this.viewId = viewId;
        this.errorViewId = errorId;
        this.rules = Arrays.asList(rules);
    }

    public BaseCondition(T viewView, E errorView, Rule<T>... rules) {
        this.targetView = viewView;
        this.errorView = errorView;

        this.viewId = viewView.getId();
        if(errorView != null)
            this.errorViewId = errorView.getId();

        this.rules = Arrays.asList(rules);
    }

    void setValidatorStrategy(ValidatorStrategy rootViewProvider) {
        this.validatorStrategy = rootViewProvider;
    }

    boolean validate() {
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

    public void setAutoValidatable(boolean isEnable) {
        this.isAutoValidatable = isEnable;
        if (isAutoValidatable) {
            onSetNotifier(this);
        } else {
            onRemoveNotifier();
        }
    }

    public boolean isValid() {
        return isConditionValid;
    }

    public T getTargetView(){
        if (targetView == null) {
            if (validatorStrategy.getRoot() == null)
                throw new IllegalStateException("You should call bindView() on Validator before use it");
            targetView = validatorStrategy.getRoot().findViewById(viewId);
            if(targetView == null){
                throw new IllegalStateException("Cannot find view with id " + viewId);
            }
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
