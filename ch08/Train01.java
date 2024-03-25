//정수연결리스트_test
package ch08;

//단순한 linked list에서 insert, delete하는 알고리즘을 코딩: 1단계

import java.util.Random;
import java.util.Scanner;

class Node1 {
	int data;
	Node1 link;

	public Node1(int element) {
		data = element;
		link = null;
	}
}

class LinkedList1 {
	Node1 first;

	public LinkedList1() {
		first = null;
	}

	public int Delete(int element) // delete the element(맨 앞과 맨 마지막을 삭제할 때 주의해야 함)
	{
		//p=element, q=element 앞의 노드
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.

	}

	public void Add(int element) // 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다(작은 수는 앞에 붙이고 큰 수는 뒤에 붙임)
	{
		Node1 temp = new Node1(element); 
		if(first == null) { //맨 처음 아무것도 없을 때
			first = temp;
			return;
		} else {
			Node1 p = first, q = null; //first는 객체, q는 삽입하려는 위치 앞의 노드를 가리킴
			while(p != null) {
				if(element > p.data) {
					q = p;//q는 p를 따라다님
					p = p.link;//p 이동
				} else {//insert 하는 부분
					temp.link = p; //(1)
					q.link = temp; //(2)
				}
			}
		}
	}

	public boolean Search(int data) { // 전체 리스트를 순서대로 출력한다.
		return true;
	}
}

public class Train01 {
	enum Menu { //Menu 클래스라고 생각하면 됨
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

		private final String message; // 표시할 문자열

		//enum의 메소드, 리턴타입은 Menu
		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환 
			for (Menu m : Menu.values()) //values(): enum의 값들을 줌
				if (m.ordinal() == idx) //ordinal(): enum의 인덱스를 줌
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//여기부터는 Menu의 클래스가 아니라 SelectMenu라는 static 함수임
	static Menu SelectMenu() {
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt(); //key는 정수
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key); //key에 해당하는 Menu를 리턴
	}

//-----------------------------------------------------------------------------------main
	
	public static void main(String[] args) {
		Menu menu; // 메뉴
		Random rand = new Random();
		System.out.println("Linked List");
		LinkedList1 l = new LinkedList1();
		Scanner sc = new Scanner(System.in);
		int data = 0;
		System.out.println("inserted");
		l.Show();
		do {
			switch (menu = SelectMenu()) { // 숫자별 명령을 입력하는 부분
			case Add: // 머리노드 삽입
				data = rand.nextInt(20);
				//double d = Math.random();
				//data = (int) (d * 50);
				l.Add(data);
				break;
			case Delete: // 머리 노드 삭제
				data = sc.nextInt();
				int num = l.Delete(data);
				System.out.println("삭제된 데이터는 " + num);
				break;
			case Show: // 꼬리 노드 삭제
				l.Show();
				break;
			case Search: // 회원 번호 검색
				int n = sc.nextInt();
				boolean result = l.Search(n);
				if (!result)
					System.out.println("검색 값 = " + n + "데이터가 없습니다.");
				else
					System.out.println("검색 값 = " + n + "데이터가 존재합니다.");
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
