package api;

import api.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.Iterator;


public class GUI extends JPanel {
    private  DirectedWeightedGraphAlgorithms graphalgo;
    private  DirectedWeightedGraph alg;
    JButton removeButton=new JButton();
    JButton addButton=new JButton();
    private JSpinner spinner;
    private JMenuBar Menu = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem LoadFile= new JMenuItem("Load File");
    SpinnerModel mode1;
    private JButton button1;
    JLabel zipLabel = new JLabel("Node:");



    public GUI(DirectedWeightedGraphAlgorithms alg){
        this.graphalgo=alg;
        this.alg=alg.getGraph();
        Menu();
        createAddButton();
        createRemoveButton();
    }
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
        while (Nodes.hasNext()){

            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
           double factor_x = getWidth()/(maxX-minX)*0.9;
            double factory= getHeight()/(maxY-minY)*0.9;
            Iterator<EdgeData> Edges = alg.edgeIter(tempN.getKey());
            while (Edges.hasNext()) {
                EdgeData tempE = Edges.next();
                GeoLocation SrcP = alg.getNode(tempE.getSrc()).getLocation();
                GeoLocation DestP = alg.getNode(tempE.getDest()).getLocation();
                g.setColor(Color.BLACK);
                g2.draw(new Line2D.Double((SrcP.x()-minX)*factor_x+20,(SrcP.y()-minY)*factory+20,(DestP.x()-minX)*factor_x+20,(DestP.y()-minY)*factory+20));
            }
            g.setColor(Color.BLACK);
            Ellipse2D.Double node = new Ellipse2D.Double((cord.x()-minX)*factor_x+15, (cord.y()-minY)*factory+15, 14, 14);
            g2.fill(node);
        }
        Menu.setLocation(0,0);
        Menu.setSize(28,20);



    }
    private void createSpinner(){
        mode1 = new SpinnerNumberModel(1,0,alg.nodeSize(),1);
        spinner=new JSpinner(mode1);
        spinner.setBounds(0,50,50,50);
        spinner.addChangeListener(e -> {
//             this.setText("Value : " + ((JSpinner)e.getSource()).getValue());
        });
        this.add(spinner);
    }
    private void createRemoveButton(){
        removeButton.setLocation(0,50);
        removeButton.setSize(100,50);
        removeButton.setText("remove");
        this.add(removeButton);
        createSpinner();
        removeButton.addActionListener(e -> {
            int removeNode= Integer.parseInt(spinner.getValue().toString());
            alg.removeNode(removeNode);
        });
    }
    private void createAddButton(){
        addButton.setLocation(0,100);
        addButton.setSize(100,50);
        addButton.setText("Add Node");
        this.add(addButton);
        addButton.addActionListener(e -> alg.addNode(new Node(alg.nodeSize()+1,new geo_location(32.5,32.1,0.0))));
    }

    public void Menu(){
        Menu.setLocation(0,0);
        Menu.setSize(25,25);
        Menu.add(file);
        file.add(LoadFile);
        LoadFile.addActionListener(e -> {
            String s=filechooser();
            if(s!=null) {
                graphalgo.load(s);
                alg = graphalgo.getGraph();
            }

             });
        this.add(Menu);
        this.setVisible(true);


    }
    private String filechooser() {
        JFileChooser fileChooser = new JFileChooser();
        String path="";
        fileChooser.setDialogTitle("Open Json File");
        String yourPath = "D:\\Users\\Ilan\\Ex2_OOP\\data";
        File directory = new File(yourPath);
        fileChooser.setCurrentDirectory(directory);
        FileFilter filter = new FileNameExtensionFilter("Json File", "json");
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
