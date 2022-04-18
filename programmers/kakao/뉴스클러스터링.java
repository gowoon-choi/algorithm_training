import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Solution {
    // 문자열 집합 만들기
    public static Map<String, Integer> make_set(String str){
        // 소문자로 변환
        str = str.toLowerCase(Locale.ROOT);

        Map<String, Integer> set = new HashMap<>();
        String temp, first, second;
        for(int i=0; i<str.length()-1; i++){
            temp = "";

            if(str.charAt(i)>=97 && str.charAt(i)<=122) first = str.substring(i,i+1);
            else continue;

            if(str.charAt(i+1)>=97 && str.charAt(i+1)<=122) second = str.substring(i+1,i+2);
            else continue;

            temp = first + second;

            if(set.containsKey(temp)) set.put(temp, set.get(temp)+1);
            else set.put(temp, 1);
        }

        return set;
    }

    // 교집합 구하기
    public static int get_intersection(Map<String, Integer> set1, Map<String, Integer> set2){
        int count = 0;
        for(String key: set1.keySet()){
            if(set2.containsKey(key)){
                count += Math.min(set1.get(key), set2.get(key));
            }
        }
        return count;
    }

    // 합집합 구하기
    public static int get_union(Map<String, Integer> set1, Map<String, Integer> set2){
        int count = 0;
        for(String key: set1.keySet()){
            if(set2.containsKey(key)){
                count += Math.max(set1.get(key), set2.get(key));
                set2.remove(key);
            }
            else count += set1.get(key);
        }
        for(String key: set2.keySet()){
            count += set2.get(key);
        }
        return count;
    }
    
    public int solution(String str1, String str2) {
        // str1 집합 생성
        Map<String, Integer> set1 = make_set(str1);
        // str2 집합 생성
        Map<String, Integer> set2 = make_set(str2);
        // 교집합 개수 구하기
        int intersection_count = get_intersection(set1, set2);
        // 합집합 개수 구하기
        int union_count = get_union(set1, set2);

        // 결과 구하기
        double jaccard;
        if(union_count == 0) jaccard = 1.0;
        else jaccard = (double) intersection_count/union_count;

        return (int) Math.floor(jaccard*65536);
    }
}
