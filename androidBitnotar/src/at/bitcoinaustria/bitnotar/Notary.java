package at.bitcoinaustria.bitnotar;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.google.bitcoin.core.Address;
import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.CountingInputStream;
import com.google.common.io.InputSupplier;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * @author apetersson
 */
public class Notary extends AsyncTask<InputSupplier<InputStream>, Long, Intent> {

    private static final NetworkParameters NETWORK = NetworkParameters.prodNet();

    @Override
    protected Intent doInBackground(final InputSupplier<InputStream>... params) {
        Preconditions.checkPositionIndex(1, params.length);
        final InputSupplier<CountingInputStream> inputSupplier = new InputSupplier<CountingInputStream>() {
            @Override
            public CountingInputStream getInput() throws IOException {
                return new CountingInputStream(params[0].getInput());
            }
        };
        try {
            HashCode hash = ByteStreams.hash(inputSupplier, Hashing.sha256());
            ECKey privateKey = new ECKey(new BigInteger(hash.asBytes()));
            Address address = privateKey.toAddress(NETWORK);
            String uri = "bitcoin:" + address;
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            Log.i("BITNOTAR", "forwarding to bitcoin uri: " + uri);
            return i;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}