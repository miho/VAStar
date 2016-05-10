/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar.pathfinding;

import eu.mihosoft.ai.astar.ConditionPredicate;
import eu.mihosoft.ai.astar.EffectPredicate;
import eu.mihosoft.ai.astar.State;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class PositionPredicate implements ConditionPredicate<XY>, EffectPredicate<XY> {

    private final XY pos;
    private static final double TOL = 0.0001;

    public PositionPredicate(XY pos) {
        this.pos = new XY(pos);
    }

    @Override
    public boolean verify(State<XY> s) {

        boolean result = Math.abs(pos.x - s.get(0).x) < TOL
                && Math.abs(pos.y - s.get(0).y) < TOL;

        return result;
    }

    @Override
    public String getName() {
        return "position-pred";
    }

    @Override
    public void apply(State<XY> s) {
        s.set(0, pos);
    }
}



