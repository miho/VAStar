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
 * A TreeNode represents a node in the graph. It is an ArrayList of TreeNodes
 * itself.
 *
 * @author miho
 */
public class TreeNode<T> extends ArrayList<TreeNode<T>> {

    private final State<T> state;
    public int ID;
    private final double heuristic;
    private int distanceToRoot;
    private double costs;
    private double sum;
    private static int nodeCount = 0;

    private TreeNode<T> parent;

    private ArrayList<TreeNode<T>> leafes;

    /**
     * Creates a new instance of TreeNode.
     *
     * @param s State that is represented by the TreeNode.
     * @param heuristic The Heuristic (estimated distance) between the node and
     * the goal.
     */
    public TreeNode(State<T> s, double heuristic) {
        this.state = (State) s.clone();
        this.heuristic = heuristic;
        this.costs = 0;
        this.distanceToRoot = 0;
        this.sum = heuristic + costs;

        nodeCount++;

        initLeafes();
    }

    /**
     * Adds a TreeNode as child node.
     *
     * @param t Child node.
     */
    @Override
    public boolean add(TreeNode<T> t) {
        t.leafes = this.leafes;
        t.costs = this.getCosts() + t.getState().getAction().getCosts();
        t.distanceToRoot = this.getDistanceToRoot()+1;
        t.sum = t.costs + t.heuristic;
        t.parent = this;

        boolean b = super.add(t);

        for (int i = 0; i < leafes.size(); i++) {
            if (leafes.get(i) == this) {
                leafes.remove(i);
            }
        }

        return addToLeafes(t);
    }

    /**
     * Each TreeNode that has been added and that is a leaf - one could also add
     * a subtree - will be added to an array of leafes. If a leaf with equal
     * state exists already, the one with lower costs is used. The other one
     * will be removed.
     *
     * @param t the node to be added.
     */
    private boolean addToLeafes(TreeNode<T> t) {
        // do we already have an equal state with lower costs?
        // then we do not add t. otherwise: add t and delete existing leaf.

        boolean equalStateExists = false;
        TreeNode tmpNode = null;

        for (TreeNode<T> i : leafes) {
            if (i.getState().equals(t.getState())) {
                equalStateExists = true;
                tmpNode = i;
            }
        }

        if (equalStateExists) {
            boolean tIsBetter = tmpNode.getCosts() > t.getCosts();

            if (tIsBetter) {
                leafes.add(t);

                if (tmpNode.getParent() != null) {
                    TreeNode tmpParent = tmpNode.getParent();
                    tmpParent.remove(tmpNode);
                }

                leafes.remove(tmpNode);
            }
        } else {
            leafes.add(t);
            return super.add(t);
        }

        return false;
    }

    /**
     * Checks if TreeNode is a leaf.
     */
    public boolean isLeaf() {
        return isEmpty();
    }

    /**
     * Returns heuristic value of the TreeNode.
     */
    public double getHeuristic() {
        return heuristic;
    }

    /**
     * Returns costs of the TreeNode.
     */
    public double getCosts() {
        return costs;
    }

    /**
     * Returns sum (costs + heurustic) of the TreeNode.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Returns the State that is represented by the TreeNode.
     */
    public State<T> getState() {
        return state;
    }

//    /**
//     * Sets the sum of the TreeNode.
//     *
//     * @param sum The sum.
//     */
//    public void setSum (double sum)
//    {
//        this.sum = sum;
//    }
    /**
     * Initializes leaf array if it is not initialized already. This will be
     * done for root node only.
     */
    public final void initLeafes() {
        if ((leafes == null) && (parent == null)) {
            leafes = new ArrayList<TreeNode<T>>();
            leafes.add(this);
        }
    }

    /**
     * Returns a reference on the leaf list.
     */
    public ArrayList<TreeNode<T>> getLeafes() {
        return leafes;
    }

    /**
     * Returns a reference to the parent node (null if node is the root node)
     */
    public TreeNode<T> getParent() {
        return parent;
    }

    /**
     * Returns number
     */
    public int getNodeCount() {
        return nodeCount;
    }

    /**
     * @return the distanceToRoot
     */
    public int getDistanceToRoot() {
        return distanceToRoot;
    }
}
