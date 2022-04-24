package AI;

import core.main;
import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BDS {
    private final Node start;
    private final Node goal;

    public BDS(Node start, Node goal) {
        this.start = start;
        this.goal = goal;
    }

    public void search() {
        Queue<Node> s_frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> s_inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> s_explored = new Hashtable<>();
        Queue<Node> t_frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> t_inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> t_explored = new Hashtable<>();

        s_frontier.add(start);
        t_frontier.add(goal);
        s_inFrontier.put(start.hash(), true);
        t_inFrontier.put(goal.hash(), true);
        Node intersect = null;
        while (!s_frontier.isEmpty() && !t_frontier.isEmpty() && intersect == null) {
            if (!s_frontier.isEmpty()) {
                Node temp = s_frontier.poll();
                s_inFrontier.remove(temp.hash());
                s_explored.put(temp.hash(), true);
                ArrayList<Node> children = temp.successor();
                for (Node child : children) {
                    if (!(s_inFrontier.containsKey(child.hash())) && !(s_explored.containsKey(child.hash()))) {
                        s_frontier.add(child);
                        s_inFrontier.put(child.hash(), true);
                    }
                }
            }
            if (!t_frontier.isEmpty()) {
                Node temp = t_frontier.poll();
                t_inFrontier.remove(temp.hash());
                t_explored.put(temp.hash(), true);
                ArrayList<Node> children = temp.successor();
                for (Node child : children) {
                    if (!(t_inFrontier.containsKey(child.hash())) && !(t_explored.containsKey(child.hash()))) {
                        t_frontier.add(child);
                        t_inFrontier.put(child.hash(), true);
                    }
                }
            }
            for (Node sNode : s_frontier) {
                for (Node tNode : t_frontier) {
                    if (tNode.currentCell.i == sNode.currentCell.i && tNode.currentCell.j == sNode.currentCell.j) {
                        intersect = sNode;
                        break;
                    }
                }
            }
            BFS bfs =new BFS(intersect);
            if (intersect!=null && bfs.search() == -1) {
                s_frontier.remove(intersect);
                t_frontier.remove(intersect);
                intersect=null;
            };
        }
    }
}


