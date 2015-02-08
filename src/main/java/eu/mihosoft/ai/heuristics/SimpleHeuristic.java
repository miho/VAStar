/*
 * SimpleHeuristic.java
 *
 * Created on 14. Juli 2007, 17:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eu.mihosoft.ai.heuristics;

import eu.mihosoft.ai.astar.Action;
import eu.mihosoft.ai.astar.Condition;
import eu.mihosoft.ai.astar.Heuristic;
import eu.mihosoft.ai.astar.State;
import eu.mihosoft.ai.astar.WorldDescription;

/**
 * This is a very simple heuristic. It estimates the distance between two states
 * by solving the relaxed problem (no negative preconditions and effects).
 *
 * @author miho
 * @param <T>
 */
public final class SimpleHeuristic<T> implements Heuristic<T> {

    int distance;

    /**
     * Estimates the distance between two states.
     *
     * @param s This is the first State.
     * @param goal
     * @param w The WorldDescription, containing the final state as well as the
     * necessary actionSet.
     * @return
     */
    @Override
    public double estimate(State<T> s, Condition<T> goal, WorldDescription<T> w) {
        distance = 0;
        State sOld = (State) s.clone();
        State sNew = (State) s.clone();

        while (!w.getGoal().verify(sNew)) {
            distance++;

            for (Action a : w.getActionSet()) {
                boolean b = a.perform(sOld, sNew);
            }

            sOld = (State) sNew.clone();
        }

        return distance;
    }
}
