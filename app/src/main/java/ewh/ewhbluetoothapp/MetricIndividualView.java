package ewh.ewhbluetoothapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MetricIndividualView extends AppCompatActivity {

    private TextView description;
    private TextView metricName;
    private TextView metricValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric_individual_view);

        description = (TextView) findViewById(R.id.textView);
        metricName = (TextView) findViewById(R.id.textView2);
        metricValue = (TextView) findViewById(R.id.textView3);

        metricName.setText(MetricListActivity.selectedMetric);
        metricValue.setText(MetricListActivity.selectedValue);
    }
}
