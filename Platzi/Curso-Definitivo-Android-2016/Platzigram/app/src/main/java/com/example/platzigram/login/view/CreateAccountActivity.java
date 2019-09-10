package com.example.platzigram.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.platzigram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {
    
    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button buttonCreateAccount;
    private TextInputEditText txtEmail, txtPassword, txtName, txtUsername, txtConfirmPassword;
    
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_tittle_create_account),true);
        buttonCreateAccount=findViewById(R.id.joinUs);
        txtEmail=findViewById(R.id.emailCreateAccount);
        txtPassword=findViewById(R.id.passwordCreateAccount);
        txtName=findViewById(R.id.nameCreateAccount);
        txtUsername=findViewById(R.id.usernameCreateAccount);
        txtConfirmPassword=findViewById(R.id.confirmPasswordCreateAccount);
        
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
        
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
    
    private void createAccount() {
        if(txtEmail.getText()!=null && txtPassword.getText()!=null){
            firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CreateAccountActivity.this, "Cuenta creada exitosamente", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(CreateAccountActivity.this, "Ocurrio un error al crear la cuenta: "+task.getException().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            Toast.makeText(CreateAccountActivity.this, "Por favor ingrese su correo y contraseña", Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
    
    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

}
