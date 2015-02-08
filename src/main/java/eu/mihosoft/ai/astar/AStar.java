/*
 * AStar.java
 *
 * Created on 12. Juli 2007, 17:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package eu.mihosoft.ai.astar;

import java.util.ArrayList;

/**
 * The AStar class contains an implementation of the well known A* algorithm.
 * It is completely independent of the domain/problem and the heuristic.
 * @author miho
 */
public class AStar
{
    private WorldDescription w;
    private TreeNode root;
    private String[] solution;
    
    
    /**
     * Creates a new instance of AStar
     *
     * @param w a WorldDescription containing initial and final states
     *          and the actionSet as we use set based instead of
     *          classical representation.
     */
    public AStar (WorldDescription w)
    {
        this.w = w;
        root = new TreeNode (w.getInitialState (),w.getHeuristic ().estimate (w.getInitialState (),w));
        
        System.out.println ("");
        System.out.println (">> A-Star initialized!");
        System.out.println ("");
        System.out.println ("------------------------------");
        System.out.println ("[ state length : " + w.getInitialState ().size () + " ]");
        System.out.println ("[ number of actions: " + w.getActionSet ().size () + " ]");
        System.out.println ("[ initial distance: " + root.getHeuristic () + " ]");
        System.out.println ("------------------------------");
        System.out.println ("");
    }
    
    
    /**
     * starts the planning process.
     */
    public void run ()
    {
        System.out.println (">> Running:");
        System.out.println ("");
        boolean foundSolution = false;
        int counter = 0;
        int maxDepth = 0;
        
        TreeNode bestNode = null;
        int heuristic = root.getHeuristic ();
        int minHeuristic = heuristic;
        
//        HelpfulActions actionFilter = new HelpfulActions(w.getInitialState (),w);
        
        if (heuristic > 0)
        {
            
            TreeNode t = root;
            
            while ( heuristic > 0 )
            {
                counter++;
                
                t = getLeafWithLowestSum (root);
                
                for ( Action a : w.getActionSet () )
                {
                    State sNew = (State) t.getState ().clone ();
                    
                    if ( a.perform (sNew,sNew) )
//                    if (actionFilter.verifyAction (sNew,a))
                    {
                        
                        heuristic =  w.getHeuristic ().estimate (sNew,w);
                        
                        t.add (new TreeNode (sNew,heuristic));
                        
                        if ( t.getCosts () > maxDepth)
                        {
                            maxDepth = t.getCosts ();
                            System.out.println ("[ path depth: " + maxDepth + " ]");
                        }
                        
                        if (heuristic == 0)
                        {
                            break;
                        }
                        
                        //Hill climbing
//                        if (heuristic < minHeuristic)
//                        {
//                            minHeuristic = heuristic;
//                            bestNode = t.get (t.size ()-1);
//                            optimizeTree (bestNode);
//                            System.out.println (">> has reached new min: " + minHeuristic);
//                        }
                    }
                }                
            }
            
            
            // find out last added node
            t = t.get (t.size ()-1);
            
            solution = new String[t.getCosts ()];
            
            while (t.getParent ()!=null)
                
                for (int i = t.getCosts () -1 ; i >=0 ; i--)
                {
                solution[i] = t.getState ().getActionName ();
                t = t.getParent ();
                }
            
            System.out.println ("");
            System.out.println (">> Solution found! ");
            
            System.out.println ("");
            System.out.println ("------------------------------");
            System.out.println ("[ number of actions: " + solution.length + " ]");
            System.out.println ("[ number of nodes: " + t.getNodeCount () + " ]");
            System.out.println ("[ number of leafes: " + t.getLeafes ().size () + " ]");
            System.out.println ("------------------------------");
            System.out.println ("");
            
            for (int i = 0; i < solution.length;i++)
            {
                System.out.println ("[ "+ i + " ]  " + solution[i]);
            }
            
            System.out.println ("");
            System.out.println ("------------------------------");
            System.out.println ("");
            
        } //endif (heuristic > 0)
        else
        {
            System.out.println ("[ solution equals initial state! ]");
            System.out.println ("");
            System.out.println ("------------------------------");
            System.out.println ("");
        }
    }
    
    /*
     * Returnes Leaf with lowest sum (costs+heuristic).
     *
     * @param node any node of the tree, as we have
     *             access to leafes from any node of
     *             the tree.
     */
    private TreeNode getLeafWithLowestSum (TreeNode node)
    {
        int sum = Integer.MAX_VALUE;
        TreeNode min = null;
        
        for (TreeNode t : node.getLeafes () )
        {
            if ( t.getSum () < sum )
            {
                sum = t.getSum ();
                min = t;
            }
        }
        return min;
    }
    
    
    /**
     * This optimization method is used for hill climbing. It is not in use in
     * the final version as this was just a test.
     */
    public void optimizeTree (TreeNode t)
    {
        ArrayList<TreeNode> delList = new ArrayList<TreeNode>();
        
        for (TreeNode i : root.getLeafes ())
        {
            if ( i != t )
            {
                delList.add (i);
            }
        }
        
        for (TreeNode i : delList)
        {
            i.getParent ().remove (i);
            root.getLeafes ().remove (i);
        }
    }
    
    
    /**
     * Returns the solution as String array.
     */
    public String[] getSolution ()
    {
        return solution;
    }
}
