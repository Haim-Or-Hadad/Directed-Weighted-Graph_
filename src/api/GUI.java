package api;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.Random;
import java.awt.geom.Line2D;


public class GUI extends JPanel {

    private DirectedWeightedGraph alg;

    public GUI(DirectedWeightedGraph alg){
        this.alg=alg;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Iterator<NodeData>Nodes=alg.nodeIter();
        g.setColor(Color.RED);
        while (Nodes.hasNext()){
            NodeData tempN=Nodes.next();
            GeoLocation cord=tempN.getLocation();
            g.fillOval((int)(cord.x()-5),(int)(cord.y()-5),10,10);
            Iterator<EdgeData> Edges = alg.edgeIter(tempN.getKey());
                while (Edges.hasNext()) {
                    EdgeData tempE = Edges.next();
                    GeoLocation SrcP = alg.getNode(tempE.getSrc()).getLocation();
                    GeoLocation DestP = alg.getNode(tempE.getDest()).getLocation();
//                    Graphics2D g2=(Graphics2D)g;
//                    g2.setStroke(new BasicStroke(10));
//                    g2.draw(new Line2D.Float((int) SrcP.x(), (int) SrcP.y(), (int) DestP.x(), (int) DestP.y()));
                    g.drawLine((int) (SrcP.x()*10), (int) (SrcP.y()*10), (int) (DestP.x()*10), (int) (DestP.y()*10));

                }
            }


    }
}