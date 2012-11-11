package at.bitcoinaustria.bitnotar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.bitcoinaustria.bitnotar.dummy.DummyContent;



import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class BitDocumentListFragment extends ListFragment {

    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    private Callbacks mCallbacks = sDummyCallbacks;
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private DeliveryBaseAdapter listAdapter;
    
    final List<NotaryItem> items = new ArrayList<NotaryItem>();

    public interface Callbacks {

        public void onItemSelected(String id);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        public void onItemSelected(String id) {
        }
    };

    public BitDocumentListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        NotaryItem testitem=new NotaryItem("Name", "aabbcc44");
        testitem.setId(34L);
        NotaryItem testitem2=new NotaryItem("Name", "aabbcc44");
        testitem2.setId(35L);
        
        items.add(testitem);
        items.add(testitem2);
       // setListAdapter(new ArrayAdapter<DummyContent.DummyItem>(getActivity(), R.layout.simple_list_item_activated_1, R.id.text1, items));
        listAdapter = new DeliveryBaseAdapter(getActivity(), R.layout.simple_list_item_activated_1, R.id.text1, items);
        setListAdapter(listAdapter);
    }
    

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && savedInstanceState
                .containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onItemSelected(items.get(position).getId().toString());
    }
    


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    public void setActivateOnItemClick(boolean activateOnItemClick) {
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    public void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }
    
    
    
    
    private class DeliveryBaseAdapter extends BaseAdapter {

        private final Context context;
        private final int resourceId;
        private final int textViewResourceId;
        private final List<NotaryItem> items;

        private DeliveryBaseAdapter(Context context, int resourceId, int textViewResourceId, List<NotaryItem> items) {
            this.context = context;
            this.resourceId = resourceId;
            this.textViewResourceId = textViewResourceId;
            this.items = new ArrayList<NotaryItem>();

            this.items.addAll(items);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public NotaryItem getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View container;
            if (convertView != null) {
                container = convertView;
            } else {
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                container = inflater.inflate(resourceId, parent, false);
            }

            final TextView text = (TextView) container.findViewById(textViewResourceId);
            final NotaryItem item = getItem(position);
            text.setText(item.getName());
            return container;
        }

        public void clear() {
            items.clear();
        }

        public void addAll(Collection<NotaryItem> collection) {
            items.addAll(collection);
        }

    }
    
    
    
    
    
    
}
