package com.appdevelopmentshop.validationp;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import com.appdevelopmentshop.validationp.conditions.EditTextCondition;
import com.appdevelopmentshop.validationp.conditions.TextInputLayoutCondition;
import com.appdevelopmentshop.validationp.rules.Rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem Sisetskyi on 6/5/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class Validator implements Serializable, ValidatorStrategy {

    private final List<BaseCondition> conditions = new ArrayList<>();
    private transient View rootView;

    private boolean              isValidatorValid;
    private OnValidStateListener validStateListener;

    private boolean isAutoValidatable;

    public Validator(boolean isAutoValidatableEnable, OnValidStateListener validStateListener){
        this.isAutoValidatable = isAutoValidatableEnable;
        this.validStateListener = validStateListener;
    }

    private void initCond(BaseCondition cond) {
        cond.setValidatorStrategy(this);
        if(isAutoValidatable){
            cond.setAutoValidatable(true);
        }
        conditions.add(cond);
    }

    public <T extends BaseCondition> void addCondition(T cond) {
        initCond(cond);
    }

    public void setAutoValidatable(boolean enable) {
        isAutoValidatable = enable;
        for (BaseCondition cond : conditions) {
            cond.setAutoValidatable(enable);
        }
    }

    public void addTextInputLayoutCondition(TextInputLayout textInputLayout,
                                            Rule... rules) {
        initCond(new TextInputLayoutCondition(textInputLayout, rules));
    }

    public void addTextInputLayoutCondition(@IdRes int editTextId, @IdRes int textInputLayoutId,
                                            Rule... rules) {
        initCond(new TextInputLayoutCondition(editTextId, textInputLayoutId, rules));
    }

    public void addEditTextCondition(EditText editText,
                                     Rule... rules) {
        initCond(new EditTextCondition(editText, rules));
    }

    public void addEditTextCondition(@IdRes int editTextId,
                                     Rule... rules) {
        initCond(new EditTextCondition(editTextId, rules));
    }

    public void bindView(View rootView) {
        this.rootView = rootView;
    }

    public void bindView(Activity activity) {
        this.rootView = activity.getWindow().getDecorView();
    }

    public void setOnValidChangeListener(OnValidStateListener onValidChangeListener){
        this.validStateListener = onValidChangeListener;
    }

    public boolean isValid(){
        return isValidatorValid;
    }

    /**
     * Star all conditions validation
     *
     * @return true if all conditions is valid
     */
    public boolean validate() {
        boolean isValid = true;
        for (BaseCondition cond : conditions) {
            if (!cond.validate() && isValid) {
                isValid = false;
            }
        }
        if (validStateListener !=null && isValidatorValid != isValid) {
            validStateListener.onValidChange(isValid);
        }
        this.isValidatorValid = isValid;
        return isValid;
    }

    private boolean isAllConditionValidNow(){
        for (BaseCondition cond : conditions) {
            if (!cond.isValid()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public View getRoot() {
        return rootView;
    }

    @Override
    public void onConditionValidStateChange(BaseCondition<?, ?> condition) {
        if(isValidatorValid && !condition.isValid()){
            isValidatorValid = false;
            if(validStateListener!=null){
                validStateListener.onValidChange(false);
            }
        }

        if(condition.isValid()){
            //need check all conditions
            if(isAllConditionValidNow()){
                isValidatorValid = true;
                if(validStateListener!=null){
                    validStateListener.onValidChange(true);
                }
            }
        }
    }

    public interface OnValidStateListener extends Serializable {
        void onValidChange(boolean newState);
    }
}
