import com.google.gson.*;
import api.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DirectedWeightedGraph.class, new JsonToGraph())
                .create();
        try {
            FileReader json = new FileReader(json_file);
            DirectedWeightedGraph ans = gson.fromJson(json, DirectedWeightedGraph.class);
            return ans;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) {
        DirectedWeightedGraph check=  getGrapg("data/G1.json");
        check.getNode(4);
    }
}