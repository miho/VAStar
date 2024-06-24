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
 * TreeNode.java
 *
 * Created on 12. Juli 2007, 15:38
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package eu.mihosoft.ai.astar;

import java.util.ArrayList;

/**
 * A TreeNode represents a node in the search tree for algorithms like A*.
 * It extends ArrayList to maintain a collection of child nodes, effectively
 * forming a tree structure.
 *
 * <p>Each TreeNode contains:
 * <ul>
 *   <li>A State object representing the current state</li>
 *   <li>Heuristic value estimating the distance to the goal</li>
 *   <li>Cost from the root node to this node</li>
 *   <li>Total estimated cost (sum of cost and heuristic)</li>
 *   <li>Reference to its parent node</li>
 *   <li>A list of leaf nodes in the subtree rooted at this node</li>
 * </ul>
 * </p>
 *
 * @param <T> the type of elements in the state
 * @author miho
 */
public class TreeNode<T> extends ArrayList<TreeNode<T>> {

    private final State<T> state;
    private final double heuristic;
    private int distanceToRoot;
    private double costs;
    private double sum;
    private static int nodeCount = 0;

    private TreeNode<T> parent;

    private ArrayList<TreeNode<T>> leafs;

    /**
     * Creates a new instance of TreeNode.
     *
     * @param s State that is represented by the TreeNode.
     * @param heuristic The heuristic (estimated distance) between the node and the goal.
     */
    public TreeNode(State<T> s, double heuristic) {
        this.state = (State<T>) s.clone();
        this.heuristic = heuristic;
        this.costs = 0;
        this.distanceToRoot = 0;
        this.sum = heuristic + costs;

        nodeCount++;

        initLeafes();
    }

    /**
     * Adds a TreeNode as a child node.
     * This method updates the child node's properties and manages the leaf list.
     *
     * @param t Child node to be added.
     * @return true if the node was successfully added, false otherwise.
     */
    @Override
    public boolean add(TreeNode<T> t) {
        // Set the child's leaf list to this node's leaf list
        t.leafs = this.leafs;

        // Calculate the cumulative cost to reach the child node
        t.costs = this.getCosts() + t.getState().getAction().getCosts(t.getState());

        // Update the child's distance from the root
        t.distanceToRoot = this.getDistanceToRoot() + 1;

        // Calculate the total estimated cost (f-score in A*)
        t.sum = t.costs + t.heuristic;

        // Set this node as the parent of the child
        t.parent = this;

        // Add the child to this node's children list
        boolean b = super.add(t);

        // Remove this node from the leaf list as it's no longer a leaf
        for (int i = 0; i < leafs.size(); i++) {
            if (leafs.get(i) == this) {
                leafs.remove(i);
                break;
            }
        }

        // Add the child to the leaf list, potentially replacing an existing leaf
        return addToLeafes(t);
    }

    /**
     * Manages the leaf list when adding a new node.
     * If a leaf with an equal state exists, the one with lower costs is kept.
     *
     * @param t the node to be added to the leaf list.
     * @return true if the node was added to the leaf list, false otherwise.
     */
    private boolean addToLeafes(TreeNode<T> t) {
        // Check if a leaf with the same state already exists
        TreeNode<T> existingLeaf = null;
        for (TreeNode<T> leaf : leafs) {
            if (leaf.getState().equals(t.getState())) {
                existingLeaf = leaf;
                break;
            }
        }

        if (existingLeaf != null) {
            // If an equal state exists, keep the one with lower costs
            if (existingLeaf.getCosts() > t.getCosts()) {
                leafs.add(t);

                // Remove the existing leaf from its parent
                if (existingLeaf.getParent() != null) {
                    existingLeaf.getParent().remove(existingLeaf);
                }

                leafs.remove(existingLeaf);
                return super.add(t);
            }
        } else {
            // If no equal state exists, add the new leaf
            leafs.add(t);
            return super.add(t);
        }

        return false;
    }

    /**
     * Checks if this TreeNode is a leaf (has no children).
     *
     * @return true if this node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return isEmpty();
    }

    /**
     * Returns the heuristic value of the TreeNode.
     *
     * @return the heuristic value
     */
    public double getHeuristic() {
        return heuristic;
    }

    /**
     * Returns the cost to reach this TreeNode from the root.
     *
     * @return the cost value
     */
    public double getCosts() {
        return costs;
    }

    /**
     * Returns the sum of costs and heuristic (f-score in A*) of the TreeNode.
     *
     * @return the sum of costs and heuristic
     */
    public double getSum() {
        return sum;
    }

    /**
     * Returns the State that is represented by this TreeNode.
     *
     * @return the State object
     */
    public State<T> getState() {
        return state;
    }

    /**
     * Initializes the leaf list for the root node.
     * This method is called only for the root node of the tree.
     */
    private void initLeafes() {
        if ((leafs == null) && (parent == null)) {
            leafs = new ArrayList<>();
            leafs.add(this);
        }
    }

    /**
     * Returns a reference to the leaf list.
     *
     * @return the ArrayList of leaf nodes
     */
    public ArrayList<TreeNode<T>> getLeafs() {
        return leafs;
    }

    /**
     * Returns a reference to the parent node.
     *
     * @return the parent TreeNode, or null if this is the root node
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    /**
     * Returns the total number of nodes created.
     *
     * @return the total node count
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * Returns the distance of this node from the root of the tree.
     *
     * @return the distance to the root
     */
    public int getDistanceToRoot() {
        return distanceToRoot;
    }
}
