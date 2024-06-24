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
 * WorldDescription.java
 *
 * Created on 11. Juli 2007, 21:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar;

import java.util.List;

/**
 * This class provides everything the A* solver needs to know about a problem
 * and its domain.
 *
 * @param <T>
 * @param goal private State<T> finalState;
 * @author miho
 */
public record WorldDescription<T>(State<T> initialState, Condition<T> goal, List<Action<T>> actionSet,
                                  Heuristic<T> heuristic) {

    //    private ArrayList<Predicate> predicateSet;

    /**
     * Creates a new instance of WorldDescription.
     *
     * @param initialState The initial state (s_0).
     * @param goal         The condition that describes the final state (s_n).
     * @param actionSet    The set of actions (containing operators with all
     *                     possible parameter combinations)
     * @param heuristic    The heuristic that is to be used.
     */
    public WorldDescription(State<T> initialState, Condition<T> goal, List<Action<T>> actionSet, Heuristic<T> heuristic) {
        this.heuristic = heuristic;

        this.initialState = initialState.clone();

        this.goal = goal;

        this.actionSet = actionSet;
    }


    /**
     * Returns the action set.
     *
     * @return the action set
     */
    @Override
    public List<Action<T>> actionSet() {
        return actionSet;
    }

    /**
     * Returns the goal condition.
     */
    @Override
    public Condition<T> goal() {
        return goal;
    }

    /**
     * Returns the initial state.
     */
    @Override
    public State<T> initialState() {
        return initialState;
    }

    /**
     * Returns the heuristic.
     */
    @Override
    public Heuristic<T> heuristic() {
        return heuristic;
    }
}
