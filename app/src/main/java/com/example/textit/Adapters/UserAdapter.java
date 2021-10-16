package com.example.textit.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.textit.ChatDetailActivity;
import com.example.textit.Models.Users;
import com.example.textit.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.MissingResourceException;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<Users> List;
    Context context;
    private ObjectInputStream.GetField list;
    private MissingResourceException dataSnapshot;

    public UserAdapter(ArrayList<Users> list, Context context) {
        List = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.ViewHolder holder, int position) {
        Users users = list.get(position);

        Picasso.get().load(users.getProfilepic()).placeholder(R.drawable.Profilpicture).into(holder.image);
        holder.userName.setText(users.getUserName());

        FirebaseDatabase.getInstance().getReference().child("chats");
                        .child(FirebaseAuth.getInstance().getUid() + users.getUserId())
                        .orderByChild("timestamp")
                        .limitToLast(1)
                        .addListenerForSingleValueEvent(new ValueEventListener(){

                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if(snapshot.hasChildren()){
                                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                                        holder.lastMessage.setText(snapshot1.child("message").getValue().toString());
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , ChatDetailActivity.class);
                intent.putExtra("userId", users.getUserId(dataSnapshot.getKey()));
                intent.putExtra("profilePicture", users.getProfilepic(dataSnapshot.getKey()));
                intent.putExtra("username", users.getUserName(dataSnapshot.getKey()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView userName, lastMessage;
        public ViewHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userNameList);
            lastMessage = itemView.findViewById(R.id.lastMessage);

        }
    }
}
