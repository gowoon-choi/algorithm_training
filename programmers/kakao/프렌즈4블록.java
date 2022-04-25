import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    static class Point{
        int row;
        int col;
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        // board 배열로 변환
        char[][] board_arr = new char[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                board_arr[i][j] = board[i].charAt(j);
            }
        }

        Queue<Point> target = new LinkedList<>();
        int[] di = {0, 0, 1, 1};
        int[] dj = {0, 1, 1, 0};
        char character;
        boolean flag;
        Point cur;
        while(true){
            // 2*2 탐색, 없으면 break
            for(int i=0; i<m-1; i++){
                for(int j=0; j<n-1; j++){
                    if(board_arr[i][j] != '0'){
                        character = board_arr[i][j];
                        flag = true;
                        for(int k=0; k<4; k++){
                            if(board_arr[i+di[k]][j+dj[k]] != character){
                                flag = false;
                                break;
                            }
                        }
                        if(flag) target.add(new Point(i, j));
                    }
                }
            }
            if(target.isEmpty()) break;
            // 지우기, 지운 블록 개수 카운팅
            while (!target.isEmpty()){
                cur = target.poll();
                for(int k=0; k<4; k++){
                    if(board_arr[cur.row+di[k]][cur.col+dj[k]] != '0'){
                        board_arr[cur.row+di[k]][cur.col+dj[k]] = '0';
                        answer++;
                    }
                }
            }
            // 블록 재배치
            int top;
            for(int j=0; j<n; j++){
                flag = false;
                top = 0;
                for(int i=m-1; i>=0; i--){
                    if(flag){
                        // 빈 공간 만난 이후
                        if(board_arr[i][j] != '0'){
                            board_arr[top][j] = board_arr[i][j];
                            board_arr[i][j] = '0';
                            top--;
                        }
                    }else{
                        // 빈 공간 아직 안 만났을 때
                        if(board_arr[i][j] == '0'){
                            flag = true;
                            top = i;
                        }
                    }
                }
            }
        }

        return answer;
    }
}
