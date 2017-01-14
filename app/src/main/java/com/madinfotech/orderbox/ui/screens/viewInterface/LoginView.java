package com.madinfotech.orderbox.ui.screens.viewInterface;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public interface LoginView {
    void showInvalidEmail();
    void showEmptyPassword();
    String getEmail();
    void showEmptyEmail();
    String getPassword();
    void removeEmailWrapperErrorMsg();
    void removePasswordWrapperErrorMsg();
    void showMessage(String msg);
    void nextActivity();
}
