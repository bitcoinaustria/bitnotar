package at.bitcoinaustria.bitnotar;

import android.app.Application;
import com.google.bitcoin.core.ECKey;
import com.google.bitcoin.core.NetworkParameters;
import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @author apetersson
 */
public class State extends Application {
    public static final String HASH_PARAM = "hash";
    public static State global;

    public ArrayList<NotaryItem> items = Lists.newArrayList();

    public State() {
        global = this;
        items.add(new NotaryItem("test1", newAddress()));
        items.add(new NotaryItem("test2", newAddress()));
        items.add(new NotaryItem("test3", newAddress()));
    }

    private String newAddress() {
        return new ECKey().toAddress(NetworkParameters.prodNet()).toString();
    }


    public void add(NotaryItem notaryItem) {
        items.add(notaryItem);
    }
}
