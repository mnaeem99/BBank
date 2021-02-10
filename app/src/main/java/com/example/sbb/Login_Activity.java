package com.example.sbb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    private RelativeLayout layoutone, layouttwo, layoutthree, layoutfive;

    private TextView phonelogin;
    private FirebaseAuth Mauth;
    private EditText email, password;
    private Button loginbutton;
    private ProgressDialog Mprogress;
    private Button register;
    private TextView forgotpassword;

    private Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            layoutfive.setVisibility(View.INVISIBLE);
            layoutone.setVisibility(View.VISIBLE);
            layouttwo.setVisibility(View.VISIBLE);
            layoutthree.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Mprogress = new ProgressDialog(this, R.style.MyAlertDialogStyle);

        Mauth = FirebaseAuth.getInstance();
        register = findViewById(R.id.LoginRegisterTextID);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        layoutone = findViewById(R.id.layoutoneID);
        layouttwo = findViewById(R.id.layoutThreeID);
        layoutthree = findViewById(R.id.layoutTwoID);
        layoutfive = findViewById(R.id.layoutFiveID);

        handler.postDelayed(runnable, 4000);

        email = findViewById(R.id.EmialID);
        password = findViewById(R.id.PasswordID);
        loginbutton = findViewById(R.id.LoginButtonID);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailtext = email.getText().toString();
                if(emailtext.isEmpty()){
                    email.setError("Email empty");
                }
                String passwordtext = password.getText().toString();
                  if(passwordtext.isEmpty()){
                    password.setError("Password empty");
                }
                else {

                    Mprogress.setTitle("Please wait");
                    Mprogress.setMessage("Please wait we are checking your authentication");
                    Mprogress.setCanceledOnTouchOutside(false);
                    Mprogress.show();
                    Mauth.signInWithEmailAndPassword(emailtext, passwordtext)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){

                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Mprogress.dismiss();
                                    }
                                    else {
                                        String errormessage = task.getException().getMessage().toString();
                                        Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
                                        Mprogress.dismiss();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            String errormessage = e.getMessage().toString();
                            Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
                            Mprogress.dismiss();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {

        FirebaseUser Muser = Mauth.getCurrentUser();
        if(Muser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}
