package acmicpc.BJ_2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_2564_경비원
 * author djunnni
 */
public class Main {
    public static int width, height;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 가로, 세로의 길이
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        // 상점의 개수 100이하
        int storeSize = Integer.parseInt(br.readLine());
        // 상점 수만큼 리스트 크기 지정
        List<int[]> storeList = new ArrayList<>(storeSize + 1);

        // 상점의 정보를 저장
        // [0] -> 1: 블록의 북쪽, 2 -> 블록의 남쪽, 3 -> 블록의 서쪽, 4 -> 블록의 동쪽
        // [1] -> 북쪽 또는 남쪽에 속할 때, 왼쪽 끝에서부터 자신의 column까지의 거리
        // [1] -> 동쪽 또는 서쪽에 위치할 때, 블록의 위쪽 경계로부터의 거리
        for(int i = 0; i < storeSize; i++) {
            st = new StringTokenizer(br.readLine()," ");
            storeList.add(new int[] {
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
            });
        }

        // 동근이의 위치[상점과 동일]
        // [1] -> 북쪽 또는 남쪽에 속할 때, 왼쪽 끝에서부터 자신의 column까지의 거리
        // [1] -> 동쪽 또는 서쪽에 위치할 때, 블록의 위쪽 경계로부터의 거리
        st = new StringTokenizer(br.readLine(), " ");
        int[] dongeun = new int[] {
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
        };

        // [0] -> 왼쪽으로부터 거리, [1] -> 오른쪽부터의 거리
        List<int[]> minDistance = new ArrayList<>(storeSize + 1);

        // 상점들을 돌면서 거리의 격 result를 거리 리스트에 저장하기
        for(int[] store: storeList) {
            int[] result = getDistance(store, dongeun);
            minDistance.add(result);
        }

        // 최소거리를 찾아서 answer에 더한다.
        int answer = 0;
        for(int[] distances : minDistance) {
            answer += distances[0] > distances[1] ? distances[1] : distances[0];
        }
        System.out.println(answer);
    }
    public static int[] getDistance(int[] store, int[] dongeun) {
        int temp[] = new int[2];

        // 둘이 같은 방향을 바라보고 있다면
        if(store[0] == dongeun[0]) {
            temp[0] = Math.abs(dongeun[1] - store[1]);
        } else if(store[0] == 1 && dongeun[0] == 2 || store[0] == 2 && dongeun[0] == 1) {
            // 한쪽이 북쪽, 한쪽이 남쪽일 경우
            temp[0] = height + store[1] + dongeun[1];
        } else if(store[0] == 3 && dongeun[0] == 4 || store[0] == 4 && dongeun[0] == 3) {
            // 한쪽이 서쪽, 한쪽이 동쪽일 경우
            temp[0] = width + store[1] + dongeun[1];
        } else if(store[0] == 1 && dongeun[0] == 3 || store[0] == 3 && dongeun[0] == 1) {
            // 하나는 서쪽 하나는 북쪽일 때,
            temp[0] = store[1] + dongeun[1];
        } else if(store[0] == 1 && dongeun[0] == 4) {
            // 상점이 북쪽, 동근이 동쪽
            temp[0] = width - store[1] + dongeun[1];
        } else if(store[0] == 4 && dongeun[0] == 1) {
            // 상점이 동쪽, 동근이 북쪽
            temp[0] = width + store[1] - dongeun[1];
        } else if(store[0] == 3 && dongeun[0] == 2) {
            // 상점이 서쪽, 동근이 남쪽
            temp[0] = height - store[1] + dongeun[1];
        } else if(store[0] == 2 && dongeun[0] == 3) {
            // 동근이 서쪽, 상점이 남쪽
            temp[0] = height + store[1] - dongeun[1];
        } else if(store[0] == 4 && dongeun[0] == 2 || store[0] == 2 && dongeun[0] == 4) {
            // 동근이 남쪽, 상점이 동쪽
            temp[0] = height + width + store[1] + dongeun[1];
        }

        temp[1] = 2 * (width + height) - temp[0];

        return temp;
    }
}
