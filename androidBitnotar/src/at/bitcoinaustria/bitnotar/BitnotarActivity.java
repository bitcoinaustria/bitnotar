package at.bitcoinaustria.bitnotar;

import java.io.IOException;
import java.io.InputStream;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import com.google.common.io.InputSupplier;

public class BitnotarActivity extends Activity {

    public static final int REQUEST_CODE = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitnotar);


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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode == REQUEST_CODE){
           String txHash = data.getStringExtra("transaction_hash");
           //todo modify new list item
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bitnotar, menu);
        return true;
    }
}
