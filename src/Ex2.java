import api.GUI;
import api.*;

import javax.swing.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return DirectedWeightedGraph
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {

        DirectedWeightedGraphAlgorithms ans = new GraphAlgo();
        ans.load(json_file);
        return ans.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return DirectedWeightedGraphAlgorithms
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans= new GraphAlgo();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        GUI gui = new GUI(alg);
        JFrame jf = new JFrame();
        jf.setTitle("Graph");
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(gui);
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        runGUI(args[0]);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000F);
    }
}
