package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.Objects;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GoInDirAction left = new GoInDirAction(new XY(-1, 0), "left");
        GoInDirAction right = new GoInDirAction(new XY(1, 0), "right");

        GoInDirAction up = new GoInDirAction(new XY(0, -1), "up");
        GoInDirAction down = new GoInDirAction(new XY(0, 1), "down");

        ArrayList<Action> actions = new ArrayList<>();

        actions.add(left);
        actions.add(right);
        actions.add(up);
        actions.add(down);

        WorldDescription w = new WorldDescription(
                new XYState(new XY(0, 1)),
                new PositionGoal(new XY(8, 4)),
                actions, new ManhattanHeuristic());

        AStar solver = new AStar(w);
        solver.run();

        System.out.println("done.");

    }
}

class XY {

    double x;
    double y;

    public XY() {
        //
    }

    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public XY(XY xy) {
        this.x = xy.x;
        this.y = xy.y;
    }
    
    @Override
    public String toString() {
        return "x: " + x + ", y: " + y;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XY other = (XY) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    
    
}

class XYState implements State<XY> {

    private String actionName;

    private XY pos;

    public XYState(XY pos) {
        this.pos = new XY(pos);
    }

    public XYState() {
        pos = new XY();
    }

    @Override
    public String getActionName() {
        return this.actionName;
    }

    @Override
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public State clone() {
        XYState result =  new XYState(pos);
        
        result.setActionName(actionName);
        
        return result;
    }

    @Override
    public XY set(int i, XY value) {

        if (i != 0) {
            throw new IndexOutOfBoundsException("Index " + i + " != 0.");
        }

        pos = value;

        return pos;
    }

    @Override
    public XY get(int i) {

        if (i != 0) {
            throw new IndexOutOfBoundsException("Index " + i + " != 0.");
        }

        return pos;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public State<XY> newInstance(int n) {

        if (n != 1) {
            throw new IndexOutOfBoundsException("Size " + n + " != 1.");
        }

        return new XYState();
    }
    
    @Override
    public String toString() {
        return "pos: " + pos.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.pos);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XYState other = (XYState) obj;
        if (!Objects.equals(this.pos, other.pos)) {
            return false;
        }
        return true;
    }
    
    
}

class GoInDirAction extends Action {

    private XY direction;

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

                if (pos.x >= 2 && pos.x < 3 && pos.y >= 0 && pos.y <=5) {
                    return false;
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

}  // end go in dir

class PositionGoal extends Condition {

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

class PositionPredicate implements ConditionPredicate<XY>, EffectPredicate<XY> {

    private final XY pos;

    public PositionPredicate(XY pos) {
        this.pos = new XY(pos);
    }

    @Override
    public boolean verify(State<XY> s) {

        boolean result = Math.abs(pos.x - s.get(0).x) < 0.001
                && Math.abs(pos.y - s.get(0).y) < 0.001;
//        
//        System.out.println("pos: " + pos + " -> state-pos: " + s.get(0) + ", result: " + result);
        
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

class ManhattanHeuristic implements Heuristic<XY> {

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
        
        return minCostsPerNode * (dx + dy);
    }

}
