package api;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class GUI extends JPanel {
    private  DirectedWeightedGraphAlgorithms graphalgo;
    private  DirectedWeightedGraph alg;
    private JButton removeButton=new JButton();
    private JButton addButton=new JButton();
    private JSpinner spinner;
    private JSpinner X_spinner,y_spinner;
    private JMenuBar Menu = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenu functions = new JMenu("Functions");
    private JMenuItem Center= new JMenuItem("Center");
    private JMenuItem shortestPathDist= new JMenuItem("shortestPathDist");
    private JMenuItem Tsp= new JMenuItem("TSP");
    private JMenuItem ShortPath= new JMenuItem("ShortPath");
    private JMenuItem LoadFile= new JMenuItem("Load File");
    private JMenuItem SaveFile=new JMenuItem("Save File");
    private SpinnerModel mode1,mode2,mode3;
    private int CenterKey=-1;
    private List<NodeData> tsp_nodes=new LinkedList<>();
    private List<NodeData> short_list=new LinkedList<>();


    /**
     * constructor
     * @param alg - DirectedWeightedGraphAlgorithms that inserted to the gui
     */
    public GUI(DirectedWeightedGraphAlgorithms alg){
        this.graphalgo=alg;
        this.alg=alg.getGraph();
        Menu();
        createAddButton();
        createRemoveButton();
    }

    /**
     * GUI paintComponent draw all the paintComponent.
     * @param g-
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setVisible(false);
        this.setVisible(true);
        Iterator<NodeData>Nodes=alg.nodeIter();
        g.setColor(Color.BLACK);
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        double minX=findMinX();
        double maxX=findMaxX();
        double minY=findMiny();
        double maxY=findMaxy();
        while (Nodes.hasNext()){  //go on all the nodes
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation(); //get the nodes locations
           double factor_x = getWidth()/(maxX-minX)*0.9; //factor for the X coordinates scale.
            double factory= getHeight()/(maxY-minY)*0.9; //factor for the Y coordinates scale.
            Iterator<EdgeData> Edges = alg.edgeIter(tempN.getKey());
            while (Edges.hasNext()) {
                EdgeData tempE = Edges.next();
                GeoLocation SrcP = alg.getNode(tempE.getSrc()).getLocation();
                GeoLocation DestP = alg.getNode(tempE.getDest()).getLocation();
                g.setColor(Color.BLACK);
                g2.draw(new Line2D.Double((SrcP.x()-minX)*factor_x+20,(SrcP.y()-minY)*factory+20,(DestP.x()-minX)*factor_x+20,(DestP.y()-minY)*factory+20)); //draw an edge between 2 nodes according to the scale that determined.
            }
            if(tempN.getKey()==CenterKey){ //if center function is activated change the center node color
                g.setColor(Color.red);
            }
            if (short_list.contains(tempN)){ //change the color for the selected nodes at the shortest path function
                g.setColor(Color.green);
            }
            Ellipse2D.Double node = new Ellipse2D.Double((cord.x()-minX)*factor_x+15, (cord.y()-minY)*factory+15, 14, 14); //draw the nodes
            g2.fill(node);
            String key=""+tempN.getKey(); //draw the node id.
            int x=(int)((cord.x()-minX)*factor_x+15);
            int y=(int)((cord.y()-minY)*factory+12);
            g.setColor(Color.BLUE);
            g2.drawString(key,x,y);
            g.setColor(Color.BLACK);

        }
        Menu.setLocation(0,0);
        Menu.setSize(100,20);



    }

    /**
     * JSpinner for the Remove Node Button.
     * Spinner include all the nodes in the graph.
     */
    private void createSpinner(){
        mode1 = new SpinnerNumberModel(0,0,alg.nodeSize(),1);
        spinner=new JSpinner(mode1);
        spinner.setBounds(0,50,50,50);
        this.add(spinner);
    }

    /**
     * Creation of the remove node Button.
     */
    private void createRemoveButton(){
        removeButton.setLocation(0,50);
        removeButton.setSize(100,50);
        removeButton.setText("Remove node");
        this.add(removeButton);
        createSpinner();
        removeButton.addActionListener(e -> {
            int removeNode= Integer.parseInt(spinner.getValue().toString());
            if(alg.removeNode(removeNode)==null){ //if a node that dont exist selected throw an error.
                JOptionPane.showMessageDialog(this,
                        "This graph don't contain this node. \n enter diffrent node number.",
                        "Error", JOptionPane.ERROR_MESSAGE);

            }
            alg.removeNode(removeNode);


        });
    }

    /**
     * Create 2 Jspinners to select the desired X and Y.
     * The values available in the Jspinners are calculated according to the min and max values of X and Y coordinates
     */
    private void createSpinner1_2() {
        mode2 = new SpinnerNumberModel(findMinX(), findMinX(),findMaxX(), 0.001);
        X_spinner = new JSpinner(mode2);
        X_spinner.setBounds(0, 100, 100, 100);
        double miny=findMiny();
        double maxy=findMaxy();
        mode3 = new SpinnerNumberModel(32.10101,32.10101,32.10781, 0.001);
        y_spinner = new JSpinner(mode3);
        y_spinner.setBounds(0, 100, 100, 100);
        this.add(X_spinner);
        this.add(y_spinner);
    }

    /**
     * Creation of the Add button that create the nodes according to the values selected in the 2 spinners above.
     */

    private void createAddButton(){
        addButton.setLocation(0,100);
        addButton.setSize(100,50);
        addButton.setText("Add Node");
        this.add(addButton);
        createSpinner1_2();
        addButton.addActionListener(e -> {
            double add_x= Double.parseDouble(X_spinner.getValue().toString());
            double add_y= Double.parseDouble(y_spinner.getValue().toString());
            alg.addNode(new Node(alg.nodeSize()+1,new geo_location(add_x,add_y,0.0)));
            this.remove(spinner);
            mode1 = new SpinnerNumberModel(0,0,alg.nodeSize(),1);
            spinner=new JSpinner(mode1);
            this.add(spinner);
        });
    }

    /**
     * Create the JMenuBar with 2 JMenu items: File and Functions
     * Plus the implementation of the load file and save file JMenu Items.
     */
    private void Menu(){
        Menu.setLocation(0,0);
        Menu.setSize(25,25);
        Menu.add(file);
        file.add(LoadFile);
        file.add(SaveFile);
        functions();
        LoadFile.addActionListener(e -> {
            String s=filechooser();
            if(s!=null) {
                if(!graphalgo.load(s)){
                    JOptionPane.showMessageDialog(this, "The Json file selected is damaged or not build accordingly. \n Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    graphalgo.load(s);
                    CenterKey = -1;
                    alg = graphalgo.getGraph();
                }
            }
             });
        SaveFile.addActionListener(e -> {
            String filename=JOptionPane.showInputDialog("Save as:");
            graphalgo.save(filename);
        });
        this.add(Menu);
        this.setVisible(true);


    }

    /**
     * Create the Functions JMenu.
     */
    private void functions() {
        Menu.add(functions);
        center();
        ShortPath();
        shortestPathDist();
        TSP();
    }

    /**
     * Creation of the center JMenu Item that find the center point of the graph.
     */
    private void center(){
        functions.add(Center);
        Center.addActionListener(e -> {
            if (graphalgo.center() == null)
                JOptionPane.showMessageDialog(this, "Graph not connected. \n center function available only for connected graphs.", "Error", JOptionPane.ERROR_MESSAGE);
            else
                CenterKey = graphalgo.center().getKey();
        });
    }

    /**
     * Creation of the ShortPath JMenu Item that find the Shortest Path in the graph for the source and destination inserted.
     */
    private  void ShortPath(){
        functions.add(ShortPath);
        ShortPath.addActionListener(e -> {
            int source=Integer.parseInt(JOptionPane.showInputDialog("Insert source node"));
            int destination=Integer.parseInt(JOptionPane.showInputDialog("Insert destination node"));
            if (graphalgo.getGraph().getNode(source)==null || graphalgo.getGraph().getNode(destination)==null)
                JOptionPane.showMessageDialog(this,
                        "This graph don't contain this node. \n enter diffrent node number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            else
                short_list=graphalgo.shortestPath(source,destination);
            StringBuilder nodesString = new StringBuilder();
            for (int i=0;i<short_list.size();i++){
                if (i==short_list.size()-1)
                    nodesString.append(short_list.get(i).getKey());
                else
                    nodesString.append(short_list.get(i).getKey()).append("--->");
            }
                JOptionPane.showMessageDialog(this,
                        " \n" +nodesString ,
                        "", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Creation of the shortestPathDist JMenu Item that show the user the shortest path distance for the 2 nodes the user insert.
     */
    private void shortestPathDist(){
        functions.add(shortestPathDist);
        shortestPathDist.addActionListener(e -> {
            int source=Integer.parseInt(JOptionPane.showInputDialog("Insert source node"));
            int destination=Integer.parseInt(JOptionPane.showInputDialog("Insert destination node"));
            if(graphalgo.getGraph().getNode(source)==null || graphalgo.getGraph().getNode(destination)==null)
                JOptionPane.showMessageDialog(this,
                        "This graph don't contain this node. \n enter diffrent node number.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            else {
                short_list = graphalgo.shortestPath(source, destination);
                JOptionPane.showMessageDialog(this
                        , "The shortest path is:" + graphalgo.shortestPathDist(source, destination) +
                                "\n ", "", JOptionPane.INFORMATION_MESSAGE);
            }

        });

    }

    /**
     * creation on the TSP JMenu Item that return the best way to travel with the points the user insert.
     */
    private void TSP(){
        functions.add(Tsp);
        Tsp.addActionListener(e -> {
            StringBuilder nodesString= new StringBuilder();
            nodesString = new StringBuilder(JOptionPane.showInputDialog("Enter the nodes you want to activate TSP on. \n please type ',' between every node"));
            String[] string_to_nodes= nodesString.toString().split(",");
            for (String string_to_node : string_to_nodes) {
                NodeData curr_Node = graphalgo.getGraph().getNode(Integer.parseInt(string_to_node));
                if (curr_Node == null)
                    JOptionPane.showMessageDialog(this,
                            "This graph don't contain this node. \n enter diffrent node number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                else
                    tsp_nodes.add(curr_Node);
            }
            tsp_nodes=graphalgo.tsp(tsp_nodes);
             nodesString = new StringBuilder();
            for (int i=0;i<tsp_nodes.size();i++){
                if (i==tsp_nodes.size()-1)
                    nodesString.append(tsp_nodes.get(i).getKey());
                else
                    nodesString.append(tsp_nodes.get(i).getKey()).append("--->");
            }
            JOptionPane.showMessageDialog(this,
                    "The best road to take is. \n" +nodesString ,
                    "Tsp  Road", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    /**
     * Implementation of the file chooser that opens when selecting LoadFile Jmenu item
     * @return String of the path of the graph the user want to load
     */
    private String filechooser() {
        String userhome = System.getProperty("user.home");
        JFileChooser fileChooser = new JFileChooser(userhome+"/Desktop"); //default opening at the user desktop.
        String path="";
        fileChooser.setDialogTitle("Open Json File");
        FileFilter filter = new FileNameExtensionFilter("Json File", "json");//filter that allow to select only Json File.
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
             path = selectedFile.getAbsolutePath();
        }
        else if(JFileChooser.CANCEL_OPTION == result)
            return null;

        return path;
    }

    /**
     * find the min X value of all the nodes in the graph for setting bounding box of the graph.
     * @return min X value
     */
    private double findMinX(){
        double min=Integer.MAX_VALUE;
        Iterator<NodeData>Nodes=alg.nodeIter();
        while(Nodes.hasNext()){
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
            if(cord.x()<min)min=cord.x();
        }
        return min;
    }

    /**
     * find the max X value of all the nodes in the graph for setting bounding box of the graph.
     * @return max X value
     */
    private double findMaxX(){
        double max=Integer.MIN_VALUE;
        Iterator<NodeData>Nodes=alg.nodeIter();
        while(Nodes.hasNext()){
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
            if(cord.x()>max)max=cord.x();
        }
        return max;
    }

    /**
     * find the min Y value of all the nodes in the graph for setting bounding box of the graph.
     * @return min Y value
     */
    private double findMiny(){
        double min=Integer.MAX_VALUE;
        Iterator<NodeData>Nodes=alg.nodeIter();
        while(Nodes.hasNext()){
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
            if(cord.y()<min)min=cord.y();
        }
        return min;
    }

    /**
     * find the max Y value of all the nodes in the graph for setting bounding box of the graph.
     * @return max Y value
     */
    private double findMaxy(){
        double max=Integer.MIN_VALUE;
        Iterator<NodeData>Nodes=alg.nodeIter();
        while(Nodes.hasNext()){
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
            if(cord.y()>max)max=cord.y();
        }
        return max;
    }
}
