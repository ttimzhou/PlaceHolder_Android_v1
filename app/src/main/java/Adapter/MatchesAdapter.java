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

import java.util.ArrayList;

import Model.MatchItem;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private ArrayList<MatchItem> matchItems;
    private Context context;

    public MatchesAdapter(Context context, ArrayList<MatchItem> matchItems) {
        this.matchItems = matchItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.ViewHolder holder, int position) {
        MatchItem item = matchItems.get(position);
        holder.currPrice.setText(item.getCurrPrice());
        holder.guestName.setText(item.getGuestTeam());
        holder.homeName.setText(item.getHomeTeam());
        holder.percentChangeInPrice.setText(item.getPercentChangeInPrice());

    }

    @Override
    public int getItemCount() {
        return matchItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView homeName;
        private TextView guestName;
        private TextView currPrice;
        private TextView percentChangeInPrice;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            homeName =  (TextView) itemView.findViewById(R.id.homeTeamName_preview);
            guestName = (TextView) itemView.findViewById(R.id.guestTeamName_preview);
            currPrice = (TextView) itemView.findViewById(R.id.currPrice);
            percentChangeInPrice = itemView.findViewById(R.id.percentChangeSinceStart);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, MatchDetailActivity.class);
//          add necessary information

            context.startActivity(intent);
        }
    }
}
