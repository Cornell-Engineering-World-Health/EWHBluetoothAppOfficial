package ewh.ewhbluetoothapp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The purpose of this class is to contain the methods necessary for doing backend tasks, such
 * as processing the JSON file into representable data.
 *
 * Created by sonia on 2/9/16.
 */
public class JSONProcessor {

    private final String TEMPERATURE = "TEMPERATURE";
    private final String PH = "PH";
    private final String TURBIDITY = "TURBIDITY";
    private final String USAGE = "USAGE";
    private final String CONDUCTIVITY = "CONDUCTIVITY";


    JSONObject currentJSON;

    //Todo: should probably figure out how to parse JSON files within this class
    public JSONProcessor(String JSONFile) throws ParseException {

        JSONParser parser = new JSONParser();
        currentJSON = (JSONObject) parser.parse(JSONFile);
    }

    public String getMetric(String metricName) {
        switch (metricName) {
            case TEMPERATURE:
                return (String) currentJSON.get(TEMPERATURE);
            case PH:
                return (String) currentJSON.get(PH);
            case TURBIDITY:
                return (String) currentJSON.get(TURBIDITY);
            case USAGE:
                return (String) currentJSON.get(USAGE);
            case CONDUCTIVITY:
                return (String) currentJSON.get(CONDUCTIVITY);
            default:
                return "";
        }
    }
}
