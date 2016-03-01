package ewh.ewhbluetoothapp;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by sonia on 2/23/16.
 *
 * same tutorial as before
 */

//Not use in lillyan's version


public class Connections {

    private static boolean state = false;

    public static boolean blueTooth()
    {
        BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
        if(!bluetooth.isEnabled())
        {
            System.out.println("Bluetooth is Disabled....");
            state = true;
        }
        else if(bluetooth.isEnabled())
        {
            String address = bluetooth.getAddress();
            String name = bluetooth.getName();
            System.out.println(name + " : " + address);
            state = false;
        }
        return state;
    }
}