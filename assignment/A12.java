package assignment;
import java.util.Random;
import java.util.Scanner;

interface MaxHeap {
	public void Insert(int x);
	public int DeleteMax();
}

class Heap implements MaxHeap {
	final int heapSize = 100;
	private int[] heap;
	private int n; // current size of MaxHeap
	private int MaxSize; // Maximum allowable size of MaxHeap
	
	public Heap(int sz) {
		this.MaxSize = sz; //
	}

	public void display() {

	}
	
	@Override
	public void Insert(int x) {
		//heap insertion의 구현은 삽입되는 element 부터 parent로 이동하면서 크기를 비교하여 insert되는 키가 크면 parent와 맞바꾼다.
		if(n > MaxSize) {
			HeapEmpty();
		}
		for(int i = 0; i < this.n; i++)
			if(x >=  this.heap[i/2]) {
				int temp = this.heap[i/2];
				x = this.heap[i/2];
				heap[n] = temp; //x의 인덱스는 어떻게 구하지?
				
			}
	}
	
	@Override
	public int DeleteMax() {
		//heap에서 최대값을 delete하여 sort된 결과를 display하는 알고리즘을 구현한다.
		if(n < 0) {
			HeapEmpty();
		}
		//Max 값 삭제
		int Max = heap[1];
		heap[1] = heap[n];
		n--;
		
		int  i = 1;
		
		while(true) {
			int leftCh
		}
		

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
	     for (int i = 0; i < d.length; i++)
	         System.out.print(d[i] + " ");
	     System.out.println();
	 }
	 
//---------------------------------------------------------------------------main
	public static void main(String[] args) {
		Random rnd = new Random();
		int select = 0;
		Scanner stdIn = new Scanner(System.in);
		Heap heap = new Heap(20);
	    final int count = 10;
	    int[] x = new int[count+1];
	    int []sorted = new int[count];

		do {
			System.out.println("Max Tree. Select: 1 insert, 2 display, 3 sort, 4 exit => ");
			select = stdIn.nextInt();
			switch (select) {
			case 1: //insert
	
				break;
			case 2://display
				heap.display();
				break;
				
			case 3://정렬(deletemax와 for문)
				if(select<=0) {
					System.out.println("Heap empty");
				}

				break;

			case 4://exit
				return;

			}
		} while (select < 5);

		return;
	}
}
