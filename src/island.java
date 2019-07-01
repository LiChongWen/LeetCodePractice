import java.util.HashSet;
import java.util.Set;

/**
 * Created by liarthur on 2019/6/5.
 */
public class island {
    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        //numIslands2(edges);
        maxAreaOfIsland(edges);
    }


    static int maxAreaOfIsland(int[][] grid) {
        int result=0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    int temp = DFS_area(grid, i, j);
                    if(temp > result){
                        result = temp;
                    }
                }
            }
        }
        return result;
    }

    static int DFS_area(int[][] grid, int row, int col){
        grid[row][col] = 0;
        int temp = 1;
        int a=0,b=0,c=0,d=0;
        if(col+1<grid[0].length && grid[row][col+1] != 0){
            a = DFS_area(grid, row, col+1);
        }
        if(col-1>=0 && grid[row][col-1] != 0){
            b = DFS_area(grid, row, col-1);
        }

        if(row+1<grid.length && grid[row+1][col] != 0){
            c = DFS_area(grid,row+1, col);
        }
        if(row-1>=0 && grid[row-1][col] != 0){
            d = DFS_area(grid,row-1, col);
        }
        temp = temp + a + b + c + d;
        return temp;
    }

    static int numIslands(char[][] grid) {
//        Boolean[][] visited = new Boolean[grid.length][grid[0].length];
//        for(int i=0; i < grid.length; i++){
//            for(int j=0; j < grid[i].length; j++){
//                visited[i][j] = false;
//            }
//        }
        int count = 0;
        for(int i=0; i < grid.length; i++){
            for(int j=0; j < grid[i].length; j++){
                if(grid[i][j] == '1'){
                    DFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    static void DFS(char[][] grid, int row, int col){
        grid[row][col] = '0';
        if(col+1<grid[0].length && grid[row][col+1] != '0'){
            DFS(grid, row, col+1);
        }

        if(col-1>=0 && grid[row][col-1] != '0'){
            DFS(grid, row, col-1);
        }

        if(row+1<grid.length && grid[row+1][col] != '0'){
            DFS(grid,row+1, col);
        }
        if(row-1>=0 && grid[row-1][col] != '0'){
            DFS(grid,row-1, col);
        }

    }
    static int numIslands2(int[][] grid) {

        int[][] distance = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFindSet u = new UnionFindSet(grid);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    for (int[] d : distance) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                            int first = i * cols + j;
                            int second = x * cols + y;
                            u.Union(first, second);
                        }
                    }
                }
            }
        }
        Set<Integer> island = new HashSet<Integer>();
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j<cols; j++){
                if (grid[i][j] == 1){
                    u.Find(i*cols+j);
                }
            }
        }
        for (int i = 0; i <= rows * cols; i++) {
            island.add(u.parents[i]);

        }
        return island.size()-1;
    }
}


class UnionFindSet {
    int[] parents;
    int[] rank;

    public UnionFindSet(int[][] grid){
        int row = grid.length;
        int col = grid[0].length;
        parents = new int[row*col+1];
        rank = new int[row*col+1];
        for(int i = 0; i<row; i++){
            for(int j = 0; j < col; j++){
                if (grid[i][j] == 1) {
                    parents[i*col+j] = i*col + j;
                    rank[i*col+j] = i*col + j;
                }else{
                    parents[i*col+j] = 1000;
                    rank[i*col+j] = 1000;
                }

            }
        }
        parents[row*col] = 1000;
    }

    public boolean Union(int first, int second){
        int firstP = Find(first);
        int secondP = Find(second);
        if(firstP == secondP) return false;

        if(rank[firstP] < rank[secondP])
            parents[secondP] = firstP;
        else if(rank[firstP] > rank[secondP])
            parents[firstP] = secondP;
//        else{
//            parents[secondP] = firstP;
//            rank
//        }
        return true;
    }

    public int Find(int node){
        while (parents[node] != node){
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }
}