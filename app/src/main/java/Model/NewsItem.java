package Model;

import android.widget.ImageView;

public class NewsItem {
    private String title;
    private String description;
    private String imageURl;
    private String id;

    public NewsItem(String URL, String title, String id) {
        this.imageURl = URL;
        this.title = title;
        this.id = id;
        this.description = "Description";
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
