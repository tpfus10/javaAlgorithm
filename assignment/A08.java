//Test_QueenEight_구현과제
package assignment;

//해가 256개 확인 필요 23.12.12
import java.util.ArrayList;
import java.util.List;
import assignment.Stack4.EmptyGenericStackException;

//https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/?ref=lbp
//N Queen problem / backtracking
//모든 해가 나오는 버젼 만들기 
/*
* 체스판은 8 x 8 체스의 기물: king/가로세로대각선 1칸만 이동, queen/가로세로 대각선/같은 편의 기물을 넘을 수 없다,
* Rook/가로,세로 이동/다른 기물을 넘을 수없다, bishop/대각선, knight/1-2칸 이동/다른 기물을 넘을 수 있다,
* pawn/처음 이동은 2칸까지 가능, 그 후 한칸만 가능, 잡을 때는 대각선 가능 체스판 최대 배치 문제 : king/16,
* Queen/8, rook/8, bishop/?, knight/? rook 2개/a, h, knight 2개/b, g, bishop
* 2개/c, f, queen 1개/black queen은 black 칸에, 폰 8개
*/

class Point {
	private int ix;
	private int iy;

	public Point(int x, int y) {
		ix = x;
		iy = y;
	}

	@Override
	public String toString() {
		return "<" + ix + ", " + iy + ">";
	}

	public int getX() {
		return ix;
	}

	public int getY() {
		return iy;
	}

	public void setX(int x) {
		ix = x;
	}

	public void setY(int y) {
		iy = y;
	}

	@Override
	public boolean equals(Object p) {
		if ((this.ix == ((Point) p).ix) && (this.iy == ((Point) p).iy))
			return true;
		else
			return false;
	}
}

class Stack4 {
	// --- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;

		public EmptyGenericStackException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException(String message) {
			super(message);
		}
	}

	private List<Point> data; // 스택용 배열
	// private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

	// --- 생성자(constructor) ---//
	public Stack4(int capacity) {
		top = 0;
		this.capacity = capacity;
		try {
			data = new ArrayList<>(capacity);
		} catch (OverflowGenericStackException e) {
			capacity = 0;
		}
	}

	// --- 스택에 x를 푸시 ---//
	public void push(Point x) throws OverflowGenericStackException {
		if (isFull())
			throw new OverflowGenericStackException("push: stack overflow");
		data.add(x);
		top++;
	}

	// --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public Point pop() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("pop: stack empty");
		}
		return data.remove(--top);
	}

	// --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public Point peek() throws EmptyGenericStackException {
		if (isEmpty()) {
			throw new EmptyGenericStackException("pop: stack empty");
		}
		return data.get(top - 1);
	}

	// --- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

	// --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(Point x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

	// --- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

	// --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

	// --- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

	// --- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

	// --- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() throws EmptyGenericStackException {
		if (top <= 0)
			throw new EmptyGenericStackException("stack:: dump - empty");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i) + " ");
			System.out.println();
		}
	}
}

public class A08 {
	public static void EightQueen(int[][] d) throws EmptyGenericStackException {
		int count = 0;// 퀸 배치 갯수
		int numberSolutions = 0;
		int ix = 0, iy = 0;// 행 ix, 열 iy
		Stack4 st = new Stack4(100); // 100개를 저장할 수 있는 스택을 만들고
		Point p = new Point(ix, iy);// 현 위치를 객체로 만들고
		d[ix][iy] = 1;// 현 위치에 queen을 넣었다는 표시를 하고
		count++;
		ix++; // 다음 행으로 이동
		st.push(p);// 스택에 현 위치 객체를 push

		while (true) {
			if (st.isEmpty() && iy==8) {
				break;
			}
			int newCol = nextMove(d, ix, iy);
			if (newCol != -1) {
				d[ix][newCol] = 1;
				st.push(new Point(ix, newCol)); // 새로운 객체를 만들어서 넣어주기
				count++;
				ix++;
				iy = 0;
				if(count != 8) 
				continue;
			}
			if (newCol == -1 || count == 8) {
				if (count == 8) {
					numberSolutions++;
					System.out.println("[" + numberSolutions + "]");
					showQueens(d);
				}
				p = st.pop();
				ix = p.getX();
				iy = p.getY();
				d[ix][iy] = 0;
				count--;
				iy++;
				continue;
			}
		}
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남서, 북동 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSW(int[][] d, int cx, int cy) { // x++, y-- or x--, y++ where 0<= x,y <= 7
		int i = cx; // 기준점인 (cx, cy)에서 방향에 따라 검사
		int j = cy;
		while (i < d.length && j >= 0) {
			if (d[i][j] == 1) // 확인 먼저 하고 인덱스 값 조정하기
				return false;
			i++;
			j--;
		}
		int k = cy;
		int q = cx;
		while (k < d.length && q >= 0) {
			if (d[q][k] == 1)
				return false;
			k++;
			q--;
		}
		return true;
	}

	// 배열 d에서 행 cx, 열 cy에 퀸을 남동, 북서 대각선으로 배치할 수 있는지 조사
	public static boolean checkDiagSE(int[][] d, int cx, int cy) {// x++, y++ or x--, y--
		int i = cx;
		int j = cy;
		while (i < d.length && j < d.length) {
			if (d[i][j] == 1)
				return false;
			i++;
			j++;
		}
		int k = cx;
		int q = cy;
		while (k >= 0 && q >= 0) {
			if (d[k][q] == 1)
				return false;
			k--;
			q--;
		}
		return true;
	}

	// 배열 d에서 행 crow에 퀸을 배치할 수 있는지 조사
	public static boolean checkRow(int[][] d, int crow) {
		int i = 0;
		while (i < d.length) {
			if (d[crow][i] == 1)
				return false;
			i++;
		}
		return true;
	}

	// 배열 d에서 열 ccol에 퀸을 배치할 수 있는지 조사
	public static boolean checkCol(int[][] d, int ccol) {
		int i = 0;
		while (i < d.length) {
			if (d[i][ccol] == 1)
				return false;
			i++;
		}
		return true;
	}

	// 배열 d에서 (x,y)에 퀸을 배치할 수 있는지 조사(CheckRow, CheckColumn)
	public static boolean checkMove(int[][] d, int x, int y) {// (x,y)로 이동 가능한지를 check
		if (checkDiagSE(d, x, y) & checkDiagSW(d, x, y) & checkRow(d, x) & checkCol(d, y)) {
			return true;
		} else {
			return false;
		}
	}

	// 배열 d에서 현재 위치(row,col)에 대하여 다음에 이동할 위치 nextCol을 반환, 이동이 가능하지 않으면 -1를 리턴
	public static int nextMove(int[][] d, int row, int col) {// 현재 row, col에 대하여 이동할 col을 return
		for (int i = col; i < 8; i++) {
			if (checkMove(d, row, i)) {
				return i;
			}
		}
		return -1;
	}

	static void showQueens(int[][] data) {// 배열 출력
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}

//-------------------------------------------------------------main

	public static void main(String[] args) throws EmptyGenericStackException {
		int row = 8, col = 8;
		int[][] data = new int[8][8]; // 체스판 만들기
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[0].length; j++)
				data[i][j] = 0;

		EightQueen(data);

	}
}