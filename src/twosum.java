import java.util.*;

/**
 * Created by liarthur on 2019/6/9.
 */
public class twosum {
    public static void main(String[] args) {

//         RandomizedSet obj = new RandomizedSet();
//        //int param_3 = obj.getRandom();
//         boolean param_1 = obj.insert(3);
//        boolean param_11 = obj.remove(3);
//        boolean param_12 = obj.remove(0);
//
//        boolean param_2 = obj.insert(3);
//        int param_31 = obj.getRandom();
//        boolean param_21 = obj.remove(0);
//        int max = lengthOfLongestSubstring("au");

//        int k = 3;
//        int[] arr = {4,5,8,2};
//        KthLargest kthLargest = new KthLargest(3, arr);
//        kthLargest.add(3);   // returns 4
//        kthLargest.add(5);   // returns 5

//        String s = "012345";
//        s = s.substring(0,5);
//        int[][] board = {   {1,1,-1},
//                            {1,1,1},
//                            {-1,1,1}};
//
//        snakesAndLadders(board);
//        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
//        List<String> frequency =  topKFrequent(words, 2);
//
//        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//
//
//        int min = minStack.getMin();
//        minStack.pop();
//        int top = minStack.top();
//        min = minStack.getMin();
//
//
//        De_serialize_BST x = new De_serialize_BST();
//        x.deserialize("8,3,1,6,4,7,10,14,13");
//        int[] coins = {2,5,10,1};
//        System.out.println(coinChange0(coins, 27));

//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");
//        boolean pass = false;
//
//        System.out.println(wordBreak1("leetcode", wordDict, false));
        //System.out.println(firstUniqChar("loveleetcode"));
        char[] x = {'a', 'b'};
        x[0] = '\0';

        int[] a = {6,3,4,2,7,5};
        quickSort(a, 0, 5);
        System.out.println("---");
    }

    static void quickSort(int a[],int l,int r){
        if(l>=r)
            return;

        int i = l; int j = r; int key = a[l];//选择第一个数为key

        while(i<j){

            while(i<j && a[j]>=key)//从右向左找第一个小于key的值
                j--;
            if(i<j){
                a[i] = a[j];
                i++;
            }

            while(i<j && a[i]<key)//从左向右找第一个大于key的值
                i++;

            if(i<j){
                a[j] = a[i];
                j--;
            }
        }
        //i == j
        a[i] = key;
        quickSort(a, l, i-1);//递归调用
        quickSort(a, i+1, r);//递归调用
    }

    static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            if(s.lastIndexOf(s.charAt(i)) == s.indexOf(s.charAt(i))) return i;
        }
        return -1;

    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(2, ((o1, o2) -> o1.val-o2.val));

        queue.add(l1);
        queue.add(l2);

        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;

            if(tail.next != null){
                queue.add(tail.next);
            }
        }
        return head.next;

    }

    static boolean wordBreak1(String s, List<String> wordDict, boolean res) {
        boolean pass[] = new boolean[s.length()+1];
        pass[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(pass[j] && wordDict.contains(s.substring(j, i))){
                    pass[i] = true;
                    break;
                }
            }
        }
        return pass[s.length()];
    }

    static List<String> wordBreak(String s, List<String> wordDict) {
        return  wordBreakHelper(s, wordDict,new HashMap<String, LinkedList<String>>());
    }
    static List<String>  wordBreakHelper(String s, List<String> wordDict, HashMap<String, LinkedList<String>>map){

        if(map.containsKey(s)){
            return map.get(s);
        }

        LinkedList<String> res = new LinkedList<>();
        if(s.length() == 0){
            res.add("");
            return res;
        }
        for (String word: wordDict) {
            if(s.startsWith(word)){
                List<String> sublist = wordBreakHelper(s.substring(word.length()),wordDict,map);
                for (String sub: sublist) {
                    res.add(word + (sub.isEmpty()? "" : " ") + sub);
                }
            }
        }
        map.put(s, res);
        return res;
       // return "";
    }
    static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        Arrays.fill(dp, 9999);
        dp[0] = 0;
        for (int i = 1; i < amount+1; i++) {
            if(i < coins[0]){
                dp[i] = -1;
            }else{
                for (int coin: coins) {
                    if(i == coin){
                        dp[i] = 1;
                    }else if(i > coin && dp[i-coin] != -1){
                        int result = dp[i-coin] + dp[coin];
                        if(result<=dp[i]){
                            dp[i] = result;
                        }
                    }
                }
            }
        }
        if(dp[amount] == 9999){
            dp[amount] = -1;
        }
        return dp[amount];

    }

    static int coinChange0(int[] coins, int amount){
        Arrays.sort(coins);
        int minSofar = Integer.MAX_VALUE;    // carray the minimum number of coins
        return coinChange_helper(coins, minSofar, coins.length-1, amount, 0);

    }

    static int coinChange_helper(int[] coins, int minSofar, int idxCoin, int amount, int prevCount){
        int maxCount = amount/coins[idxCoin];
        if(amount % coins[idxCoin] == 0){
            return prevCount+maxCount;
        }
        if(idxCoin==0){
            return -1;
        }

        for (int i = maxCount; i >=0; i--) {
            int newAmount = amount-i*coins[idxCoin];
            int newCount = i + prevCount;
            int nextCoin = coins[idxCoin-1];
            if( newCount + (newAmount + nextCoin - 1 ) / nextCoin < minSofar){   // (newAmount + nextCoin - 1 ) / nextCoin is used for ceiling the result of division
                int res = coinChange_helper(coins, minSofar, idxCoin-1, newAmount, newCount);
                if(res!=-1){
                    minSofar = Math.min(res, minSofar);     // update minSofar when we a solution to make a change
                }
            }
        }
        return minSofar == Integer.MAX_VALUE? -1: minSofar;
    }

    static int lengthOfLongestSubstring(String s) {
        int max = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<Character>();
        while (j<s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
                max = Math.max(max, set.size());

            }else {
                set.remove(s.charAt(i));
                i++;
            }
        }

        return max;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList1) {
        Set<String> wordList = new HashSet<String>(wordList1);
        if(!wordList.contains(endWord)) return 0;
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        int distance = 1;
        while(!reached.contains(endWord)){
            Set<String> Add = new HashSet<String>();
            for (String word: reached) {
                for (int i = 0; i < word.length(); i++) {
                    char[] wordArray = word.toCharArray();
                    for (char j = 'a'; j <= 'z'; j++) {
                        wordArray[i] = j;
                        if(wordList.contains(new String(wordArray))){
                            Add.add(new String(wordArray));
                            wordList.remove(new String(wordArray));
                        }
                    }
                }
            }
            distance++;
            if(Add.size() == 0) return 0;
            reached = Add;
        }
        
        
        return distance;
    }

    static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])){
                result[0] = map.get(target-nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    public Node copyRandomList(Node head) {
        Node node = head;
        Map<Node, Node> copy= new HashMap<Node, Node>();

        while(node != null){
            copy.put(node, new Node(node.val, null, null));
            node = node.next;
        }
        node = head;
        while (node != null){
            copy.get(node).next = copy.get(node.next);
            copy.get(node).random = copy.get(node.random);
            node = node.next;
        }
        return copy.get(head);
    }
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (a,b) -> a.val- b.val); //new Comparator<ListNode>() {
//            @Override
//            public int compare(ListNode o1, ListNode o2) {
//                if(o1.val<o2.val){
//                    return  -1;
//                }else if(o1.val == o2.val){
//                    return 0;
//                }else {
//                    return 1;
//                }
//            }
//        });
        for (int i = 0; i < lists.length; i++) {
            if(lists[i]!=null)  queue.add(lists[i]);
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;

            if(tail.next != null){
                queue.add(tail.next);
            }
        }
        return head.next;
    }

    static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> set = new HashMap<>();
        for (String word: words) {
            if(set.containsKey(word)){
                set.put(word, set.get(word)+1);
            }else{
                set.put(word,1);
            }
        }
        List<String> candidates = new ArrayList(set.keySet());
        Collections.sort(candidates,(w1,w2) ->
                        set.get(w1).equals(set.get(w2))?
                        w1.compareTo(w2) : set.get(w2) - set.get(w1));
        return candidates.subList(0,k);
    }

    static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int j=0, index=0, inc = 1, i = n-1;
        while(index < n*n){
            arr[index++] = board[i][j];
            if(inc == 1 && j == n-1){
                inc = -1;
                i--;
            }else if(inc == -1 && j==0){
                inc = 1;
                i--;
            }else{
                j = j + inc;
            }
        }
        boolean[] visited = new boolean[n*n];
        Queue<Integer> q = new LinkedList<>();
        int start = arr[0] > -1? arr[0] - 1 : 0;
        q.offer(start);
        visited[start] = true;
        int step = 0;

        while(!q.isEmpty()){
            int size = q.size();
            while(size != 0){
                int currentPos = q.poll();
                if(currentPos == n*n-1){
                    return step;
                }
                for(int next = currentPos+1; next <= Math.min(currentPos+6, n*n-1); next++){
                    int destination = arr[next] > -1 ? arr[next]-1 : next;
                    if(!visited[destination]){
                        visited[destination] = true;
                        q.offer(destination);
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }
}


class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> nums = new ArrayList<Integer>();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();

    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            map.put(val, nums.size());
            nums.add(val);
            return true;
        }else {
            return false;
        }
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }else {
            int loc = map.get(val);
            if(loc != nums.size()-1){
                int last = nums.get(nums.size()-1);
                map.put(last,loc);
                nums.set(loc, last);
            }
            map.remove(val);
            nums.remove(nums.size()-1);

            return true;
        }
    }

    /** Get a random element from the set. */
    public int getRandom() {
//        if(index == 0) return 0;
        return nums.get((int)(Math.random()*nums.size()));
    }
}

class KthLargest {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        queue = new PriorityQueue<Integer>(k);
        for (int a: nums) {
            add(a);
        }
    }

    public int add(int val) {
        if(queue.size()<k){
            queue.offer(val);
        }else if(queue.peek()<val){
            queue.poll();
            queue.offer(val);
        }
        return queue.peek();
    }
}
class De_serialize_BST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return "null";
        Stack<TreeNode> st= new Stack<>();
        st.push(root);
        while (!st.empty()){
            root = st.pop();
            sb.append(root.val).append(",");
            if(root.right != null) st.push(root.right);
            if(root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[] strA = data.split(",");

        Queue<Integer> q= new LinkedList<Integer>();
        for (String nums: strA){
            q.offer(Integer.parseInt(nums));
        }
        return getNode(q);
    }
    public TreeNode getNode(Queue<Integer> q){
        if(q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());
        Queue<Integer> smallerPart = new LinkedList<>();
        while (!q.isEmpty() && q.peek()<root.val){
            smallerPart.add(q.poll());
        }
        root.left = getNode(smallerPart);
        root.right = getNode(q);
        return root;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
class  Two_Sum_BTS  {
    public boolean findTarget(TreeNode root, int k) {
        List <Integer> list = new ArrayList();
        inorderTree(root, list);
        int top = 0, bottom = list.size()-1;
        while(top<bottom){
            int sum = list.get(top) + list.get(bottom);
            if(sum == k){
                return true;
            }
            if(sum < k){
                top++;
            }
            if(sum > k){
                bottom--;
            }
        }
        return false;
    }
    public void inorderTree(TreeNode root, List <Integer> list){
        if(root != null){
            inorderTree(root.left, list);
            list.add(root.val);
            inorderTree(root.right, list);
        }else{
            return;
        }
    }
}

class MinStack {
    Stack<Integer> stack;
    int min = Integer.MAX_VALUE;

    /** initialize your data structure here. */
    public MinStack() {
        stack  = new Stack<>();

    }
    public void push(int x) {
        if(x <= min){
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        int peek = stack.pop();
        if(peek == min){
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}