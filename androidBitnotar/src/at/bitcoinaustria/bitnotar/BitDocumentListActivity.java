package at.bitcoinaustria.bitnotar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.common.io.InputSupplier;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BitDocumentListActivity extends FragmentActivity
        implements BitDocumentListFragment.Callbacks {

    private boolean mTwoPane;
    public static final int REQUEST_CODE = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitdocument_list);

        
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        String action = intent.getAction();

        // if this is from the share menu
        if (Intent.ACTION_SEND.equals(action))
        {
            if (extras.containsKey(Intent.EXTRA_STREAM))
            {
                try
                {
                    //noinspection unchecked
                    new Notary(){
                        @Override
                        protected void onPostExecute(Intent intent) {
                            //todo create new List item for new Signer
                            startActivityForResult(intent, REQUEST_CODE);
                        }
                    }.execute(new InputSupplier<InputStream>() {
                        @Override
                        public InputStream getInput() throws IOException {
                            Uri uri = (Uri) extras.getParcelable(Intent.EXTRA_STREAM);
                            ContentResolver cr = getContentResolver();
                            InputStream is = cr.openInputStream(uri);
                            return is;
                        }
                    });
                    
                    
                    
                    //Uri uri = (Uri) extras.getParcelable(Intent.EXTRA_STREAM);
                    //File f = new File("" + uri);
                    //f.getName();

                    
                    return;
                } catch (Exception e)
                {
                    Log.e(this.getClass().getName(), e.toString());
                }

            } else if (extras.containsKey(Intent.EXTRA_TEXT))
            {
                //
            }
        }
        
        
        
        
        
        
        if (findViewById(R.id.bitdocument_detail_container) != null) {
            mTwoPane = true;
            ((BitDocumentListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.bitdocument_list))
                    .setActivateOnItemClick(true);
        }
    }

    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(BitDocumentDetailFragment.ARG_ITEM_ID, id);
            BitDocumentDetailFragment fragment = new BitDocumentDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.bitdocument_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, BitDocumentDetailActivity.class);
            detailIntent.putExtra(BitDocumentDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
