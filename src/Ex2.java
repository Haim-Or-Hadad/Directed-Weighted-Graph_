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
//        alg.getGraph().removeEdge(1,0);// tests for isconnected
//        //alg.getGraph().removeEdge(16,0);
//        alg.getGraph().removeEdge(0,1);
//        alg.getGraph().removeEdge(0,16);
        GUI gui = new GUI(alg.getGraph());
        JFrame jf = new JFrame();
        jf.setTitle("Graph");
        //jf.setPreferredSize(new Dimension(20, 22));
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        System.out.println(alg.isConnected());
        System.out.println(alg.shortestPathDist(1,4));
        for (int i=0;i<alg.shortestPath(1,4).size();i++) {
            System.out.println(alg.shortestPath(1, 4).get(i).getKey());
        }
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(gui);
    }

    public static void main(String[] args) {
        runGUI("data/myGraph.json");
    }
}