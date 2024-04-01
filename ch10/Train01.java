//Test_실습10_1정수체인해시
package ch10;

import java.util.Scanner;
//체인법에 의한 해시
//--- 해시를 구성하는 노드 ---//

//Node 클래스
class Node {
	int key; // 키값
	Node next; // 뒤쪽 포인터(뒤쪽 노드에 대한 참조)

	// Node의 생성자
	public Node(int key) {
		this.key = key;
		this.next = null; // 처음엔 next값을 받을 수 없음
	}
}

//SimpleChainHash 클래스
class SimpleChainHash {
	private int size; // 해시 테이블의 크기
	private Node[] table; // 해시 테이블

	// SimpleChainHash의 생성자
	public SimpleChainHash(int capacity) {
		try {
			table = new Node[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}
	}

	public int hashvalue(int key) {
		return key % size;
	}

//--- 키값이 key인 요소를 검색(데이터를 반환) ---//
	public int search(int key) {
		int hash = hashvalue(key); // 해시 함수로 키값을 해시값으로 변환
		Node p = table[hash];

		while (p != null) {
			if (p.key == key) {
				return 1; // 검색 성공
			}
			p = p.next;
		}
		return 0; // 검색 실패
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int key) {
		int hash = hashvalue(key);
		Node p = table[hash];

		// 해시 충돌이 발생했을 경우 체인의 끝까지 이동하여 중복을 확인
		while (p != null) {
			if (p.key == key) {
				return 1; // 중복된 키가 이미 존재함
			}
			p = p.next; // 해당 해시에서 다음 노드로 이동
		}

		// 해시 충돌이 발생하지 않았을 경우 새로운 노드를 추가
		Node newNode = new Node(key);
		newNode.next = table[hash];
		table[hash] = newNode;
		return 0;
		}

	// --- 키값이 key인 요소를 삭제 ---//
	public int delete(int key) {
		int hash = hashvalue(key); // 삭제할 데이터의 해시값
		Node p = table[hash]; // 선택 노드
		Node q = null; // 바로 앞의 선택 노드

		while (p != null) {
			if (p.key == key) {
				if (q == null) { // 해시 테이블의 맨 처음 값을 삭제할 때
					table[hash] = p.next;
				} else {
					q.next = p.next;
					return 0;
				}
			}
			q = p;
			p = p.next;
		}
		return 1; // 해당 키값이 없을 때
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			Node p = table[i];
			System.out.print("[" + i + "]" + " "); // 출력되는 정수의 최소 자깃수를 2로 지정하고 남는 공간을 0으로 채움

			while (p != null) {
				System.out.print(p.key + " ");
				p = p.next;
			}
			System.out.println();
		}
	}
}

public class Train01 {

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

//---------------------------------------------------------------main   

//체인법에 의한 해시 사용 예
	public static void main(String[] args) {
		Menu menu;
		SimpleChainHash hash = new SimpleChainHash(11);
		Scanner stdIn = new Scanner(System.in);
		int select = 0, result = 0, val = 0;
		final int count = 15;
		do {
			switch (menu = SelectMenu()) {
			case Add:
				// 크기가 15인 input 리스트 생성(16개 요소)
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				System.out.println();
				// input 리스트 요소를 hash에 넣어줌
				for (int i = 0; i < count; i++) {
					if ((hash.add(input[i])) == 0)
						System.out.println("Insert Duplicated data");
				}
				break;
			case Delete:
				// Delete
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.delete(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;
			case Search:
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result == 1)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case Show:
				hash.dump();
				break;
			}
		} while (menu != Menu.Exit);

	}
}
