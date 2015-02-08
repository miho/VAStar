package eu.mihosoft.ai.astar;

import eu.mihosoft.ai.astar.pathfinding.DiagonalHeuristic;
import eu.mihosoft.ai.astar.pathfinding.GoInDirAction;
import eu.mihosoft.ai.astar.pathfinding.ManhattanHeuristic;
import eu.mihosoft.ai.astar.pathfinding.PositionGoal;
import eu.mihosoft.ai.astar.pathfinding.XY;
import eu.mihosoft.ai.astar.pathfinding.XYState;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<XY> obstacles = new ArrayList<>();

        obstacles.add(new XY(2, -5));
        obstacles.add(new XY(2, -4));
        obstacles.add(new XY(2, -3));
        obstacles.add(new XY(2, -2));
        obstacles.add(new XY(2, -1));
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

        GoInDirAction left = new GoInDirAction(new XY(-1, 0), "left", obstacles);
        GoInDirAction right = new GoInDirAction(new XY(1, 0), "right", obstacles);
        GoInDirAction up = new GoInDirAction(new XY(0, -1), "up", obstacles);
        GoInDirAction down = new GoInDirAction(new XY(0, 1), "down", obstacles);
        
        GoInDirAction upLeft = new GoInDirAction(new XY(-1, -1), "up-left", obstacles);
        GoInDirAction upRight = new GoInDirAction(new XY(1, -1), "up-right", obstacles);
        GoInDirAction rightDown = new GoInDirAction(new XY(1, 1), "down-right", obstacles);
        GoInDirAction leftDown = new GoInDirAction(new XY(-1, 1), "down-left", obstacles);

        ArrayList<Action> squareActions = new ArrayList<>();

        squareActions.add(left);
        squareActions.add(right);
        squareActions.add(up);
        squareActions.add(down);
        
        ArrayList<Action> squareAndDiagonal = new ArrayList<>(squareActions);
        
        squareAndDiagonal.add(upLeft);
        squareAndDiagonal.add(upRight);
        squareAndDiagonal.add(rightDown);
        squareAndDiagonal.add(leftDown);

        WorldDescription w = new WorldDescription(
                new XYState(new XY(0, 1)),
                new PositionGoal(new XY(8, 4)),
                squareActions, new ManhattanHeuristic());

        WorldDescription w2 = new WorldDescription(
                new XYState(new XY(0, 1)),
                new PositionGoal(new XY(8, 4)),
                squareAndDiagonal, new DiagonalHeuristic());

        AStar solver = new AStar(w);
        solver.run();

        AStar solver2 = new AStar(w2);
        solver2.run();

        System.out.println("done.");
    }
}
