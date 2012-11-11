package at.bitcoinaustria.bitnotar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BitnotarActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitnotar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bitnotar, menu);
        return true;
    }
}
