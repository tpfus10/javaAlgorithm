//정수배열 이진탐색
package ch03;

//3장 이진탐색 + 1번 실습 과제: 스트링배열정렬 > 2번 실습: 스트링객체정렬 > 3번 실습: 객체정렬
//comparator 구현 실습
/*
* 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
* 함수(메소드) 전체를 작성하는 훈련 
*/
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Train04 {

	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);// 구현 반복 숙달 훈련

		showList("정렬 전 배열[]:: ", data);
		sortData(data);// 구현 반복 숙달 훈련
		// Arrays.sort(data);
		showList("정렬 후 배열[]:: ", data);// 구현 반복 숙달 훈련

		int key = 13;
		int resultIndex = linearSearch(data, key);// 교재 99-100:실습 3-1 참조, 교재 102: 실습 3-2
		// Arrays 클래스에 linear search는 없다
		System.out.println("\nlinearSearch(13): result = " + resultIndex);

		key = 19;
		/*
		 * 교재 109~113
		 */
		resultIndex = binarySearch(data, key);
		System.out.println("\nbinarySearch(19): result = " + resultIndex);

		key = 10;
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 * Collectios.binarySearch도 있음
		 */
		resultIndex = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(10): result = " + resultIndex);

	}
	
	//----------------------------------------------------------------------main
	
	private static void showList(String string, int[] data) {
		System.out.println(string);
			for(int da : data) {
				System.out.print(da + " ");
			}
			System.out.println();
	}

	private static void inputData(int[] data) {
		data[0] = 70;
		data[1] = 39;
		data[2] = 31;
		data[3] = 15;
		data[4] = 19;
		data[5] = 13;
		data[6] = 10;
		data[7] = 58;
		data[8] = 5;
		data[9] = 95;
	}

	static void sortData(int[] data) {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data.length; j++) {
				if(data[i] < data[j]) {
					swap(data, i, j);
				}
			}
		}
	}

	private static void swap(int[] data, int ind1, int ind2) {
		int t = data[ind1];
		data[ind1] = data[ind2];
		data[ind2] = t;
	}

	static int linearSearch(int[] item, int key) {
		int i = 0;
		int n = item.length;
		
		while (true) {
			if(i == n) 
				return -1;
			if(item[i] == key)
				return i;
			i++;
		}
	}

	static int binarySearch(int[] item, int key) {
		int pl = 0;
		int pr = item.length - 1;
		
		do {
			int pc = (pl + pr)/2;
			if(item[pc] == key)
				return pc;
			else if (item[pc] < key) 
				pl = pc+1;
			else 
				pr = pc-1;
		} while (pl <= pr);
			return -1; 
	}
}
