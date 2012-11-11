package at.bitcoinaustria.bitnotar;

import android.content.Intent;
import android.test.AndroidTestCase;
import android.test.UiThreadTest;
import com.google.common.io.InputSupplier;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

/**
 * @author apetersson
 */
public class NotaryTest extends AndroidTestCase{


    @UiThreadTest
    public void testHashing() throws ExecutionException, InterruptedException {
        Notary notary = new Notary();
        notary.execute(new InputSupplier<InputStream>() {
            @Override
            public InputStream getInput() throws IOException {
                return NotaryTest.class.getResourceAsStream("test.jpg");

            }
        });
        Intent intent = notary.get();
        assertEquals("bitcoin:14pni5ZHty86QP6oFT4vPkp1wMMFBpN1eY?amount=0.01", intent.getData().toString());
    }


}
