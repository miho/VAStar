package eu.mihosoft.ai.altimpl;

import java.util.*;


// Relaxed Problem Heuristic
public class RelaxedProblemHeuristic implements AStar.Heuristic {
    @Override
    public double estimate(AStar.WorldState state) {
        if (!(state instanceof RelaxableWorldState)) {
            throw new IllegalArgumentException("WorldState must be RelaxableWorldState");
        }
        return solveRelaxedProblem((RelaxableWorldState) state);
    }

    private double solveRelaxedProblem(RelaxableWorldState initialState) {
        Queue<RelaxableWorldState> queue = new LinkedList<>();
        Set<RelaxableWorldState> visited = new HashSet<>();
        Map<RelaxableWorldState, Integer> distance = new HashMap<>();

        queue.offer(initialState);
        visited.add(initialState);
        distance.put(initialState, 0);

        while (!queue.isEmpty()) {
            RelaxableWorldState current = queue.poll();

            if (current.isGoal()) {
                return distance.get(current);
            }

            for (AStar.Action action : current.getApplicableActions()) {
                if (action instanceof RelaxableAction && action.isApplicable(current)) {
                    RelaxableAction relaxableAction = (RelaxableAction) action;
                    RelaxableWorldState next = current.applyRelaxed(relaxableAction);

                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                        distance.put(next, distance.get(current) + 1);
                    }
                }
            }
        }

        return Double.POSITIVE_INFINITY; // No solution found in relaxed problem
    }

    // Extended WorldState interface to support relaxed problem solving
    public interface RelaxableWorldState extends AStar.WorldState {
        Set<Object> getGoals();
        Set<Object> getAchievedGoals();
        RelaxableWorldState applyRelaxed(AStar.Action action);
    }

    // Extended Action interface to support relaxed effects
    public interface RelaxableAction extends AStar.Action {
        Set<Object> getRelaxedEffects(RelaxableWorldState state);
    }
}