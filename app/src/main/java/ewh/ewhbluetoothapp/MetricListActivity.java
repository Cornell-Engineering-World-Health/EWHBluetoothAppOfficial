package ewh.ewhbluetoothapp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

// Custom ListView http://www.androidinterview.com/android-custom-listview-with-image-and-text-using-arrayadapter/

//public class MetricListActivity extends AppCompatActivity, ListActivity {
public class MetricListActivity extends AppCompatActivity {

    private ListView listView2;
    //For bluetooth device list

    ArrayList<String> metricList =new ArrayList<String>();
    ArrayAdapter<String> metricListAdapter;

    String[] metricName = {"Temperature", "Conductivity", "pH", "Turbidity", "Usage"};
    // To get metric values from json
    Integer[] metricValue = new Integer[5];

    static String selectedMetric = "";
    static String selectedValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_list);

        listView2 = (ListView) findViewById(R.id.listView2);

        System.out.println("DATA:::::" + MainActivity.recievedMessage);

        JSONProcessor newData = null;

        try {
            System.out.println("in the looppppppp");
            newData = new JSONProcessor(MainActivity.recievedMessage);

            System.out.println("THE TEMPERATURE: " + newData.getMetric(newData.TEMPERATURE));

            metricValue[0] = newData.getMetric(newData.TEMPERATURE);
            metricValue[1] = newData.getMetric(newData.CONDUCTIVITY);
            metricValue[2] = newData.getMetric(newData.PH);
            metricValue[3] = newData.getMetric(newData.TURBIDITY);
            metricValue[4] = newData.getMetric(newData.USAGE);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //CustomListAdaptor adaptor = new CustomListAdaptor(this, metricName, metricValue);
        //System.out.println("ADAPTOR" + adaptor.toString());
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(metricName[0] + " " + metricValue[0]);


//        metricList.add("Temperature           15 deg C");
//        metricList.add("Conductivity          Value 2");
//        metricList.add("pH                    13");
//        metricList.add("Turbidity             7");

        metricListAdapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                temp);

        //metricListAdapter.add("Temperature: 0");

        listView2.setAdapter(metricListAdapter);


        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String itemSelected = metricListAdapter.getItem(position);
                System.out.println("Item selected: " + itemSelected);

                selectedMetric = itemSelected;
                selectedValue = "" + metricValue[position];

                Intent intent = new Intent(MetricListActivity.this, SingleMetricDisplay.class);
                startActivity(intent);
                }
        });
    }
}
