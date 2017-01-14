package com.madinfotech.orderbox.ui.screens.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;

import com.madinfotech.orderbox.network.http.OrderBoxService;
import com.madinfotech.orderbox.network.http.OrderBoxServiceImp;
import com.madinfotech.orderbox.ui.screens.presenter.LoginPresenterImp;
import com.madinfotech.orderbox.ui.util.Constants;

import java.util.regex.Pattern;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * Created by PrathamK on 7/9/2016.
 */
@Module
public class LoginActivityModule {

    @Provides
    @Named(Constants.Injection.Named.EMAIL_PATTERN)
    public String provideEmailValidation() {
        return "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    }

    @Provides
    public Pattern providePattern(@Named(Constants.Injection.Named.EMAIL_PATTERN) String email_pattern) {
        return Pattern.compile(email_pattern);
    }

    @Provides
    public InputMethodManager provideInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    @Provides
    public OrderBoxService provideOrderBoxService(RestAdapter restAdapter){
        return new OrderBoxServiceImp(restAdapter);
    }

    @Provides
    public LoginPresenterImp provideLoginPresenter(Pattern pattern, OrderBoxService service, SharedPreferences preferences){
        return new LoginPresenterImp(pattern,service,preferences);
    }


}
