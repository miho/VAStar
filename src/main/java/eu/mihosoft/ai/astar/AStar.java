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
import java.util.List;

/**
 * The AStar class contains an implementation of the well known A* algorithm. It
 * is completely independent of the domain/problem and the heuristic.
 *
 * @author miho
 */
public class AStar<T> {

    private WorldDescription<T> w;
    private TreeNode<T> root;
    private Action[] solution;

    /**
     * Creates a new instance of AStar
     *
     * @param w a WorldDescription containing initial and final states and the
     * actionSet as we use set based instead of classical representation.
     */
    public AStar(WorldDescription<T> w) {
        this.w = w;
        root = new TreeNode<>(w.getInitialState(),
                w.getHeuristic().estimate(w.getInitialState(), w.getGoal(), w));

        System.out.println("");
        System.out.println(">> A-Star initialized!");
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("[ state length : " + w.getInitialState().size() + " ]");
        System.out.println("[ number of actions: " + w.getActionSet().size() + " ]");
        System.out.println("[ initial distance: " + root.getHeuristic() + " ]");
        System.out.println("------------------------------");
        System.out.println("");
    }

    /**
     * starts the planning process.
     */
    public void run() {
        System.out.println(">> Running:");
        System.out.println("");
//        boolean foundSolution = false;
        int counter = 0;
        int maxDepth = 0;

        TreeNode<T> bestNode = null;
        double heuristic = root.getHeuristic();
//        double minHeuristic = heuristic;

        boolean accepted = false;

//        HelpfulActions actionFilter = new HelpfulActions(w.getInitialState (),w);
        if (!w.getGoal().verify(w.getInitialState())) {
            TreeNode<T> t = root;

            while (!accepted) {
                counter++;

                t = getLeafWithLowestSum(root);

//                System.out.println("min: " + t.getState() + ", h: " + t.getHeuristic());
                for (Action a : w.getActionSet()) {

                    State<T> sNew = t.getState().clone();

                    boolean performable = a.perform(sNew, sNew);

//                    System.out.println("s: " + sNew + ", performable: " + performable + ", a: " + a.getName());
                    if (!performable) {
//                        System.out.println(" --> rejecting s: " + sNew + ", h: " + heuristic + ", a: " + a.getName());
                    }
                    if (performable) //                    if (actionFilter.verifyAction (sNew,a))
                    {

                        heuristic = w.getHeuristic().estimate(sNew, w.getGoal(), w);

//                        System.out.println(" --> adding s: " + sNew + ", h: " + heuristic + ", a: " + a.getName());
                        t.add(new TreeNode(sNew, heuristic));

                        if (t.getDistanceToRoot()> maxDepth) {
                            maxDepth = t.getDistanceToRoot();
                            System.out.println("[ path depth: " + maxDepth + " ]");
                        }

                        accepted = w.getGoal().verify(sNew);

                        if (accepted) {
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
            t = t.get(t.size() - 1);

            solution = new Action[t.getDistanceToRoot()];
            

            while (t.getParent() != null) {
                for (int i = t.getDistanceToRoot() - 1; i >= 0; i--) {
                    solution[i]=t.getState().getAction();
                    t = t.getParent();
                }
            }


            System.out.println("");
            System.out.println(">> Solution found! ");

            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("[ number of actions: " + solution.length + " ]");
            System.out.println("[ number of nodes: " + t.getNodeCount() + " ]");
            System.out.println("[ number of leafes: " + t.getLeafes().size() + " ]");
            System.out.println("------------------------------");
            System.out.println("");

            for (int i = 0; i < solution.length; i++) {
                System.out.println("[ " + i + " ]  " + solution[i].getName());
            }

            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("");

        } //endif (heuristic > 0)
        else {
            System.out.println("[ goal reached in initial state! ]");
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("");
        }
    }

    /*
     * Returnes Leaf with lowest sum (costs+heuristic).
     *
     * @param node any node of the tree, as we have
     *             access to leafes from any node of
     *             the tree.
     */
    private TreeNode<T> getLeafWithLowestSum(TreeNode<T> node) {
        double sum = Integer.MAX_VALUE;
        TreeNode min = null;

        for (TreeNode<T> t : node.getLeafes()) {
            if (t.getSum() < sum) {
                sum = t.getSum();
                min = t;
            }
        }
        return min;
    }

    /**
     * This optimization method is used for hill climbing. It is not in use in
     * the final version as this was just a test.
     * @param t
     */
    public void optimizeTree(TreeNode<T> t) {
        ArrayList<TreeNode<T>> delList = new ArrayList<>();

        for (TreeNode<T> i : root.getLeafes()) {
            if (i != t) {
                delList.add(i);
            }
        }

        for (TreeNode<T> i : delList) {
            i.getParent().remove(i);
            root.getLeafes().remove(i);
        }
    }

    /**
     * Returns the solution as String array.
     * @return 
     */
    public Action[] getSolution() {
        return solution;
    }
}
