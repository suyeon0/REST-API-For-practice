package com.example.apitest.coding_test.test1012;

import java.util.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {

    @Test
    public String solution(String input) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> dataStack = new Stack<>();
        StringBuilder output = new StringBuilder();
        /*
            숫자 만나면 numStack 으로 넣는다
            '[' 만나면 안에 있는 데이터 모은다
                     '[' 한번 더 만나면 모으던거 dataStack 에 넣고 다시 모은다
            --> '[' 만나면 모으던걸 dataStack 에 넣고 output 빌더 만들어서 다시 모으기 시작한다
            ']' 만나면 모아놓은 dataStack 데이터를 tmpRepeatObj 로 꺼내놓고
                     numStack pop() 해서 그 수만큼 리피트 한다. <<반복횟수 자리수 계산 방법 생각해보기>>
                     dataStack 에 데이터 있으면 pop() 해서 append 하고 output 에 넣는다
        */

        int idx = 0;
        while (idx < input.length()) {
            if (Character.isDigit(input.charAt(idx))) {
                numStack.add(Character.getNumericValue(input.charAt(idx)));
                idx++;
            } else if (input.charAt(idx) == '[') {
                dataStack.add(output.toString());
                output = new StringBuilder("");
                idx++;
            } else if (input.charAt(idx) == ']') {
                StringBuilder tmpRepeatObj = new StringBuilder(dataStack.pop());
                int repeatNum = numStack.pop();
                for (int i = 0; i < repeatNum; i++) {
                    tmpRepeatObj.append(output);
                }
                output = tmpRepeatObj;
                idx++;
            } else {
                output.append(input.charAt(idx++));
            }
        }

        return output.toString();
    }

    @Test
    public void test() {
        Assertions.assertEquals(this.solution("2[ab]"), "abab");
        Assertions.assertEquals(this.solution("2[ab3[cd]]"), "abcdcdcdabcdcdcd");
        Assertions.assertEquals(this.solution("2[ab]3[cd]"), "ababcdcdcd");
        Assertions.assertEquals(this.solution("3[a2[bc]d]"), "abcbcdabcbcdabcbcd");
    }

}
