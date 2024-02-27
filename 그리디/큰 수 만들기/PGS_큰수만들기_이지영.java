import java.util.*;

public class PGS_큰수만들기_이지영 {

    class Solution {
        Stack<Integer> stack;
        public String solution(String number, int k) {
            stack = new Stack<>();

            for (int i=0; i<number.length(); i++) {
                int n = number.charAt(i) - '0';
                if (stack.isEmpty()) {
                    stack.push(n);
                    continue;
                }

                while (!stack.isEmpty() && stack.peek()<n && k>0) {
                    stack.pop();
                    k--;
                }
                stack.push(n);
            }

            while (k>0) {
                stack.pop();
                k--;
            }

            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }
    }


}
