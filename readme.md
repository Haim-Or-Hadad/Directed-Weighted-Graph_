#  **Exercise 2**
## **Directed Weighted Graph**
### Introduction:
This project is dedicated for planning and implement of a Directed Weight Graphs with data sturctures. <br/> 
Graph is a structure with sets of nodes and edges. when the nodes are objects on the space and the edges <br/>
are connecting between them .<br/>
In this task we need to get files that contains list of vertexs and edges and firstly create objects and structure<br/> 
for them and for the graph that will present them together.
Then we use the graph for testing and running diffrent algorithms . <br/>
In addition the project includes a GUI interface that draws graph on the screen and run diffrent functions on him.<br/>

## UML
UML is a general-purpose, developmental, modeling language in the field of software<br/>
engineering that is intended to provide a standard way to visualize the design of a system.<br/>
In this UML we are planning how the project will look like , and 
<img src="https://user-images.githubusercontent.com/93033782/145278744-43371902-53c7-4800-93b2-0d3957f1fc0d.jpeg" width="600">




## api(packeage)
### JsonToGraph
JsonToGraph  deserialize the graph json file and create a new graph (constructor) , using gson library functions.
This function run complexity time O(e)/O(n). |E|=e(edges) , |V|=n(nodes) , the json file include edges and nodes and the function <br/>
create a graph running on each of them separately. 
This function complexity time is O(e) if we get more edges then nodes. 
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
- private HashMap<Integer, NodeData> nodes - data structures that save the nodes , key 1 is node 1.
- private HashMap<Integer, HashMap<Integer, EdgeData>> edges - data structure that save for any node is edges .
- private int numOfNodes - num of the nodes in the graph.
- private int numOfEdges - num of the edge in the graph.
- private int MC - changes in the graph . </br>

**we add to the table a complexity time of graph methods **  <br/>

 Methods       | Performs | Complexity
--------------------------|-----------------------------------------|---------
Graph(DirectedWeightedGraph G) | constructor of new graph | |V|=n(nodes),|E|=e(edges) -->O(n*e)-->O(n^2) (1)
Edge(int src ,int dest,double weight)| counstructor of new Edge |
getSrc                               | The id of the source node of this edge. |
getDest()                            | The id of the destination node of this edge |
getWeight()                          | return the weight of this edge (positive value). |
getInfo()                            | Returns the remark (meta data) associated with this edge. |
setInfo(String s)                    | Allows changing the remark (meta data) associated with this edge. |
getTag()                             | can be used be algorithms  |
setTag(int t)                        | This method allows setting the "tag" value for temporal marking an edge - common |  <br/> 

###Elaboration###
- (1)
- (2)
- (3)
- (4)
- (5)
- (6)
- (7)
- (8)

### GraphAlgo
GraphAlgo is class that run algorithms on the graph. the class implement the interface **Directed Weighted Graph**.<br/>
This interface represents a Directed (positive) Weighted Graph Theory Algorithms that includes 8 algorithms .
 
 Methods       | Performs | Complexity
--------------------------|-----------------------------------------|---------
init(DirectedWeightedGraph g) | Inits the graph on which this set of algorithms operates on | 
DirectedWeightedGraph getGraph()|  Returns the underlying graph of which this class works |
DirectedWeightedGraph copy()                               | Computes a deep copy of this weighted graph |
isConnected()                            | Returns true if and only if (iff) there is a valid path from each node to each other node. |
shortestPathDist(int src, int dest)                         | Computes the length of the shortest path between src to dest |
shortestPath(int src, int dest)                            | Computes the the shortest path between src to dest |
center()                    | Finds the NodeData which minimizes the max distance to all the other nodes. |
tsp(List<NodeData> cities)                             | Computes a list of consecutive nodes which go over all the nodes in cities.  |
save(String file)                        | Saves this weighted (directed) graph to the given |  <br/>
 
 
 ## GUI
 In this class we implement GUI interface that draws graph on the screen and run algorithms on him . <br/>
 The class includes all the algorithms of GraphAlgo and add/remove nodes methods.
 we attach a tutorial how use in the GUI . <br/>
 
  Button       | Action
-----------------|-----------------------------------------
File             | open menu that include save and load options.
Load File        | This method loads a graph to this graph algorithm.
Save File        | Saves this weighted (directed) graph to the given
functions        | open menu that include all algorithms of graphAlgo. 
Center           | Finds the NodeData which minimizes the max distance to all the other nodes.
shortPath        | Computes the the shortest path between src to dest .
shortestPathDist | Computes the length of the shortest path between src to dest
TSP              | Computes a list of consecutive nodes which go over all the nodes in cities.  <br/>
 <br/>
 ## GUI represent G1.json  <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145280460-92df3f39-dcad-46f2-9058-e184f4ea7451.png" width="800">
 ## File
In top of the panel there are a buttons of load/save in File button functions .<br/>
 when you are clicking on File button you will see two buttons .<br/>
 If you click on load file you will see: <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145281523-7b1ba908-30ed-483e-980a-467b6310112f.png" width="300" <br/>
  Here you can load a new graph from your list of json graphs.<br/>
 If you click on save file you will see:<br/>
 <img src="https://user-images.githubusercontent.com/93033782/145281899-5878e0a4-8dd8-4f17-8dc5-12808ce0a915.png" width="300" <br/>
Here you can to give new name for your new graph. <br/>
  ## functions <br/>
 Click on center  recollor the center node like we can see in the picture: <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145282759-f2b55413-40d9-4304-b2dd-901a6e37f9be.png" width="300" <br/>
  Click on ShortPath open a message dialog for the source and destination nodes and return the nodes of the shortest path. <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145283597-085f83e7-d608-498f-935c-c828c4ea8cc1.png" width="300" <br/>
Example of shortest path between node 15 to node 6. <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145283901-01aaedba-6e3b-459e-b756-8ca8b05d350d.png" width="300" <br/>
 Click on TSP open to you this message dialog ,  enter all the nodes with comma between them : <br/>
 <img src="https://user-images.githubusercontent.com/93033782/145285115-8f0831d0-edac-4141-9ece-02beda89947a.png" width="300" <br/>



