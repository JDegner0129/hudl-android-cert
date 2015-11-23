package degner.jordan.hudlu.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jordan on 11/22/15.
 */
public class MashableNews {
    @SerializedName("new") // we need this to properly deserialize the JSON from Mashable via GSON
    public List<MashableNewsItem> newsItems;
}
