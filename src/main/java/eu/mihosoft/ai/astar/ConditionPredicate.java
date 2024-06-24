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
 * ConditionPredicate.java
 *
 * Created on 11. Juli 2007, 17:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * The ConditionPredicate interface represents a predicate that can verify
 * a condition on a given state. It is typically used to define goal conditions,
 * constraints, preconditions for actions or other testable properties of states
 * in a state-space search problem, such as in the A* algorithm.
 *
 * <p>Implementations of this interface should define how a condition is
 * checked against a given state, returning true if the condition is met
 * and false otherwise.</p>
 *
 * <p>In the context of the A* algorithm or similar search algorithms,
 * ConditionPredicates might represent:
 * <ul>
 *   <li>Goal state conditions</li>
 *   <li>Validity checks for states</li>
 *   <li>Heuristic evaluations</li>
 *   <li>Constraints that must be maintained during the search</li>
 * </ul>
 * </p>
 *
 * @param <T> the type of elements in the state
 * @author miho
 */
public interface ConditionPredicate<T> {

    /**
     * Verifies if the condition is true for the given state.
     *
     * <p>This method should check whether the condition represented by this
     * predicate holds true for the given state. The exact nature of the
     * condition depends on the specific implementation and problem domain.</p>
     *
     * @param s the state to be verified
     * @return {@code true} if the condition is met, {@code false} otherwise
     */
    boolean verify(State<T> s);

    /**
     * Returns the name of the condition predicate.
     *
     * <p>This method should return a human-readable name or description
     * of the condition. This can be useful for debugging, logging, or
     * presenting information about the search process to users.</p>
     *
     * @return a String representing the name of the condition predicate
     */
    String getName();
}
