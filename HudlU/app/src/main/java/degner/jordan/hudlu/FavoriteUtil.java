package degner.jordan.hudlu;

import android.content.Context;

import degner.jordan.hudlu.models.Favorite;
import degner.jordan.hudlu.models.MashableNewsItem;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Jordan on 12/1/15.
 */
public class FavoriteUtil {
    public static void addFavorite(Context context, MashableNewsItem newsItem) {
        Realm realm = Realm.getInstance(context);

        Favorite favorite = new Favorite(newsItem.title,
                newsItem.author,
                newsItem.image,
                newsItem.link);

        realm.beginTransaction();
        realm.copyToRealm(favorite);
        realm.commitTransaction();
    }

    public static void removeFavorite(Context context, MashableNewsItem newsItem) {
        Realm realm = Realm.getInstance(context);

        Favorite favorite = realm.where(Favorite.class).equalTo("link", newsItem.link).findFirst();

        realm.beginTransaction();
        favorite.removeFromRealm();
        realm.commitTransaction();
    }

    public static boolean isFavorite(Context context, MashableNewsItem newsItem) {
        Realm realm = Realm.getInstance(context);

        Favorite favorite = realm.where(Favorite.class).equalTo("link", newsItem.link).findFirst();

        return (favorite != null);
    }

    public static RealmResults<Favorite> getAllFavorites(Context context) {
        Realm realm = Realm.getInstance(context);

        return realm.allObjects(Favorite.class);
    }
}
