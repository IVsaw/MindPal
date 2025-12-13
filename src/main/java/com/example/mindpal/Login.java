package com.example.mindpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    ImageView imgLogo;
    TextView txtWelcome, txtSign;
    Button btnLogin;
    TextInputLayout layoutEmail, layoutPass;
    TextInputEditText editEmail, editPass;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgLogo = findViewById(R.id.imgLogo);
        txtWelcome = findViewById(R.id.txtWelcome);
        txtSign = findViewById(R.id.txtSign);
        btnLogin = findViewById(R.id.btnLogin);
        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        layoutEmail = findViewById(R.id.layoutEmail);
        layoutPass = findViewById(R.id.layoutPass);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(v -> {
            loginUser();
        });

        txtSign.setOnClickListener(v -> {
            Intent intent = new Intent(this, Signin.class);
            startActivity(intent);
        });
    }

    private void loginUser(){
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Email is Required");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPass.setError("Password Is Required");
            editEmail.requestFocus();
            return;
        }
        Toast.makeText(this, "Log In Success!", Toast.LENGTH_SHORT).show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task->{
            if(task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();

                if(user != null){
                    if(user.isEmailVerified()){
                        Log.d("LOGIN_SUCCESS", "Login Success");
                        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                    } else{
                        Log.w("LOGIN_FAILED", "Please Verify Your Email");
                        Toast.makeText(Login.this, "Please Verify Your Email", Toast.LENGTH_LONG).show();
                        mAuth.signOut();
                    }
                }

            } else{
                Log.w("Login Failed", "Login Failed", task.getException());
                Toast.makeText(Login.this, "Login Failed" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
        });








    }


}
