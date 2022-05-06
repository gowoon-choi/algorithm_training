class Solution {
   public static String RESULT_NOT_FOUND_MESSAGE = "(None)";

    // 재생 시간 계산 ( HH:MM 으로 돼있는 두 밸류 시간차이 - 하루가 넘어가는 경우는 없음, 즉 start <= end )
    public static int calculateLength(String start, String end){
        int startHour = Integer.parseInt(start.substring(0, 2));
        int startMinute = Integer.parseInt(start.substring(3));
        int endHour = Integer.parseInt(end.substring(0, 2));
        int endMinute = Integer.parseInt(end.substring(3));

        int result = 0;
        if(startHour == endHour){
            result += (endMinute-startMinute);
        }else{
            result += (60*(endHour-startHour-1));
            result += (60-startMinute);
            result += endMinute;
        }

        return result;
    }

    // #은 앞에 있는 알파벳에 붙어있는 문자라고 생각해서 0부터 endIndex까지 substring A - 한글자 / A# - 한글자
    // param : AA#BB#CC#, 5 > AA#BB#C
    public static String substringWithSharp(String str, int endIndex){
        StringBuilder result = new StringBuilder();
        int count = 0;
        char ch;
        for(int i=0; i<str.length(); i++){
            ch = str.charAt(i);
            if(str.charAt(i) != '#') count++;
            if(count > endIndex) break;
            result.append(ch);
        }
        return result.toString();
    }

    // # 바로 앞의 알파벳을 소문자로
    public static String lowercaseWithSharp(String str){
        char[] temp = str.toCharArray();
        for(int i=0; i<temp.length; i++){
            if(temp[i] == '#') temp[i-1] = Character.toLowerCase(temp[i-1]);
        }
        return new String(temp);
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = RESULT_NOT_FOUND_MESSAGE; // 결과값, 일치하는 곡 없으면 NONE, 있으면 곡 제목
        int answerLength = 0; // 순서대로 탐색하면서 일치하는 곡 만나면 재생 길이 저장하여 더 긴 경우만 update ( 일치 곡이 여러개면 더 긴 재생시간인 것 출력, 재생시간도 동일하면 먼저 들어온 곡 출력 조건 )

        String[] elem;
        int length, musicLength, repeat, head;
        String music, played;
        for(String musicInfo : musicinfos){
            elem = musicInfo.split(","); // 정보 split [ 시작시간, 종료시간, 제목, 곡 ]
            length = calculateLength(elem[0], elem[1]); // 재생 시간 계산
            music = elem[3]; // 곡
            musicLength = music.replaceAll("#", "").length(); // #을 고려한 곡 길이
            music = lowercaseWithSharp(music); // A. A#을 다르게 보기 위해서 ( 특히 반복 재생하는 경우까지 ) #이 붙은 경우는 소문자로 치환

            if(musicLength >= length){
                // 곡 길이가 재생된 길이보다 짧거나 같으면 ( 반복 재생할 필요 없으면 ) 재생된 길이만큼만 substring 물론 # 고려해서
                played = substringWithSharp(music, length);
            }else{
                // 곡 길이보다 재생된 시간이 더 길다면 반복 재생된 곡 흐름 문자열 제작
                repeat = length/musicLength; // 반복 횟수
                head = length%musicLength; // repeat회 반복하고 추가로 재생되는 분
                played = music.repeat(repeat); // 반복
                played += substringWithSharp(music, head); // 추가 재생 분만큼 잘라서 붙이기 물론 # 고려해서
            }

            // 만약 흘러나온 곡이 들은 곡을 포함하고 있다면
            if(played.contains(lowercaseWithSharp(m))){
                if(answer.equals(RESULT_NOT_FOUND_MESSAGE)){
                    // 아직 일치하는 곡이 없었다면 저장
                    answer = elem[2];
                    answerLength = length;
                }else{
                    // 일치하는 곡 있었다면
                    if(answerLength < length){
                        // 이미 저장된 곡보다 재생 길이가 긴 경우만 다시 저장
                        answer = elem[2];
                        answerLength = length;
                    }
                }
            }
        }

        return answer;
    }

}
