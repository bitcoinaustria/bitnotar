package at.bitcoinaustria.bitnotar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.io.InputSupplier;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
    private ListView myList;
    private NotaryItem lastItem;
    private ArrayAdapter<String> adapter;

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

                final Uri uri = (Uri) extras.getParcelable(Intent.EXTRA_STREAM);
                final String name = Iterables.getLast(Splitter.on("/").split(uri.toString()));


                //noinspection unchecked
                new Notary() {
                    @Override
                    protected void onPostExecute(Intent intent) {
                        String hash = intent.getStringExtra(State.HASH_PARAM);
                        NotaryItem item = new NotaryItem(name, hash);
                        item.setStatus(ItemStatus.INIT);
                        lastItem = item;
                        State.global.add(item);
                        adapter.notifyDataSetChanged();
                        startActivityForResult(intent, WALLET_REQUEST_CODE);
                    }
                }.execute(new InputSupplier<InputStream>() {
                    @Override
                    public InputStream getInput() throws IOException {
                        return getContentResolver().openInputStream(uri);
                    }
                });


            } else if (extras.containsKey(Intent.EXTRA_TEXT)) {
                //
            }
        }


        myList = (ListView) findViewById(R.id.list);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailIntent = new Intent(BitnotarActivity.this, BitDocumentDetailActivity.class);
                detailIntent.putExtra("notaryItem", State.global.items.get(position));
                startActivity(detailIntent);
            }
        });

        List<String> itemDisplay = Lists.transform(State.global.items, new Function<NotaryItem, String>() {
            @Nullable
            @Override
            public String apply(NotaryItem input) {
                return input.getName();
            }
        });

        adapter = new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1
                , itemDisplay);

        myList.setAdapter(adapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == WALLET_REQUEST_CODE) {
            if (data != null) {
                String txHash = data.getStringExtra("transaction_hash");
                lastItem.setStatus(ItemStatus.PAID);
                lastItem.setTxHash(txHash);

                //todo modify new list item
            }else{
                lastItem.setStatus(ItemStatus.NOT_PAID);
            }
            adapter.notifyDataSetChanged();
        }
    }

}
