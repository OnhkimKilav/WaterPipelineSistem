Water pipeline system

REQUIREMENTS
------------
JDK 13
Maven

CONTENTS
--------
    /readme.txt - this file
	/pipeline.csv - a CSV file that describes the water pipeline system
	/pipelineSet.csv - a CSV file with a set of points, between which you need to find the route
	/result.csv - a CSV file with the result of the program
    /pom.xml - project settings file
    /src - the main project folder where all the code is stored 

DESCRIPTION OF THE PROGRAMM
-------------------
The essence of this program is to find a short path between two points and find the minimum length of this water supply.
Data for the program is stored in 3 files: pipeline.csv, pipelineSet.csv, result.csv. 
The data is processed in the service package in the CSVReaderWriter class. After that the data is transferred further to the program.
Files store data. Description the water pipeline system. Set of points, between which you need to find the route. And the result of the program.
The result is found using the breadth-first search algorithm. One of the methods for traversing the graph.
Let the graph G = (V, E) be given and the original vertex s is selected. 
The breadth-first search algorithm systematically traverses all the edges of G to "open" all vertices reachable from s,
calculating the distance (minimum number of edges) from s to each reachable vertex from s.
When the required minimum path is found, its length is found and written to the result.csv file.
Before starting the calculation of the path, the file with the description of the pipeline is overwritten in the H2 database in the "PIPELINCE" table.