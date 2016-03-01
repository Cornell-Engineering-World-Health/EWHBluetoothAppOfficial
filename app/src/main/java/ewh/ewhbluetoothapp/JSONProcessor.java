package ewh.ewhbluetoothapp;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The purpose of this class is to contain the methods necessary for doing backend tasks, such
 * as processing the JSON file into representable data.
 *
 * Created by sonia on 2/9/16.
 */
public class JSONProcessor {

    private final String TEMPERATURE = "TEMPERATURE";
    private final String QUALITY = "QUALITY";

    JSONObject currentJSON;

    //Todo: should probably figure out how to parse JSON files within this class
    public JSONProcessor(JSONObject JSONFile) {
        currentJSON = JSONFile;
    }

    public String getMetric(String metricName) throws JSONException {
        switch (metricName) {
            case TEMPERATURE:
                return currentJSON.getString(TEMPERATURE);
            case QUALITY:
                return currentJSON.getString(QUALITY);
            default:
                return "";
        }
    }
}
