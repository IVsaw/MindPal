package com.example.mindpal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Profile extends AppCompatActivity {

    ImageView imgProfile;
    TextView txtName, txtEmail;
    Button btnLogOut;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // === INIT VIEW ===
        imgProfile = findViewById(R.id.imgprofile);

        txtName = findViewById(R.id.txtname);
        txtEmail = findViewById(R.id.txtemail);
        btnLogOut = findViewById(R.id.btnLogOut);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser(); //ambil data user yang lagi login

        if(currentUser!=null){
            loadUserData(currentUser.getUid());
        } else {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
        }

        btnLogOut.setOnClickListener(v->{
            mAuth.signOut();
            Toast.makeText(this, "Logged Out!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void loadUserData(String UserId){
        DocumentReference userRef = db.collection("users").document(UserId);

        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if(documentSnapshot.exists()){
                String name = documentSnapshot.getString("name");
                String email = documentSnapshot.getString("email");

                //buat ubah yang txtname sama yang txtemail
                txtName.setText(name);
                txtEmail.setText(email);
            } else{
                Log.w("TAG", "No such document");
                Toast.makeText(Profile.this, "Failed To Load Profile", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> {
            Log.e("TAG", "Error loading user data", e);
            Toast.makeText(Profile.this, "Server Connection Failed", Toast.LENGTH_SHORT).show();
        });
    }

}