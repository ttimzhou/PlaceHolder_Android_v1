package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.placeholder.NewsDetailActivity;
import com.example.placeholder.R;


import java.util.List;

import Model.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>  {

    private Context context;
    private List<NewsItem> newsItems;



    public NewsAdapter(Context context, List listItem1) {
        this.context = context;
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
        holder.image.setImageResource(R.drawable.newphoto);
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.image);

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView title;
        private TextView description;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            image = (ImageView) itemView.findViewById(R.id.newsImage);
            title = (TextView) itemView.findViewById(R.id.newsTitle);
            description = (TextView) itemView.findViewById(R.id.newsDescription);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            onSelectedNews(newsItems.get(position));
        }
    }




    public void onSelectedNews(NewsItem selectedNews) {
//        process data and find the right activity
        Intent intent = new Intent(context, NewsDetailActivity.class);
        context.startActivity(intent);
    };
}
