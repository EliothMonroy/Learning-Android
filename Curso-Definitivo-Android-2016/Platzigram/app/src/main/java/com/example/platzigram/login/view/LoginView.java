package com.example.platzigram.login.view;

import android.view.View;

public interface LoginView {
    void goCreateAccount();
    void goHome();
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();
    void loginError(String error);
    boolean validateInputs();
}
