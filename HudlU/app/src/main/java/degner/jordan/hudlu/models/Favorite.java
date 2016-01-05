package degner.jordan.hudlu.models;

import io.realm.RealmObject;

/**
 * Created by Jordan on 12/1/15.
 */
public class Favorite extends RealmObject {
    private String title;
    private String author;
    private String image;
    private String link;

    public Favorite() {
        super();
    }

    public Favorite(String title, String author, String image, String link) {
        super();

        this.title = title;
        this.author = author;
        this.image = image;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
