package com.example.benard.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonsignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    //private EditText textEmailAddress;
    private TextView textviewsigin;
    //private progressDialog progressDialog;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonsignin =(Button) findViewById (R.id.buttonsignin);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextEmail=(EditText)findViewById(R.id.editTextPassword);
        textviewsigin=(TextView)findViewById(R.id.textviewsigin);

        progressDialog=new ProgressDialog(this);
        buttonsignin.setOnClickListener(this);
        textviewsigin.setOnClickListener(this);
        firebaseAuth= FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            finish();
        }



    }
    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if (TextUtils.isEmpty(email )){
            Toast.makeText(this,"Please Enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            if (TextUtils.isEmpty(password )){
                Toast.makeText(this,"Please Enter your email",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        progressDialog.setMessage("Register user");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Registred succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Could not Register Succesfully", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view==buttonsignin){
            userLogin();
        }
        else if (view==textviewsigin){
            //finish();
            startActivity(new Intent(this,MainActivity.class) );
        }

    }
}
