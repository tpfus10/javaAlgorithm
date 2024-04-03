package ch11;

/*
 Graph Representation
 Adjacency Lists + BFS + DFS
*/

import java.util.Scanner;

class ListNode {
	int data;
	ListNode link;

	public ListNode(int data) {
		this.data = data;
		link = null;
	}
}

class List {
	ListNode first;

	public List() {
		first = null;
	}

	void Insert(int k) {// 같은 값을 체크하지 않아 중복 입력됨
		// 구현할 부분
		ListNode temp = new ListNode(k);
		if (first == null) { // HeadNode[i]가 null일 때
			first = temp;
			return;
		}

		ListNode p = first;
		ListNode q = null;
		while (p != null) {
			if (k > p.data) {
				q = p;
				p = p.link;
			} else {
				if (q == null) {
					first = temp;
					temp.link = p;
					return;
				} else {
					q.link = temp;
					temp.link = p;
					return;
				}
			}

		}
		p = temp;
		q.link = temp;
	}

	void Delete(int k) {
		ListNode q = null;
		ListNode p = first;

		// 헤드 노드가 삭제되어야 하는 경우
		if (p != null && p.data == k) {
			first = p.link;
			return;
		}

		// k 값을 가진 노드를 찾아 삭제
		while (p != null) {
			if (p.data == k) {
				q.link = p.link;
				return;
			}
			q = p;
			p = p.link;
		}
	}
}

class ListIterator {

	private List list;
	private ListNode current; // 현재 가리키고 있는 포인터

	public ListIterator(List l) {
		list = l;
		current = list.first; // ex.HeadNodes[0]에서는 1이 current가 됨
	}

	int First() {
		if (current != null)
			return current.data;
		else
			return 0;
	}

	int Next() {
		int data = current.data;
		current = current.link;
		return data;
	}

	boolean NotNull() {
		if (current != null)
			return true;
		else
			return false;
	}

	boolean NextNotNull() {
		if (current.link != null)
			return true;
		else
			return false;
	}

}

class QueueNode {
	int data;
	QueueNode link;

	QueueNode(int data, QueueNode link) {
		this.data = data;
		this.link = link;
	}
}

class Queue {
	private QueueNode front, rear;

	void QueueEmpty() {
		front = rear = null;
	}

	public Queue() {
		QueueEmpty();
	}

	boolean IsEmpty() {
		if (front == null)
			return true;
		else
			return false;
	}

	void Insert(int y) {
		// 구현할 부분
	}

	int Delete() {
		return 1;
		// 구현할 부분
	}
}

class StackNode {
	ListNode data;
	StackNode link;

	StackNode(ListNode data, StackNode link) {
		this.data = data;
		this.link = link;
	}
}

class Stack {
	private StackNode top;

	void StackEmpty() {
		top = null;
	}

	public Stack() {
		StackEmpty();
	}

	boolean IsEmpty() {
		if (top == null)
			return true;
		else
			return false;
	}

	void Insert(ListNode y) {
		// 구현할 부분
	}

	ListNode Delete()
	// delete the top node in stack and return its data
	{
		// 구현할
		return null;
	}
}

class Graph {
	private List[] HeadNodes;// HeadNodes는 참조변수
	int n;
	boolean[] visited;

	// 그래프 생성자
	public Graph(int vertices) {
//		/* 1 */ int[][] data = new int[3][4]; // 1과 2는 동일한 결과

//		/* 2 */ int[][] a = new int[3][]; // 행 세 개 생성
//		for (int i = 0; i < a.length; i++) {
//			a[i] = new int[4]; // 각 행별로 4개의 열 생성 => 2차원 배열
//		}

		n = vertices;
		HeadNodes = new List[n]; // 1~n 숫자 배열 헤드노드
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			HeadNodes[i] = new List(); // 각 헤드노드에 대한 linked-list
			visited[i] = false;
		}

	}

	void displayAdjacencyLists() {
		for (int i = 0; i < n; i++) {
			System.out.print("\n" + i + "[ ]");
			ListNode p = HeadNodes[i].first;
			while (p != null) {
				System.out.print("->" + p.data);
				p = p.link;
			}
		}
	}

	void InsertVertex(int start, int end) {
		if (start < 0 || start >= n || end < 0 || end >= n) {
			System.out.println("the start node number is out of bound.");
			return;
		}
		// (0,1)을 삽입할 때는 0-1과 1-0을 모두 입력해야 함
		HeadNodes[start].Insert(end);
		HeadNodes[end].Insert(start);
	}

	void BFS(int v) {
		boolean[] visited = new boolean[n]; // visited is declared as a Boolean
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited
		// 구현할 부분
		// BFS(v); //recursive 버전
		// queue를 사용하여 구현
	}

	void ShowList(List l) {
		ListIterator li = new ListIterator(l);
		// 구현할 부분
	}

	// Driver: main에서 호출
	void DFS(int v) {
		for (int i = 0; i < n; i++)
			visited[i] = false; // initially, no vertices have been visited

		_DFS(v); // start search at vertex 0
		// _NonRecursiveDFS(v); //stack을 이용한 non-recursive 버전
	}

	// Workhorse
	void _DFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		visited[v] = true;
		System.out.print(v + ", ");
		// v는 0인 상태, HeadNodes[0]은 adjacency list의 첫 번째 linked list
		ListIterator li = new ListIterator(HeadNodes[v]);// li는 ListIterator의 참조변수
		if (!li.NotNull())
			return;
		int w = li.First();// HeadNodes[0]에서는 1이 w인 상태로 시작
		while (true) {
			if (!visited[w])// 0-1
				_DFS(w);// 1-0
			if (li.NotNull())
				w = li.Next();
			else
				return;
		}
	}

	// Workhorse 2
	void _NonRecursiveDFS(int v)
	// visit all previously unvisited vertices that are reachable from vertex v
	{
		// 구현할 부분

	}
}

//------------------------------------------------------------main

public class DFS_BFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// select는 enum 대신 사용하는 메뉴 선택 변수, n은 노드 갯수
		// startEdge, endEdge => 방향성이 있음을 의미
		int select = 10, n, startEdge = -1, endEdge = -1;
		int startBFSNode = 0;// BFS를 하기 위한 시작 노드
		/*
		 * System.out.println("vertex 숫자 입력: ");
		 * 
		 * n = sc.nextInt();
		 */
		n = 8;
		Graph g = new Graph(n);

		while (select != '0') {
			System.out.println("\n명령 선택 1: edge 추가, 2: Adjacency Lists 출력, 3: BFS, 4: DFS, 5: 종료 => ");
			select = sc.nextInt();// 메뉴 선택받는 부분
			switch (select) {
			case 1:
				/*
				 * System.out.println("edge 추가: from vertext: "); // 시작부분 입력받음 startEdge =
				 * sc.nextInt(); System.out.println("to vertex: "); // 끝부분 입력받음 endEdge =
				 * sc.nextInt(); if (startEdge < 0 || startEdge >= n || endEdge < 0 || endEdge
				 * >= n) { System.out.println("the input node is out of bound."); break; } //
				 * get smallest start node. if (startEdge < startBFSNode) startBFSNode =
				 * startEdge; if (endEdge < startBFSNode) startBFSNode = endEdge;
				 * g.InsertVertex(startEdge, endEdge);
				 */
				// edge 입력
				int[][] inputData = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 }, { 2, 6 }, { 3, 7 }, { 4, 7 },
						{ 5, 7 }, { 6, 7 } };
				for (int i = 0; i < inputData.length; i++) {
					g.InsertVertex(inputData[i][0], inputData[i][1]);
				}
				break;
			case 2:
				// display
				g.displayAdjacencyLists();
				break;

			case 3:
				System.out.println("Start BFS from node: " + startBFSNode);
				g.BFS(startBFSNode);
				break;
			case 4:
				System.out.println("Start DFS from node: " + startBFSNode);
				g.DFS(startBFSNode); // 0번으로 시작
				break;
			case 5:
				System.exit(0);
				break;
			default:
				System.out.println("WRONG INPUT  " + "\n" + "Re-Enter");
				break;
			}
		}

		return;
	}
}
