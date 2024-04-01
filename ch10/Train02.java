//실습10_3정수오픈해시
package ch10;

//오픈 주소법에 의한 해시의 사용 예

import java.util.Scanner;

//오픈 주소법에 의한 해시

class OpenHash2 {
	// OpenHash2의 필드
	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

//--- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	} // {데이터 저장, 비어있음, 삭제 완료}

//--- 버킷 ---// 내부 클래스
	static class Bucket {
		private int data; // 데이터
		private Status stat; // 상태

		// 생성자: 버킷을 비워줌
		public Bucket() {
			this.stat = Status.EMPTY;
		}

		public void set(int data, Status stat) {
			this.data = data;
			this.stat = stat;
		}

		public void setStat(Status stat) {
			this.stat = stat;
		}
	}

//--- OpenHash2의 생성자(constructor) ---//
	public OpenHash2(int size) {
		try {
			// 연결리스트 생성
			this.size = size;
			table = new Bucket[size]; // 해시 테이블을 생성, 모든 슬롯은 null로 초기화 됨
			for (int i = 0; i < size; i++) {
				table[i] = new Bucket(); // 각 슬롯마다 새로운 bucket 객체를 생성하여 할당함
											// 각 슬롯에 대해 연결 리스트의 헤드를 생성
			}
		} catch (OutOfMemoryError e) { // 테이블을 생성할 수 없음
			this.size = 0;
		}
	}

//--- 해시값을 구함 ---//
	public int hashValue(int key) {
		return key % size;
	}

//--- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

//--- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(int key) {
		int hash = hashValue(key);
		Bucket p = table[hash];

		for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
			if (p.stat == Status.OCCUPIED && p.data == key) {
				return p;
			}
		}
		return null;
	}

//--- 키값이 key인 요소를 검색(데이터를 반환)---//
	public int search(int key) {
		Bucket p = searchNode(key);
		if (p != null) {
			return p.data;
		} else {
			return 0;
		}
	}

//--- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(int data) {
		int hash = hashValue(data);
		Bucket p = table[hash];

		for (int i = 0; i < size; i++) {
			if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
				p.set(data, Status.OCCUPIED);
				return 0;
			}
			// 재해시
			hash = rehashValue(hash);
			p = table[hash];
		}
		return 2; // 해시 테이블이 가득 참
	}

//--- 키값이 key인 요소를 삭제 ---//
	public int remove(int key) {
		Bucket p = searchNode(key);
		if (p == null) {
			return 1;
		}

		p.setStat(Status.DELETED);
		return 0;
	}

//--- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			System.out.print("[" + i + "]" + " ");
			switch (table[i].stat) {
			case OCCUPIED: {
				System.out.println(table[i].data+ " ");
				break;
			}
			case EMPTY: {
				System.out.println("--비어 있음--");
				break;
			}
			case DELETED: {
				System.out.println("--비어 있음--");
				break;
			}
			}
		}
	}
}

public class Train02 {
//--- 메뉴 열거형 ---//
	enum Menu {
		ADD("추가"), REMOVE("삭제"), SEARCH("검색"), DUMP("표시"), TERMINATE("종료");

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

//------------------------------------------------------------------------------main

//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		int select = 0, result = 0, val = 0;
		final int count = 8;
		Scanner stdIn = new Scanner(System.in);
		OpenHash2 hash = new OpenHash2(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				// input 리스트 생성
				int[] input = new int[count];
				for (int ix = 0; ix < count; ix++) {
					double d = Math.random();
					input[ix] = (int) (d * 20);
					System.out.print(" " + input[ix]);
				}
				System.out.println();
				// input 리스트를 hash에 복제
				for (int i = 0; i < count; i++) {
					int k = hash.add(input[i]);
					switch (k) {
					case 1:
						System.out.printf("(%d) -> ", input[i]);
						System.out.println("그 키값은 이미 등록되어 있습니다.");
						break;
					case 2:
						System.out.println("해시 테이블이 가득 찼습니다.");
						break;
					}
				}
				break;

			case REMOVE: // 삭제
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.remove(val);
				if (result == 0)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case SEARCH: // 검색
				System.out.println("Search Value:: ");
				val = stdIn.nextInt();
				result = hash.search(val);
				if (result != 0)
					System.out.println(" 검색 데이터가 존재한다");
				else
					System.out.println(" 검색 데이터가 없음");
				System.out.println();
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
