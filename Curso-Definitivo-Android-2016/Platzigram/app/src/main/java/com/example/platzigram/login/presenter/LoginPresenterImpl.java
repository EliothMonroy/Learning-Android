package com.example.platzigram.login.presenter;

import com.example.platzigram.login.interactor.LoginInteractor;
import com.example.platzigram.login.interactor.LoginInteractorImpl;
import com.example.platzigram.login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor=new LoginInteractorImpl(this);
    }

    @Override
    public void signIn(String username, String password) {
        loginView.disableInputs();
        loginView.showProgressBar();
        loginInteractor.signIn(username,password);
    }

    @Override
    public void loginSuccess() {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.goHome();
    }

    @Override
    public void loginFailed(String error) {
        loginView.enableInputs();
        loginView.hideProgressBar();
        loginView.loginError(error);
    }
}
