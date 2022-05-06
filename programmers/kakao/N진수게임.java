class Solution {
    static char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String calculateNotation(int n, int num){
        String result = "";
        int share, reminder;
        while (true){
            share = num/n;
            reminder = num%n;
            result = number[reminder]+result;
            if(share == 0) break;
            else num = share;
        }
        return result;
    }
    
    public String solution(int n, int t, int m, int p) {
        String answer = "";

        char[] turn = new char[m*(t-1)+p];
        int num = 0, index = 0;
        String result;
        while(true) {
            result = calculateNotation(n, num++);
            for(int i=0; i<result.length()&&index< turn.length; i++){
                turn[index++] = result.charAt(i);
            }
            if(index >= turn.length) break;
        }
        for(int i=0; i<t; i++){
             answer += turn[(m*i)+p-1];
        }

        return answer;
    }
}
