package com.example.newvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
   private EditText fullname,gender,age,aadhar,mobile;


    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Online Voting System ");
        fullname= findViewById(R.id.fullname);
        gender= findViewById(R.id.gender);
        age= findViewById(R.id.age);
        aadhar= findViewById(R.id.aadhar);
        mobile= findViewById(R.id.mobile);
        register=findViewById(R.id.buttonreg);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(MainActivity.this,ResultActivity.class));
                HashMap<String,Object>map =new HashMap<>();
                map.put("A  Full name",fullname.getText().toString());
                map.put("B  Gender",gender.getText().toString());
                map.put("C  Date of Birth",age.getText().toString());
                map.put("D  Age",aadhar.getText().toString());
                map.put("E  ID Number",mobile.getText().toString());
                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();



                FirebaseDatabase.getInstance().getReference().child("Voter Details").push()
                .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull   Task<Void> task) {
                                Log.i("kpppp", "onComplete: ");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull   Exception e) {
                        Log.i("kpppp", "onFailure:" +e.toString());
                    }
                });





            }
        });

    }
}