package com.example.sbb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Post#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Post extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference Mpostdatabase;
    private FloatingActionButton requestbutton;
    boolean Likecheacker = false;
    private FirebaseAuth Mauth;
    private String CurrentUserID;
    private DatabaseReference MlikeDatabase;

    public Post() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        MlikeDatabase = FirebaseDatabase.getInstance().getReference().child("Likes");
        Mauth = FirebaseAuth.getInstance();
        CurrentUserID = Mauth.getCurrentUser().getUid();
        Mpostdatabase = FirebaseDatabase.getInstance().getReference().child("Post");
        Mpostdatabase.keepSynced(true);
        recyclerView = view.findViewById(R.id.CardViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestbutton = view.findViewById(R.id.RrquestButtonID);
        requestbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    @Override
    public void onStart() {
        FirebaseRecyclerAdapter<PostModal, Post.MPostHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PostModal, Post.MPostHolder>(
                PostModal.class,
                R.layout.postbanner,
                Post.MPostHolder.class,
                Mpostdatabase
        ) {
            @Override
            protected void populateViewHolder(final Post.MPostHolder mPostHolder, PostModal post_getset, int i) {
                final String UID = getRef(i).getKey();
                Mpostdatabase.child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            mPostHolder.likepostcheack(UID);
                            if (dataSnapshot.hasChild("application")) {
                                String applicationget = dataSnapshot.child("application").getValue().toString();
                                mPostHolder.setapplicaton(applicationget);
                            }
                            if (dataSnapshot.hasChild("bloodgroup")) {
                                String bloodgroupget = dataSnapshot.child("bloodgroup").getValue().toString();
                                mPostHolder.setbloodgroup(bloodgroupget);
                            }
                            if (dataSnapshot.hasChild("date")) {
                                String dateget = dataSnapshot.child("date").getValue().toString();
                                mPostHolder.setpostdate(dateget);
                            }
                            if (dataSnapshot.hasChild("profileimage")) {
                                String profileimageget = dataSnapshot.child("profileimage").getValue().toString();
                                mPostHolder.setiamge(profileimageget);
                            }
                            if (dataSnapshot.hasChild("name")) {
                                String nameget = dataSnapshot.child("name").getValue().toString();
                                mPostHolder.setname(nameget);
                            }
                            if (dataSnapshot.hasChild("locationname")) {
                                String locationnameget = dataSnapshot.child("locationname").getValue().toString();
                                mPostHolder.setloction(locationnameget);
                            }
                            if (dataSnapshot.hasChild("hospitalname")) {
                                String hospitalnameget = dataSnapshot.child("hospitalname").getValue().toString();
                                mPostHolder.sethospitalset(hospitalnameget);
                            }
                            mPostHolder.likeicon.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Likecheacker = true;

                                    MlikeDatabase.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            if(Likecheacker==true){
                                                if (dataSnapshot.child(UID).hasChild(CurrentUserID)) {
                                                    MlikeDatabase.child(UID).child(CurrentUserID).removeValue();
                                                    Likecheacker = false;
                                                } else {
                                                    MlikeDatabase.child(UID).child(CurrentUserID).setValue(true);
                                                    Likecheacker = false;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                }
                            });
                            mPostHolder.del.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Mpostdatabase.child(UID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getContext(), "Post deleted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            });
                        } else {
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        super.onStart();
    }

    public static class MPostHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private View Mview;
        private TextView bloodgroup;
        private TextView postapplication;
        private TextView postdate;
        private CircleImageView postprofileimage;
        private Context context;
        private TextView hospital;
        private TextView location;

        private ImageView likeicon;
        private ImageView del;
        private TextView likecount;

        private int countlike;
        private String CurrentUseID;
        private DatabaseReference MlikeDatabase;

        public MPostHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            username = Mview.findViewById(R.id.bannerusernameID);
            bloodgroup = Mview.findViewById(R.id.bannerblood);
            postapplication = Mview.findViewById(R.id.PostApplicationID);
            postdate = Mview.findViewById(R.id.CurrentDateID);
            postprofileimage = Mview.findViewById(R.id.cardprofileimageID);
            context = Mview.getContext();
            hospital = Mview.findViewById(R.id.HospitalNameID);
            location = Mview.findViewById(R.id.LocationNameID);

            likeicon = Mview.findViewById(R.id.LikeID);
            del = Mview.findViewById(R.id.ivdel);
            likecount = Mview.findViewById(R.id.LikeCountID);

            MlikeDatabase = FirebaseDatabase.getInstance().getReference().child("Likes");
            CurrentUseID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        public void likepostcheack(final String UID) {

            MlikeDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {



                    if (dataSnapshot.child(UID).hasChild(CurrentUseID)) {
                        countlike = (int) dataSnapshot.child(UID).getChildrenCount();
                        likeicon.setBackgroundResource(R.drawable.redlike);
                        likecount.setText(Integer.toString(countlike)+" Likes");
                    }
                    else {
                        countlike = (int) dataSnapshot.child(UID).getChildrenCount();
                        likeicon.setBackgroundResource(R.drawable.like);
                        likecount.setText(Integer.toString(countlike)+" Likes");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        public void setname(String nam) {
            username.setText(nam);
        }

        public void setbloodgroup(String group) {
            bloodgroup.setText(group);
        }

        public void setapplicaton(String app) {
            postapplication.setText(app);
        }

        public void setiamge(String img) {
            Picasso.with(context).load(img).placeholder(R.drawable.defaltimage).into(postprofileimage);
            Picasso.with(context).load(img).networkPolicy(NetworkPolicy.OFFLINE).into(postprofileimage, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                }
            });
        }

        public void setpostdate(String date) {
            postdate.setText(date);
        }

        public void sethospitalset(String hos) {
            hospital.setText(hos);
        }

        public void setloction(String loc) {
            location.setText(loc);
        }
    }

}
