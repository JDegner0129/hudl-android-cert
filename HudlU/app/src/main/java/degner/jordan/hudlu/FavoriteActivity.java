package degner.jordan.hudlu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import degner.jordan.hudlu.models.Favorite;

/**
 * Created by Jordan on 12/13/15.
 */
public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.OnAdapterInteractionListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<Favorite> dataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        Toolbar toolbar = (Toolbar) findViewById(R.id.favorite_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.favorite_recycler);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        dataset = FavoriteUtil.getAllFavorites(this);

        mAdapter = new FavoriteAdapter(this, dataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClicked(View view, int position) {
        Favorite item = dataset.get(position);
        Uri webUri = Uri.parse(item.getLink());
        Intent intent = new Intent(Intent.ACTION_VIEW, webUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
