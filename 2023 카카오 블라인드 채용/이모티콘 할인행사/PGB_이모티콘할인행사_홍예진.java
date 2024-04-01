import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class User {
    int discountLower;
    int totalUpper;
    int cost;

    public User(int discountLimit, int totalLimit, int cost) {
        this.discountLower = discountLimit;
        this.totalUpper = totalLimit;
        this.cost = cost;
    }

    public String toString(){
        return "User : "+ discountLower +" "+ totalUpper +" "+cost;
    }
}
class Solution {
    int n, m;
    Queue<User> userList;
    int[] originPrice;
    int maxCount;
    int maxTotalCost;

    public int getUserTotalCost(int[] discounts, User user){
        int total = 0;
        for(int i = 0; i < m; i++){
            int discount = discounts[i];
            if(user.discountLower <= discount){
                total += getDiscountedPrice(originPrice[i], discount);
            }
        }
        return total;
    }
    public void solve(int emoIndex, int totalCost, int[] discount, Queue<User> userList){

        // 현재(emoIndex) 이모티콘의 할인율을 10% 줄인다
        int[] nextDiscount = discount.clone();
        nextDiscount[emoIndex] -= 10;

        Queue<User> nextList = new LinkedList<>();

        // 할인율이 줄어든다.
        // = 1. 총 청구 요금이 증가한다
        // = nonPlus 사용자들의 구매 비용 기준보다 청구금액이 높아져 plus로 전환할 수 있다.

        // = 2. 기준 할인율보다 줄어들면 사지 않는다 = 총 청구 요금이 감소한다.
        // = plus 사용자들의 구매 비용 기준보다 청구금액이 낮아져 nonPlus로 전환할 수 있다.
        int plusCount = 0;
        int changedTotalCost = 0;
        while(!userList.isEmpty()){
            User user = userList.poll();
            user.cost = getUserTotalCost(nextDiscount, user);
            // 청구 금액이 구매 기준 금액보다 높다면 plus로 전환한다.
            if(user.totalUpper <= user.cost){
                user.cost = 0;
                plusCount++;
            } else {
                changedTotalCost += user.cost;
            }
            nextList.add(user);
        }

        if(plusCount > maxCount){
            maxCount = plusCount;
            maxTotalCost = changedTotalCost;
        } else if(plusCount == maxCount){
            maxTotalCost = Math.max(changedTotalCost, maxTotalCost);
        }

        userList.addAll(nextList);

        // 다음 경우의 수
        // 현재 대상 이모티콘을 10% 더 줄이거거나,
        if(nextDiscount[emoIndex] > 0) {
            solve(emoIndex, changedTotalCost, nextDiscount, nextList);
        }

        // 현재 할인율에서 그치고 다음 이모티콘으로 넘어가거나
        if(emoIndex < m-1) {
            solve(emoIndex + 1, changedTotalCost, nextDiscount, nextList);
        }
    }
    public int getDiscountedPrice(int originPrice, int discount){
        return (originPrice)*(100 - discount)/100;
    }
    public void init(int[][] users, int[] emoticons){
        n = users.length;
        m = emoticons.length;
        originPrice = emoticons.clone();

        int total = 0;
        for(int emoticon : emoticons){
            total += getDiscountedPrice(emoticon, 40);
        }

        userList = new LinkedList<>();
        int plusUserCount = 0;
        int nonPlusUserCount = 0;

        for(int[] user : users){
            User u = new User(user[0], user[1], total);
            // 각 사용자의 기준 금액 이상이라면 플러스 서비스를 사용한다.
            if(u.totalUpper <= total){
                plusUserCount++;
            }
            userList.add(u);
        }
        nonPlusUserCount = n - plusUserCount;

        maxCount = plusUserCount;
        maxTotalCost = nonPlusUserCount*total; // 모든 이모티콘이 최대 할인(40)을 하고 있기 때문에, 기준을 충족하는 사용자는 모든 이모티콘을 구매한다.
    }
    public int[] solution(int[][] users, int[] emoticons){
        int[] answer;
        init(users, emoticons);
        int[] discount = new int[m]; // 각 이모티콘별 할인가
        Arrays.fill(discount, 40);
        for(int emoIndex = 0; emoIndex < m; emoIndex++)
            solve(emoIndex, maxTotalCost, discount, userList);

        answer = new int[]{maxCount, maxTotalCost};
        return answer;
    }
}