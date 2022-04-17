import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 필요한 자료구조 생성
        Map<String, Integer> counter = new HashMap<>();
        Map<String, Set<String>> mail_list = new HashMap<>();
        for(String id : id_list){
            counter.put(id, 0);
            mail_list.put(id, new HashSet<>());
        }

        // report 처리
        String[] usr;
        for(String elem : report){
            usr = elem.split(" ");
            if(!mail_list.get(usr[0]).contains(usr[1])){
                counter.put(usr[1], counter.get(usr[1])+1);
                mail_list.get(usr[0]).add(usr[1]);
            }
        }

        // 메일 전송 결과 추출
        int count;
        for(int i=0; i<id_list.length; i++){
            count = 0;
            for(String id: mail_list.get(id_list[i])){
                if(counter.get(id) >= k) count++;
            }
            answer[i] = count;
        }

        return answer;
    }
}
