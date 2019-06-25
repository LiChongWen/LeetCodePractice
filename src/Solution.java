import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by liarthur on 2019/6/2.
 */
public class Solution {


    public static void main(String[] args) {
        int[] a = new int[2];

        int[][] edges = new int[][]{
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 4},
                {1, 5}
                //{'1'}
        };
        //System.out.println(numIslands(grid))
        UnionFindSetR u = new UnionFindSetR(edges.length);
        for (int[] edge : edges){
            if (!u.Union(edge[0],edge[1])){
                System.out.println(edge);
            }
        }
    }


}
class UnionFindSetR{
    private int[] parent;
    private int[] rank;


    public UnionFindSetR(int size){
        parent = new int[size+1];
        rank = new int[size+1];
        for(int i = 0; i<parent.length; i++){
            parent[i] = i;
            rank[i] = i;
        }
    }

    public boolean Union(int first, int second){
        int firstP = Find(first);
        int secondP = Find(second);
        if(firstP==secondP) return false;

        if(firstP>secondP){
            parent[secondP] = firstP;
        }
        else if(firstP<secondP){
            parent[firstP] = secondP;
        }
        else{
            parent[secondP] = firstP;
            rank[first]++;
        }
        return true;
    }
    public int Find(int node){
        while (parent[node] != node){
            parent[node] = parent[parent[node]];
            node = parent[node];
        }
        return node;
    }

}