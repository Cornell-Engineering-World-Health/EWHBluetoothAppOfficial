package ewh.ewhbluetoothapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*
* Screen to send data to server
* Lillyan Pan 4/1/16
*
* */

public class SendServer extends AppCompatActivity {

    Button button;
    TextView value;
    String strValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_server);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        value = (TextView) findViewById(R.id.value1);
        button = (Button) findViewById(R.id.sendData);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendData(view);
            }
        });


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

    public void sendData(View v) {
        strValue = value.getText().toString();
        JSONObject wellData = new JSONObject();

        try {
            wellData.put("value1", strValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (wellData.length() > 0) {
            new SendToServer().execute(String.valueOf(wellData));
        }
    }

    class SendToServer extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];

            // Create URL object for server connection
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                // Open URL connection
                URL url = new URL("http://example.com/");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);

                // Set headers and method
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("TO SET", "TO SET");

                // Write json data
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);

                writer.close();

                // Input Stream
                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                // Get response and convert to string
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    buffer.append(inputLine + "\n");
                    if (buffer.length() == 0) {
                        return null;
                    }
                    JsonResponse = buffer.toString();
                    //Log.i(TAG, JsonResponse);

                    // Response datat
                    return JsonResponse;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        System.out.println("Error in closing stream");
                    }
                }
            }
            return null;
        }
    }
}
