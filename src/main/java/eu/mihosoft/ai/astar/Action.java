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
 * Action.java
 *
 * Created on 11. Juli 2007, 18:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar;

/**
 * An Action consists of preconditions and effects. These are predicates that can
 * be verified or be applied to a state. The action class does combine these two.
 * It can perform an action to a given state s.
 *
 * @author miho
 */
public class Action<T> {

    private String name;
    public Condition<T> precond;
    public Effect<T> effect;

    /**
     * Creates a new instance of Action
     */
    public Action() {
        precond = new Condition<>();
        effect = new Effect<>();
    }

    /**
     * Performing an action means to apply all effects (predicates) to State s
     * if preconditions are true.
     *
     * @param s State on which the action operates on.
     * @return {@code true} if action can be performed; {@code false} otherwise
     */
    public boolean perform(State<T> s) {
        boolean performable = precond.verify(s);

        if (performable) {
            effect.apply(s);
            s.setActionName(this.getName());
            s.setAction(this);
        } else {
//            System.out.println ("in Action: " + getName ());
        }

        return performable;
    }

    /**
     * Overloaded version of the above named method. Using this method gives you
     * the possibility to verify preconditions on one state and apply effects to
     * another one.
     *
     * @param s1 State on which the action is verified.
     * @param s2 State on which the action operates on.
     * @return {@code true} if action can be performed; {@code false} otherwise
     */
    public boolean perform(State<T> s1, State<T> s2) {
        boolean performable = precond.verify(s1);

        if (performable) {
            effect.apply(s2);
            s2.setActionName(this.getName());
            s2.setAction(this);
        } else {
//            System.out.println ("in Action: " + getName ());
        }

        return performable;
    }

//    /**
//     * Overloaded version of the above named method. Using this method gives you
//     * the choice to only apply positive or negative effects.
//     *
//     * @param s State on which the action operates on.
//     */
//    public boolean perform(State s, boolean predicateValue) {
//        boolean performable = precond.verify(s);
//
//        if (performable) {
//            effect.apply(s, predicateValue);
//            s.setActionName(this.getName());
//        } else {
////            System.out.println("in Action: " + getName());
//        }
//
//        return performable;
//    }

//    /**
//     * Overloaded version of the above named method. Using this method gives you
//     * the choice to only apply positive or negative effects and the possibility
//     * to verify preconditions on one state and apply effects to another.
//     *
//     * @param s1 State on which the action is verified.
//     * @param s2 State on which the action operates on.
//     */
//    public boolean perform(State s1, State s2, boolean predicateValue) {
//        boolean performable = precond.verify(s1);
//
//        if (performable) {
//            effect.apply(s2, predicateValue);
//            s2.setActionName(this.getName());
//        } else {
////            System.out.println("in Action: " + getName());
//        }
//
//        return performable;
//    }

    /**
     * Returns the String description of the action. (nice for screen output)
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name (description) of the action.
     */
    protected void setName(String name) {
        this.name = name;
    }
    
    public double getCosts(State<T> s) {
        return 1.0;
    }

}
