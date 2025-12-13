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
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Signin extends AppCompatActivity {

    ImageView imgLogo;
    TextView txtWelcome, txtLogin;
    Button btnSignIn;
    TextInputLayout layoutEmail, layoutPass, layoutPass2, layoutName;
    TextInputEditText editEmail, editPass, editPass2, editName;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgLogo = findViewById(R.id.imgLogo);

        txtWelcome = findViewById(R.id.txtWelcome);
        txtLogin = findViewById(R.id.txtLogin);

        btnSignIn = findViewById(R.id.btnSignIn);

        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        editPass2 = findViewById(R.id.editPass2);
        editName = findViewById(R.id.editName);

        layoutEmail = findViewById(R.id.layoutEmail);
        layoutPass = findViewById(R.id.layoutPass);
        layoutPass2 = findViewById(R.id.layoutPass2);
        layoutName = findViewById(R.id.layoutName);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        txtLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        btnSignIn.setOnClickListener(v -> {
            registerUser();
        });
    }

    private void registerUser(){
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        String passwordConfirm = editPass2.getText().toString().trim();

        if(name.isEmpty()){
            editName.setError("Name is Required");
            editName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("Email is Required");
            editEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPass.setError("Password is Required");
            editPass.requestFocus();
            return;
        }
        if(passwordConfirm.isEmpty()){
            editPass2.setError("Password is Required");
            editPass2.requestFocus();
            return;
        }

        //validasi email
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                if(user != null){
                    user.sendEmailVerification().addOnCompleteListener(this, task2 -> {
                        if(task2.isSuccessful()){
                            Toast.makeText(this, "Sign Up Success! Please Verify Your Account Via Email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Failed To Send Email. Try Again.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    //ambil userID
                    String userId = user.getUid();

                    Map<String, Object> userData = new HashMap<>();
                    userData.put("name", name);
                    userData.put("email", email);

                    db.collection("users").document(userId).set(userData)
                            .addOnSuccessListener(aVoid -> {
                                Log.d("TAG", "DocumentSnapshot successfully written!");
                            })
                            .addOnFailureListener(e -> {
                                Log.w("TAG", "Error writing document", e);
                            });
                }

                Intent intent = new Intent(getApplicationContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else{
                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                Toast.makeText(this, "Sign Up Failed. Try Again.", Toast.LENGTH_SHORT).show();
            }
        });




    }
}