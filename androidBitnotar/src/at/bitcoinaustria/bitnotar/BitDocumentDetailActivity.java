package at.bitcoinaustria.bitnotar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class BitDocumentDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitdocument_detail);
        Intent orig = getIntent();
        final NotaryItem notaryItem = (NotaryItem) orig.getSerializableExtra("notaryItem");
        TextView id = (TextView) findViewById(R.id.name);
        TextView txhash = (TextView) findViewById(R.id.txhash);
        TextView filehash = (TextView) findViewById(R.id.filehash);
        id .setText(notaryItem.getName());
        txhash.setText(notaryItem.getTxHash());
        filehash.setText(notaryItem.getAddress());

        Button check = (Button) findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String harald = "http://10.20.30.140:5000/verify/"+notaryItem.getAddress();
                Intent i = new Intent();
                i.setData(Uri.parse(harald));
                startActivity(i);
            }
        });
    }

}
