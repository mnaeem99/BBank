package com.example.sbb;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestAdapter
        /*extends FirebaseRecyclerAdapter<post_getset, RequestAdapter.MPostHolder> */{
    /*Context context;
    public RequestAdapter(@NonNull FirebaseRecyclerOptions<post_getset> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MPostHolder mPostHolder, final int position, @NonNull final post_getset model) {
        mPostHolder.username.setText(model.getName());
        mPostHolder.bloodgroup.setText(model.getBloodgroup());
        mPostHolder.postapplication.setText(model.getApplication());
        mPostHolder.hospital.setText(model.getHospitalname());
        mPostHolder.location.setText(model.getLocationname());
        mPostHolder.likepostcheack(getRef(position).getKey());
        mPostHolder.setiamge(model.getProfileimage());
    }

    @NonNull
    @Override
    public MPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.postbanner, parent, false);
        return new MPostHolder(view);
    }

    static class MPostHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private View Mview;
        private TextView bloodgroup;
        private TextView postapplication;
        private CircleImageView postprofileimage;
        private Context context;
        private TextView hospital;
        private TextView location;
        private ImageView likeicon;
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
            postprofileimage = Mview.findViewById(R.id.cardprofileimageID);
            context = Mview.getContext();
            hospital = Mview.findViewById(R.id.HospitalNameID);
            location = Mview.findViewById(R.id.LocationNameID);
            likeicon = Mview.findViewById(R.id.LikeID);
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

    }

*/
}

/*
FirebaseRecyclerOptions<post_getset> options =
                new FirebaseRecyclerOptions.Builder<post_getset>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Post"), post_getset.class)
                        .build();
        requestAdapter = new RequestAdapter(options, this);
        recyclerView.setAdapter(requestAdapter);

 */