/*
 * Copyright 2007-2016 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY Michael Hoffer <info@michaelhoffer.de> "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Michael Hoffer <info@michaelhoffer.de> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Michael Hoffer <info@michaelhoffer.de>.
 */
package eu.mihosoft.ai.astar.pathfinding;

import eu.mihosoft.ai.astar.Action;
import eu.mihosoft.ai.astar.ConditionPredicate;
import eu.mihosoft.ai.astar.EffectPredicate;
import eu.mihosoft.ai.astar.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class GoInDirAction extends Action<XY> {

    private XY direction;
    private List<XY> obstacles = new ArrayList<>();
    private Map<XY, Double> costs = new HashMap<>();

    public boolean verify(State<XY> s) {

        s = s.clone();

        effect.apply(s);
        return precond.verify(s);
    }

    public GoInDirAction(XY direction, String name, List<XY> obstacles, Map<XY, Double> costs) {
        this(direction, name);
        setObstacles(obstacles);
        setCosts(costs);
    }

    public GoInDirAction(XY direction, String name) {

        setName(name);

        this.direction = new XY(direction);

        precond.add(new ConditionPredicate<XY>() {

            @Override
            public boolean verify(State<XY> s) {

                s = s.clone();

                s.get(0).x += GoInDirAction.this.direction.x;
                s.get(0).y += GoInDirAction.this.direction.y;

                XY pos = s.get(0);

                for (XY xy : getObstacles()) {
                    if (xy.equals(s.get(0))) {
                        return false;
                    }
                }

                return true;

            }

            @Override
            public String getName() {
                return name;
            }
        });
        effect.add(new EffectPredicate<XY>() {

            @Override
            public void apply(State<XY> s) {
                s.get(0).x += GoInDirAction.this.direction.x;
                s.get(0).y += GoInDirAction.this.direction.y;
            }

            @Override
            public String getName() {
                return name;
            }
        });
    } // end constructor

    /**
     * @return the obstacles
     */
    public List<XY> getObstacles() {
        return obstacles;
    }

    /**
     * @param obstacles the obstacles to set
     */
    public final void setObstacles(List<XY> obstacles) {
        this.obstacles.clear();
        this.obstacles.addAll(obstacles);
    }

    /**
     * @param costs the costs to set
     */
    public final void setCosts(Map<XY, Double> costs) {
        this.costs.clear();
        this.costs.putAll(costs);
    }

    @Override
    public double getCosts(State<XY> s) {
        Double result = costs.get(s.get(0));

        if (result == null) {
            return 1.0;
        }

        return result;
    }

}  // end go in dir



