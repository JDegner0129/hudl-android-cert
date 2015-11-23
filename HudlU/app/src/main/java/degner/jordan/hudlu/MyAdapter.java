package degner.jordan.hudlu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import degner.jordan.hudlu.models.MashableNewsItem;

/**
 * Created by Jordan on 11/15/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<MashableNewsItem> mDataset;
    private OnAdapterInteractionListener mListener;

    public interface OnAdapterInteractionListener {
        void onItemClicked(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mItemTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mItemTextView = (TextView) itemView.findViewById(R.id.item_text_view);
        }
    }

    public MyAdapter(Context context, List<MashableNewsItem> dataset) {
        mListener = (OnAdapterInteractionListener) context;
        mDataset = dataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.mItemTextView.setText(mDataset.get(position).title);

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
