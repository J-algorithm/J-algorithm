import java.util.*;

public class PGS_큰수만들기_이지영 {

    class Solution {
        Deque<Integer> deque;
        public String solution(String number, int k) {
            deque = new ArrayDeque<>();

            for (int i=0; i<number.length(); i++) {
                int n = number.charAt(i) - '0';
                while (!deque.isEmpty() && deque.peekLast()<n && k>0) {
                    deque.pollLast();
                    k--;
                }
                deque.add(n);
            }

            while (k > 0) {
                deque.pollLast();
                k--;
            }

            StringBuilder sb = new StringBuilder();
            while(!deque.isEmpty()) {
                sb.append(deque.pollFirst());
            }
            return sb.toString();
        }
    }

}
