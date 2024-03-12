import java.util.Arrays;

public class PGS_단속카메라_공진호 {

    static class Car implements Comparable<Car>{
        int start;
        int end;

        public Car(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Car o) {
            return Integer.compare(this.end, o.end);
        }
    }


    Car[] cars;

    void init(int [][] routes){
        int n = routes.length;
        cars = new Car[n];
        for(int i = 0; i < n; i++){
            cars[i] = new Car(routes[i][0], routes[i][1]);
        }
    }

    public int solution(int[][] routes) {

        init(routes);

        // 차를 나간 지점을 기준으로 오름차순으로 정렬
        Arrays.sort(cars);
        // 현재 카메라 위치
        int cameraLoc = cars[0].end;
        int cnt = 1;
        for(Car car : cars){
            // 카메라 위치보다 자동차 진입 시접이 느리다면 카메라 위치를 차가 진출 지점으로 옮김
            if(cameraLoc < car.start){
                cameraLoc = car.end;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        PGS_단속카메라_공진호 solution = new PGS_단속카메라_공진호();
        int[][] routes = {{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}};
        System.out.println(solution.solution(routes));
    }
}
