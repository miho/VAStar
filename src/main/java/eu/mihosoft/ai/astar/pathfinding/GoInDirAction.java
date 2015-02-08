/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
