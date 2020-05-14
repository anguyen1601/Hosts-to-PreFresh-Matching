class AdjacencyList { //adjacencyList
     int vertices;
   List[] adjacent;

    public AdjacencyList(int numVertices) { //adjacencyList
        vertices = numVertices;
        adjacent = new List[numVertices];
        for(int i = 0; i< vertices; i++){
            adjacent[i] = new List();
        }
    }

    public void addEdge(int i, Person toAdd) {
        adjacent[i].add(toAdd);
    }

    public void removeEdge(int i) {
        adjacent[i].delete();

    }

    public boolean hasEdge(int i, Person toFind) {
        return adjacent[i].contains(toFind);
    }

    public List outEdges(int i) {
        return adjacent[i];
    }

    public List inEdges(Person toFind) {
        List edges = new List();
        for(int j = 0; j < vertices; j++)
            if(adjacent[j].contains(toFind)) edges.add(toFind);

        return edges;

    }


}
