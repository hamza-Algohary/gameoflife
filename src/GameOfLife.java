public class GameOfLife {
    private boolean[][] matrix1 , matrix2;
    private int width , height;

    public GameOfLife(int width , int height) {
        matrix1 = new boolean[width][height];
        matrix2 = new boolean[width][height];
        this.width = width;
        this.height = height;
    }

    public void update() {
        for(int x=0 ; x<matrix1.length ; x++) {
            for(int y=0 ; y<matrix1[x].length ; y++) {
                int number = numberOfLiveNeighbours(x, y);
                if (number < 2 || number > 3) {
                    matrix2[x][y] = false;
                } else if (number == 3) {
                    matrix2[x][y] = true;
                } else {
                    matrix2[x][y] = matrix1[x][y];
                }
            }
        }
        swap();
    }

    public void swap() {
        boolean[][] tmp = matrix1;
        matrix1 = matrix2;
        matrix2 = tmp;
    }

    private int numberOfLiveNeighbours(int x , int y) {
        int number = 0;
        for (int i=x-1 ; i<=x+1 ; i++) {
            for (int j=y-1 ; j<=y+1 ; j++) {
                if (getCell(i, j) && !(i==x && j==y)) {
                    number++;
                }
            }
        }
        return number;
    }

    public void toggleCell(int x , int y) {
        setCell(x, y, !getCell(x, y));
    }

    public boolean getCell(int x , int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return false;
        }
        return matrix1[x][y];
    }

    public void setCell(int x , int y , boolean value) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        matrix1[x][y] = value;
    }
}


/*
 * Concepts:
 * 1. References.
 * 
 */