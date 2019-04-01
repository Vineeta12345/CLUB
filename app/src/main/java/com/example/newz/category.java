package com.example.newz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

public class category extends AppCompatActivity {


    Button proadd;
    Button sportsadd;
    Button cultureadd;
    Button webadd;
    Button androadd;
    Button networkadd;
    Button dataadd;


    Button jump;
    FirebaseUser crnt;
    String currentuser;
    String user_token;
    String username;
    DatabaseReference mdatabse;
    DatabaseReference sdatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        proadd = (Button) findViewById(R.id.proadd);
        sportsadd = (Button) findViewById(R.id.sportsadd);
        cultureadd = (Button) findViewById(R.id.cultureadd);
        webadd = (Button) findViewById(R.id.webadd);
        networkadd = (Button) findViewById(R.id.networkadd);
        dataadd = (Button) findViewById(R.id.dataadd);
        androadd = (Button) findViewById(R.id.androadd);

        user_token = FirebaseInstanceId.getInstance().getToken();

        crnt = FirebaseAuth.getInstance().getCurrentUser();
        currentuser = crnt.getUid().toString();


        mdatabse = FirebaseDatabase.getInstance().getReference().child("Users").child(currentuser);
        sdatabase = FirebaseDatabase.getInstance().getReference().child("Names");

        mdatabse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                username = dataSnapshot.child("Name").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        proadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdatabse.child("Club").setValue("Programing Club");
                sdatabase.child("Programing Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();
            }
        });


        sportsadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Sports Club");
                sdatabase.child("Sports Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();

            }
        });

        cultureadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Cultural Club");
                sdatabase.child("Cultural Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();

            }
        });

        androadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Android App Club");
                sdatabase.child("Android App Club").child(currentuser).setValue(username);
Intent intent = new Intent(category.this,home_new.class);
startActivity(intent);
                finish();

            }
        });


        webadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Web Designing Club");
                sdatabase.child("Web Designing Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();
            }
        });


        networkadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Networking Club");
                sdatabase.child("Networking Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();

            }
        });

        dataadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabse.child("Club").setValue("Database Club");
                sdatabase.child("Database Club").child(currentuser).setValue(username);
                Intent intent = new Intent(category.this,home_new.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
