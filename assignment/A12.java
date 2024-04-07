//실습6_16_1heap정렬
package assignment;

import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);

	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap; // heap은 참조변수
	private int n; // MaxHeap의 현재 입력된 element 개수
	private int MaxSize; // Maximum allowable size of MaxHeap

	public Heap(int sz) { // 생성자
		n = 0;
		MaxSize = sz;
		heap = new int[MaxSize + 1]; // heap에 메모리 공간 할당
	}

	public void display() {// heap 배열을 출력한다. 배열 인덱스와 heap[]의 값을 출력한다.
		for (int i = 1; i <= n; i++) { // n이 1부터 시작하기 때문에 개수와 일치
			System.out.printf("[%d] : %d\n", i, heap[i]);
		}

	}

	@Override
	public void Insert(int x) {// max heap이 되도록 insert한다. 삽입후 complete binary tree가 유지되어야 한다.
		int i;
		if (n == MaxSize) {
			HeapFull();
			return;
		}

		n++;
		for (i = n; i > 1; i = i / 2) { // 루트까지 부모 노드와의 비교를 반복
			// heap[i / 2]: 부모 노드
			if (x <= heap[i / 2]) // 삽입하는 값이 부모 노드보다 작거나 같을 때
				break; // for문을 중지하고 break
			heap[i] = heap[i / 2]; // 삽입하는 값이 부모 노드보다 클 때 자식의 노드에 부모 노드 데이터 저장, if문에서 break가 되거나 for문이 끝날 때까지 반복
		}
		heap[i] = x; // for문을 돌면서 i는 값을 바꾸기로 한 부모 노드의 인덱스가 됨(break 후에 오는 곳)
						// 또는 새로운 값을 노드의 끝에 저장

	}

	@Override
	public int DeleteMax() {// heap에서 가장 큰 값을 삭제하여 리턴한다.
		// heap이 비어있는 경우
		if (n == 0) {
			HeapEmpty();
			return 0;
		}

		// heap이 비어있지 않은 경우
		int x = heap[1]; // 힙의 최대값을(root) x에 저장해둠
		int i, j; // i는 현재 위치, j는 왼쪽 자식 노드의 위치
		int last = heap[n]; // 마지막 리프 노드값
		n--; // 마지막 리프 노드를 삭제하기 위해 힙의 크기를 줄여줌

		for (i = 1, j = 2; j <= n;) {// 루트 노드부터 마지막 노드까지 이동하면서 힙을 재구성
			if (j < n) { // j가 n이면 오른쪽 leaf가 없는 경우임
				if (heap[j] < heap[j + 1])// 오른쪽 자식 노드가 왼쪽 자식 노드보다 크면 j를 오른쪽 자식 노드로 설정
					j++;
				if (last >= heap[j])// 마지막 리프 노드의 값이 현재 노드보다 작거나 같은 경우 루프를 종료
					break;
				heap[i] = heap[j];
				i = j;
				j = j * 2; // 다음 child로 이동
			}
		}

		return x;
	}

	private void HeapEmpty() {
		System.out.println("Heap Empty");
	}

	private void HeapFull() {
		System.out.println("Heap Full");
	}
}

public class A12 {
	static void showData(int[] d) {
		for (int num : d) {
			System.out.print(num + " ");
		}
	}

	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(10);
		final int count = 10;// 난수 생성 갯수
		int data = 0;
		int[] x = new int[count + 1];// x[0]은 사용하지 않으므로 11개 정수 배열을 생성
		int[] sorted = new int[count];// heap을 사용하여 deleted 정수를 배열 sorted[]에 보관후 출력

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1:// 난수를 생성하여 배열 x에 넣는다 > heap에 insert한다.
				for (int i = 1; i < count + 1; i++) {
					data = stdIn.nextInt(20);
					System.out.println("Input data: " + data);
					x[i] = data;
					heap.Insert(data);
				}
				showData(x);
				System.out.println();
				heap.display();
				break;

			case 2: // heap 트리구조를 배열 인덱스를 사용하여 출력한다.
				heap.display();
				break;

			case 3:// heap에서 delete를 사용하여 삭제된 값을 배열 sorted에 넣는다 > 배열 sorted[]를 출력하면 정렬 결과를 얻는다
				for (int i = 0; i < count; i++) {
					sorted[i] = heap.DeleteMax();
				}
				showData(sorted);
				break;

			case 4:
				return;

			}
		} while (select < 5);

		return;
	}
}
