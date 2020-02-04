package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.MatchDetailActivity;
import com.example.placeholder.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Model.TransactionModel;

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.ViewHolder> {


    private ArrayList<TransactionModel> itemLists;

    public TransactionHistoryAdapter(ArrayList<TransactionModel> itemLists, Context context) {
        this.itemLists = itemLists;
    }

    @NonNull
    @Override
    public TransactionHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_historycardview, parent ,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHistoryAdapter.ViewHolder holder, int position) {
        TransactionModel curr = itemLists.get(position);
        holder.detail.setText(curr.getAmount());
        holder.type.setText(curr.getType());
        holder.time.setText(curr.getTime());
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView type;
        private TextView detail;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.transactionType);
            detail = itemView.findViewById(R.id.transactionDetail);
            time = itemView.findViewById(R.id.time);
        }

    }
}
