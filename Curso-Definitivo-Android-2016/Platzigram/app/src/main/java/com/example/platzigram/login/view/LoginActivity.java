package com.example.platzigram.login.view;

import android.content.Intent;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.platzigram.R;
import com.example.platzigram.login.presenter.LoginPresenter;
import com.example.platzigram.login.presenter.LoginPresenterImpl;
import com.example.platzigram.view.ContainerActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText inputUsername, inputPassword;
    private Button buttonLogin;
    private ProgressBar progressBarLogin;
    private LoginPresenter loginPresenter;
    private TextView textViewCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputUsername=findViewById(R.id.username);
        inputPassword=findViewById(R.id.password);
        buttonLogin=findViewById(R.id.login);
        progressBarLogin=findViewById(R.id.progressbarLogin);
        loginPresenter=new LoginPresenterImpl(this);
        textViewCreateAccount=findViewById(R.id.createHere);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCreateAccount();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateInputs()){
                    loginPresenter.signIn(inputUsername.getText().toString(),inputPassword.getText().toString());
                }else{
                    loginError(getResources().getString(R.string.login_invalid));
                }
            }
        });
        hideProgressBar();
    }

    @Override
    public boolean validateInputs() {
        return !(inputUsername.getText()==null || inputPassword.getText()==null || inputUsername.getText().toString().equals("") || inputPassword.getText().toString().equals(""));
    }

    @Override
    public void goCreateAccount(){
        Intent intent=new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goHome(){
        Intent intent=new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void enableInputs() {
        inputUsername.setEnabled(true);
        inputPassword.setEnabled(true);
        buttonLogin.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        inputUsername.setEnabled(false);
        inputPassword.setEnabled(false);
        buttonLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
    }
}
