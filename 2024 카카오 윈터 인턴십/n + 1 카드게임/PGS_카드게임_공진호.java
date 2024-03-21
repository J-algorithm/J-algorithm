
import java.util.*;
class PGS_카드게임_공진호 {
    static class PairCard implements Comparable<PairCard> {
        int n;
        int cost;

        public PairCard(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }

        @Override
        public int compareTo(PairCard o) {
            return this.cost - o.cost;
        }
    }


    public int solution(int coin, int[] cards) {
        int N = cards.length;

        boolean[] isInitCard = new boolean[N + 1];

        HashSet<Integer> myCards = new HashSet<>();
        PriorityQueue<PairCard> PQ = new PriorityQueue<>();

        //처음에 얻는 카드에 대하여 쌍이 존재하는지 체크
        for (int i = 0; i < N / 3; i++) {
            int card = cards[i];
            int another = findPair(card, N);
            if (myCards.contains(another)) {
                PQ.add(new PairCard(card, 0));
            }
            myCards.add(card);
            isInitCard[card] = true;
        }

        int round = 1;
        int p = N / 3;
        while (p < N) {
            int firstCard = cards[p++];
            int secondCard = cards[p++];
            int firstPair = findPair(firstCard, N);

            myCards.add(firstCard);
            // 내 카드들 중에 페어가 존재한다면
            if (myCards.contains(firstPair)) {
                // 초기 카드라면
                if (isInitCard[firstPair]) {
                    PQ.add(new PairCard(firstCard, 1));
                } else {
                    PQ.add(new PairCard(firstCard, 2));
                }
            }
            myCards.add(secondCard);
            int secondPair = findPair(secondCard, N);
            // 내 카드들 중에 페어가 존재한다면
            if (myCards.contains(secondPair)) {
                // 초기 카드라면
                if (isInitCard[secondPair]) {
                    PQ.add(new PairCard(secondCard, 1));
                } else {
                    PQ.add(new PairCard(secondCard, 2));
                }
            }

            // 지금 페어가 존재하지 않거나, 코인이 부족한 경우
            if (PQ.isEmpty() || coin < PQ.peek().cost) {
                break;
            }

            coin -= PQ.poll().cost;
            round++;
        }
        return round;
    }

    private static int findPair(int n, int cardLen) {
        return cardLen + 1 - n;
    }


    public static void main(String[] args) {
        PGS_카드게임_공진호 sol = new PGS_카드게임_공진호();
        int coin = 3;
        int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};
        System.out.println(sol.solution(coin, cards));
    }
}