import java.util.HashSet;
import java.util.Set;

/**
 * Created by liarthur on 2019/6/5.
 */
public class FriendCircles {
    public static void main(String[] args) {

        int[][] M = new int[][]{
                {1,0,0,1},
                {0,1,1,0},
                {0,1,1,1},
                {1,0,1,1}
        };
//        int count = 0;
//        for(int i=0; i < M.length; i++){
//            for (int j = 0; j< M.length; j++){
//                if(M[i][j] == 1){
//                    M = DFS(M, i);
//                    count++;
//                }
//            }
//
//        }
//        System.out.println(count);

        int n = M.length;
        UnionFindSetF u = new UnionFindSetF(n);
        for(int i = 0; i<n; i++){
            for(int j=0; j<n; j++){
                if(M[i][j] == 1){
                    u.Union(i,j);
                }
            }
        }
        Set<String> circle = new HashSet<String>();
        for(int i = 0; i < n ; i++){
            circle.add(u.Find(i)+"");
        }

    }
    static int[][] DFS(int[][] M, int row){
        for(int i = 0; i < M.length; i++){
            if(M[row][i] == 1){
                M[row][i] = 0;
                M[i][row] = 0;
                DFS(M, i);
            }
        }

            return M;
    }


}



class UnionFindSetF{
    private int[] parents;
    private int[] ranks;
    public UnionFindSetF(int n){
        parents = new int[n+1];
        ranks = new int[n+1];
        for(int i=0; i < parents.length; i++){
            parents[i] = i;
            ranks[i] = i;
        }
    }

    public boolean  Union(int first, int second){
        int firstP = Find(first);
        int secondP = Find(second);

        if(firstP == secondP) return false;

        if(ranks[firstP] > ranks[secondP]){
            parents[secondP] = parents[firstP];
        } else if(ranks[firstP] < ranks[secondP]){
            parents[firstP] = parents[secondP];
        }else{
            parents[firstP] = parents[secondP];
            ranks[firstP]++;
        }

        return true;
    }

    public int Find(int node){
        while(parents[node] != node){
            parents[node] = parents[parents[node]];
            node = parents[node];
        }
        return node;
    }
}