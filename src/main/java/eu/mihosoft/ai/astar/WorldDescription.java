/*
 * WorldDescription.java
 *
 * Created on 11. Juli 2007, 21:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides everything the a* solver needs to know about a problem
 * and its domain.
 *
 * @author miho
 * @param <T>
 */
public class WorldDescription<T> {

    private final Heuristic<T> heuristic;
    private State<T> initialState;
//    private State<T> finalState;
    private Condition<T> goal;
    private List<Action<T>> actionSet;
//    private ArrayList<Predicate> predicateSet;

    /**
     * Creates a new instance of WorldDescription.
     *
     * @param initialState The initial state (s_0).
     * @param goal The condition that describes the final state (s_n).
     * @param actionSet The set of actions (containing operators with all
     * possible parameter combinations)
     * @param heuristic The heuristic that is to be used.
     */
    public WorldDescription(State<T> initialState, Condition<T> goal, List<Action<T>> actionSet, Heuristic<T> heuristic) {
        this.heuristic = heuristic;

        this.initialState = initialState.clone();

//        this.finalState = initialState.newInstance(initialState.size());

//        this.setFinalState (initialState.newInstance(initialState.size()));
        this.goal = goal;

//        Effect g = new Effect();
//
//        for (int i = 0; i < goal.size(); i++) {
//            g.add((EffectPredicate) goal.get(i));
//        }
//
//        g.apply(this.finalState);

        this.actionSet = actionSet;
//        this.predicateSet = null;
    }

//    /**
//     * Overloaded version of the above named constructor.
//     * This constructor is to be used if one wants to use the domain independant
//     * world generator.
//     *
//     * @param initialState The initial state (s_0).
//     * @param actionSet The set of actions (containing operators with all 
//     *                  possible parameter combinations)
//     * @param heuristic The heuristic that is to be used.
//     */
//    public WorldDescription (State<T> initialState,
//            ArrayList<Action> actionSet,
////            ArrayList<Predicate> predicateSet,
//            Heuristic<T> heuristic)
//    {
//        this.heuristic = heuristic;
//        
//        this.setInitialState (initialState);
//        
//        this.actionSet = actionSet;
////        this.predicateSet = predicateSet;
//    }
//    
    /**
     * Returns the action set.
     *
     * @return
     */
    public List<Action<T>> getActionSet() {
        return actionSet;
    }

    /**
     * Returns the goal condition.
     */
    public Condition<T> getGoal() {
        return goal;
    }

    /**
     * Returns the initial state.
     */
    public State<T> getInitialState() {
        return initialState;
    }

    /**
     * Returns the heuristic.
     */
    public Heuristic<T> getHeuristic() {
        return heuristic;
    }

//    /**
//     * Returns the final state (s_n).
//     *<br><br>
//     * <b>Warning:</b> If the WorldDescriptions initial and final states
//     *          will be initialized by a WorldGenerator, this method
//     *          returns null as long as this has not be done.
//     */
//    public State getFinalState ()
//    {
//        return finalState;
//    }
//    /**
//     * Sets the initial state.
//     *
//     * @param initialState The initial state (s_0).
//     */
//    public final void setInitialState(State<T> initialState) {
//        this.initialState = initialState.clone();
//    }

//    /**
//     * Sets the final state.
//     *
//     * @param finalState The final state (s_n).
//     */
//    public final void setFinalState (State<?> finalState)
//    {
//        this.finalState = (State) finalState.clone ();
//        
//        goal = new Condition ();
//        
//        for (int i = 0; i < finalState.size ();i++)
//        {
//            if (finalState.get (i) == true )
//            {
//                goal.add ( new Predicate (i, true) );
//            }
//        }
//    }
//    /**
//     * Returns the set of predicates.
//     * <br><br>
//     * <b>Warning:</b> This method will return null if WorldDescription
//     *          is not initialized for use with a WorldGenerator.
//     */
//    public ArrayList<Predicate> getPredicateSet ()
//    {
//        return predicateSet;
//    }
}
