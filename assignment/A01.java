//2장 과제1: 스트링 배열 정렬
package assignment;
//String 배열 정렬 정수의 sortData를 사용할 수 없음, compareTo() 사용하기

//문자열은 compareTo() 메서드를 사용해 문자열의 유니코드 값을 비교하여 사전 순서대로 정렬함

public class A01 {
	public static void main(String[] args) {
		String[] data = { "apple", "grape", "persimmon", "pear", "blueberry", "strawberry", "melon", "oriental_melon" };

		showData(data);
		sortData(data);
		showData(data);
	}

	static void showData(String[] arr) {
		for (String elem : arr) {
			System.out.print(elem + " ");
		}System.out.println();
	}

	static void swap(String[] arr, int ind1, int ind2) {
		String t = arr[ind1];
		arr[ind1] = arr[ind2];
		arr[ind2] = t;
	}

	static void sortData(String[] arr) {
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++) {
				boolean result = comp(arr, i, j);
				if (result) {
					swap(arr, i, j);
				}
			}
	}

	static boolean comp(String[] arr, int ind1, int ind2) {
		boolean B = false;
		int compResult = arr[ind1].compareTo(arr[ind2]); 
		// 음수: 호출 객체가 매개변수로 전달된 객체보다 작다
		// 양수: 호출 객체가 매개변수로 전달된 객체보다 크다
		if (compResult > 0) { 
			// arr[ind1]가 arr[ind2] 사전 순서에서 뒤에 있을 경우 swap을 하도록 해야 함
			//양수: 현재 객체(arr[ind1])가 obj(arr[ind2])보다 사전 순서에서 뒤에 있다는 의미
			B = true;
		}
		return B;
	}
}
