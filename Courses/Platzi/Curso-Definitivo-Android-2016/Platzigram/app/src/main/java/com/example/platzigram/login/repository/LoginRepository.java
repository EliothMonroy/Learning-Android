package com.example.platzigram.login.repository;

public interface LoginRepository {
    void signIn(String username, String password);
    void signInFacebook(String token);
    void startFirebase();
    void onStart();
    void onStop();
}
