package api;
import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonToGraph implements JsonDeserializer<DirectedWeightedGraph> {

    @Override
    public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonfile = jsonElement.getAsJsonObject();
        DirectedWeightedGraph graph = new Graph();
        JsonArray nodesArray = jsonfile.get("Nodes").getAsJsonArray();
        for (int i = 0; i < nodesArray.size(); i++) {
            JsonObject jsonObjectNode = nodesArray.get(i).getAsJsonObject();
            JsonElement location = jsonObjectNode.get("pos");
            String s = location.getAsString();
            String[] XYZ = s.split(",");
            double x = Double.parseDouble(XYZ[0]);
            double y = Double.parseDouble(XYZ[1]);
            double z = Double.parseDouble(XYZ[2]);
            GeoLocation pos = new geo_location(x, y, z);
            int key = jsonObjectNode.get("id").getAsInt();
            NodeData n = new Node(key, pos);
            graph.addNode(n);
        }

        JsonArray edgesArray = jsonfile.get("Edges").getAsJsonArray();
        for (int i = 0; i < edgesArray.size(); i++) {
            JsonObject jsonObjectEdge = edgesArray.get(i).getAsJsonObject();
            int src = jsonObjectEdge.get("src").getAsInt();
            int dest = jsonObjectEdge.get("dest").getAsInt();
            double w = jsonObjectEdge.get("w").getAsDouble();
            graph.connect(src,dest,w);
        }
        return graph;
    }
}
