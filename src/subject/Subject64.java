package subject;


public class Subject64 {
//    public int minPathSum(int[][] grid) {
//        return calculate(grid, 0, 0);
//    }
//
//    public int calculate(int[][] grid, int i, int j) {
//        if ((i == grid.length) || (j == grid[0].length)) return Integer.MAX_VALUE;
//        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
//        return grid[i][j] + Math.min(calculate(grid, i + 1, j), calculate(grid, i, j + 1));
//    }
    // 暴力搜索

    //grid[i][j]=grid[i][j]+min(grid[i+1][j],grid[i][j+1])
    public int minPathSum(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                //i+1或者j+1的位置已经计算过了
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    grid[i][j] = grid[i][j] +  grid[i][j + 1];//只能往右走的情况
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + grid[i + 1][j];//只能往下走的情况
                else if(j != grid[0].length - 1 && i != grid.length - 1)//可以往下或者往右走的情况
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j],grid[i][j + 1]);
            }
        }
        return grid[0][0];
    }
    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(new Subject64().minPathSum(grid));
    }
}
