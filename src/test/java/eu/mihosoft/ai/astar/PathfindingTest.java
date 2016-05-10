package eu.mihosoft.ai.astar;

import eu.mihosoft.ai.astar.pathfinding.DiagonalHeuristic;
import eu.mihosoft.ai.astar.pathfinding.GoInDirAction;
import eu.mihosoft.ai.astar.pathfinding.ManhattanHeuristic;
import eu.mihosoft.ai.astar.pathfinding.PositionGoal;
import eu.mihosoft.ai.astar.pathfinding.XY;
import eu.mihosoft.ai.astar.pathfinding.XYState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class PathfindingTest {

    private final List<XY> obstacles;
    private final Map<XY, Double> costs;

    public PathfindingTest() {
        obstacles = new ArrayList<>();
        costs = new HashMap<>();

        //        costs.put(new XY(1, -4), 5.0);
//        costs.put(new XY(1, -3), 5.0);
//        costs.put(new XY(1, -2), 5.0);
//        costs.put(new XY(1, -1), 5.0);
//        costs.put(new XY(1, 0), 5.0);
//        costs.put(new XY(1, 1), 5.0);
//        costs.put(new XY(1, 2), 5.0);
//        costs.put(new XY(0, 2), 5.0);
//        costs.put(new XY(-1, 2), 5.0);
//        costs.put(new XY(-2, 2), 5.0);
//        costs.put(new XY(-3, 2), 5.0);
//        costs.put(new XY(-4, 2), 5.0);
//        costs.put(new XY(-5, 2), 5.0);
//        costs.put(new XY(-6, 2), 5.0);
//        costs.put(new XY(-7, 2), 5.0);
        obstacles.add(new XY(2, 0));
        obstacles.add(new XY(2, 1));
        obstacles.add(new XY(2, 2));
        obstacles.add(new XY(2, 3));
        obstacles.add(new XY(2, 4));
        obstacles.add(new XY(2, 5));

        obstacles.add(new XY(5, 3));
        obstacles.add(new XY(5, 4));
        obstacles.add(new XY(5, 5));
        obstacles.add(new XY(5, 6));

    }

    public WorldDescription<XY> createManhattanWorldDescription() {
        GoInDirAction left = new GoInDirAction(new XY(-1, 0), "left", obstacles, costs);
        GoInDirAction right = new GoInDirAction(new XY(1, 0), "right", obstacles, costs);
        GoInDirAction up = new GoInDirAction(new XY(0, -1), "up", obstacles, costs);
        GoInDirAction down = new GoInDirAction(new XY(0, 1), "down", obstacles, costs);

        GoInDirAction upLeft = new GoInDirAction(new XY(-1, -1), "up-left", obstacles, costs);
        GoInDirAction upRight = new GoInDirAction(new XY(1, -1), "up-right", obstacles, costs);
        GoInDirAction rightDown = new GoInDirAction(new XY(1, 1), "down-right", obstacles, costs);
        GoInDirAction leftDown = new GoInDirAction(new XY(-1, 1), "down-left", obstacles, costs);

        ArrayList<Action<XY>> squareActions = new ArrayList<>();

        squareActions.add(left);
        squareActions.add(right);
        squareActions.add(up);
        squareActions.add(down);

        ArrayList<Action<XY>> squareAndDiagonal = new ArrayList<>(squareActions);

        squareAndDiagonal.add(upLeft);
        squareAndDiagonal.add(upRight);
        squareAndDiagonal.add(rightDown);
        squareAndDiagonal.add(leftDown);

        WorldDescription<XY> w = new WorldDescription<>(
                new XYState(new XY(0, 1)),
                new PositionGoal(new XY(8, 4)),
                squareActions, new ManhattanHeuristic());

        return w;
    }

    public WorldDescription<XY> createDiagonalWorldDescription() {
        GoInDirAction left = new GoInDirAction(new XY(-1, 0), "left", obstacles, costs);
        GoInDirAction right = new GoInDirAction(new XY(1, 0), "right", obstacles, costs);
        GoInDirAction up = new GoInDirAction(new XY(0, -1), "up", obstacles, costs);
        GoInDirAction down = new GoInDirAction(new XY(0, 1), "down", obstacles, costs);

        GoInDirAction upLeft = new GoInDirAction(new XY(-1, -1), "up-left", obstacles, costs);
        GoInDirAction upRight = new GoInDirAction(new XY(1, -1), "up-right", obstacles, costs);
        GoInDirAction rightDown = new GoInDirAction(new XY(1, 1), "down-right", obstacles, costs);
        GoInDirAction leftDown = new GoInDirAction(new XY(-1, 1), "down-left", obstacles, costs);

        ArrayList<Action<XY>> squareActions = new ArrayList<>();

        squareActions.add(left);
        squareActions.add(right);
        squareActions.add(up);
        squareActions.add(down);

        ArrayList<Action<XY>> squareAndDiagonal = new ArrayList<>(squareActions);

        squareAndDiagonal.add(upLeft);
        squareAndDiagonal.add(upRight);
        squareAndDiagonal.add(rightDown);
        squareAndDiagonal.add(leftDown);

        WorldDescription<XY> w2 = new WorldDescription<>(
                new XYState(new XY(0, 1)),
                new PositionGoal(new XY(8, 4)),
                squareAndDiagonal, new DiagonalHeuristic());

        return w2;
    }

    private void createTest(String expected, WorldDescription<XY> w) {
        AStar<XY> solver = new AStar<>(w);
        solver.run();
        Action[] solutionCommands = solver.getSolution();

        String solution = "";

        for (Action action : solutionCommands) {
            solution += action.getName() + ";";
        }

        Assert.assertEquals(expected, solution);
    }

    @Test
    public void testPathfindingManhattan() {
        createTest(
                "right;up;up;right;right;right;right;right;right;right;down;down;down;down;down;",
                createManhattanWorldDescription());
    }

    @Test
    public void testPathfindingDiagonal() {
        createTest("up-right;up-right;right;down-right;down-right;down-right;down-right;down-right;",
                createDiagonalWorldDescription());
    }

}