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
 * Created by Andreas on 2014-05-31.
 */
public class ItemListAdapter extends ArrayAdapter<ItemContent.Item> {
    private Context context;
    private List<ItemContent.Item> items;
    private static final int defaultLayout = R.layout.fragment_item_list_item;

    public ItemListAdapter(Context context, List<ItemContent.Item> items){
        super(context, defaultLayout);
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        if (items != null){
            return items.size();
        }
        return 0;
    }

    @Override
    public ItemContent.Item getItem(int position) {
        if (items != null){
            return items.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (items != null){
            return items.get(position).hashCode();
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

        ItemContent.Item currentItem = items.get(position);

        TextView textView = (TextView) view.findViewById(R.id.txtItemName);
        textView.setText(currentItem.name);

        textView = (TextView) view.findViewById(R.id.txtItemDescription);
        textView.setText(currentItem.description);

        textView = (TextView) view.findViewById(R.id.txtRestaurantName);
        textView.setText(currentItem.restaurant);

        textView = (TextView) view.findViewById(R.id.txtPrice);
        textView.setText(Float.toString(currentItem.price));

        return view;
    }
}
