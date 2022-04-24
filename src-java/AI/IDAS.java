package AI;

import core.main;
import model.Node;

import java.util.ArrayList;

public class IDAS {
    public void search(Node startNode) {
        int cutoff = startNode.h;
        while (true) {
            int distance = deepening(startNode, cutoff);
            if (distance == Integer.MAX_VALUE) {
                System.out.println("no solution");
                break;
            } else if (distance < 0) break;
            else cutoff = distance;
        }
    }

    //node.sum = g(x)
    private int deepening(Node current, int cutoff) {
        if (current.isGoal()) {
            main.printResult(current, 0);
            System.out.println(current.sum);
            return -current.g_idsa;
        }
        int f = current.g_idsa + current.h;
        if (f > cutoff) return f;
        int min = Integer.MAX_VALUE;
        ArrayList<Node> children = current.successor();
        for (Node child : children) {
            int d = deepening(child, cutoff);
            if (d < 0) return d;
            else if (d < min) min = d;
        }
        return min;
    }
}
