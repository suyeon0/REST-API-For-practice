package com.example.apitest.coding_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AsciiTest {

    /**
     * string -> ascii , ascii -> string
     *
     * @param data
     */
    public String transferAsciiAndString(String data) {
        byte[] asciiData = data.getBytes();
        for (int i = 0; i < data.length(); i++) {
            System.out.println("Character [" + data.charAt(i) + "] To ASCII Code : " + asciiData[i]);
        }
        String stringData = new String(asciiData);
        return stringData;
    }

    /**
     * 숫자, 영문 빼고 나머지는 전부다 공백으로 바꾸기
     */
    public String solution(String data) {
        // (1) 바꿀 String 문장 아스키로 변환
        byte[] asciiData = data.getBytes();

        // (2) ascii 로 변경 대상인지 판단 후 실행.
        for (int i = 0; i < data.length(); i++) {
            boolean isTarget;

            if (asciiData[i] >= 48 && asciiData[i] <= 57) { // 숫자
                isTarget = false;
            } else if (asciiData[i] >= 65 && asciiData[i] <= 90) { // 대문자
                isTarget = false;
            } else if (asciiData[i] >= 97 && asciiData[i] <= 122) { // 소문자
                isTarget = false;
            } else {
                isTarget = true;
            }

            // 두 경우 제외하고 공백 아스키 값으로 변환
            if (isTarget) {
                asciiData[i] = 32;
            }
        }

        // (3) String 타입으로 변경
        String result = new String(asciiData);
        return result;
    }

    @Test
    public void testCase() {
        String test1 = "http://www.Naver.com/1234";
        String result1 = "http   www Naver com 1234";

        Assertions.assertEquals(this.solution(test1), result1);
    }

    @Test
    public void sampleTest() {
        /*
            Kakao Naver Coupang 텍스트를 ascii 코드로 바꾸는 TestCase 짜오기
        */
        String string1 = "Kakao ";
        String string2 = "Naver ";
        String string3 = "Coupang";
        String string4 = "09azAZN";

        int[] arr = {};
        arr[0] = 1;
//        Assertions.assertEquals(transferAsciiAndString(string1), string1);
//        Assertions.assertEquals(transferAsciiAndString(string2), string2);
//        Assertions.assertEquals(transferAsciiAndString(string3), string3);
        Assertions.assertEquals(transferAsciiAndString(string4), string4);

    }

    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        int metchCount = 0; // 로또 일치 수
        int notFixedNum = 0; // 알아볼 수 없는 번호(0)

        for(int lotto : lottos){
            if(lotto == 0){ // 알아볼 수 없는 숫자 카운트 해놓기
                notFixedNum+=1;
            }else{ // 알아볼 수 있는 숫자는 로또와 일치하는지 확인
                // for(int num : win_nums){
                //     if(lotto == num){
                //         // 로또와 일치하면
                //         metchCount+=1;
                //         break;
                //     }
                // }
            }
        }

        answer[0] = metchCount; //min
        //answer[1] = metchCount + notFixedNum; //max

        return answer;
    }

    @Test
    public void test1(){
        int[] a = {44, 1, 0, 0, 31, 25};
        int[] b = {31, 10, 45, 1, 6, 19};
        System.out.println(this.solution(a,b));
    }


}
