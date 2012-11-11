package at.bitcoinaustria.bitnotar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.common.io.InputSupplier;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;

public class BitnotarActivity extends Activity {

    static {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.e("BITNOTAR", "exception", ex);
            }
        });
    }

    public static final int WALLET_REQUEST_CODE = 999;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitnotar);

        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        String action = intent.getAction();

        // if this is from the share menu
        if (Intent.ACTION_SEND.equals(action)) {
            if (extras.containsKey(Intent.EXTRA_STREAM)) {
                //noinspection unchecked
                new Notary() {
                    @Override
                    protected void onPostExecute(Intent intent) {
                        //todo create new List item for new Signer
                        startActivityForResult(intent, WALLET_REQUEST_CODE);
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
            } else if (extras.containsKey(Intent.EXTRA_TEXT)) {
                //
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == WALLET_REQUEST_CODE) {
            if (data != null) {
                String txHash = data.getStringExtra("transaction_hash");
                //todo modify new list item
            }

        }
    }

}
