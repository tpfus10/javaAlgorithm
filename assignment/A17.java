//실습10_2객체체인해시
package assignment;

//hash node가 student 객체일 때를 구현하는 과제
//체인법에 의한 해시
import java.util.Comparator;
import java.util.Scanner;

class SimpleObject5 { // 노드를 구성하는 객체
	static final Comparator<SimpleObject5> NO_ORDER = new CompNo();
	
	static final int NO = 1;
	static final int NAME = 2;
	String no; // 회원번호
	String name; // 이름

	//SimpleObject5 생성자
	public SimpleObject5(String no, String name) {
		this.no = no;
		this.name = name;
	}

	public SimpleObject5() {
		// TODO Auto-generated constructor stub
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
}

//ChainHash5 클래스
class ChainHash5 {
	// --- 해시를 구성하는 노드 ---//
	class Node2 {
		private SimpleObject5 data; // 키값
		private Node2 next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

		public SimpleObject5 getData() {
			return data;
		}

		public void setData(SimpleObject5 data) {
			this.data = data;
		}

		public Node2 getNext() {
			return next;
		}

		public void setNext(Node2 next) {
			this.next = next;
		}
	}

	private int size; // 해시 테이블의 크기
	private Node2[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public ChainHash5(int capacity) {
		try {
			table = new Node2[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}
	}
	
	public int hashvalue(int data) {
		return data % size;
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = hashvalue(Integer.parseInt(st.no));
		Node2 p = table[hash];
		
		while(p != null) {
			if(p.data == st) {
				return 1; //검색 성공
			}
			p = p.next;
		}
		return 0; //검색 실패
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = hashvalue(Integer.parseInt(st.no));
		Node2 p = table[hash]; //삽입하려는 노드
		
		//해시 충돌이 발생했을 경우 체인의 끝까지 이동하여 중복을 확인
		while(p != null) {
			if(p.data == st) {
				return 1; //중복된 키가 이미 존재
			}
			p = p.next; //헤당 해시에서 다음 노드로 이동
		}
		
		//해시 충돌이 발생하지 않았을 경우 새로운 노드를 추가
		Node2 newNode = new Node2();
		newNode.setData(st);
		newNode.setNext(table[hash]); 
		table[hash] = newNode;
		return 0;
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int delete(SimpleObject5 st, Comparator<? super SimpleObject5> c) {
		int hash = hashvalue(Integer.parseInt(st.no)); //삭제할 데이터의 해시값
		Node2 p = table[hash]; //선택 노드
		Node2 q = null; //바로 앞의 노드
		
		while (p != null) {
			if(c.compare(p.data, st) == 0) { //객체 비교하는 방법은 객체.compare(a,b)
				if(q == null) {
					table[hash] = p.next;
				} else {
					q.next = p.next;
					return 0;
				}
			}
			q = p;
			p = p.next;
		}
		return 1; //해당 키값이 없을 때
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for(int i = 0; i < size; i++) {
			Node2 p = table[i];
			System.out.print("[" + i + "]" + " ");
			
			while(p != null) {
				System.out.print(p.data.no + ": " + p.data.name);
				p = p.next;
			}
			System.out.println();
		}
	}
}

class CompNo implements Comparator<SimpleObject5> {
	@Override
	public int compare(SimpleObject5 o1, SimpleObject5 o2) {
		if(Integer.parseInt(o1.no) > Integer.parseInt(o2.no)) return 1;
		else if (Integer.parseInt(o1.no) <  Integer.parseInt(o2.no)) return -1;
		return 0;
	}
}

public class A17 {
	
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), Show("출력"), Exit("종료");

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

//---------------------------------------------------------------------------main

	public static void main(String[] args) {
		Menu menu;
		ChainHash5 hash = new ChainHash5(15);
		Scanner stdIn = new Scanner(System.in);
		SimpleObject5 data; //data는 참조변수
		int select = 0, result = 0;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject5();
				data.scanData("삽입", SimpleObject5.NO | SimpleObject5.NAME);
				result = hash.add(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 중복 데이터가 존재한다");
				else
					System.out.println(" 입력 처리됨");
				break;
			case Delete:
				// Delete
				data = new SimpleObject5();
				data.scanData("삭제", SimpleObject5.NO);
				result = hash.delete(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;
			case Search:
				data = new SimpleObject5();
				data.scanData("검색", SimpleObject5.NO);
				result = hash.search(data, SimpleObject5.NO_ORDER);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				break;
			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
