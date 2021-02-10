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

public class UsersAdapter
        /*extends FirebaseRecyclerAdapter<UsersModal, UsersAdapter.userHolder> */{
    /*Context context;
    public UsersAdapter(@NonNull FirebaseRecyclerOptions<UsersModal> options, Context context) {
        super(options);
        this.context = context;
    }
    @Override
    protected void onBindViewHolder(@NonNull userHolder userHolder, final int position, @NonNull final UsersModal model) {
        userHolder.name.setText(model.getName());
        userHolder.addrss.setText(model.getAddress());
        userHolder.blood.setText(model.getBlood());
        userHolder.setiamge(model.getProfile_imageLink());
    }

    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacts_layout, parent, false);
        return new userHolder(view);
    }

    static class userHolder extends RecyclerView.ViewHolder{

        private TextView name, blood, addrss;
        private CircleImageView profiliameg;
        private View Mview;
        private Context context;

        public userHolder(@NonNull View itemView) {
            super(itemView);
            Mview = itemView;
            name = Mview.findViewById(R.id.UserNameIDID);
            blood = Mview.findViewById(R.id.UserBloodID);
            addrss = Mview.findViewById(R.id.AddrIDID);
            profiliameg = Mview.findViewById(R.id.ProfileImageIDID);
            context = Mview.getContext();
        }
        public void setiamge(String img){
            Picasso.with(context).load(img).placeholder(R.drawable.defaltimage).into(profiliameg);
            Picasso.with(context).load(img).networkPolicy(NetworkPolicy.OFFLINE).into(profiliameg, new Callback() {
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