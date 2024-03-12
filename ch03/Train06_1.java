//객체배열 이진탐색(p123~129 )
package ch03;

/*
 * Comparable 구현
 */
/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 */
import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData2 implements Comparable<PhyscData2> {
	String name;
	int height;
	double vision;

	public PhyscData2(String string, int i, double d) {
		this.name = string;
		this.height = i;
		this.vision = d;
	}

	@Override
	public String toString() { //showData의 출력 형태를 결정
		return "PhyscData2{name=" + name + ", height=" + height + ", vision=" + vision + "}";
	}

	@Override
	public int compareTo(PhyscData2 p) {
		return this.name.compareTo(p.name);
//		if (vision > p.vision)
//			return 1;
//		else if (vision < p.vision)
//			return -1;
//		else if (height > p.height)
//			return 1;
//		else 
//			return -1;
	}

	public boolean equals(PhyscData2 key) { //두 객체의 내용이 동일한지 비교하는 메서드
		if (this == key)
		return true;
		else
		return false;
	}
}

public class Train06_1 {
	private static int binarySearch(PhyscData2[] data, PhyscData2 key) {
		int pl = 0;
		int pr = data.length-1;
		
		do {
			int pc = (pl+pr)/2;
			if(data[pc].equals(key) == true) {
				return 1;
			}
			else if(data[pc].compareTo(key) > 0) {
				pr = pc-1;
			}
			else {
				pl = pc+1;
			}
		} while(pl <= pr);
			return -1;
	}
	
	private static int linearSearch(PhyscData2[] data, PhyscData2 key) {
		int i = 0;
		int n = data.length;
		
		while(true) {
			if(i == n)
				return -1;
			if(data[i].equals(key) == true) 
				return i;
				i++;
		}
	}
	
	private static void reverse(PhyscData2[] data) {
		for(int i = 0; i < data.length/2; i++) {
			swap(data, i, data.length-1-i);
		}
		
	}
	
	private static void swap(PhyscData2[] arr, int i, int j) {
		PhyscData2 t = arr[i]; // arr의 데이터 타입이 PhyscData2이기 때문에 맞춰줘야 함
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	private static void showData(String string, PhyscData2[] data) {
		System.out.println(string);
		for(PhyscData2 obj : data) { //객체배열을 출력할 때는 데이터타입을 객체명으로 해야함
			System.out.println(obj);
		}
		
	}
	
	static void sortData(PhyscData2[] arr) {
		//확장형 for문은 index가 없어 swap() 처리 불가능
		for(int i = 0; i<arr.length; i++) {
			for(int j = i+1; j<arr.length; j++) {
				if(arr[i].compareTo(arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}
	}

	public static void main(String[] args) {
		PhyscData2[] data = { 
				new PhyscData2("홍길동", 162, 0.3), 
				new PhyscData2("나동", 164, 1.3),
				new PhyscData2("최길", 152, 0.7),
				new PhyscData2("김홍길동", 172, 0.3), 
				new PhyscData2("박동", 182, 0.6),
				new PhyscData2("이동", 167, 0.2), 
				new PhyscData2("길동", 167, 0.5), };
		
		if(data[0].compareTo(data[1])>0)
			System.out.println();
		
		showData("정렬전", data);
		sortData(data);
		System.out.println();
		
		showData("정렬후", data);
		reverse(data);
		System.out.println();
		
		showData("역순 재배치후", data);
		Arrays.sort(data);// 사용된다 그 이유는? 실제로는 내가 만들어둔compareTo를 사용함
		                  //(내가 설정한 compare 기준에 따라 정렬함, 예: 이름, 키, 시력 등)
		System.out.println();
		
		showData("Arrays.sort() 정렬후", data);
		System.out.println();

		PhyscData2 key = new PhyscData2("길동", 167, 0.2);
		int resultIndex = linearSearch(data, key);
		System.out.println("\nlinearSearch(<길동,167,02>): result = " + resultIndex);
		System.out.println();

		key = new PhyscData2("박동", 182, 0.6);
		/*
		 * 교재 109~113
		 */
//		resultIndex = binarySearch(data, key);// comparable를 사용
		System.out.println("\nbinarySearch(<박동,182,0.6>): result = " + resultIndex);
		
		key = new PhyscData2("이동", 167, 0.6);
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);// comparable를 사용(Arrays가 내가 만든 comparable을 사용한다는 점을 이용)
		System.out.println("\nArrays.binarySearch(<이동,167,0.6>): result = " + resultIndex);
	}


}
