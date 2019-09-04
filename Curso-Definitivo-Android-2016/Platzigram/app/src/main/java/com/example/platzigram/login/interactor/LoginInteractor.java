package com.example.platzigram.login.interactor;

import com.example.platzigram.login.presenter.LoginPresenter;

public interface LoginInteractor {
    void signIn(String username, String password);
    void loginSuccess();
    void loginFailed(String error);
    void onStart();
    void onStop();
    LoginPresenter getPresenter();
}
