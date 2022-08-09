
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * D3_6808_규영이와인영이의카드게임
 * 
 * 자바 6초, 메모리 256MB
 * 
 * 둘이 1 ~ 18까지의 수가 적힌 18장의 카드로 게임중이다.
 * 한 번의 게임에 둘은 카드를 섞어 9장씩 나눈다. 그리고 9라운드에 걸쳐 게임한다.
 * 
 * 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고
 * 낮은 수가 적힌 카드를 낸 사람은 아무런 점수가 없다.
 * 
 * 아홉라운드를 끝내고 총점을 따졌을 때, 더 높은 사람이 승자다.
 * 
 * 두 사람의 총점이 같으면 무승부다.
 * 
 * 규영이가 내는 카드의 순서를 고정하면 인영이가 어떻게 카드를 내는지에 따라 9!이다.
 * 
 * 규영이가 이기는 경우와 지는 경우는?
 * 
 * 접근 방법
 * 1. hashSet으로 인영이의 카드를 정리 -> 조합 -> 게임 진행 -> 2초
 * 2. 배열로 인영이의 카드 정리 -> 조합 -> 게임 진행 -> 0.2초
 * 
 * @author djunnni
 *
 */
public class Solution {
	public static int CARD_SIZE = 9;
	public static int CARD_MAX = 18;
	public static long win, fail;
	public static int[] gyu_cards, in_cards;
	public static boolean[] isSelected;
	public static HashSet<Integer> in_set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			gyu_cards = new int[CARD_SIZE + 1]; // 1 <= cards <= 9
			in_cards = new int[CARD_SIZE + 1];
			isSelected = new boolean[CARD_SIZE + 1];
			
			in_set = new HashSet<>();
			win = fail = 0;
			// 인영이가 가지고 있는 카드를 set에 넣기
			for(int i = 1; i <= CARD_MAX; i++) {
				in_set.add(i);
			}
			// 규영이가 가지고 있는 카드 넣기
			for(int i = 1; i <= CARD_SIZE; i++) {
				gyu_cards[i] = Integer.parseInt(st.nextToken());
				in_set.remove(gyu_cards[i]);
			}
			int cnt = 1;
			for(int v : in_set) {
				in_cards[cnt++] = v;
			}
			in_set = null;
			
			game(1, 0, 0);
			
			System.out.println("#" + test_case + " " + win + " " + fail);
		}
	}
	public static void game(int round, int gyu_score, int in_score) {
		if(in_cards.length == round) {
			if(gyu_score > in_score) {
				win++;
			} else if(gyu_score < in_score) {
				fail++;
			}
			return;
		}
		for(int i = 1; i < in_cards.length; i++) {
			if(isSelected[i]) {
				continue;
			}
			isSelected[i] = true;
			if(in_cards[i] < gyu_cards[round]) {
				// 규영이가 이길 때,
				game(round + 1, gyu_score + in_cards[i] + gyu_cards[round], in_score);
			} else {
				// 규영이가 질 때,
				game(round + 1, gyu_score, in_score + in_cards[i] + gyu_cards[round]);
			}
			isSelected[i] = false;
		}

	}
}
