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
        State<T> sOld = (State<T>) s.clone();
        State<T> sNew = (State<T>) s.clone();

        while (!w.goal().verify(sNew)) {
            distance++;

            for (Action<T> a : w.actionSet()) {
                boolean b = a.perform(sOld, sNew);
            }

            sOld = (State<T>) sNew.clone();
        }

        return distance;
    }
}
