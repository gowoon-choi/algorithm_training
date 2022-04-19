import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    static class Point{
        int row;
        int column;

        Point(int row, int column){
            this.row = row;
            this.column = column;
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static Set<String> list = new HashSet<>();
    public static void dfs(char[] out, char[][] board, Point current, int depth, boolean[][] visited){
        if(depth == 2){
            list.add(new String(out));
            return;
        }
        for(int i=0; i<4; i++){
            visited[current.row][current.column] = true;
            Point next = new Point(current.row+dx[i], current.column+dy[i]);
            if(next.row>=0 && next.row<5 && next.column>=0 && next.column<5){
                if(!visited[next.row][next.column]){
                    out[depth] = board[next.row][next.column];
                    dfs(out, board, next, depth+1, visited);
                    visited[current.row][current.column] = false;
                }
            }
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        char[][] place = new char[5][5];
        ArrayList<Point> person = new ArrayList<>();
        char temp;
        int result;
        for(int count=0; count<places.length; count++){
            person.clear();
            result = 1;
            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    temp = places[count][i].charAt(j);
                    place[i][j] = temp;
                    if(temp == 'P'){
                        person.add(new Point(i, j));
                    }
                }
            }
            
            for(int i=0; i<person.size(); i++){
                list.clear();
                dfs(new char[2], place, person.get(i), 0, new boolean[5][5]);
                for(String str : list){
                    if(str.startsWith("P") || str.equals("OP")){
                        result = 0;
                        break;
                    }
                }
            }
            answer[count] = result;
        }

        return answer;
    }
}
