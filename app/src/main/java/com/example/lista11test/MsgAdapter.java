package com.example.lista11test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView msgTextView;
        public TextView dateTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            msgTextView = (TextView) itemView.findViewById(R.id.tvMsg);
            dateTextView = (TextView) itemView.findViewById(R.id.tvDate);
        }
    }

    private List<Msg> messages;

    public MsgAdapter(List<Msg> messages){this.messages=messages;}



    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_message, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Msg message = messages.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.msgTextView;
        textView.setText(message.getText());
        TextView dateTextView = holder.dateTextView;
        dateTextView.setText(String.valueOf(message.getDate()));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
