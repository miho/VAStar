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
    public State<XY> clone() {
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
        
        return Objects.equals(this.pos, other.pos);
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
