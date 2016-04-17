package ewh.ewhbluetoothapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class SingleMetricDisplay extends AppCompatActivity {

    private TextView description;
    private TextView metricName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_metric_display);

        description = (TextView) findViewById(R.id.textView);
        metricName = (TextView) findViewById(R.id.textView2);

        System.out.println("SELECTED METRIC VALUE " + MetricListActivity.selectedMetric);

        metricName.setText(MetricListActivity.selectedMetric);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        **/
    }

}
