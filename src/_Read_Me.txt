Christian Boettcher
Tcss 342
Dijkstra's algorithm project

This project is from tcss 342 winter 2016
the starter code was provided by the instructor Ka Yee Yeung which includes the interface Graph and the classes Vertex, Edge, and Path.
the class FindPath was also provided but has received minor modifications. 

The main part of the algorithm can be found in MyGraph under the functions "dijkstra" and "adjacentVertices". In adjacentVertices, I iterated over the edges of any particular vertex to deturmine the cost of each near by node instead of making a separate dijstra node (making nodes was an option other students took, but during my implementation, I didn't want to alter the instructors code for Vertex which led me to work with edges instead).

to work with this code, there are 2 .txt files for vertices and edges. the locations for these can either be Hard Coded in or read in from the command line from the Driver "FindPaths". I've commented out both of these options, so in order to start this code the proper adjustments need to be made.    