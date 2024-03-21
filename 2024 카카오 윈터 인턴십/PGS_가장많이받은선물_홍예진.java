import java.util.*;

class Solution {
    int n;
    Map<String, Friend> map;
    Friend[] friendArr;
    int[][] giveCount;
    public void init(String[] friends, String[] gifts){
        n = friends.length;
        map = new HashMap<>();
        friendArr = new Friend[n];
        giveCount = new int[n][n];

        for(int number = 0; number < n; number++){
            String friendName = friends[number];
            friendArr[number] = new Friend(friendName, number, 0);
            map.put(friendName, friendArr[number]);
        }

        for(String gift : gifts){
            StringTokenizer st = new StringTokenizer(gift);
            String giverName = st.nextToken();
            String receiverName = st.nextToken();

            Friend giver = map.get(giverName);
            Friend receiver = map.get(receiverName);

            giver.giftIndex++;
            receiver.giftIndex--;
            giveCount[giver.number][receiver.number]++;
        }

    }
    public int findReceiver(int i, int j){
        int diff = giveCount[i][j] - giveCount[j][i]; // 선물을 준 횟수의 차이
        if(diff > 0){
            return i;
        } else if(diff < 0){
            return j;
        }

        Friend fi = friendArr[i];
        Friend fj = friendArr[j];
        diff = fi.giftIndex - fj.giftIndex;
        if(diff > 0){
            return i;
        } else if(diff < 0){
            return j;
        }

        // 두 사람의 선물 지수가 같을 때
        return -1;
    }

    public int getMax(int[] nextGiftCount){
        int maxIndex = 0;
        int max = nextGiftCount[maxIndex];
        for(int i = 0; i < n; i++){
            if(max >= nextGiftCount[i]) continue;
            maxIndex = i;
            max = nextGiftCount[i];
        }
        return max;
    }

    public int solve(){
        int[] nextGiftCount = new int[n];
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int receiver = findReceiver(i, j);
                if(receiver == -1) continue;
                nextGiftCount[receiver]++;
            }
        }

        return getMax(nextGiftCount);
    }

    public int solution(String[] friends, String[] gifts) {
        init(friends, gifts);
        return solve();
    }
}
class Friend {
    String name;
    int number;
    int giftIndex;
    public Friend (String name, int number, int giftIndex){
        this.name = name;
        this.number = number;
        this.giftIndex = giftIndex;
    }
}