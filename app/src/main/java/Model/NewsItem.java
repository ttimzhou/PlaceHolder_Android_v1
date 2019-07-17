package Model;

import android.widget.ImageView;

public class NewsItem {
    private String title;
    private String description;
    private String imageURl;

    public NewsItem(String URL, String title, String description) {
        this.imageURl = URL;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }
}
