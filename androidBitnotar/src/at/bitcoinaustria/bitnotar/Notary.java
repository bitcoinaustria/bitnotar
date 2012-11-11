package at.bitcoinaustria.bitnotar;

import android.content.ContentResolver;
import android.os.AsyncTask;
import com.google.bitcoin.core.Sha256Hash;
import com.google.common.base.Preconditions;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.InputSupplier;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author apetersson
 */
public class Notary extends AsyncTask<ContentResolver,Long,Sha256Hash> {

/*

    public String createUri(File input) {
        File testFile = new File("testFile.dat");
        ensureFileExists(testFile);
        Sha256Hash


    }
*/

    private void ensureFileExists(File testFile) {
        if (!testFile.exists()) {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(testFile));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected Sha256Hash doInBackground(ContentResolver... params) {

        return Sha256Hash.ZERO_HASH;
/*        int buff = 16384;
        Preconditions.checkPositionIndex(1,params.length);
        final ContentResolver stream = params[0];
        try {
           // RandomAccessFile file = new RandomAccessFile("T:\\someLargeFile.m2v", "r");

            long startTime = System.nanoTime();
            MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

            byte[] buffer = new byte[buff];
            byte[] partialHash = null;

            long read = 0;

            // calculate the hash of the hole file for the test

            ByteStreams.hash(new InputSupplier<InputStream>() {
                @Override
                public InputStream getInput() throws IOException {
                    return stream.;
                }
            }, Hashing.sha256());

            int readBytes; readBytes = stream.read(buffer);
            hashSum.update(buffer);
            long offset = file.length();
            int unitsize;
            while (read < offset) {
                unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
                file.read(buffer, 0, unitsize);

                hashSum.update(buffer, 0, unitsize);

                read += unitsize;
            }

            file.close();
            partialHash = new byte[hashSum.getDigestLength()];
            partialHash = hashSum.digest();

            long endTime = System.nanoTime();


            System.out.println(endTime - startTime);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/
    }
}