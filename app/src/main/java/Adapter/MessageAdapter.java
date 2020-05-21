package Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;

import java.util.ArrayList;

import Model.MessageItem;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<MessageItem> messageItems;
    private Context context;

    public MessageAdapter(Context context, ArrayList<MessageItem> messageItems) {
        this.messageItems = messageItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_recyclerview_layout, parent, false);
        return new MessageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        MessageItem item = messageItems.get(position);
        holder.message.setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return messageItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView message;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            message =  (TextView) itemView.findViewById(R.id.message1);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
