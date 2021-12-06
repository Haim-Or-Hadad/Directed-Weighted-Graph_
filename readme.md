#  **Exercise 2**
## **Directed Weighted Graph**
### Introduction:
This project is dedicated to planning and implementaion of Directed Weight Graps with data sturcture. <br/> 
Graph is a structure with sets of nodes and edges. when the nodes are objects on the space and the edges <br/>
are connect between them .<br/>
In this task we need to get files that contains list of vertexs and edges and firstly create objects and structure<br/> 
for the them and for the graph that will describe them together.
Then we should use in our graph and run <br/>
algoritems on him.<br/>
In addition the project includes a GUI interface that draws graph on the screen and do some methods on him.
## api(packeage)
### geo_location
geo_location this class that implements GeoLocation interface , This interface represents a geo location <x,y,z>.
Methods       | Performs
------------- | -------------
x             | returns the value of x 
y             | returns the value of y
z             | returns the value of z
geo_location  | constructor that get x,y,z and build geo_location
geo_location  | constructor that get GeoLocation and build new geo_location
distance      | get GeoLocation and calculate the distance from this.geo_location <br/>

**significance: A Node class will use it**
### Node
Node this class that implements the NodeData interface , This interface represents the set of operations applicable on a <br/>
 node (vertex) in a (directional) weighted graph.
 ***new fields:***
- private int id - the key that will be useful for the structure of graph.
- private double weight- the weight of the node for the algoritem.
- private String Info.
- private int Tag- useful for the algoritems.
- private HashMap<Integer,Double> min_dist_from-the minimun distance from any ather vertex(algoritem). <br/>

 Methods       | Performs
--------------------------|-----------------------------------------
Node(int id , GeoLocation coord)| counstructor of new Node.
get_min_dist_size()       |-----------------
min_update                |-----------------
getKey()                  | Returns the key (id) associated with this node.
getLocation()             | Returns the location of this node, if none return null.
setLocation(GeoLocation p)| new location  (position) of this node.
getWeight()               | Returns the weight associated with this node.
setWeight(double w)       | the new weight
getInfo()                 | Returns the remark (meta data) associated with this node.
setInfo(String s)         | Allows changing the remark (meta data) associated with this node.
getTag()                  | Temporal data (aka color: e,g, white, gray, black) - for algoritems.
setTag(int t)             | new value of the tag  <br/>

**significance: This class represents a point in space and is useful for all classes and algorithms in the project**
### Edge
Edge this class that implements the EdgeData interface ,This interface represents the set of operations applicable on a 
directional edge(src,dest) in a (directional) weighted graph. <br/>
***new fields:*** <br/>
- private int src- the source of the edge.
- private int dest - the destination of the edge.
- private double weight- the weight of the edge.
- private int Tag- tag for algoritems.
- private String Info <br/>

 Methods       | Performs
--------------------------|-----------------------------------------
Edge(int src ,int dest,double weight)| counstructor of new Edge
getSrc                               | The id of the source node of this edge.
getDest()                            | The id of the destination node of this edge
getWeight()                          | return the weight of this edge (positive value).
getInfo()                            | Returns the remark (meta data) associated with this edge.
setInfo(String s)                    | Allows changing the remark (meta data) associated with this edge.
getTag()                             | can be used be algorithms 
setTag(int t)                        | This method allows setting the "tag" value for temporal marking an edge - common  <br/>

**significance: This class represents edge between two nodes and is useful for all classes and algorithms in the project**
### Graph
Graph this class that build the new graph . By use in nodes and edges we build a new structure that represent the new graph. <br/>
The class implement the interface **Directed Weighted Graph**, This interface represents a Directional Weighted Graph <br/>
The interface has a road-system or communication network in mind - and should support a large number of nodes . our implemention <br/>
based on an hashmapes and this an efficient compact representation. <br/>
***New Fields*** <br/>
- private HashMap<Integer, NodeData> nodes - 
- private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
- private int numOfNodes=0;
- private int numOfEdges=0;
- private int MC=0

