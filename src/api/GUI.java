package api;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Random;

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
            GeoLocation cord=Nodes.next().getLocation();
            g.fillOval((int)cord.x(),(int)cord.y(),30,40);
            if(alg.edgeIter(Nodes.next().getKey()).hasNext()) {
                Iterator<EdgeData> Edges = alg.edgeIter(Nodes.next().getKey());
                while (Edges.hasNext()) {
                    EdgeData tempE = Edges.next();
                    GeoLocation SrcP = alg.getNode(tempE.getSrc()).getLocation();
                    GeoLocation DestP = alg.getNode(tempE.getDest()).getLocation();
                    g.drawLine((int) SrcP.x(), (int) SrcP.y(), (int) DestP.x(), (int) DestP.y());

                }
            }
        }

    }
}