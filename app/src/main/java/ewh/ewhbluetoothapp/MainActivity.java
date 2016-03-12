package ewh.ewhbluetoothapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Set;

/**
 * created based on http://stackoverflow.com/questions/10795424/how-to-get-the-bluetooth-devices-as-a-list tutorial
 */

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    //For bluetooth device list
    ArrayList<String> deviceList = new ArrayList<String>();
    private BluetoothAdapter adapter;
    private static final UUID MY_UUID = UUID.fromString("0000110E-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listView);
        adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.startDiscovery();

//      Facilitate communication between components
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);                                    // mReceiver will be called with any broadcast Intent that matches filter
                                                                                // (bluetooth device found)
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

//  Component that responds to system-wide broadcast announcements (bluetooth device found)
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {                 // BR only valid for duration of onReceive call; modifies current result
            String action = intent.getAction();                                 // Describe general way info in intent should be handled
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {                  // Broadcast Action: Remote device discovered
                BluetoothDevice device = intent                                 // contains the BluetoothDevice that the intent applies to
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                deviceList.add(device.getName() + "\n" + device.getAddress());
                Log.i("BT", device.getName() + "\n" + device.getAddress());     // Report device found success; posts information to the log
                listView.setAdapter(new ArrayAdapter<String>(context,           // Sets the data behind listView
                        android.R.layout.simple_list_item_1, deviceList));      // android.R.layout.simple_list_item_1: built-in XML layout document


                //Opening a bluetooth socket
                BluetoothSocket socket = null;
                try {
                    socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                } catch (IOException e) {
                    System.out.println("UNABLE TO CREATE BLUETOOTH SOCKET FROM DEVICE: " + device.getName());
                    e.printStackTrace();
                }
                try {
                    socket.connect();

                    InputStream in = null;
                    OutputStream out = null;

                    try{
                        in = socket.getInputStream();
                        out = socket.getOutputStream();

                        //Trying to use the input stream
                        byte[] buffer = new byte[1024];
                        int bytes;

                        while(true)
                        {
                            try {
                                // Read from the InputStream
                                bytes = in.read(buffer);
                                // Send the obtained bytes to the UI activity
                                Handler mHandler = new Handler();
                                int MESSAGE_READ = 1; //NEED TO FIGURE OUT WHAT THIS VALUE SHOULD BE
                                Message newMessage = mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer);
                                System.out.println(newMessage.toString());

                            } catch (IOException e) {
                                break;
                            }
                        }

                        socket.close();

                    } catch (IOException e) {
                        System.out.println("UNABLE TO CREATE STREAMS FROM SOCKET");
                    }


                } catch (IOException e) {
                    System.out.println("UNABLE TO CONNECT BLUETOOTH SOCKET FROM DEVICE: " + device.getName());
                    e.printStackTrace();
                }

            }
        }
    };
}