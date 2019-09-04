package com.example.platzigram.login.interactor;

import com.example.platzigram.login.presenter.LoginPresenter;
import com.example.platzigram.login.repository.LoginRepository;
import com.example.platzigram.login.repository.LoginRepositoryImpl;

public class LoginInteractorImpl implements LoginInteractor {
    
    private LoginPresenter loginPresenter;
    private LoginRepository loginRepository;
    
    public LoginInteractorImpl(LoginPresenter loginPresenter) {
        this.loginPresenter=loginPresenter;
        loginRepository=new LoginRepositoryImpl(this);
    }
    
    @Override
    public void signIn(String username, String password) {
        loginRepository.signIn(username,password);
    }
    
    @Override
    public void loginSuccess() {
        loginPresenter.loginSuccess();
    }
    
    @Override
    public void loginFailed(String error) {
        loginPresenter.loginFailed(error);
    }
}
