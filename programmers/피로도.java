import java.util.*;

class Solution {
    ArrayList<int[]> permutations = new ArrayList<>();

    void permutation(int depth, int[] out, int n, boolean[] visited){
        if(depth == n){
            permutations.add(out.clone());
            return;
        }
        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = i;
                permutation(depth+1, out, n, visited);
                visited[i] = false;
            }
        }
    }

    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        int len = dungeons.length;
        permutation(0, new int[len], len, new boolean[len]);
        for(int[] per : permutations){
            int count = 0;
            int cur = k;
            for(int i : per){
                int[] val = dungeons[i];
                if(cur >= val[0]){
                    count++;
                    cur-=val[1];
                }else{
                    break;
                }
            }
            if(count > answer) answer = count;
        }
        return answer;
    }
}
