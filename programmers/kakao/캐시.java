import java.util.ArrayList;
import java.util.Locale;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        ArrayList<String> cache = new ArrayList<>();
        for(String city : cities){
            city = city.toLowerCase(Locale.ROOT);
            if(cache.contains(city)){
                // hit
                cache.remove(city);
                cache.add(city);
                answer+=1;
            }else{
                // miss
                cache.add(city);
                answer+=5;
                if(cache.size()>cacheSize){
                    cache.remove(0);
                }
            }
        }

        return answer;
    }
}
