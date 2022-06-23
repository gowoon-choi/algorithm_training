package com.gowoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        boolean[] broken = new boolean[10];

        String target = br.readLine();
        int n = Integer.parseInt(br.readLine());
        if(n != 0){
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int result = Math.abs(Integer.parseInt(target)-100);
        for(int i=0; i<999900; i++){
            String cur = String.valueOf(i);
            boolean possible = true;
            for(int j=0; j<cur.length(); j++){
                if(broken[cur.charAt(j)-'0']){
                    possible = false;
                    break;
                }
            }
            if(possible){
                int count = Math.abs(Integer.parseInt(target)-i)+cur.length();
                result = Math.min(result, count);
            }
        }

        bw.write(result + "\n");
        bw.close();
        br.close();
    }
}
