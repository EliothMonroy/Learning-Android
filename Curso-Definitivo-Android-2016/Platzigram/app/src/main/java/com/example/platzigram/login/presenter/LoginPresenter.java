package com.example.platzigram.login.presenter;

public interface LoginPresenter {
    void signIn(String username, String password); //interactor
    void loginSuccess();
    void loginFailed(String error);
}
