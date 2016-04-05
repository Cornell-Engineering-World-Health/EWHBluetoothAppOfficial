package ewh.ewhbluetoothapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MetricListActivity extends AppCompatActivity {

    private ListView listView2;
    //For bluetooth device list

    ArrayList<String> metricList =new ArrayList<String>();
    ArrayAdapter<String> metricListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_list);

        listView2 = (ListView) findViewById(R.id.listView2);

        metricList.add("Temperature           15 deg C");
        metricList.add("Conductivity          Value 2");
        metricList.add("pH                    13");
        metricList.add("Turbidity             7");

        metricListAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                metricList);

        listView2.setAdapter(metricListAdapter);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = metricListAdapter.getItem(position);
                System.out.println("Item selected: " + itemSelected);

                if (position == 0) {
                    Intent intent = new Intent(MetricListActivity.this, SingleMetricDisplay.class);
                    startActivity(intent);
                }

            }
        });
    }
}
