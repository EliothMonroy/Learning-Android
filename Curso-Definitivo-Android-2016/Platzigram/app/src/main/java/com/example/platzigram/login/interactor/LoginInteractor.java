package com.example.platzigram.login.interactor;

public interface LoginInteractor {
    void signIn(String username, String password);
    void loginSuccess();
    void loginFailed(String error);
}
