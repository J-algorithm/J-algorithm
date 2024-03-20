import java.util.Arrays;

public class PGS_주사위고르기_공진호 {


    int N;
    boolean[] selected; // A가 선택한 주사위

    int sumIdx = 0;
    int[] sumDice;

    int maxWinCnt;
    int[] winDice;


    void init(int[][] dice) {
        N = dice.length;
        selected = new boolean[N];

        maxWinCnt = 0;
        winDice = new int[N / 2];
    }


    // 사용자가 사용하는 주사위 인덱스 배열 반환
    int[] getDice(boolean isA) {
        int size = N / 2;
        int[] dice = new int[size];

        int idx = 0;

        for(int i= 0 ; i<N;i++){
            if(selected[i]== isA){
                dice[idx++] = i;
            }
        }

        return dice;
    }


    void findSumValue(int depth, int sum, int[][] dice,int[] selectDice) {
        if (depth == N / 2) {
            sumDice[sumIdx++] = sum;
            return;
        }

        for(int i = 0 ;i <6;i++){
            int diceValue = dice[selectDice[depth]][i];
            findSumValue(depth+1,sum+diceValue,dice,selectDice);
        }

    }
    int[] getRoll(int[][] dice, int[] selectDice){
        sumIdx= 0 ;
        // N/2의 주사위 돌려서 나오는 경우 6^(N/2)
        int size = (int) Math.pow(6, (N / 2));
        sumDice = new int[size];
        findSumValue(0, 0, dice, selectDice);
        return sumDice;
    }

    int rollDice(int[] diceA, int[] diceB,int[][] dice) {
        // A가 N/2의 주사위를 돌린다.
        // B도 N/2의 주사위를 돌린다.

        int[] sumDiceA = getRoll( dice, diceA);
        int[] sumDiceB = getRoll( dice, diceB);

        Arrays.sort(sumDiceA);
        Arrays.sort(sumDiceB);

        // 각각의 경우에서 A가 이기는 경우 확인

        int cnt = 0;
        for(int sumA : sumDiceA){
            cnt+= getWinCnt(sumA, sumDiceB);
        }
        return cnt;
    }

    private int getWinCnt(int target, int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;

        while(lo<=hi){
            int m = (lo+hi)/2;

            if(arr[m]< target){
                lo = m+1;
            }else{
                hi = m-1;
            }
        }
        return lo;
    }

    void comb(int depth,int start, int[][] dice) {
        if (depth == N / 2) {

            int[] diceA = getDice(true);
            int[] diceB = getDice(false);
            int winCnt = rollDice(diceA,diceB,dice);

            if(winCnt> maxWinCnt){
                maxWinCnt = winCnt;
                winDice = diceA;
            }

            return;
        }
        for (int i = start; i < N; i++) {
            selected[i] = true;
            comb(depth + 1, i+1, dice);
            selected[i] = false;
        }
    }


    public int[] solution(int[][] dice) {

        // A가 N/2의 주사위를 가져간다.
        // B가 N/2의 주사위를 가져간다.
        init(dice);
        comb(0,0,dice);
        return Arrays.stream(winDice).map(n-> n+1).toArray();
    }

    public static void main(String[] args) {
        PGS_주사위고르기_공진호 solution = new PGS_주사위고르기_공진호();
        int[][] dice = {{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}};

        int[] result = solution.solution(dice);
        System.out.println(Arrays.toString(result));
    }

}
