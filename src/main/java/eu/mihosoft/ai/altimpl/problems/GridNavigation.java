package eu.mihosoft.ai.altimpl.problems;

import eu.mihosoft.ai.altimpl.AStar;
import eu.mihosoft.ai.altimpl.RelaxedProblemHeuristic;


import java.util.*;


// Main class to demonstrate the usage
public class GridNavigation {
    public static void main(String[] args) {
        int width  = 5;
        int height = 5;
        boolean[][] obstacles = new boolean[height][width];
        obstacles[1][2] = true;
        obstacles[2][2] = true;
        obstacles[3][2] = true;

        GridPosition start = new GridPosition(0, 0);
        GridPosition goal = new GridPosition(4, 4);

        GridWorldState initialState = new GridWorldState(width, height, obstacles, start, goal);

        RelaxedProblemHeuristic heuristic = new RelaxedProblemHeuristic();

        AStar astar = new AStar();
        List<AStar.Action> path = astar.search(initialState, heuristic);

        if (path != null) {
            System.out.println("Path found:");
            for (AStar.Action action : path) {
                MoveAction move = (MoveAction) action;
                System.out.println("Move: (" + move.dx + ", " + move.dy + ")");
            }
        } else {
            System.out.println("No path found.");
        }

        visualizeGrid(initialState, path);
    }

    public static void visualizeGrid(GridWorldState initialState, List<AStar.Action> path) {
        int width = initialState.getWidth();
        int height = initialState.getHeight();
        char[][] grid = new char[height][width];

        // Fill the grid with empty spaces
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = '.';
            }
        }

        // Mark obstacles
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (initialState.getObstacles()[y][x]) {
                    grid[y][x] = '#';
                }
            }
        }

        // Mark start and goal positions
        grid[initialState.getCurrentPosition().y][initialState.getCurrentPosition().x] = 'S';
        grid[initialState.getGoalPosition().y][initialState.getGoalPosition().x] = 'G';

        // Mark path
        if (path != null) {
            int x = initialState.getCurrentPosition().x;
            int y = initialState.getCurrentPosition().y;
            for (AStar.Action action : path) {
                MoveAction move = (MoveAction) action;
                x += move.dx;
                y += move.dy;
                if (grid[y][x] == '.') {
                    grid[y][x] = '*';
                }
            }
        }

        // Print the grid
        System.out.println("Grid Visualization:");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }

        // Print legend
        System.out.println("\nLegend:");
        System.out.println("S - Start");
        System.out.println("G - Goal");
        System.out.println("# - Obstacle");
        System.out.println("* - Path");
        System.out.println(". - Empty space");

        // Available actions
        System.out.println("\nAvailable Actions from Start:");
        for (AStar.Action action : initialState.getApplicableActions()) {
            System.out.println(action);
        }
    }

    private static String getDirectionName(int dx, int dy) {
        if (dx == 0 && dy == -1) return "Up";
        if (dx == 0 && dy == 1) return "Down";
        if (dx == -1 && dy == 0) return "Left";
        if (dx == 1 && dy == 0) return "Right";
        return "Unknown";
    }


    // GridPosition represents a position on the grid
    public static class GridPosition {
        int x, y;

        GridPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GridPosition that = (GridPosition) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // GridWorldState represents the state of the grid world
    public static class GridWorldState implements RelaxedProblemHeuristic.RelaxableWorldState {
        private final int width, height;
        private final boolean[][] obstacles;
        private final GridPosition currentPosition;
        private final GridPosition goalPosition;

        GridWorldState(int width, int height, boolean[][] obstacles, GridPosition currentPosition, GridPosition goalPosition) {
            this.width = width;
            this.height = height;
            this.obstacles = obstacles;
            this.currentPosition = currentPosition;
            this.goalPosition = goalPosition;
        }

        public GridPosition getGoalPosition() {
            return goalPosition;
        }

        public boolean[][] getObstacles() {
            return obstacles;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        @Override
        public boolean isGoal() {
            return currentPosition.equals(goalPosition);
        }

        public GridPosition getCurrentPosition() {
            return currentPosition;
        }

        @Override
        public List<AStar.Action> getApplicableActions() {
            List<AStar.Action> actions = new ArrayList<>();
            actions.add(new MoveAction(0, 1));  // Up
            actions.add(new MoveAction(0, -1)); // Down
            actions.add(new MoveAction(1, 0));  // Right
            actions.add(new MoveAction(-1, 0)); // Left
            return actions;
        }

        @Override
        public double getCost(AStar.Action action) {
            return 1.0; // Uniform cost for all moves
        }


        @Override
        public Set<Object> getGoals() {
            Set<Object> goals = new HashSet<>();
            goals.add(goalPosition);
            return goals;
        }

        @Override
        public Set<Object> getAchievedGoals() {
            Set<Object> achieved = new HashSet<>();
            if (isGoal()) {
                achieved.add(goalPosition);
            }
            return achieved;
        }

        @Override
        public RelaxedProblemHeuristic.RelaxableWorldState applyRelaxed(AStar.Action action) {
            if (action instanceof MoveAction) {
                MoveAction moveAction = (MoveAction) action;
                int newX = currentPosition.x + moveAction.dx;
                int newY = currentPosition.y + moveAction.dy;

                // In relaxed problem, ignore obstacles
                if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                    return new GridWorldState(width, height, obstacles, new GridPosition(newX, newY), goalPosition);
                }
            }
            return this;
        }

        boolean isValidMove(int x, int y) {
            return x >= 0 && x < width && y >= 0 && y < height && !obstacles[y][x];
        }
    }

    // MoveAction represents a move in the grid
    public static class MoveAction implements RelaxedProblemHeuristic.RelaxableAction {
        int dx, dy;

        MoveAction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public boolean isApplicable(AStar.WorldState state) {
            if (state instanceof GridWorldState gridState) {
                int newX = gridState.getCurrentPosition().x + dx;
                int newY = gridState.getCurrentPosition().y + dy;
                return gridState.isValidMove(newX, newY);
            }
            return false;
        }

        @Override
        public AStar.WorldState apply(AStar.WorldState state) {
            if (state instanceof GridWorldState gridState) {
                int newX = gridState.getCurrentPosition().x + dx;
                int newY = gridState.getCurrentPosition().y + dy;
                return new GridWorldState(gridState.getWidth(), gridState.getHeight(),
                        gridState.getObstacles(), new GridPosition(newX, newY), gridState.getGoalPosition());
            }
            return state;
        }

        @Override
        public Set<Object> getRelaxedEffects(RelaxedProblemHeuristic.RelaxableWorldState state) {
            if (state instanceof GridWorldState gridState) {
                int newX = gridState.getCurrentPosition().x + dx;
                int newY = gridState.getCurrentPosition().y + dy;
                Set<Object> effects = new HashSet<>();
                effects.add(new GridPosition(newX, newY));
                return effects;
            }
            return Collections.emptySet();
        }

        @Override
        public String toString() {
            return "Move: (%2d,%2d)".formatted(dx, dy);
        }

    }

}