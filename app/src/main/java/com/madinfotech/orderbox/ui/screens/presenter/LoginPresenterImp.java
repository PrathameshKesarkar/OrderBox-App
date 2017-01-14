package com.madinfotech.orderbox.ui.screens.presenter;

import android.content.SharedPreferences;

import com.madinfotech.orderbox.network.data.ResponseUser;
import com.madinfotech.orderbox.network.http.OrderBoxListener;
import com.madinfotech.orderbox.network.http.OrderBoxService;
import com.madinfotech.orderbox.ui.screens.viewInterface.LoginView;

import java.util.regex.Pattern;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public class LoginPresenterImp implements LoginPresenter {

    Pattern pattern;
    LoginView loginView;
    OrderBoxService orderBoxService;
    SharedPreferences preferences;
    public LoginPresenterImp(Pattern pattern, OrderBoxService orderBoxService, SharedPreferences preferences) {
        this.pattern = pattern;
        this.orderBoxService = orderBoxService;
        this.preferences=preferences;
    }

    @Override
    public void validateEmail() {
        String userEmail = loginView.getEmail();
        if (userEmail.isEmpty())
            loginView.showEmptyEmail();
        else if (isEmailEmpty(userEmail))
            loginView.showInvalidEmail();
        else
            loginView.removeEmailWrapperErrorMsg();

    }

    private boolean isEmailEmpty(String email){
        return !pattern.matcher(email).matches();
    }

    @Override
    public void setView(LoginView view) {
        loginView = view;
    }

    @Override
    public void unBindView() {
        loginView=null;
    }

    @Override
    public void checkInputNotEmpty() {
        final String userEmail = loginView.getEmail();
        String userPassword = loginView.getPassword();

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            if (userEmail.isEmpty())
                loginView.showEmptyEmail();
            if (userPassword.isEmpty()) {
                loginView.showEmptyPassword();
            }
        }else  if(isEmailEmpty(userEmail)){
            loginView.showInvalidEmail();
        }
        else{
            loginView.removeEmailWrapperErrorMsg();
            loginView.removePasswordWrapperErrorMsg();
            orderBoxService.performLogin(userEmail, userPassword, new OrderBoxListener() {
                @Override
                public void onLoginSuccessful(ResponseUser user) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Header",user.getHeader());
                    editor.apply();
                   //loginView.showMessage("Login Successful");
                    loginView.nextActivity();
                }

                @Override
                public void onLoginFailed(String errorMsg) {
                    loginView.showMessage(errorMsg);

                }
            });
        }
    }
}
