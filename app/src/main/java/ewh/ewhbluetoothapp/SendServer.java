package ewh.ewhbluetoothapp;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    String strValue = "{\"TEMPERATURE\":\"20\"," +
                        "\"PH\":\"30\"," +
                        "\"TURBIDITY\":\"40\"," +
                        "\"CONDUCTIVITY\":\"50\"," +
                        "\"USAGE\":\"60\"}";

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
        //strValue = value.getText().toString();

        String stringToReverse = "hithere";

        URL url = null;
        try {
            url = new URL("http://requestb.in/133sa1w1");
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                connection.getOutputStream());
        out.write("string=" + stringToReverse);
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));
        String decodedString;
        while ((decodedString = in.readLine()) != null) {
            System.out.println(decodedString);
        }
        in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        JSONProcessor wellData = null;
        try {
            wellData = new JSONProcessor(strValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        new SendToServer().execute(String.valueOf(wellData));
        **/
    }

    class SendToServer extends AsyncTask<String, String, String> {

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];

            System.out.println(JsonDATA);

            // Create URL object for server connection
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                // Open URL connection
                URL url = new URL("http://requestb.in/x3f01gx3");
                urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.setDoOutput(true);

                // Set headers and method
                //urlConnection.setRequestMethod("POST");
                //urlConnection.setRequestProperty("TO SET", "TO SET");

                urlConnection.setDoOutput( true );
                urlConnection.setInstanceFollowRedirects( false );
                urlConnection.setRequestMethod( "POST" );
                urlConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setRequestProperty( "charset", "utf-8");
                //urlConnection.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                urlConnection.setUseCaches( false );

                // Write json data
            //   Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            //    writer.write("poisdjf");

                try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                    wr.writeChars("ljsdfksdfjlsdkj");
                }
                catch (Exception e)
                {
                    System.out.println("SENDING REQUEST FAILED");
                }

                System.out.println("SENT TO SERVER");

            //    writer.close();

                /*
                // Input Stream
                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                /*
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
                **/

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
