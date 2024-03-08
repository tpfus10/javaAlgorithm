//정수배열 정렬
package ch02;

import java.util.Arrays;
//교재 67 - 실습 2-5
//2번 실습
import java.util.Random;

public class Train05 {

	public static void main(String[] args) {
		int[] data = new int[10];
		inputData(data);
		showData(data);
		
		reverse(data);// 역순으로 재배치 - 정렬 아님
		showData(data);

		sortData(data);// 정렬프로그램 직접 코딩
		showData(data);

		reverseSort(data);// 역순으로 재배치 - 정렬 아님
		showData(data);

		Arrays.sort(data);// 자바 라이브러리를 사용한 정렬
	}

	static void showData(int[] data) {// 확장형 for문 활용
		for (int num : data) {
			System.out.print("[" + num + "]");
		} System.out.println("\n");
	}

	static void inputData(int[] data) { // 난수 생성해서 데이터 입력
		Random random = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = random.nextInt(100);
		}
	}

	static void swap(int[] arr, int ind1, int ind2) {// 교재 67페이지
		int t = arr[ind1];
		arr[ind1] = arr[ind2]; // = 기호는 오른쪽 값을 왼쪽에 넣는다는 의미
		arr[ind2] = t;
	}

	static void sortData(int[] arr) { // 올림차순 정렬
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					swap(arr, i, j);
				}
			}
		}

	}

	static void reverse(int[] a) {// 교재 67페이지
		for (int i = 0; i < (a.length / 2); i++) {
			swap(a, i, a.length - i - 1);
		}
	}

	static void reverseSort(int[] arr) { // 내림차순 정렬
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j<arr.length; j++) {
				if(arr[j] > arr[i]) {
					swap(arr, i, j);
				}
			}
		}
	}

}
