class PGS_아날로그시계_공진호 {
    int TWELVE_HOUR = 12 * 60 * 60;

    class Time {
        // 1초에 시침이 움직이는 정도를 1로 설정
        int h;
        // 1초에 분침이 움직이는 정도는? 12
        int m;
        // 1초에 초침이 움직이는 정도는? 12*60
        int s;

        public Time(int seconds) {
            seconds = get12Standard(seconds);
            this.h = getHourDegree(seconds);
            this.m = getMinuteDegree(seconds);
            this.s = getHourDegree(getSecondDegree(seconds));
        }

        // 1초에 시침이 움직이는 정도를 1로 설정
        int getHourDegree(int seconds) {
            return seconds;
        }

        // 1초에 분침이 움직이는 정도는? 12
        int getMinuteDegree(int seconds) {
            seconds = seconds % 3600;
            return seconds * 12;
        }
        // 1초에 초침이 움직이는 정도는? 12*60
        int getSecondDegree(int seconds) {
            seconds = seconds % 60;
            return seconds * 12 * 60;
        }

        // 12시간을 넘어가면 12시간을 빼줌
        int get12Standard(int seconds) {
            if (seconds >= TWELVE_HOUR) {
                seconds -= TWELVE_HOUR;
            }
            return seconds;
        }

        // 초침과 분침이 만나는 경우
        boolean secondMinuteSame(Time nextTime) {
            if (this.s < this.m && nextTime.s >= nextTime.m) {
                return true;
            }
            // 초침이 현재 59초이고 분침이 59초와 00초 사이에 있을 때
            if (this.s == 12 * 60 * 59 && this.m >= getSecondDegree(59)) {
                return true;
            }

            return false;
        }

        // 초침과 시침이 만나는 경우
        boolean secondHourSame(Time nextTime) {
            if (this.s < this.h && nextTime.s >= nextTime.h) {
                return true;
            }
            // 초침이 현재 59초이고 시침이 59초와 00초 사이에 있을 때
            if (this.s == 12 * 60 * 59 && this.h >= getSecondDegree(59)) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "h=" + h +
                    ", m=" + m +
                    ", s=" + s +
                    '}';
        }
    }


    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {

        // 초침이 분침,시침에 맞을 떄마다 알람이 울림
        // 각도를 이용하여 문제 계산
        int time = h1 * 60 * 60 + m1 * 60 + s1;
        int endTime = h2 * 60 * 60 + m2 * 60 + s2;

        Time prev = new Time(time);
        int cnt = 0;

        // 시작하자마자 알람이 울리는 경우는 00시, 12시
        if (time % TWELVE_HOUR == 0) {
            cnt++;
        }

        while (time < endTime) {
            Time next = new Time(time + 1);
            // 초침과 분침이 만나는 경우, 초침과 시침이 만나는 경우
            // 위치를 이용하여 판단함
            if (prev.secondHourSame(next)) {
                cnt++;
            }
            if (prev.secondMinuteSame(next)) {
                cnt++;
            }
            // 12시,00시 인 경우 1번만 울림
            if (time + 1 == TWELVE_HOUR) {
                cnt--;
            }
            prev = next;
            time++;

        }
        return cnt;
    }

    public static void main(String[] args) {
        PGS_아날로그시계_공진호 solution = new PGS_아날로그시계_공진호();
        int h1 = 11;
        int m1 = 59;
        int s1 = 30;
        int h2 = 12;
        int m2 = 0;
        int s2 = 0;
        int ret = solution.solution(h1, m1, s1, h2, m2, s2);
        System.out.println(ret);

    }
}