package AI;

import core.main;
import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    private final Node startNode;

    public BFS(Node start) {
        this.startNode = start;
    }

    public int search() {
        Queue<Node> frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        if (startNode.isGoal()) {
            System.out.println("score : " + startNode.sum);
            main.printResult(startNode, 0);
            return 1;
        }
        frontier.add(startNode);
        inFrontier.put(startNode.hash(), true);
        while (!frontier.isEmpty()) {
            Node temp = frontier.poll();
            inFrontier.remove(temp.hash());
            explored.put(temp.hash(), true);
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
                if (!(inFrontier.containsKey(child.hash())) && !(explored.containsKey(child.hash()))) {
                    if (child.isGoal()) {
                        main.printResult(child, 0);
                        System.out.println(child.sum);
                        return 1;
                    }
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }
        System.out.println("no solution");
        return -1;
    }
}
