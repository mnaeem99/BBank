package com.example.sbb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetail extends AppCompatActivity {
    private String ReciverID;
    private DatabaseReference MuserDatabase;
    private FirebaseAuth Mauth;
    private DatabaseReference FriendsDatabase;
    private TextView phonenumber;
    private TextView blood;
    private TextView sextext;
    private TextView height;
    private TextView Age;
    private TextView friendsname;

    //////friends user details
    private String FriendsBloodGroup;
    private String FriendsGender;
    private String FriendsHeight;
    private String FriendsAge;
    private String FriendsPhoneNumber;
    private CircleImageView reciverimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);


        friendsname = findViewById(R.id.FriendsNameID);
        blood = findViewById(R.id.BloodGroup);
        phonenumber = findViewById(R.id.PhoenNumberID);
        sextext = findViewById(R.id.Sex);
        height = findViewById(R.id.Height);
        Age = findViewById(R.id.AgeText);
        reciverimage = findViewById(R.id.ReciverImageID);

        Mauth = FirebaseAuth.getInstance();
        MuserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        MuserDatabase.keepSynced(true);

        ReciverID = getIntent().getStringExtra("Key");
        FriendsDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(ReciverID);
        FriendsDatabase.keepSynced(true);
        FriendsDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    if (dataSnapshot.hasChild("phone")) {
                        FriendsPhoneNumber = dataSnapshot.child("phone").getValue().toString();
                        phonenumber.setText(FriendsPhoneNumber);
                    }
                    if (dataSnapshot.hasChild("blood")) {
                        FriendsBloodGroup = dataSnapshot.child("blood").getValue().toString();
                        blood.setText(FriendsBloodGroup);
                    }
                    if (dataSnapshot.hasChild("gender")) {
                        FriendsGender = dataSnapshot.child("gender").getValue().toString();
                        sextext.setText(FriendsGender);
                    }
                    if (dataSnapshot.hasChild("hight")) {
                        FriendsHeight = dataSnapshot.child("hight").getValue().toString();
                        height.setText(FriendsHeight);
                    }
                    if (dataSnapshot.hasChild("age")) {
                        FriendsAge = dataSnapshot.child("age").getValue().toString();
                        Age.setText(FriendsAge);
                    }
                    if (dataSnapshot.hasChild("profile_imageLink")) {
                        String profile_imageLinkget = dataSnapshot.child("profile_imageLink").getValue().toString();
                        Picasso.with(getApplicationContext()).load(profile_imageLinkget).placeholder(R.drawable.defaltimage).into(reciverimage);
                        Picasso.with(getApplicationContext()).load(profile_imageLinkget).networkPolicy(NetworkPolicy.OFFLINE).into(reciverimage, new Callback() {
                            @Override
                            public void onSuccess() {

                            }
                            @Override
                            public void onError() {

                            }
                        });
                    }
                    if (dataSnapshot.hasChild("name")) {
                        String nameget = dataSnapshot.child("name").getValue().toString();
                        friendsname.setText(nameget);
                    }
                } else {
                    String errormessage = "somethings error";
                    Toast.makeText(getApplicationContext(), errormessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
