package api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Iterator;


public class GUI extends JPanel {

    private final DirectedWeightedGraph alg;
    JButton removeButton=new JButton();
    private JSpinner removeSpinner;
    private JMenuItem k = new JMenuItem("1");
    final JPopupMenu menu = new JPopupMenu();

    public GUI(DirectedWeightedGraph alg){
        this.alg=alg;
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
        createRemoveButton();
    }
    private void createRemoveButton(){
        removeButton.setLocation(0,0);
        removeButton.setSize(100,50);
        removeButton.setText("remove");
        this.add(removeButton);
        removeButton.add(k);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                alg.removeNode(4);
            }
        });
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