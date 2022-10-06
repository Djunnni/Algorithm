package acmicpc.BJ_2239;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * acmicpc.BJ_2239_스도쿠
 * author djunnni
 */
class Main {
    static int SIZE = 9;
    static int[][] matrix;
    static boolean rowUsed[][], columnUsed[][], threeByThreeUsed[][];
    static List<String> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        matrix = new int[SIZE][SIZE];
        // matrix와 같이 0~8까지 들어가며 이때 숫자는 1 ~ 9까지로 한다.
        rowUsed = new boolean[SIZE][SIZE + 1];
        columnUsed = new boolean[SIZE][SIZE + 1];
        threeByThreeUsed = new boolean[SIZE][SIZE + 1];

        for (int i = 0; i < SIZE; i++) {
            String data = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = data.charAt(j) - '0';
                if (matrix[i][j] != 0) {
                    rowUsed[i][matrix[i][j]] = true;
                    columnUsed[j][matrix[i][j]] = true;
                    threeByThreeUsed[getMatric(i, j)][matrix[i][j]] = true;
                }
            }
        }
        // 출력
        // for(int[] row : matrix) {
        // System.out.println(Arrays.toString(row));
        // }
        answer = new ArrayList<>();

        play(1);

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        char[] data = answer.get(0).toCharArray();

        for (int i = 0; i <= 80; i++) {
            if (i % 9 == 0 && i != 0) {
                sb.append("\n");
            }
            sb.append(data[i]);
        }

        System.out.println(sb.toString());

    }

    static boolean play(int number) {
        // 기저조건
        if (number == 82) {
            answer.add(printMatrix(matrix));
            return true;
        }
        int[] position = getPosition(number);

        // 해당위치에 이미 숫자가 있다면 다음번 숫자로 넘어가기
        if (matrix[position[0]][position[1]] != 0) {
            if (play(number + 1)) {
                return true;
            }
        } else {
            int numMatric = getMatric(position[0], position[1]);
            // 숫자가 없다면
            int[] canUse = canUse(
                    rowUsed[position[0]],
                    columnUsed[position[1]],
                    threeByThreeUsed[numMatric]);
            if (canUse.length == 0) {
                return false;
            }
            for (int v : canUse) {
                rowUsed[position[0]][v] = true;
                columnUsed[position[1]][v] = true;
                threeByThreeUsed[numMatric][v] = true;
                matrix[position[0]][position[1]] = v;
                if (play(number + 1)) {

                } else {
                    rowUsed[position[0]][v] = false;
                    columnUsed[position[1]][v] = false;
                    threeByThreeUsed[numMatric][v] = false;
                    matrix[position[0]][position[1]] = 0;
                }
            }

        }

        return false;
    }

    static String printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(matrix[i][j]);
            }
        }
        return sb.toString();
    }

    static int[] canUse(boolean[] rowUsed, boolean[] columnUsed, boolean[] threeUsed) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= SIZE; i++) {
            if (rowUsed[i] || columnUsed[i] || threeUsed[i]) {
            } else {
                list.add(i);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    static int[] getPosition(int number) {
        int r = (number - 1) / 9;
        int c = (number - 1) % 9;

        return new int[] { r, c };
    }

    private static int getMatric(int r, int c) {
        int rIdx = (r / 3) * 3;
        int cIdx = (c / 3);

        return rIdx + cIdx;
    }
}