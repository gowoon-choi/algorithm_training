package com.gowoon;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();

        int a = 0;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == 'a') a++;
        }

        int b = 0;
        for(int i=0; i<a; i++){
            if(input.charAt(i) == 'b') b++;
        }

        int min = b;
        int cnt = min;
        int len = input.length();
        input += input;
        for(int i=0; i<len; i++){
            if(input.charAt(i) == 'b') cnt--;
            if(input.charAt(i+a) == 'b') cnt++;
            if(cnt < min) min = cnt;
        }

        bw.write(min + "\n");
        bw.close();
        br.close();
    }
}
