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
///*
// * HelpfulActions.java
// *
// * Created on 17. Juli 2007, 11:55
// *
// * To change this template, choose Tools | Template Manager
// * and open the template in the editor.
// */
//
//package eu.mihosoft.ai.astar;
//
///**
// * This is just a testing class and not used in the final program.
// * @author miho
// */
//public class HelpfulActions
//{
////    private Condition goal;
//    private State goal;
//    private WorldDescription w;
//    
//    public HelpfulActions (State s, WorldDescription w)
//    {
//        this.w = w;
//        
//        generateGoal(s);
//    }
//    
//    private void generateGoal(State s)
//    {
//        int n = 5;
//        State sOld = (State) s.clone ();
//        State sNew = (State) s.clone ();
//       
//        
//        for (int i = 0; i < n; i++)
//        {
//            for( Action a : w.getActionSet () )
//            {
//                boolean b = a.perform ( sOld, sNew, true );
//            }
//            
//            sOld = (State) sNew.clone ();
//        }
//      
//        goal = sNew;
//    }
//    
//    
//    
//    public boolean verifyAction (State s, Action a)
//    {
////        generateGoal(s);
//        
////        State sNew = (State) s.clone ();
//        
//        boolean isHelpful = false;
//        
//        if ( !a.perform (s) )
//        {
//            isHelpful = false;
//        }
//        else
//        {
//            for ( EffectPredicate e : a.effect)
//            {
//                if (e.getPredicateValue () == true)
//                {
//                    ConditionPredicate c = (ConditionPredicate)e;
//                    if ( c.verify (goal) )
//                    {
//                        isHelpful = true;
//                        break;
//                    }
//                }
//            }
//        }
//        
//        return isHelpful;
//    }
//}
