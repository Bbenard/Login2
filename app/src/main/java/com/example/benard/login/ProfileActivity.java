package com.example.benard.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
 private FirebaseAuth firebaseAuth;
 private TextView textViewUserMail;
 private Button buttonlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        buttonlogout=(Button)findViewById(R.id.buttonlogout);
        textViewUserMail=(TextView)findViewById(R.id.textviewsignin);
        buttonlogout=(Button)findViewById(R.id.buttonlogout);
        firebaseAuth=FirebaseAuth.getInstance();

        buttonlogout.setOnClickListener(this);
        if (firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserMail=(TextView)findViewById(R.id.textviewsignin);
        textViewUserMail.setText("welcome"+user.getEmail());


        }



    @Override
    public void onClick(View view) {
        if (view==buttonlogout){
            firebaseAuth.signOut();
            startActivity(new Intent(this,LoginActivity.class));

        }

    }
}
