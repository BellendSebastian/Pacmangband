package com.diquebutte.pacmangband;

import java.util.Random;

public class MapGenerator {
    private int width;
    private int height;
    private int spawns;
    private int moves;

    private int[][] grid;

    public MapGenerator(int width, int height, int spawns, int moves) {
        this.width = width;
        this.height = height;
        this.spawns = spawns;
        this.moves = moves;
        grid = new int[height][width];
        fill();
        carve();
        mirrorGrid();
    }

    private void fill() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x] = 1;
            }
        }
    }

    private void carve() {
        int startX = grid[0].length;
        Random rand = new Random();
        for (int i = 0; i < spawns; i++) {
            int startY = rand.nextInt(grid.length);
            saunter(startX, startY);
        }
    }

    private void saunter(int x, int y) {
        int currentX = x;
        int currentY = y;
        int currentDirection = 3;
        Random rand = new Random();
        for (int i = 0; i < moves; i++) {
            if (rand.nextInt(10) % 2 == 0) {
                currentDirection = getDirection(currentX, currentY);
            }
            switch (currentDirection) {
                case 0:
                    currentY--;
                    break;
                case 1:
                    currentX++;
                    break;
                case 2:
                    currentY++;
                    break;
                case 3:
                    currentX--;
                    break;
            }
            carveBlock(currentX, currentY);
        }
    }

    private void carveBlock(int x, int y) {
        if (x > 0 && x < grid[0].length && y > 0 && y < grid.length - 1) {
            grid[y][x] = 0;
        }
    }

    private int getDirection(int x, int y) {
        int direction = 0;
        int[] dirs = {0, 0, 1, 1, 2, 2, 3, 3};
        Random rand = new Random();
        shuffleArray(dirs);
        if (x == grid[0].length) {
            direction = 3;
        }
        if (rand.nextInt(10) % 2 == 0) {
            direction = 3;
        }
        for (int i = 0; i <= 3; i++) {
            if (checkTile(dirs[i], x, y)) {
                direction = dirs[i];
            }
        }
        return direction;
    }

    static void shuffleArray(int[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i >= 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private boolean checkTile(int dir, int x, int y) {
        boolean ret;
        if (dir == 0) {
            ret = (checkWall(x, y -1)) ? true : false;
        } else if (dir == 1) {
            ret = (checkWall(x + 1, y)) ? true : false;
        } else if (dir == 2) {
            ret = (checkWall(x, y + 1)) ? true : false;
        } else {
            ret = (checkWall(x - 1, y)) ? true : false;
        }
        return ret;
    }

    private boolean checkInBounds(int x, int y) {
        return (x < 0 && x > grid[0].length - 1 && y < 0 && y > grid.length - 1);
    }

    private boolean checkWall(int x, int y) {
        if (checkInBounds(x, y)) {
            return (grid[y][x] == 1);
        } else {
            return false;
        }
    }

    private void mirrorGrid() {
        int[][] mirror = new int[height][width];
        for (int y = 0; y < grid.length; y++) {
            for (int x = grid[0].length; x > 0; x--) {
                mirror[y][x] = grid[y][x];
            }
        }
    }
}