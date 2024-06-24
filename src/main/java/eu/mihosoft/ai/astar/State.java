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
 * State.java
 *
 * Created on 11. Juli 2007, 16:46
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

/**
 * The State interface represents a state in the planning domain of a search problem,
 * such as those solved by the A* algorithm. It encapsulates a collection of state
 * values and provides methods to manipulate and query these values.
 *
 * <p>A State typically represents a specific configuration or situation in the
 * problem space. It can be thought of as a node in the search graph.</p>
 *
 * <p>This interface allows for generic typing, enabling different types of state
 * values to be used (e.g., Double, Boolean, custom objects) depending on the
 * specific problem domain.</p>
 *
 * @param <T> the type of elements stored in this state
 * @author miho
 */
public interface State<T> extends Cloneable {

    /**
     * Retrieves the Action that was applied to reach this state.
     *
     * @return the Action that led to this state, or null if this is an initial state
     */
    Action<T> getAction();

    /**
     * Sets the Action that was applied to reach this state.
     *
     * @param a the Action to associate with this state
     */
    void setAction(Action<T> a);

    /**
     * Retrieves the name of the action that last changed the state.
     *
     * @return the name of the last applied action, or null if no action has been applied
     */
    String getActionName();

    /**
     * Sets the name of the action that last changed the state.
     *
     * @param actionName the name of the action to associate with this state
     */
    void setActionName(String actionName);

    /**
     * Compares this state with another object for equality.
     *
     * <p>Two states are considered equal if they have the same values in the same order.</p>
     *
     * @param o the object to compare with this state
     * @return true if the states are equal, false otherwise
     */
    @Override
    boolean equals(Object o);

    /**
     * Creates and returns a deep copy of this state.
     *
     * @return a new State object that is a deep copy of this state
     */
    State<T> clone();

    /**
     * Sets the value at the specified index in this state.
     *
     * @param i the index at which to set the value
     * @param value the value to be stored at the specified index
     * @return the previous value at the specified index, or null if there was no previous value
     * @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i >= size())
     */
    T set(int i, T value);

    /**
     * Retrieves the value at the specified index in this state.
     *
     * @param i the index of the value to retrieve
     * @return the value at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (i < 0 || i >= size())
     */
    T get(int i);

    /**
     * Returns the number of elements in this state.
     *
     * @return the number of elements in this state
     */
    int size();

    /**
     * Creates a new instance of a State with the specified initial capacity.
     *
     * <p>This method acts as a factory method for creating new State instances.</p>
     *
     * @param n the initial capacity of the new State
     * @return a new State instance with the specified capacity
     */
    State<T> newInstance(int n);

    /**
     * Returns a string representation of the state.
     *
     * <p>This method should provide a human-readable representation of the state,
     * which can be useful for debugging and logging purposes.</p>
     *
     * @return a string representation of the state
     */
    @Override
    String toString();

    /**
     * Calculates a hash code for this state.
     *
     * <p>The hash code should be consistent with the equals method, such that
     * if two states are equal according to equals(), they should have the same hash code.</p>
     *
     * @return a hash code value for this state
     */
    @Override
    int hashCode();
}