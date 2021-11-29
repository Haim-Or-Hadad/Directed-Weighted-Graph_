import com.google.gson.*;
import api.*;

import javax.swing.*;
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
        DirectedWeightedGraph alg = getGrapg(json_file);
        GUI gui = new GUI(alg);
        JFrame jf = new JFrame("Graph");
        jf.setSize(1000,600);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(gui);
    }

    public static void main(String[] args) {
        runGUI("data/G1.json");
    }

}