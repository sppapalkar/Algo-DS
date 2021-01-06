/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
Return the number of closed islands.
*/
public class ClosedIsland {
    private boolean checkIsland(int[][] grid, int row, int col){
        if(row >= 0 && col >= 0 && row<grid.length && col<grid[0].length && grid[row][col] == 1)
            return true;
        if(row >= 0 && col >= 0 && row<grid.length && col<grid[0].length && grid[row][col] == 0){
            grid[row][col] = 1;
            if(!checkIsland(grid, row-1, col))
                grid[row][col] = -1;
            if(grid[row][col] == 1 && !checkIsland(grid, row, col-1))
                grid[row][col] = -1;
            if(grid[row][col] == 1 && !checkIsland(grid, row+1, col))
                grid[row][col] = -1;
            if(grid[row][col] == 1 && !checkIsland(grid, row, col+1))
                grid[row][col] = -1;
            return grid[row][col] != -1; 
        }
        return false;
    }
    public int findclosedIsland(){
        int count = 0;
        int[][] grid = {{1,1,1,1,1,1,1,0},{1,0,0,0,0,1,1,0},{1,0,1,0,1,1,1,0},{1,0,0,0,0,1,0,1},{1,1,1,1,1,1,1,0}};
        for(int row = 0; row<grid.length; row++){
            for(int col = 0; col<grid[0].length; col++){
                if(grid[row][col] == 0){
                    if(checkIsland(grid, row, col)){
                        count += 1;
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        ClosedIsland ob = new ClosedIsland();
        ob.findclosedIsland();
    }
}