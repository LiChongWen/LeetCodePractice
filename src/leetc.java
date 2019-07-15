import java.util.*;

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
//        String s = "0123";
//        int x = 13;
//
//        String subs = s.substring(0,1);
//        HashMap<Character, Character> map = new HashMap<>();
//        map.put( ')', '(');
//        map.put( '}', '{');
//        map.put( ']' ,'[' );
//        boolean pass = isValid("(()]");
//        System.out.println(pass);

//        int[] nums = {4,5,6,7,0,1,2};
//       // productExceptSelf(nums);
//        TreeNode root = new TreeNode(3);
//
//
//        TreeNode five = new TreeNode(5);
//        TreeNode one = new TreeNode(1);
//        TreeNode six = new TreeNode(6);
//        TreeNode two = new TreeNode(2);
//        TreeNode zero = new TreeNode(0);
//        TreeNode eight = new TreeNode(8);
//        root.left = five;
//        root.right = one;
//        five.left = six;
//        five.right = two;
//        one.left = zero;
//        one.right = eight;
//        lowestCommonAncestor(root, five, two);

//        int[][] x = {{-1,4,7,11,15},
//                     {2,5,8,12,19},
//                     {3,6,9,16,22},
//                     {10,13,14,17,24},
//                     {18,21,23,26,30}};
//        searchMatrix(x, 5);

//        String s = "012345";
//        String[] sa = {"1", "2"};
//        s.contains("23");
//        s = s.substring(1,1+2);
//        Stack<Character> a = new Stack<>();
//
//        int[][] x = {{0,0,0},
//                {0,1,1}};
//
//        x = floodFill(x,1,1,2);
//        int[] x = {-1,0,0,0,0,3,3};
//        removeDuplicates(x);
        countAndSay(5);

    }
    static String countAndSay(int n) {
        if(n == 1) return "1";
        String x = "1";
        for(int i = 1 ; i < n; i++){
            x = count(x);
        }
        return x;
    }
    static String count(String num){
        String result="";
        int size = num.length();
        if(size == 1) return "1"+num;
        for(int i = 0; i< size; i++){
            int number = 1;
            while(i+1<size && num.charAt(i+1) == num.charAt(i)){
                if(i+1<size){
                    i++;
                    number++;
                }else
                    break;
            }
            String x = (String.valueOf(number) + num.charAt(i));
            result += x;
        }
        return result;
    }
    static int removeDuplicates(int[] nums) {
        int size = nums.length;
        int result = 1;
        if(size < 3) return size;
        int j = 1;
        for(int i = 0; i < size; i++){
            if(j<size){
                while(nums[j] == nums[i]){
                    if(j<size-1){
                        j++;
                    }else{
                        break;
                    }
                }
                if(nums[j] != nums[i]){
                    nums[i+1] = nums[j++];
                    result++;
                }else if(j == size-1){
                    break;
                }
            }

        }
        return result;
    }
    static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        if( s.length() == 0 || s.charAt(0) != '(' && s.charAt(0) != '[' && s.charAt(0) != '{'){
            return false;
        }
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(')');
            }else if(s.charAt(i) == '['){
                stack.push(']');
            }else if(s.charAt(i) == '{'){
                stack.push('}');
            }else if(s.charAt(i) != stack.pop()){
                return false;
            }
        }
        return true;
    }
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                fast = head;
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }

        return null;
    }
    static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) return image;
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        if(sr-1>=0 && image[sr-1][sc] == oldColor) floodFill(image, sr-1, sc, newColor);
        if(sr+1<=image.length-1 && image[sr+1][sc] == oldColor) floodFill(image, sr+1, sc, newColor);
        if(sc-1>=0 && image[sr][sc-1] == oldColor) floodFill(image, sr, sc-1, newColor);
        if(sc+1<=image[0].length-1 && image[sr][sc+1] == oldColor) floodFill(image, sr, sc+1, newColor);
        return image;
    }
    static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length-1;
        rows = 0;
        while( rows < matrix.length && cols >= 0){
            if(matrix[rows][cols] == target) return true;
            else if(matrix[rows][cols] > target) cols--;
            else rows++;
        }
        return false;
    }
    static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }
    static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        Queue<TreeNode> Q = new LinkedList<>();
        HashMap<Integer, TreeNode> map = new HashMap<>();
        Q.offer(root);
        int ground = 1;
        root.level = ground;
        while(!Q.isEmpty()){
            TreeNode node = Q.poll();

            if(node.left != null){
                node.left.level = node.level+1;
                map.put(node.left.val, node);
                Q.offer(node.left);
            }
            if(node.right != null){
                node.right.level = node.level+1;
                map.put(node.right.val, node);
                Q.offer(node.right);
            }
        }

        while(p.level > q.level){
            p = map.get(p.val);
        }
        while(q.level > p.level){
            q = map.get(q.val);
        }
        while(p.val != q.val){
            p = map.get(p.val);
            q = map.get(q.val);
        }
        return p;

    }
    static int search(int[] nums, int target) {
        int size = nums.length;
        int i = 0, start = 0, end = size-1;
        while(start < end){
            int mid = start + (end-start)/2;
            if(nums[mid] > nums[end]) start = mid+1;
            else end = mid;
        }

        int min = start;

        if(target == nums[min]) return min;
        end = target>nums[size-1] ? min : size-1;
        start = target<=nums[size-1] ? min : 0;

        while(start <= end){
            int mid = start + (end-start)/2;
            if(target == nums[mid]) return mid;
            if(target > nums[mid]) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }
    static int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        int right =1;

        for(int i = 1; i < nums.length; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        for(int i = nums.length-1; i >= 0; i--){
            left[i] = right * left[i];
            right = right * nums[i];
        }
        return left;
    }

    public String intToRoman(int num) {
        String anwser = "";
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        for (int i = 0; i < symbols.length; i++) {
            while (num >= numbers[i]) {
                num -= numbers[i];
                anwser = anwser + symbols[i];
            }
        }
        return anwser;
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
