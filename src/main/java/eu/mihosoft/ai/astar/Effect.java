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
 * Effect.java
 *
 * Created on 11. Juli 2007, 17:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;

/**
 * An Effect is an ArrayList of EffectPredicates. It groups different
 * predicates and applies them to a State s. It does not support logical operators such
 * as OR. All predicates are combined via AND.
 * @author miho
 */
public class Effect<T> extends ArrayList<EffectPredicate<T>>
{
    
    /**
     * Applies all effect predicates to state s.
     * @param s State
     */
    public void apply (State<T> s)
    {
        for (EffectPredicate<T> i : this)
        {
            i.apply (s);
        }
    }
    
//    /**
//     * Overloaded version of the above named method.
//     * It gives you the choice to only apply positive
//     * or negative effects.
//     * @param s State
//     * @param predicateValue defines whether only positive
//     *                       or negative effects will be applied.
//     */
//    public void apply (State s, boolean predicateValue)
//    {
//        for (EffectPredicate i : this)
//        {
//            if (i.getPredicateValue () == predicateValue)
//            {
//                i.apply (s);
//            }
//        }
//    }
}
