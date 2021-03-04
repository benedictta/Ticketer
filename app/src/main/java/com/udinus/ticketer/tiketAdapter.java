package com.udinus.ticketer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tiketAdapter extends RecyclerView.Adapter<tiketAdapter.tiketViewHolder> {

    private  ArrayList<ticket_item> mTiketList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class tiketViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nama;
        public TextView noktp;

        public tiketViewHolder(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
            nama = itemView.findViewById(R.id.textNama);
            noktp = itemView.findViewById(R.id.textNoktp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
    public tiketAdapter(ArrayList<ticket_item> tiketlist){

        mTiketList = tiketlist;
    }
    @NonNull
    @Override
    public tiketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tiket_list,parent,false);
        tiketViewHolder tiket = new tiketViewHolder(v,mListener);
        return tiket;
    }

    @Override
    public void onBindViewHolder(@NonNull tiketViewHolder holder, int position) {

        ticket_item currentItem = mTiketList.get(position);

        holder.nama.setText(currentItem.getNama());
        holder.noktp.setText(currentItem.getNoktp());
    }

    @Override
    public int getItemCount() {

        return mTiketList.size();
    }
}
