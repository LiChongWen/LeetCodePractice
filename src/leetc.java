/**
 * Created by liarthur on 2019/7/6.
 */
public class leetc {
    public static void main(String[] args) {
        char[][] board = {
                {'A','B', 'C', 'E'},
                {'S','F', 'E', 'S'},
                {'A','D', 'E', 'E'}
                                };
        String word = "ABCESEEEFS";
        boolean pass = exist(board, word);
        System.out.println(pass);
    }



    static boolean exist(char[][] board, String word) {
        boolean result = false;
        char start = word.charAt(0);
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == start){
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    result = DFS(i, j, visited, board, word.substring(1));
                    if(result) return result;
                }
            }
        }
        return result;
    }

    static boolean DFS(int row, int col, boolean[][] visited, char[][] board, String word){
        if(word.length() == 0) return true;
        visited[row][col] = true;
        char start = word.charAt(0);
        if(row+1 < board.length && board[row+1][col] == start && !visited[row+1][col]){
            if(DFS(row+1, col, visited, board, word.substring(1))){
                return true;
            }
        }
        if(col+1 < board[0].length && board[row][col+1] == start && !visited[row][col+1]){
            if(DFS(row, col+1, visited, board, word.substring(1))){
                return true;
            }
        }
        if(row > 0 && board[row-1][col] == start && !visited[row-1][col]){
            if(DFS(row-1, col, visited, board, word.substring(1))){
                return true;
            }
        }
        if(col > 0 && board[row][col-1] == start && !visited[row][col-1]){
            if(DFS(row, col-1, visited, board, word.substring(1))){
                return true;
            }
        }
        visited[row][col] = false;
        return false;
    }
}
