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
