package omok;

import java.util.Scanner;

public class MyOmok {

	// 바둑판에 돌을 두는 배열
	static int[][] omok = new int[10][10];

	/**
	 * 매번 갱신되는 바둑판.
	 */
	public static void baDukPan() {

		System.out.printf("   ┌");
		for (int i = 1; i <= 10; i++) {
			System.out.printf("─┬");
		}
		System.out.printf("─┐");
		System.out.println();
		for (int i = 0; i < 10; i++) {
			System.out.printf("%-3d", i + 1);
			System.out.printf("├");
			for (int j = 0; j < 10; j++) {
				if (omok[i][j] == 1) {
					System.out.printf("─●");
				} else if (omok[i][j] == 2) {
					System.out.printf("─○");
				} else {
					System.out.printf("─┼");
				}
			}
			System.out.printf("─┤");
			System.out.println();
		}
		System.out.printf("   └");
		for (int i = 1; i <= 10; i++) {
			System.out.printf("─┴");
		}
		System.out.printf("─┘");
		System.out.println();
		System.out.printf("     ");
		for (int i = 1; i <= 10; i++) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}

	/**
	 * 실제 게임 플레이.
	 * 
	 * @param x
	 * @param y
	 * @param turn
	 * @return
	 */
	public static int gamePlay(int x, int y, int turn) {
		int X = x - 1;
		int Y = y - 1;
		int checkX;
		int checkY;
		int cnt;
		
		if(omok[Y][X] != 0 ) {
			System.out.println("이미 둔 자리입니다.");
			return 0;
		}

		omok[Y][X] = turn;
		
		// 가로 체크(흰 돌만)
		checkX = X;
		checkY = Y;
		cnt = 0;

		while (omok[checkY][checkX] == 2) {
			if (checkX == 0) {
				checkX--;
				break;
			}
			checkX--;
		}
		checkX++;
		while (omok[checkY][checkX] == 2) {
			if (cnt >= 5 || checkX == 9) {
				break;
			}
			cnt++;
			checkX++;

		}
		System.out.println("가로cnt : " + cnt);
		if (cnt >= 5) {
			baDukPan();
			System.out.println("종료합니다.");
			return 1;
		}

		// 세로 체크(흰 돌만)
		checkX = X;
		checkY = Y;
		cnt = 0;

		while (omok[checkY][checkX] == 2) {
			if (checkY == 0) {
				checkY--;
				break;
			}
			checkY--;
		}
		checkY++;
		while (omok[checkY][checkX] == 2) {
			if (cnt == 5 || checkY == 9) {
				break;
			}
			cnt++;
			checkY++;

		}
		System.out.println("세로cnt : " + cnt);
		if (cnt >= 5) {
			baDukPan();
			System.out.println("종료합니다.");
			return 1;
		}

		// 대각선 ↗체크(흰 돌만)

		checkX = X;
		checkY = Y;
		cnt = 0;

		while (omok[checkY][checkX] == 2) {
			if (checkX == 0 && checkY == 9) {
				checkX--;
				checkY++;
				break;
			}
			if (checkX == 0) {
				checkX--;
				break;
			}
			if (checkY == 9) {
				checkY++;
				break;
			}
			checkX--;
			checkY++;
		}
		if (checkX != 9)
			checkX++;
		if (checkY != 0)
			checkY--;

		while (omok[checkY][checkX] == 2) {
			cnt++;
			if (checkY != 0)
				checkY--;
			if (checkX != 9)
				checkX++;
			if (cnt == 5 || checkY == 0 || checkX == 9) {
				break;
			}
		}
		System.out.println("대각선 ↗ cnt : " + cnt);
		if (cnt >= 5) {
			baDukPan();
			System.out.println("종료합니다.");
			return 1;
		}

		// 대각선 ↘체크(흰 돌만)

		checkX = X;
		checkY = Y;
		cnt = 0;

		while (omok[checkY][checkX] == 2) {
			if (checkX == 0 && checkY == 0) {
				checkX--;
				checkY--;
				break;
			}
			if (checkX == 0) {
				checkX--;
				break;
			}
			if (checkY == 0) {
				checkY--;
				break;
			}
			checkX--;
			checkY--;
		}
		if (checkX != 9)
			checkX++;
		if (checkY != 9)
			checkY++;

		while (omok[checkY][checkX] == 2) {
			cnt++;
			if (checkY != 9)
				checkY++;
			if (checkX != 9)
				checkX++;
			if (cnt == 5 || checkY == 0 || checkX == 0) {
				break;
			}
		}
		System.out.println("대각선 ↘ cnt : " + cnt);
		if (cnt >= 5) {
			baDukPan();
			System.out.println("종료합니다.");
			return 1;
		}

		return 0;
	}

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		int gameEnd = 0; // 게임이 진행중인지 종료할지 정하는 변수
		int turn = 0; // 턴을 정하는 변수
		int x = 0;
		int y = 0;

		while (gameEnd == 0) {

			baDukPan();

			System.out.printf("X좌표 : ");
			x = sc.nextInt();
			System.out.printf("Y좌표 : ");
			y = sc.nextInt();

			turn = (turn == 1) ? 2 : 1;

			gameEnd = gamePlay(x, y, turn);

		}

	}

}
