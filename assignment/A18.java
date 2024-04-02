//객체오픈해시
package assignment;

import java.util.Comparator;
import java.util.Scanner;

import assignment.ChainHash5.Node2;

//오픈 주소법에 의한 해시

class CompNo2 implements Comparator<SimpleObject4> {
	@Override
	public int compare(SimpleObject4 o1, SimpleObject4 o2) {
		if (Integer.parseInt(o1.sno) > Integer.parseInt(o2.sno))
			return 1;
		else if (Integer.parseInt(o1.sno) < Integer.parseInt(o2.sno))
			return -1;
		return 0;
	}
}

class SimpleObject4 {
	static final Comparator<SimpleObject4> NO_ORDER = new CompNo2();
	static final int NO = 1;
	static final int NAME = 2;
	String sno; // 회원번호
	String sname; // 이름

	public SimpleObject4(String sno, String sname) {
		this.sno = sno;
		this.sname = sname;
	}

	public SimpleObject4() {
		// TODO Auto-generated constructor stub
	}

	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임 sw가 3이면 &는 비트 연산이므로 결과는 1(1==1일 때는 번호 입력받아라)
			System.out.print("번호: ");
			sno = sc.next();
		}
		if ((sw & NAME) == NAME) {// sw가 3이고 NAME과 비트 & 연산하면 결과는 2(2==2일 때는 이름 입력받아라)
			System.out.print("이름: ");
			sname = sc.next();
		}
	}
}

//*
class OpenHash {
	// --- 버킷의 상태 ---//
	enum Status {
		OCCUPIED, EMPTY, DELETED
	}; // {데이터 저장, 비어있음, 삭제 완료}

	// --- 버킷 ---//
	static class Bucket {
		private SimpleObject4 data; // 데이터
		private Status stat; // 상태

		public Bucket() {
			this.stat = Status.EMPTY;
		}

		public void set(SimpleObject4 data, Status stat) {
			this.data = data;
			this.stat = stat;
		}

		public void setStat(Status stat) {
			this.stat = stat;
		}
	}

	private int size; // 해시 테이블의 크기
	private Bucket[] table; // 해시 테이블

	// --- 생성자(constructor) ---//
	public OpenHash(int capacity) {
		try {
			table = new Bucket[capacity];
			this.size = capacity;
		} catch (OutOfMemoryError e) {
			this.size = 0;
		}
	}

	// --- 해시값을 구함 ---//
	public int hashValue(SimpleObject4 key) {
		return Integer.parseInt(key.sno) % size;
	}

	// --- 재해시값을 구함 ---//
	public int rehashValue(int hash) {
		return (hash + 1) % size;
	}

	// --- 키값 key를 갖는 버킷 검색 ---//
	private Bucket searchNode(SimpleObject4 key, Comparator<? super SimpleObject4> c) {
		int hash = hashValue(key);
		Bucket p = table[hash];

		for (int i = 0; p.stat != Status.EMPTY && i < size; i++) {
			if (p.stat == Status.OCCUPIED && c.compare(p.data, key) == 0) {
				return p;
			}
		}
		return null;
	}

	// --- 키값이 key인 요소를 검색(데이터를 반환)---//
	public SimpleObject4 search(SimpleObject4 key, Comparator<? super SimpleObject4> c) {
		Bucket p = searchNode(key, c);
		if (p != null) {
			return p.data;
		} else {
			return null;
		}
	}

	// --- 키값이 key인 데이터를 data의 요소로 추가 ---//
	public int add(SimpleObject4 key, Comparator<? super SimpleObject4> c) {
		int hash = hashValue(key);
		Bucket p = table[hash];

		// 처음 값을 삽입할 때(p가 null인 경우를 처리해줘야 함)
		if (p == null) { //p가 null이라는 것은 해당 테이블의 위치에 버킷이 아직 생성되지 않았음을 의미함
			table[hash] = new Bucket();
			table[hash].set(key, Status.OCCUPIED);
			return 0;
		}

		// table이 비어있지 않을 때
		for (int i = 0; i < size; i++) {
			if (p.stat == Status.EMPTY || p.stat == Status.DELETED) {
				p.set(key, Status.OCCUPIED);
				return 0; // 삽입에 성공함
			} else if (p.stat == Status.OCCUPIED) {
				return 1;
			} else {
				hash = rehashValue(hash); // 재해시
				p = table[hash];
			}
		}
		return 2; // 해시 테이블이 가득 참
	}

	// --- 키값이 key인 요소를 삭제 ---//
	public int remove(SimpleObject4 key, Comparator<? super SimpleObject4> c) {
		Bucket p = searchNode(key, c);
		if (p == null) {
			return 1;
		}

		p.setStat(Status.DELETED);
		return 0;
	}

	// --- 해시 테이블을 덤프(dump) ---//
	public void dump() {
		for (int i = 0; i < size; i++) {
			System.out.print("[" + i + "]" + " ");
			if(table[i] == null) { //table[i]가 비어있는 경우를 처리해줘야 함
				System.out.println("--비어 있음");
			} else {
			switch (table[i].stat) {
			case OCCUPIED: {
				System.out.println(table[i].data.sno + ": " + table[i].data.sname);
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
}

//*/
public class A18 {

	static Scanner stdIn = new Scanner(System.in);

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

//--- 메뉴 선택 ---//
	static Menu SelectMenu() {
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());

		return Menu.MenuAt(key);
	}
	
//--------------------------------------------------------------------------------main

	public static void main(String[] args) {
		Menu menu; // 메뉴

		SimpleObject4 temp; // 읽어 들일 데이터
		int result;
		OpenHash hash = new OpenHash(13);
		do {
			switch (menu = SelectMenu()) {
			case ADD: // 추가
				temp = new SimpleObject4();
				temp.scanData("추가", SimpleObject4.NO | SimpleObject4.NAME);
				int k = hash.add(temp, SimpleObject4.NO_ORDER);
				switch (k) {
				case 1:
					System.out.println("그 키값은 이미 등록되어 있습니다.");
					break;
				case 2:
					System.out.println("해시 테이블이 가득 찼습니다.");
					break;
				case 0:
				}
				break;

			case REMOVE: // 삭제
				temp = new SimpleObject4();
				temp.scanData("삭제", SimpleObject4.NO);
				result = hash.remove(temp, SimpleObject4.NO_ORDER);
				if (result == 0)
					System.out.println(" 삭제 처리");
				else
					System.out.println(" 삭제 데이터가 없음");
				break;

			case SEARCH: // 검색
				temp = new SimpleObject4();
				temp.scanData("검색", SimpleObject4.NO);
				SimpleObject4 t = hash.search(temp, SimpleObject4.NO_ORDER);
				if (t != null)
					System.out.println("그 키를 갖는 데이터는 " + t + "입니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case DUMP: // 표시
				hash.dump();
				break;
			}
		} while (menu != Menu.TERMINATE);
	}
}
