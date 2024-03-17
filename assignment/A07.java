//실습4_5원형큐객체배열2
package assignment;
/*
 * 원형 큐로서 큐에 Point 객체를 저장
 * class CircularQueue의 필드는 QUEUE_SIZE, que,	front, rear, isEmptyTag 변수만 사용
 */

import java.util.Random;
import java.util.Scanner;

class Point5 {
	private int ix;
	private int iy;

	public Point5(int x, int y) {
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
		if ((this.ix == ((Point5) p).ix) && (this.iy == ((Point5) p).iy))
			return true;
		else
			return false;
	}
}

class CircularQueue {
	static int QUEUE_SIZE = 0;
	Point5[] que;// 배열로 객체원형 큐 구현
	int front, rear;
	static boolean isEmptyTag;

	// --- 실행시 예외: 큐가 비어있음 ---//
	class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String message) {
			super(message);
		}
	}

	// --- 실행시 예외: 큐가 가득 찼음 ---//
	class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String message) {
			super(message);
		}
	}

	public CircularQueue(int count) {
		front = rear = 0;
		QUEUE_SIZE = count;
		isEmptyTag = true;
		try {
		que = new Point5[QUEUE_SIZE];
		} catch (OverflowQueueException e) {
			QUEUE_SIZE = 0;
		}
		// 다음 2개 field 가 필요한지 확인 필요
	}

	void push(Point5 it) throws OverflowQueueException {
		if (isFull()) {
			throw new OverflowQueueException("push: circular queue overflow");
		}
		que[rear] = it;
		rear = (rear + 1)%QUEUE_SIZE;
		isEmptyTag = false;
	}

	Point5 pop() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("pop: circular queue empty");
		}
		
		Point5 x = que[front];
		front = (front + 1)%QUEUE_SIZE;// 수정하기
		
		if (front == QUEUE_SIZE) {
			front = 0;
			isEmptyTag = true;
		}
		return x;
	}

	void clear() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("enque: circular queue empty");
		}
		front = rear = 0;
		isEmptyTag = true;
	}

	// --- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return QUEUE_SIZE;
	}

	// --- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {// front, rear를 사용하여 갯수를 size로 계산
		if(front > rear) {
			return rear + QUEUE_SIZE - front;
		} 
		else {
			return rear - front;
		}
	}

	// --- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		if (isEmptyTag)
			return true;
		else
			return false;
	}

	// --- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		if (front == rear && !isEmptyTag)
			return true;
		else
			return false;
	}

	public void dump() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("dump: queue empty");
		else {
			for(Point5 p : que) {
				System.out.println(p);
			}
		}
	}

	public Point5 peek() throws EmptyQueueException {
		if (isEmpty())
			throw new EmptyQueueException("peek: queue empty"); // 큐가 비어있음
		return que[front];
	}
}

//------------------------------------------------------------------------------main

public class A07 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		CircularQueue oq = new CircularQueue(4); // 최대 4개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, rndy = 0;
		Point5 p = null;
		
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5) clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				rndy = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx + ", " + rndy + ")");
				p = new Point5(rndx, rndy);
				try {
					oq.push(p);
					System.out.println("push: size() = " + oq.size());
				} catch (CircularQueue.OverflowQueueException e) {
					System.out.println("queue이 full입니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 디큐
				try {
					p = oq.pop();
					System.out.println("pop:" + p + "\tsize() = " + oq.size());
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 4: // 덤프
				try {
					System.out.println("덤프한 데이터는 다음과 같습니다.");
					oq.dump();
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}

				break;
			case 5: // clear
				try {
					oq.clear();
					System.out.println("queue를 클리어 했습니다."); 
				} catch (CircularQueue.EmptyQueueException e) {
					System.out.println("queue이 비어있습니다." + e.getMessage());
					e.printStackTrace();
				}

				break;
			}
		}
	}
}
//현재 데이터 개수가 4까지 안 가고 다시 0으로 가는 문제
