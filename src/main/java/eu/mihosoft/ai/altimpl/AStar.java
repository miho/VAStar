package eu.mihosoft.ai.altimpl;

import java.util.*;



// A* algorithm implementation
public class AStar {

    public AStar() {
        //
    }

    public List<Action> search(WorldState initialState, Heuristic heuristic) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<WorldState> closedSet = new HashSet<>();

        Node startNode = new Node(initialState, null, null, 0, heuristic.estimate(initialState));
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.state.isGoal()) {
                return reconstructPath(current);
            }

            closedSet.add(current.state);

            var actions = current.state.getApplicableActions();

            for (Action action : actions) {
                if (action.isApplicable(current.state)) {
                    WorldState nextState = action.apply(current.state);

                    if (closedSet.contains(nextState)) {
                        continue;
                    }

                    double tentativeG = current.g + current.state.getCost(action);

                    Node neighbor = new Node(nextState, current, action, tentativeG, heuristic.estimate(nextState));

                    if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        return null; // No path found
    }

    private List<Action> reconstructPath(Node goalNode) {
        List<Action> path = new ArrayList<>();
        Node current = goalNode;

        while (current.parent != null) {
            path.add(0, current.action);
            current = current.parent;
        }

        return path;
    }


    // Interface for defining the world state
    public interface WorldState {
        boolean isGoal();
        List<Action> getApplicableActions();
        double getCost(Action action);
    }

    // Interface for defining actions
    public interface Action {
        boolean isApplicable(WorldState state);
        WorldState apply(WorldState state);
    }

    // Interface for defining heuristics
    public interface Heuristic {
        double estimate(WorldState state);
    }

    // Node class for A* algorithm
    static class Node implements Comparable<Node> {
        WorldState state;
        Node parent;
        Action action;
        double g;
        double h;

        Node(WorldState state, Node parent, Action action, double g, double h) {
            this.state = state;
            this.parent = parent;
            this.action = action;
            this.g = g;
            this.h = h;
        }

        double f() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.f(), other.f());
        }
    }


}
