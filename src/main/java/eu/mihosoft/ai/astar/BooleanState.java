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
package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Michael Hoffer &lt;info@michaelhoffer.de&gt;
 */
public class BooleanState implements State<Boolean> {
    private String actionName;
    private final List<Boolean> internalState;
    private Action<Boolean> action;
    
    /**
     * Creates an instance of State.
     *
     * @param n Size of State.
     */
    public BooleanState (int n)
    {
        internalState = new ArrayList<>(n);
        
        for (int i = 0; i < n; i++)
        {
            internalState.add (i,false);
        }
    }
    
    @Override
    public String getActionName ()
    {
        return actionName;
    }
    

    @Override
    public void setActionName (String actionName)
    {
        this.actionName = actionName;
    }
    

    @Override
    public boolean equals (Object o)
    {
        
        if (!(o instanceof BooleanState)) {
            return false;
        }
        
        BooleanState l = (BooleanState) o;
        
        if (this.size () != l.size () )
        {
            return false;
        }
        
        for (int i =0; i < this.size (); i++)
        {
            if ( !Objects.equals(this.get (i), l.get (i)) )
            {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public State<Boolean> clone() {
        BooleanState newState = new BooleanState(size());
        
        newState.internalState.clear();
        newState.internalState.addAll(internalState);
        newState.action = action;
        newState.actionName = actionName;
        
        return newState;
    }

    @Override
    public Boolean set(int i, Boolean value) {
        return internalState.set(i, value);
    }

    @Override
    public Boolean get(int i) {
        return internalState.get(i);
    }

    @Override
    public int size() {
        return internalState.size();
    }

    @Override
    public State<Boolean> newInstance(int n) {
        return new BooleanState(n);
    }

    @Override
    public Action<Boolean> getAction() {
        return action;
    }

    @Override
    public void setAction(Action<Boolean> a) {
        this.action = a;
    }
}
