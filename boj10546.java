package com.gowoon;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> parties = new HashMap<>();
        for(int i=0; i<n; i++){
            String name = br.readLine();
            if(parties.containsKey(name)){
                parties.put(name, parties.get(name)+1);
            }else{
                parties.put(name, 1);
            }
        }

        for(int i=0; i<n-1; i++){
            String name = br.readLine();
            if(parties.get(name) == 1){
                parties.remove(name);
            }else{
                parties.put(name, parties.get(name)-1);
            }
        }
        for(String key : parties.keySet()){
            bw.write(key+"\n");
        }

        bw.close();
        br.close();
    }
}
