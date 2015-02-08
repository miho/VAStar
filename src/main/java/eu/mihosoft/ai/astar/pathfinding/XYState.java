/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar.pathfinding;

import eu.mihosoft.ai.astar.Action;
import eu.mihosoft.ai.astar.State;
import java.util.Objects;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class XYState implements State<XY> {

    private String actionName;
    private Action<XY> action;

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
        XYState result = new XYState(pos);

        result.setActionName(actionName);
        result.setAction(action);

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

    @Override
    public Action<XY> getAction() {
        return this.action;
    }

    @Override
    public void setAction(Action<XY> a) {
        this.action = a;
    }
}
