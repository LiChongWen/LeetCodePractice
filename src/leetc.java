import java.util.HashMap;
import java.util.Stack;

/**
 * Created by liarthur on 2019/7/6.
 */
public class leetc {
    public static void main(String[] args) {
//        char[][] board = {
//                {'A','B', 'C', 'E'},
//                {'S','F', 'E', 'S'},
//                {'A','D', 'E', 'E'}
//                                };
//        String word = "ABCESEEEFS";
//        boolean pass = exist(board, word);
//        System.out.println(pass);
        String s = "0123";
        String subs = s.substring(0,1);
        HashMap<Character, Character> map = new HashMap<>();
        map.put( ')', '(');
        map.put( '}', '{');
        map.put( ']' ,'[' );
        boolean pass = isValid("(()]");
        System.out.println(pass);
    }
    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    static boolean isValid(String s, HashMap<Character, Character> map) {
        //char[] brackets = {'(', ')', '{', '}', '[', ']'};
        if(s.length() % 2 != 0) return false;
        if(s.length() == 0) return true;

        for(int i = 0; i<s.length(); i++){
            if(map.containsKey(s.charAt(i))){
                if(i > 0){
                    if(s.charAt(i-1) == map.get(s.charAt(i))){
                        if(isValid(s.substring(0,i-1) + s.substring(i+1), map)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
