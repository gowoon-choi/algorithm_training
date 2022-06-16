import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> memo = new HashSet<>();
        for(int i=0; i<n; i++){
            memo.add(br.readLine());
        }

        for(int i=0; i<m; i++){
            String input = br.readLine();
            String[] keyword = input.split(",");
            for(int j=0; j<keyword.length; j++){
                memo.remove(keyword[j]);
            }
            bw.write(memo.size() + "\n");
        }

        bw.close();
        br.close();
    }
}
