import java.util.*;

class Solution {
    public static ArrayList<String> result = new ArrayList<>();
    public static void combination(int col, int start, int depth, int target_depth, boolean[] visited, char[] out, String[][] relation){
        if(depth == target_depth){
            String comb = new String(out);
            if(isUnique(relation, out) && isLeast(comb)){
                System.out.println(comb);
                result.add(comb);
            }
            return;
        }
        for(int i=start; i<col; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = (char)(i+'0');
                combination(col, i+1, depth+1, target_depth, visited, out, relation);
                visited[i] = false;
            }
        }
    }

    public static boolean isUnique(String[][] relation, char[] comb){
        Set<String> row_list = new HashSet<>();
        for(int i=0; i<relation.length; i++){
            String temp = "";
            for(int j=0; j<comb.length; j++){
                temp += relation[i][comb[j]-'0'];
                temp += ":";
            }
            row_list.add(temp);
        }
        return row_list.size() == relation.length;
    }

    public static boolean isLeast(String comb){
        boolean isLeast;
        for(String elem: result){
            isLeast = false;
            for(int i=0; i<elem.length(); i++){
                if(!comb.contains(elem.substring(i, i+1))){
                    isLeast = true;
                    break;
                }
            }
            if(!isLeast) return false;
        }
        return true;
    }
    
    public int solution(String[][] relation) {
        int col = relation[0].length;
        for(int i=1; i<=col; i++){
            combination(col, 0, 0, i, new boolean[col], new char[i], relation);
        }

        return result.size();
    }
}
