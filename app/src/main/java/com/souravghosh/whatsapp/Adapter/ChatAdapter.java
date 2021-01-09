package com.souravghosh.whatsapp.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.souravghosh.whatsapp.Modeles.MassagesModel;
import com.souravghosh.whatsapp.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MassagesModel> massagesModels;
    Context context;
    String reciveId;


    int SENDER_VIEW_TYPE =1;
    int RECEIVER_VIEW_TYPE =2;

    public ChatAdapter(ArrayList<MassagesModel> massagesModels, Context context) {
        this.massagesModels = massagesModels;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MassagesModel> massagesModels, Context context, String reciveId) {
        this.massagesModels = massagesModels;
        this.context = context;
        this.reciveId = reciveId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender , parent, false);
            return new SenderViewVolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver , parent, false);
            return new ReciverViewVolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (massagesModels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }else {
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MassagesModel massagesModel = massagesModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setTitle("Delete").setMessage("Are you sure you want to delete this massage?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String senderRoom = FirebaseAuth.getInstance().getUid() + reciveId;
                        database.getReference().child("Chats").child(senderRoom).child(massagesModel.getMessageId()).setValue(null);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return false;
            }
        });

        if(holder.getClass() == SenderViewVolder.class){
            ((SenderViewVolder)holder).senderMsg.setText(massagesModel.getMessage());
        }else {
            ((ReciverViewVolder)holder).reciverMsg.setText(massagesModel.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return massagesModels.size();
    }

    public class ReciverViewVolder extends RecyclerView.ViewHolder{

        TextView reciverMsg , reciverTime;
        public ReciverViewVolder(@NonNull View itemView) {
            super(itemView);
            reciverMsg = itemView.findViewById(R.id.recivertext);
            reciverTime = itemView.findViewById(R.id.reciverTime);
        }
    }

    public class SenderViewVolder extends RecyclerView.ViewHolder{

        TextView senderMsg , senderTime;
        public SenderViewVolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.sendertext) ;
            senderTime = itemView.findViewById(R.id.sendertime);
        }
    }
}
