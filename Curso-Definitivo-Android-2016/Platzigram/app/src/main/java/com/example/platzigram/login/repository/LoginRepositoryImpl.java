package com.example.platzigram.login.repository;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.platzigram.login.interactor.LoginInteractor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepositoryImpl implements LoginRepository {
    
    private LoginInteractor loginInteractor;
    private Activity activity;
    private static final String TAG = "LoginRepositoryImpl";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    
    public LoginRepositoryImpl(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
        this.activity=loginInteractor.getPresenter().getActivity();
        startFirebase();
    }
    
    @Override
    public void signIn(String username, String password) {
        firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    loginInteractor.loginSuccess();
                }else{
                    loginInteractor.loginFailed("Ocurrio el siguiente error: "+task.getException().toString());
                }
            }
        });
    }
    
    @Override
    public void startFirebase() {
        //Obtenemos una instancia de firebase a partir del json
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if(firebaseUser!=null){
                    Log.w(TAG,"Usuario loggeado: "+firebaseUser.getEmail());
                }else{
                    Log.w(TAG,"Usuario  no loggeado");
                }
            }
        };
    }
    
    @Override
    public void onStart() {
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    
    @Override
    public void onStop() {
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
    
}
