package com.example.card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList card_id, card_name, card_company, card_field, card_phone, card_email;

    CustomAdapter(Context context,
                  ArrayList card_id,
                  ArrayList card_name,
                  ArrayList card_company,
                  ArrayList card_field,
                  ArrayList card_phone,
                  ArrayList card_email){
        this.context = context;
        this.card_id = card_id;
        this.card_name = card_name;
        this.card_company = card_company;
        this.card_field = card_field;
        this.card_phone = card_phone;
        this.card_email = card_email;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.onecard, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card_id_txt.setText(String.valueOf(card_id.get(position)));
        holder.card_name_txt.setText(String.valueOf(card_name.get(position)));
        holder.card_company_txt.setText(String.valueOf(card_company.get(position)));
        holder.card_field_txt.setText(String.valueOf(card_field.get(position)));
        holder.card_phone_txt.setText(String.valueOf(card_phone.get(position)));
        holder.card_email_txt.setText(String.valueOf(card_email.get(position)));
    }

    @Override
    public int getItemCount() {
        return card_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView card_id_txt, card_name_txt, card_company_txt, card_field_txt, card_phone_txt, card_email_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_id_txt = itemView.findViewById(R.id.card_id_txt);
            card_name_txt = itemView.findViewById(R.id.card_name_txt);
            card_company_txt = itemView.findViewById(R.id.card_company_txt);
            card_field_txt = itemView.findViewById(R.id.card_field_txt);
            card_phone_txt = itemView.findViewById(R.id.card_phone_txt);
            card_email_txt = itemView.findViewById(R.id.card_email_txt);

        }
    }
}

