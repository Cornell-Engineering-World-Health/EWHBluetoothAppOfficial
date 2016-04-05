package ewh.ewhbluetoothapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Lillyan on 4/5/16.
 */
public class CustomListAdaptor extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] wellMetric;
    private final Integer[] value;

    public CustomListAdaptor(Activity context, String[] wellMetric, Integer[] value) {
        super(context, R.layout.list_row, wellMetric);

        this.context = context;
        this.wellMetric = wellMetric;
        this.value = value;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_row, null, true);

        TextView metricTitle = (TextView) rowView.findViewById(R.id.metricName);
        TextView metricVal = (TextView) rowView.findViewById(R.id.metricValue);

        metricTitle.setText(wellMetric[position]);
        metricVal.setText(value[position]);
        return rowView;

    }
}
