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
        
        solution = null;
        
        System.out.println(">> Running:");
        System.out.println("");

        int maxDepth = 0;
        double heuristic ;
        boolean accepted = false;

        if (!w.getGoal().verify(w.getInitialState())) {
            TreeNode<T> t = root;

            // while not accepted and not empty list do ...
            while (!accepted && !w.getActionSet().isEmpty()) {

                t = getLeafWithLowestSum(root);

                for (Action<T> a : w.getActionSet()) {

                    State<T> sNew = t.getState().clone();
                    boolean performable = a.perform(sNew, sNew);

                    if (performable) {

                        heuristic = w.getHeuristic().estimate(sNew, w.getGoal(), w);
                        t.add(new TreeNode<T>(sNew, heuristic));

                        if (t.getDistanceToRoot()> maxDepth) {
                            maxDepth = t.getDistanceToRoot();
                            System.out.println("[ path depth: " + maxDepth + " ]");
                        }

                        accepted = w.getGoal().verify(sNew);

                        if (accepted) {
                            break;
                        }
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
            System.out.println("[ number of leafes: " + t.getLeafs().size() + " ]");
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
            solution = new Action[0];
            
            System.out.println("[ goal reached in initial state! ]");
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("");
        }
    }

    /*
     * Returnes Leaf with the lowest sum (costs+heuristic).
     *
     * @param node any node of the tree, as we have
     *             access to leafes from any node of
     *             the tree.
     */
    private TreeNode<T> getLeafWithLowestSum(TreeNode<T> node) {
        double sum = Double.MAX_VALUE;
        TreeNode<T> min = null;

        for (TreeNode<T> t : node.getLeafs()) {
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

        for (TreeNode<T> i : root.getLeafs()) {
            if (i != t) {
                delList.add(i);
            }
        }

        for (TreeNode<T> i : delList) {
            i.getParent().remove(i);
            root.getLeafs().remove(i);
        }
    }

    /**
     * Returns the solution.
     * @return the solution or <code>null</code> if no solution has been found.
     */
    public Action[] getSolution() {
        return solution;
    }
}
