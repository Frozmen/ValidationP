package com.appdevelopmentshop.validationp.rules;

/**
 * Created by Artem Sisetskyi on 7/10/18.
 * AppDevelopmentShop
 * sisetskyi.a@gmail.com
 */
public class LengthRule extends BaseTextRule {

  private int targetLength;

  public LengthRule(int length, String errorMsg) {
    super(errorMsg);
    targetLength = length;
  }

  @Override
  public boolean isTextValid(String text) {
    return text.length() == targetLength;
  }
}

