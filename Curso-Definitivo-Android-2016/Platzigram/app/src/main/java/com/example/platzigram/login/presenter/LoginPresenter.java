package com.example.platzigram.login.presenter;

import android.app.Activity;

public interface LoginPresenter {
    void signIn(String username, String password); //interactor
    void signInFacebook(String token);
    void loginSuccess();
    void loginFailed(String error);
    void onStart();
    void onStop();
    Activity getActivity();
}
