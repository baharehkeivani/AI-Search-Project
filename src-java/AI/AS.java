package AI;

import core.main;
import model.Node;

import java.util.*;

public class AS {
    HashMap<String, Integer> f_table = new HashMap<String, Integer>(); //for having nodes sorted by their f value in queues

    public void search(Node startNode) {
        Queue<Node> opened = new PriorityQueue<Node>(Comparator.comparing(node -> f_table.get(node.hash())));
        Queue<Node> close = new PriorityQueue<Node>(Comparator.comparing(node -> f_table.get(node.hash())));
        Hashtable<String, Boolean> visited = new Hashtable<>();
        f_table.put(startNode.hash(), startNode.h);

        opened.add(startNode);
        visited.put(startNode.hash(), true);
        while (!opened.isEmpty()) {
            Node current = opened.poll();
            if (current.isGoal()) {
                main.printResult(current, 0);
                break;
            }
            ArrayList<Node> children = current.successor();
            for (Node child : children) {
                if (!visited.contains(child.hash())) {
                    f_table.put(child.hash(), child.g + child.h);
                    opened.add(child);
                    visited.put(child.hash(), true);
                }
                else {
                    if (current.g > child.g) {
                        f_table.put(child.hash(), child.g + child.h);
                        if (close.contains(child)) {
                            close.remove(child);
                            opened.add(child);
                        }
                    }
                }
            }
            opened.remove(current);
            close.add(current);
        }
    }
}
