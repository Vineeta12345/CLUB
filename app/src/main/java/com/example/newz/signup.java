package com.example.newz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    EditText emailtext ;
    EditText password ;
    EditText confirmpassword ;
    EditText username;
    Button registeruser;
    FirebaseAuth mauth;
    ProgressDialog registerdialog;
    Animation uptodown,lefttoright;
    ImageView appsign;
    FirebaseUser crnt;
    String currentuser;
    private CheckBox mcheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailtext=(EditText)findViewById(R.id.emailtext);
        password=(EditText)findViewById(R.id.passwordtext);
        username = (EditText) findViewById(R.id.username);
        confirmpassword=(EditText)findViewById(R.id.confirmpasswordtext);
        registeruser=(Button)findViewById(R.id.signup);
        mauth= FirebaseAuth.getInstance();
        registerdialog = new ProgressDialog(this);
        registerdialog.setTitle("Registering user");
        registerdialog.setMessage("Please wait while we create your Account");
        registerdialog.setCanceledOnTouchOutside(false);
        mcheckbox = (CheckBox) findViewById(R.id.checkbox) ;
        appsign = (ImageView) findViewById(R.id.applogo);

        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        lefttoright = AnimationUtils.loadAnimation(this,R.anim.lefttoright);

        appsign.setAnimation(uptodown);
        registeruser.setAnimation(lefttoright);

        registeruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailtext.getText().toString();
                String passwordd = password.getText().toString();
                String name = username.getText().toString();
                String confirmpasswordd = confirmpassword.getText().toString();
                registerdialog.show();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(passwordd) && !TextUtils.isEmpty(confirmpasswordd))
                {
                    if(passwordd.equals(confirmpasswordd))
                    {
                        if(mcheckbox.isChecked())

                        {
                            int checked = 1;
                            register_user(passwordd,email,checked,name);
                        }
                        else
                        {
                            int checked = 0;
                            register_user(passwordd,email,checked,name);
                        }


                    }
                    else
                    {
                        registerdialog.hide();
                        Toast.makeText(signup.this,"Password does not match  ",Toast.LENGTH_LONG).show();

                    }

                }
                else
                {
                    registerdialog.hide();
                    Toast.makeText(signup.this,"Field empty my dude ",Toast.LENGTH_LONG).show();

                }
            }
        });


    }
    private void register_user(String passwordd, final String email, final int checked, final String username) {

        mauth.createUserWithEmailAndPassword(email,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    crnt = FirebaseAuth.getInstance().getCurrentUser();
                    currentuser = crnt.getUid().toString();


                    DatabaseReference mdatabse;
                    mdatabse = FirebaseDatabase.getInstance().getReference().child("Users");
                    mdatabse.child(currentuser).child("Faculty").setValue(checked);
                    mdatabse.child(currentuser).child("Name").setValue(username);
                    registerdialog.dismiss();
                    Intent loginnow = new Intent(signup.this, signin.class);
                    startActivity(loginnow);
                    finish();




                }
            }
        });
    }
}