//실습4_4정수선형큐_리스트(삭제 시 자동으로 데이터가 이동됨)
package ch04;
//선형 큐 구현
import java.util.Scanner;

import ch04.IntStack4.EmptyIntStackException;

/*
* Queue of ArrayList
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//int형 고정 길이 큐

class Queue4 {
	private List<Integer> que;//원형큐로 구현하지 않는다 
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
//	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String message) {
			super(message);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String message) {
			super(message);
		}
	}

//--- 생성자(constructor) ---//
public Queue4(int maxlen) {
	front = rear = 0;
	capacity = maxlen;
	try {
		que = new ArrayList<>(capacity);
	} catch (OverflowQueueException e) {
		capacity = 0;
	}
}

//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowQueueException {
		if (isFull()) {
			throw new OverflowQueueException("Inqueue: queue overflow");
		}
		que.add(x);
		rear++;
		return x;
	}

//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Dequeue: queue empty");
		}
		int x = que.remove(front);
		rear--;//리스트는 자동으로 데이터가 이동하기 때문에 front가 아니라 rear를 조절해줘야 함
		return x;
	}

	public void dump() {
		if(isEmpty()) {
			throw new EmptyQueueException("Dump: queue empty");
		}
		else {
			for(int i = 0; i < capacity; i++) {
				System.out.print(que.get(i) + " ");
			}
		}
	}
	
//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException("Peek: queue empty");
		}
		return que.get(front);
	}

//--- 큐를 비움 --- peek() 처럼 예외 발생 구현//
	public void clear() throws EmptyQueueException {
		/*
		 * queue을 empty로 만들어야 한다.
		 * queue이 empty일 때 clear()가 호출된 예외 발생해야 한다 
		 */
		if (isEmpty()) {// queue이 빔
			throw new EmptyQueueException("Clear: queue empty");
		}
		front = rear = 0;
	}
	
//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(int x) {
		for (int i = 0; i < rear+1; i++) {
			int idx = (i + front) % capacity; // 검색은 물리적인 첫 요소가 아니라 논리적인 첫 요소인 프런트에서 시작함
			if (que.get(idx) == x) // 검색 성공
				return idx;
		}
		return -1; // 검색 실패
	}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		return rear-front;//rear는 다음 값을 가리키기 때문에 +1이 되어 있음
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		if(front == 0 && rear == 0) 
			return true;
		else
			return false;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		if(rear == capacity) //front == rear == capacity 이렇게 안 해도 됨
			return true;
		else
			return false;
	}

//-----------------------------------------------------------------------------------------------main
	
}
public class Train4_0 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		Queue4 oq = new Queue4(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx, p = 0;
		 
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(Queue4.OverflowQueueException e) {
					System.out.println("queue이 가득차있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;

			case 3: // 피크
				try {
					p = oq.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
			case 4: // 덤프
				try {
					System.out.println("큐를 덤프한 결과는 다음과 같습니다.");
					oq.dump();
				} catch (Queue4.EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다." + e.getMessage());
					e.printStackTrace();
				}
				break;
				
			case 5: //clear
				 try {
					 oq.clear();
					 System.out.println("큐를 클리어 했습니다.");
				 } catch (Queue4.EmptyQueueException e) {
					 System.out.println("큐가 비어 있습니다." + e.getMessage());
					 e.printStackTrace();
				 }
				break;
			default:
				break;
			}
		}
	}
}