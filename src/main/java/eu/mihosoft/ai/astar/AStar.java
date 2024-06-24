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
 * The AStar class implements the A* search algorithm, a popular pathfinding and graph traversal algorithm.
 * This implementation is domain-independent and can be applied to various problem spaces.
 *
 * <p>The A* algorithm uses a best-first search approach and finds the least-cost path from a given initial
 * state to a goal state. It uses a heuristic function to estimate the cost from any given state to the goal,
 * which guides the search towards the most promising paths.</p>
 *
 * <p>This implementation uses a set-based representation for actions and states, allowing for flexible
 * problem modeling.</p>
 *
 * @param <T> The type of elements in the state representation
 * @author miho
 */
public class AStar<T> {

    // The world description containing problem-specific information
    private WorldDescription<T> w;

    // The root node of the search tree
    private TreeNode<T> root;

    // The array of actions representing the solution path
    private Action[] solution;

    /**
     * Creates a new instance of AStar.
     *
     * @param w A WorldDescription object containing the initial state, goal state, action set,
     *          and problem-specific heuristic. The WorldDescription encapsulates the problem
     *          domain, allowing the AStar algorithm to remain domain-independent.
     */
    public AStar(WorldDescription<T> w) {
        this.w = w;
        // Initialize the root node with the initial state and its heuristic value
        root = new TreeNode<>(w.initialState(),
                w.heuristic().estimate(w.initialState(), w.goal(), w));

        // Print initialization information
        System.out.println("");
        System.out.println(">> A-Star initialized!");
        System.out.println("");
        System.out.println("------------------------------");
        System.out.println("[ state length : " + w.initialState().size() + " ]");
        System.out.println("[ number of actions: " + w.actionSet().size() + " ]");
        System.out.println("[ initial distance: " + root.getHeuristic() + " ]");
        System.out.println("------------------------------");
        System.out.println("");
    }

    /**
     * Executes the A* algorithm to find a solution path from the initial state to the goal state.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Initializes the search tree with the initial state as the root</li>
     *   <li>Iteratively expands the most promising leaf node (lowest f-score)</li>
     *   <li>Generates child nodes by applying available actions</li>
     *   <li>Evaluates each new state using the heuristic function</li>
     *   <li>Continues until the goal state is reached or all possibilities are exhausted</li>
     * </ol>
     *
     * <p>If a solution is found, it populates the {@code solution} array with the sequence of actions
     * leading from the initial state to the goal state.</p>
     */
    public void run() {
        solution = null;

        System.out.println(">> Running:");
        System.out.println("");

        int maxDepth = 0;
        double heuristic;
        boolean accepted = false;

        // Check if the initial state is already the goal state
        if (!w.goal().verify(w.initialState())) {
            TreeNode<T> t = root;

            // Main loop: continue until goal is reached or no more actions are available
            while (!accepted && !w.actionSet().isEmpty()) {
                // Get the most promising leaf node
                t = getLeafWithLowestSum(root);

                // Try to apply each action to the current state
                for (Action<T> a : w.actionSet()) {
                    // Clone the current state to create a new state
                    State<T> sNew = t.getState().clone();
                    boolean performable = a.perform(sNew, sNew);

                    if (performable) {
                        // Calculate heuristic for the new state
                        heuristic = w.heuristic().estimate(sNew, w.goal(), w);
                        // Add the new state as a child node
                        t.add(new TreeNode<T>(sNew, heuristic));

                        // Update max depth reached (for informational purposes)
                        if (t.getDistanceToRoot() > maxDepth) {
                            maxDepth = t.getDistanceToRoot();
                            System.out.println("[ path depth: " + maxDepth + " ]");
                        }

                        // Check if the goal state is reached
                        accepted = w.goal().verify(sNew);

                        if (accepted) {
                            break;
                        }
                    }
                }
            }

            // If a solution is found, backtrack to construct the solution path
            if (accepted) {
                // Find the last added node (goal state)
                t = t.get(t.size() - 1);

                solution = new Action[t.getDistanceToRoot()];

                // Backtrack from goal to root, storing actions
                while (t.getParent() != null) {
                    for (int i = t.getDistanceToRoot() - 1; i >= 0; i--) {
                        solution[i] = t.getState().getAction();
                        t = t.getParent();
                    }
                }

                // Print solution information
                System.out.println("");
                System.out.println(">> Solution found! ");
                System.out.println("");
                System.out.println("------------------------------");
                System.out.println("[ number of actions: " + solution.length + " ]");
                System.out.println("[ number of nodes: " + t.getNodeCount() + " ]");
                System.out.println("[ number of leaves: " + t.getLeafs().size() + " ]");
                System.out.println("------------------------------");
                System.out.println("");

                // Print each action in the solution
                for (int i = 0; i < solution.length; i++) {
                    System.out.println("[ " + i + " ]  " + solution[i].getName());
                }

                System.out.println("");
                System.out.println("------------------------------");
                System.out.println("");
            }
        } else {
            // Initial state is already the goal state
            solution = new Action[0];

            System.out.println("[ goal reached in initial state! ]");
            System.out.println("");
            System.out.println("------------------------------");
            System.out.println("");
        }
    }

    /**
     * Finds the leaf node with the lowest f-score (g-score + heuristic) in the search tree.
     * This method is crucial for the A* algorithm's efficiency, as it determines which node
     * to expand next.
     *
     * @param node Any node in the tree; typically the root node
     * @return The leaf node with the lowest f-score
     */
    private TreeNode<T> getLeafWithLowestSum(TreeNode<T> node) {
        double sum = Double.MAX_VALUE;
        TreeNode<T> min = null;

        // Iterate through all leaf nodes
        for (TreeNode<T> t : node.getLeafs()) {
            // Compare the f-score (sum of cost and heuristic)
            if (t.getSum() < sum) {
                sum = t.getSum();
                min = t;
            }
        }
        return min;
    }

    /**
     * Returns the solution found by the A* algorithm, if any.
     *
     * @return An array of Action objects representing the solution path from the initial state
     *         to the goal state, or null if no solution was found
     */
    public Action[] getSolution() {
        return solution;
    }

    /**
     * This optimization method was used for hill climbing experiments.
     * It is not used in the final version of the A* implementation.
     *
     * @param t The node to optimize around
     */
    public void optimizeTree(TreeNode<T> t) {
        ArrayList<TreeNode<T>> delList = new ArrayList<>();

        // Find all leaf nodes except the given node
        for (TreeNode<T> i : root.getLeafs()) {
            if (i != t) {
                delList.add(i);
            }
        }

        // Remove all other leaf nodes
        for (TreeNode<T> i : delList) {
            i.getParent().remove(i);
            root.getLeafs().remove(i);
        }
    }

}