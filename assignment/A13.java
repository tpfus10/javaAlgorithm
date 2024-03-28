 //실습9_2객체연결리스트_test

package assignment;
/*
 * 정수 리스트 > 객체 리스트: 2번째 실습 대상
 */
import java.util.Comparator;
import java.util.Scanner;

//import Fruit4;

class SimpleObject {
	// 상수는 클래스 내부 및 외부에서 모두 접근 가능함
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	// 멤버변수는 클래스 내부의 메서드들만 접근할 수 있음(객체가 생성될 때마다 각 인스턴스마다 별도의 메모리 공간에 할당됨)
	private String no; // 회원번호
	private String name; // 이름
	String expire;// 유효기간 필드를 추가

	// --- 문자열 표현을 반환 ---//
	@Override
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject() { // 값을 초기화하는 생성자
		no = null;
		name = null;
	}

	// --- 데이터를 읽어들임 ---//교재 p314
	void scanData(String guide, int sw) {// sw가 3이면 11 비트 연산 > NO, NAME을 모두 입력받는다
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임 sw가 3이면 &는 비트 연산이므로 결과는 1(1==1일 때는 번호 입력받아라)
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {// sw가 3이고 NAME과 비트 & 연산하면 결과는 2(2==2일 때는 이름 입력받아라)
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject> {
		public int compare(SimpleObject d1, SimpleObject d2) {
			return d1.name.compareTo(d2.name);//?왜  NO_ORDER랑 다르지??
		}
	}
}

class Node2 {
	SimpleObject data;
	Node2 link;

	public Node2(SimpleObject element) {
		data = element;
		link = null;
	}
}

class LinkedList2 {
	Node2 first;//first는 참조변수

	public LinkedList2() {
		first = null;
	}

	public void Add(SimpleObject element, Comparator<SimpleObject> cc)
	// 임의 값을 삽입할 때 리스트가 오름차순으로 정렬이 되도록 한다
	{
		Node2 newNode = new Node2(element);
		
		if (first == null) // 1)빈 리스트에 값을 넣을 때
		{
			first = newNode;
			return;
		}
		else { //2)값이 있는 리스트에 값을 넣을 때
			Node2 p = first;
			Node2 q = null;
			
			while(p != null) {
				if(cc.compare(element, p.data) > 0) { //p가 newNode보다 커질 때까지 p를 이동
					q = p; //p가 이동하기 전의 값을 저장
					p = p.link; //p를 이동해줌
					
				} else {
					if(q == null) { //2-1)삽입하는 값이 맨 앞에 들어갈 때
						first = newNode;
						newNode.link = p;
						return;
					} else { //2-2)삽입하는 값이 중간에 들어갈 때
						q.link = newNode;
						newNode.link = p;
						return;
					}
				}
			}
			p = newNode; //2-3)삽입하는 값이 맨 끝에 들어갈 때
			q.link = newNode;
			
		}
		
	}
	
	public int Delete(SimpleObject element, Comparator<SimpleObject> cc)
	// 삭제하려고 전달된 element를 찾을 때 comparator 객체를 사용한다
	{
		Node2 q = null;
		Node2 current = first;
		
		while(current != null) {
			if(cc.compare(element, current.data) == 0) {
				if(q == null) { //1)삭제할 노드가 첫 번째 노드인 경우
					first = current.link; //first값을 바꿔줌
					current = null;
				} else if(current.link == null) {
					 current = null;
				} else {
					q.link = current.link;
					current = null;
				}
				return 1;
			}
			q = current;
			current = current.link;
		}
		return -1;// 삭제할 대상이 없다.
	}

	public void Show() { // 전체 리스트를 순서대로 출력한다.
		Node2 p = first;
		SimpleObject so;
		while(p != null) {
			System.out.println(p.data + " ");
			p = p.link;
		}
		System.out.println();
	}


	public boolean Search(SimpleObject element, Comparator<SimpleObject> cc) {
		//전달된 data 값을 찾아 존재하면 true로 리턴, 없으면 false로 리턴
		Node2 ptr = first;
		
		while(ptr != null) {
			if(cc.compare(ptr.data, element) == 0) {
				return true;
			}
			ptr = ptr.link;
		}
		return false;
	}

	void Merge(LinkedList2 b) {
		/*
		 * 연결리스트 a,b에 대하여 a = a + b merge하는 알고리즘 구현으로 
		 * in-place 방식으로 합병/이것은 새로운 노드를 만들지 않고 합병하는 알고리즘 구현 
		 * 난이도 등급: 최상급 
		 * 회원번호에 대하여 a = (3, 5, 7), b = (2,4,8,9)이면 
		 * a = (2,3,4,5,8,9)가 되도록 구현하는 코드
		 */
	}
}

public class A13 {

	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), 
		Search("검색"), Merge("합병"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
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

	// --- 메뉴 선택 ---//
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
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}
	
//----------------------------------------------------------------------------------------main

	public static void main(String[] args) {
		Menu menu;
		LinkedList2 l1 = new LinkedList2();//연결리스트1
		LinkedList2 l2 = new LinkedList2();//연결리스트2
		
		Scanner sc = new Scanner(System.in);
		int count = 3; // 3개의 객체 입력 개수
		SimpleObject data;
		
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject();
				data.scanData("입력", 3); //왜 3을 전달할까?(비트 연산 결과에 따라 1을 전달하면 번호만, 2를 입력하면 이름만 입력받음 3을 입력하면 모두 받음)
				l1.Add(data, SimpleObject.NO_ORDER);// 회원번호 순서로 정렬 입력
				break;
			case Delete:
				data = new SimpleObject();
				data.scanData("삭제", SimpleObject.NO);
				int num = l1.Delete(data, SimpleObject.NO_ORDER);// 회원번호 조건 비교하여 삭제
				System.out.println("삭제된 데이터 성공은 " + num);
				break;
			case Show:
				l1.Show();
				break;
			case Search: // 회원 번호 검색
				data = new SimpleObject();
				data.scanData("탐색", SimpleObject.NO);
				boolean result = l1.Search(data, SimpleObject.NO_ORDER);// 회원번호로 검색
				if (result)
					System.out.println("검색 성공 = " + result);
				else
					System.out.println("검색 실패 = " + result);
				break;
			case Merge:// 난이도 상
				for (int i = 0; i < count; i++) {// 3개의 객체를 연속으로 입력받아 l2 객체를 만든다
					data = new SimpleObject();
					data.scanData("병합", 3);
					l2.Add(data, SimpleObject.NO_ORDER);
				}
				l1.Merge(l2);
				break;
			case Exit: // 꼬리 노드 삭제
				break;
			}
		} while (menu != Menu.Exit);
	}
}
