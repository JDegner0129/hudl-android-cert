package degner.jordan.hudlu;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import degner.jordan.hudlu.models.Favorite;

/**
 * Created by Jordan on 11/15/15.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Favorite> mDataset;
    private OnAdapterInteractionListener mListener;
    private RequestQueue mRequestQueue;

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTitleView;
        TextView mItemAuthorView;
        ImageView mItemImageView;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            mItemTitleView = (TextView) itemView.findViewById(R.id.favorite_item_title);
            mItemAuthorView = (TextView) itemView.findViewById(R.id.favorite_item_author);
            mItemImageView = (ImageView) itemView.findViewById(R.id.favorite_item_image);
        }
    }

    public FavoriteAdapter(Context context, List<Favorite> dataset) {
        mListener = (OnAdapterInteractionListener) context;
        mDataset = dataset;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_item, parent, false);

        FavoriteViewHolder vh = new FavoriteViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final FavoriteViewHolder holder, final int position) {
        final Favorite newsItem = mDataset.get(position);

        holder.mItemTitleView.setText(newsItem.getTitle());
        holder.mItemAuthorView.setText(newsItem.getAuthor());

        ImageRequest request = new ImageRequest(newsItem.getImage(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.mItemImageView.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("FetchNewsImage", error.getMessage());
            }
        });

        mRequestQueue.add(request);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
