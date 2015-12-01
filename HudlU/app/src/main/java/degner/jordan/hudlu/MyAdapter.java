package degner.jordan.hudlu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import degner.jordan.hudlu.models.MashableNewsItem;

/**
 * Created by Jordan on 11/15/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MashableNewsItem> mDataset;
    private OnAdapterInteractionListener mListener;
    private RequestQueue mRequestQueue;
    private Context mContext;

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTitleView;
        TextView mItemAuthorView;
        ImageView mItemImageView;
        Button mItemFavoriteButton;

        public MyViewHolder(View itemView) {
            super(itemView);

            mItemTitleView = (TextView) itemView.findViewById(R.id.item_title);
            mItemAuthorView = (TextView) itemView.findViewById(R.id.item_author);
            mItemImageView = (ImageView) itemView.findViewById(R.id.item_image);
            mItemFavoriteButton = (Button) itemView.findViewById(R.id.item_favorite);
        }
    }

    public MyAdapter(Context context, List<MashableNewsItem> dataset) {
        mListener = (OnAdapterInteractionListener) context;
        mDataset = dataset;
        mRequestQueue = Volley.newRequestQueue(context);
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class4, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MashableNewsItem newsItem = mDataset.get(position);
        boolean isFavorite = FavoriteUtil.isFavorite(mContext, newsItem);

        setFavoriteButtonColors(holder.mItemFavoriteButton, isFavorite);

        holder.mItemTitleView.setText(newsItem.title);
        holder.mItemAuthorView.setText(newsItem.author);

        ImageRequest request = new ImageRequest(newsItem.image, new Response.Listener<Bitmap>() {
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

        holder.mItemFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isFavorite = FavoriteUtil.isFavorite(mContext, newsItem);

                if (isFavorite) {
                    FavoriteUtil.removeFavorite(mContext, newsItem);
                } else {
                    FavoriteUtil.addFavorite(mContext, newsItem);
                }

                setFavoriteButtonColors(holder.mItemFavoriteButton, !isFavorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void setFavoriteButtonColors(Button favoriteButton, boolean isFavorite) {
        if (isFavorite) {
            favoriteButton.setBackgroundColor(Color.parseColor("#FF6600"));
            favoriteButton.setTextColor(Color.WHITE);
        } else {
            favoriteButton.setBackgroundColor(Color.WHITE);
            favoriteButton.setTextColor(Color.BLACK);
        }
    }
}
