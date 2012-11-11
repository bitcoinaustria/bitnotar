package at.bitcoinaustria.bitnotar;

import android.os.AsyncTask;
import com.google.bitcoin.core.Sha256Hash;

import java.io.*;
import java.security.MessageDigest;

/**
 * @author apetersson
 */
public class Notary extends AsyncTask<InputStream,Float,Sha256Hash> {

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
    protected Sha256Hash doInBackground(InputStream... params) {
        return Sha256Hash.ZERO_HASH;
      /*  publishProgress();
        int buff = 16384;
        try {
           // RandomAccessFile file = new RandomAccessFile("T:\\someLargeFile.m2v", "r");

            long startTime = System.nanoTime();
            MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

            byte[] buffer = new byte[buff];
            byte[] partialHash = null;

            long read = 0;

            // calculate the hash of the hole file for the test
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

        }*/
}
}