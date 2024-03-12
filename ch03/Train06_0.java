//스트링배열정렬 이진탐색
package ch03;

//comparator 구현 실습
/*
* 교재 121 실습 3-6 
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;

public class Train06_0 {

	static void reverse(String[] a) {// 교재 67페이지
		for(int i = 0; i<a.length/2; i++) {
			swap(a, i, a.length-i-1);
		}
	}

	private static void swap(String[] a, int ind1, int ind2) {
		String t = a[ind1];
		a[ind1] = a[ind2];
		a[ind2] = t;
	}
	
	private static void showData(String string, String[] data) {
		System.out.println(string);
		for(String fruit : data) {
			System.out.print(fruit + " ");
		} System.out.println();
	}
	
	private static void sortData(String[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = i+1; j<data.length; j++) {
				if(data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}
	}
	private static int linearSearch(String[] data, String key) {
		int n = data.length;
		int i = 0;
		
		while(true) {
			if(i == n) 
				break;
			if(data[i].compareTo(key) == 0)
				return i;
			i++;
		}	
		return -1;
	}
	
	private static int binarySearch(String[] data, String key) {
		int pl = 0;
		int pr = data.length-1;
		
		do {
			int pc = (pl+pr)/2;
			if(data[pc] == key)
				return pc;
			else if(data[pc].compareTo(key) > 0)
				pr = pc-1;
			else pl = pl+1;
		} while(pl <= pr);
			 return -1;
	}
	

	public static void main(String[] args) {
		String[] data = { "사과", "포도", "복숭아", "감", "산딸기", "블루베리", "대추", "수박", "참외" };// 홍봉희 재배 과수

		showData("정렬전:: ", data);
		System.out.println();
		
		sortData(data);
		showData("정렬후:: ", data);
		System.out.println();
		
		reverse(data);// 역순으로 재배치
		showData("역순 재배치후:: ", data);
		System.out.println();
		
		Arrays.sort(data);// Arrays.sort(Object[] a);
		showData("Arrays.sort()로 정렬후:: ", data);

		String key = "포도";
		int resultIndex = linearSearch(data, key);//만들어야 함
		System.out.println("\nlinearSearch(포도): result = " + resultIndex);

		key = "배";
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);//만들어야 함
		System.out.println("\nbinarySearch(배): result = " + resultIndex);
		key = "산딸기";
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		resultIndex = Arrays.binarySearch(data, key);//정렬된 데이터에 딕셔너리 사용
		System.out.println("\nArrays.binarySearch(산딸기): result = " + resultIndex);

	}
}
