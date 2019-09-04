package com.example.platzigram.login.repository;

import com.example.platzigram.login.interactor.LoginInteractor;

public class LoginRepositoryImpl implements LoginRepository {
    
    private LoginInteractor loginInteractor;
    
    public LoginRepositoryImpl(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }
    
    @Override
    public void signIn(String username, String password) {
        boolean success=true;
        if(success){
            loginInteractor.loginSuccess();
        }else{
            loginInteractor.loginFailed("Ocurrió un error");
        }
    }
}
