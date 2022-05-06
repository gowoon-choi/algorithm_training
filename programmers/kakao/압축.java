import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> result = new ArrayList<>();

        Map<String, Integer> dictionary = new HashMap<>();
        for(int i=0; i<26; i++){
            dictionary.put(String.valueOf((char)(65+i)), i+1);
        }

        int length;
        int index = 27;
        int output = 0;
        String temp = "";
        for(int i=0; i<msg.length(); i++){
            length = 1;
            while (true){
                if(i+length > msg.length()){
                    if(!dictionary.containsKey(temp)) dictionary.put(temp, index++);
                    result.add(output);
                    i+=(length-2);
                    break;
                }
                temp = msg.substring(i, i+length);
                if(dictionary.containsKey(temp)){
                    output = dictionary.get(temp);
                    length++;
                }else {
                    result.add(output);
                    dictionary.put(temp, index++);
                    i+=(length-2);
                    break;
                }
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }
}
