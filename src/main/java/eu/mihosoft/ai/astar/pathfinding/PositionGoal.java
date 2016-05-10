/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar.pathfinding;

import eu.mihosoft.ai.astar.Condition;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class PositionGoal extends Condition<XY> {

    private final XY pos;

    public PositionGoal(XY pos) {
        this.pos = pos;
        add(new PositionPredicate(pos));
    }

    /**
     * @return the pos
     */
    public XY getPos() {
        return pos;
    }

}
