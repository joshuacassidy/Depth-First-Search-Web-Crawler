import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    public boolean visited;
    public List<Vertex> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        adjacencyList = new ArrayList<>();
    }

    public void addAdjacency(Vertex vertex) {
        adjacencyList.add(vertex);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setAdjacencyList(List<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public List<Vertex> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public String toString() {
        return name;
    }
}
