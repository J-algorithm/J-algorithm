import java.util.*;


class Solution {
    int n;

    public int minChangeCnt(char ch1, char ch2){
        if(ch1 > ch2){
            char temp = ch1;
            ch1 = ch2;
            ch2 = temp;
        }

        int changeCnt = (int)(ch2 - ch1);
        if(changeCnt > 13) changeCnt = 26 - changeCnt;
        return changeCnt;
    }

    public int solve(int index, char[] arr, char[] str){
        int answer = 0;
        answer += minChangeCnt(arr[index], str[index]);

        char temp = arr[index];
        arr[index] = str[index];

        int costR = Integer.MAX_VALUE;
        int costL = Integer.MAX_VALUE;

        for(int i = 1; i <= n; i++){
            int right = (index + i)%n;
            if(arr[right] != str[right]){
                costR = i + solve(right, arr, str);
                break;
            }
        }

        for(int i = 1; i <= n; i++){
            int left = (index + (n-1)*i)%n;
            if(arr[left] != str[left]){
                costL = i + solve(left, arr, str);
                break;
            }
        }

        int costMin = Math.min(costR, costL);
        if(costMin != Integer.MAX_VALUE){
            answer += costMin;
        }

        arr[index] = temp;

        return answer;
    }


    public int solution(String name) {
        int answer = 0;
        n = name.length();
        char[] str = name.toCharArray();
        char[] arr = new char[n];
        Arrays.fill(arr, 'A');

        answer = solve(0, arr, str);

        return answer;
    }
}
