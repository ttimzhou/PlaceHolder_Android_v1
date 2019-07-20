package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.R;


import java.util.List;

import Model.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private Context context;
    private List<NewsItem> newsItems;



    public NewsAdapter(Context context1, List listItem1) {
        this.context = context1;
        this.newsItems = listItem1;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_news, parent, false);
        return new ViewHolder(view);
    }


    // Set information for individual texts
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        NewsItem item = newsItems.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());
        holder.image.setImageResource(R.drawable.news_view_holder);
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.image);

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;
        private TextView description;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.newsImage);
            title = (TextView) itemView.findViewById(R.id.newsTitle);
            description = (TextView) itemView.findViewById(R.id.newsDescription);
        }
    }
}
