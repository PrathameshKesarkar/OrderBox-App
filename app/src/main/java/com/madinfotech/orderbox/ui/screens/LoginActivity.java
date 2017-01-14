package com.madinfotech.orderbox.ui.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.madinfotech.orderbox.OrderBoxApplication;
import com.madinfotech.orderbox.R;
import com.madinfotech.orderbox.ui.screens.presenter.LoginPresenterImp;
import com.madinfotech.orderbox.ui.screens.viewInterface.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by PrathamK on 7/9/2016.
 */
public class LoginActivity extends BaseActivity implements LoginView {


    @BindView(R.id.login_email_wrapper)
    TextInputLayout usernameWrapper;

    @BindView(R.id.login_password_wrapper)
    TextInputLayout passwordWrapper;

    @BindView(R.id.login_email_editText)
    EditText emailEditText;

    @BindView(R.id.login_password_editText)
    EditText passwordEditText;

    @BindView(R.id.login_root_view)
    View view;

    @BindView(R.id.login_button)
    Button loginButton;

    @Inject
    LoginPresenterImp presenter;

    @Inject
    InputMethodManager inputManager;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        unbinder = ButterKnife.bind(this);
        presenter.setView(this);
        emailEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    presenter.validateEmail();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkInputNotEmpty();
            }
        });

    }

    @Override
    public void setupActivityComponent() {
        ((OrderBoxApplication) getApplication()).createLoginComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((OrderBoxApplication) getApplication()).releaseLoginComponent();
        unbinder.unbind();
        presenter.unBindView();
    }

    @Override
    public void showInvalidEmail() {
        usernameWrapper.setError(getString(R.string.invalid_email));
        enableEmailError();
    }

    @Override
    public void showEmptyPassword() {
        passwordWrapper.setError(getString(R.string.empty_password));
        enablePasswordError();
    }

    @Override
    public String getEmail() {
        return emailEditText.getText().toString();
    }

    @Override
    public void showEmptyEmail() {
        usernameWrapper.setError(getString(R.string.empty_email));
        enableEmailError();

    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();

    }

    @Override
    public void removeEmailWrapperErrorMsg() {
        usernameWrapper.setErrorEnabled(false);
    }

    @Override
    public void removePasswordWrapperErrorMsg() {
        passwordWrapper.setErrorEnabled(false);
    }

    public void enableEmailError() {
        usernameWrapper.setErrorEnabled(true);
    }

    public void enablePasswordError() {
        passwordWrapper.setErrorEnabled(true);
    }

    @Override
    public void showMessage(String message) {
        final Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                .show();
    }

    @Override
    public void nextActivity() {
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }

}
