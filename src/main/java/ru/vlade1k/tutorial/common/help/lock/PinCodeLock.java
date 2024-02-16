package ru.vlade1k.tutorial.common.help.lock;

public class PinCodeLock {

  private final String password = "25565";
  private String enteredPassword = "";


  public void decrEnteredPassword() {
    if (!enteredPassword.isEmpty()) enteredPassword = enteredPassword.substring(0, enteredPassword.length() - 1);
  }
  public void getEnteredNum(int value) {
    enteredPassword += Integer.toString(value);
  }

  public boolean isCorrectPassword() {
    return enteredPassword.equals(password);
  }

  public void cleanEnteredPassword() {
    enteredPassword = "";
  }

  public String getEnteredPassword() {
    return enteredPassword;
  }
}
