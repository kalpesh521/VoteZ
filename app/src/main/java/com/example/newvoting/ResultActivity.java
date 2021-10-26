package com.example.newvoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResultActivity extends AppCompatActivity {
    RadioButton male,female,na,nota;
    private Button register;
    voter voter;
    FirebaseDatabase database;
    DatabaseReference reference;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        getSupportActionBar().setTitle("Online Voting System ");
        register=findViewById(R.id.buttonreg);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        na=findViewById(R.id.na);
        nota=findViewById(R.id.nota);
        voter= new voter() ;
        reference=database.getInstance().getReference().child("Result");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if (datasnapshot.exists()){

                    i=(int )datasnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String m1= male.getText().toString();
                String m2= female.getText().toString();
                String m3= na.getText().toString();
                String m4= nota.getText().toString();
                startActivity( new Intent(ResultActivity.this,SplExitActivity.class));
                Toast.makeText(ResultActivity.this, "Vote Submitted Successfully", Toast.LENGTH_LONG).show();
                LoadingDialog loadingDialog = new LoadingDialog(ResultActivity.this);

                if (male.isChecked()){

                    voter.setParty(m1);
                    reference.child(String.valueOf(i+1)).setValue(voter);
                }
                else if(na.isChecked()){
                    voter.setParty(m3);
                    reference.child(String.valueOf(i+
                            1)).setValue(voter);
                }
                else if(nota.isChecked()){
                    voter.setParty(m4);
                    reference.child(String.valueOf(i+
                            1)).setValue(voter);
                }

                else{
                    voter.setParty(m2);
                    reference.child(String.valueOf(i+
                            1)).setValue(voter);
                }

                loadingDialog.startloadingDialog();
                Handler handler =new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },100000);



            }
        });





    }
}