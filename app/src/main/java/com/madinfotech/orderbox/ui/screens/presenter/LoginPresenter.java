package com.madinfotech.orderbox.ui.screens.presenter;

import com.madinfotech.orderbox.ui.screens.viewInterface.LoginView;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public interface LoginPresenter {
    void validateEmail();
    void setView(LoginView view);
    void checkInputNotEmpty();
    void unBindView();
}
