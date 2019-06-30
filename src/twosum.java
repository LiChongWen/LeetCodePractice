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
        int[] coins = {2,5,10,1};
        System.out.println(coinChange0(coins, 27));
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