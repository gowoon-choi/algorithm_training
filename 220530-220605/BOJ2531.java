package com.gowoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int index;
        int max = 0;
        for(int i=0; i<n; i++){
            boolean[] visited = new boolean[d+1];
            int count = 0;
            for(int j=0; j<k; j++){
                index = (i+j)%n;
                if(!visited[arr[index]]){
                    count++;
                    visited[arr[index]] = true;
                }
            }
            if(!visited[c]) count++;
            if(max < count) max = count;
        }

        bw.write(max+"\n");
        bw.close();
        br.close();
    }
}
