package at.bitcoinaustria.bitnotar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class BitDocumentDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitdocument_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(BitDocumentDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(BitDocumentDetailFragment.ARG_ITEM_ID));
            
            BitDocumentDetailFragment fragment = new BitDocumentDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.bitdocument_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, BitDocumentListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
