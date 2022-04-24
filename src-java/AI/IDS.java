package AI;

import core.main;
import model.Node;

import java.util.*;

public class IDS {
    public void search(Node startNode) {
        for(int i=0;i<startNode.board.getRow()*startNode.board.getCol();i++){
            Deque<Node> frontier = new LinkedList<Node>(); //FILO stack
            Hashtable<String, Boolean> inFrontier = new Hashtable<>();
            Hashtable<String, Boolean> explored = new Hashtable<>();
            if (startNode.isGoal()) {
                System.out.println("score : " + startNode.sum);
                main.printResult(startNode, 0);
                return;
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
                            return;
                        }
                        frontier.add(child);
                        inFrontier.put(child.hash(), true);
                    }
                }
            }
            System.out.println("no solution");
            explored.clear(); // deleting explored branches
        }
    }
}
