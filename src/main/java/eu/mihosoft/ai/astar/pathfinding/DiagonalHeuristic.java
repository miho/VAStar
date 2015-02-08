/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar.pathfinding;

import eu.mihosoft.ai.astar.Condition;
import eu.mihosoft.ai.astar.Heuristic;
import eu.mihosoft.ai.astar.State;
import eu.mihosoft.ai.astar.WorldDescription;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class DiagonalHeuristic implements Heuristic<XY> {

    public DiagonalHeuristic() {
        //
    }

    @Override
    public double estimate(State<XY> s, Condition<XY> goal, WorldDescription<XY> w) {
        XY pos = s.get(0);

        if (!(goal instanceof PositionGoal)) {
            return (int) Double.MAX_VALUE;
        }

        XY goalPos = ((PositionGoal) goal).getPos();

        double minCostsPerNode = 1.0;

        double dx = Math.abs(pos.x - goalPos.x);
        double dy = Math.abs(pos.y - goalPos.y);

        return minCostsPerNode * Math.max(dx, dy);
    }

}
