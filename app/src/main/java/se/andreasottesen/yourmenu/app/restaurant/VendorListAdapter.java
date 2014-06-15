package se.andreasottesen.yourmenu.app.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import se.andreasottesen.yourmenu.app.R;

/**
 * Created by Andreas on 2014-06-14.
 */
public class VendorListAdapter extends ArrayAdapter<Vendor>{
    private Context context;
    private List<Vendor> vendors;
    private static final int defaultLayout = R.layout.fragment_item_list_item;

    public VendorListAdapter(Context context, List<Vendor> vendors){
        super(context, defaultLayout);

        this.context=context;
        this.vendors=vendors;
    }

    @Override
    public int getCount() {
        if (vendors != null){
            return vendors.size();
        }
        return 0;
    }

    @Override
    public Vendor getItem(int position) {
        if (vendors != null){
            return vendors.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (vendors != null){
            return vendors.get(position).hashCode();
        }

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(defaultLayout, null);
        }

        Vendor currentVendor = vendors.get(position);

        TextView textView = (TextView) view.findViewById(R.id.txtItemName);
        textView.setText(currentVendor.name);

        textView = (TextView) view.findViewById(R.id.txtItemDescription);
        textView.setText(currentVendor.description);

        return view;
    }
}
