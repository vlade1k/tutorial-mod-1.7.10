package ru.vlade1k.tutorial.common.help.lock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PinCodeLock{

  private String password = "";
  private String enteredPassword = "";
  private boolean passwordIsSetted = false;



  public void decrEnteredPassword() {
    if (!enteredPassword.isEmpty()) enteredPassword = enteredPassword.substring(0, enteredPassword.length() - 1);
  }
  public void getEnteredNum(int value) {
    if (enteredPassword.length() < 5) enteredPassword += Integer.toString(value);
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

  public boolean getPasswordStatus() {
    return passwordIsSetted;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPasswordStatus(boolean status) {
    this.passwordIsSetted = true;
  }

  public String getPassword(){
    return password;
  }

}
