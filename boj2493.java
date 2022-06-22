import java.io.*;
import java.util.*;

public class Main {
    static class Tower implements Comparable<Tower>{
        int id;
        int height;

        Tower(int id, String height){
            this.id = id;
            this.height = Integer.parseInt(height);
        }

        @Override
        public int compareTo(Tower o) {
            return this.height-o.height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        String[] heights = input.split(" ");
        int[] result = new int[n];
        Arrays.fill(result, 0);

        PriorityQueue<Tower> target = new PriorityQueue<>();
        target.add(new Tower(n - 1, heights[n - 1]));
        for(int i=n-2; i>=0 ;i--){
            Tower cur = new Tower(i, heights[i]);
            while (!target.isEmpty()){
                // TODO 높이 똑같으면 수신할 수 있는지 없는지 확인하고 바꿀 부분
                if(target.peek().height <= cur.height){
                    result[target.peek().id] = cur.id+1;
                    target.poll();
                }else{
                    break;
                }
            }
            target.add(cur);
        }

        for(int i=0; i<n; i++){
            bw.write(result[i] + " ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }
}
