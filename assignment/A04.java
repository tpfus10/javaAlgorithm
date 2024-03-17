//3장 과제2:객체배열이진탐색
package assignment;

//3장 객체 배열 정렬 - binary search
/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* Arrays.sort(array, myComparator);
* Collections.sort(list, myComparator);
*/

import java.util.Arrays;
import java.util.Comparator;

class Fruit4 {
	@Override
	public String toString() {
		return  "Fruit4{name=" + name + ", price=" + price + ", expire=" + expire + "}";
	}

	String name;
	int price;
	String expire;

	public Fruit4(String string, int i, String string2) {
		this.name = string;
		this.price = i;
		this.expire = string2;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
	
	public String getExpire() {
		return name;
	}

	public int compareTo(Fruit4 newFruit4) {
		return price - newFruit4.price;
	}
}

//교재 123~129 페이지 참조하여 구현
class FruitNameComparator2 implements Comparator<Fruit4> { //comparator 인터페이스를 구현한 클래스
	public int compare(Fruit4 f1, Fruit4 f2) {//concrete class(객체 만들기 가능, 추상 클래스는 불가능)
		//함수의 body->람다식의 함수본체
		if(f1.name.compareTo(f2.name) > 0) return 1; //**여기로 가는 것
		else if (f1.name.compareTo(f2.name) < 0) return -1;
		else return 0;
		}
	}

public class A04 {
	private static void sortData_p(Fruit4[] arr, Comparator<Fruit4> cc_price) {
		for (int i = 0; i<arr.length; i++) {
			for(int j = i+1; j<arr.length; j++) {
				if(cc_price.compare(arr[i], arr[j]) > 0) {
					swap(arr, i, j);
				}
			}
		}
	}
	
	static void swap(Fruit4[]arr, int ind1, int ind2) {
		Fruit4 temp = arr[ind1]; 
		arr[ind1] = arr[ind2]; 
		arr[ind2] = temp;
	}
	
	static void sortData(Fruit4 []arr, FruitNameComparator2 cc) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++)
				if (cc.compare(arr[i],arr[j]) > 0) //**여기에서
					swap(arr, i, j); //(FruitNameComparator2에 구현된) cc객체에는 compare 메소드만 있음
		}
	}

	private static void showData(String string, Fruit4[] arr) {
		System.out.println(string);
		for(Fruit4 item : arr) {
			System.out.println(item);
		}System.out.println();
	}
	
	private static void reverse(Fruit4[] arr) {
		for(int i = 0; i < arr.length/2; i++) {
			swap(arr, i, arr.length-1-i);
		}
	}
	
	private static int binarySearch(Fruit4[] arr, Fruit4 newFruit4, Comparator<Fruit4> cc_price) {
		int pl = 0; 
		int pr = arr.length-1;
		
		do {
			int pc = (pl + pr)/2;
			if(arr[pc].compareTo(newFruit4) == 0) {
				return 1;
			}
			else if(arr[pc].compareTo(newFruit4) > 0) {
				pr = pc-1;
			}
			else {
				pl = pc+1;
			}
		}while(pl <= pr);
		return -1;
	}
	
//--------------------------------------------------------------------------------------------main
	
	public static void main(String[] args) {

		Fruit4[] arr = {
				new Fruit4("사과", 200, "2023-5-8"), 
				new Fruit4("감", 500, "2023-6-8"),
				new Fruit4("대추", 200, "2023-7-8"), 
				new Fruit4("복숭아", 50, "2023-5-18"), 
				new Fruit4("수박", 880, "2023-5-28"),
				new Fruit4("산딸기", 10, "2023-9-8") 
				};
		
		System.out.println("\n정렬전 객체 배열: ");
		showData("정렬전 객체", arr);
		
		FruitNameComparator2 cc = new FruitNameComparator2();//cc 객체 생성(comparator로 정의한 정렬 기준)
		
		System.out.println("\ncomparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc); //기본적인 병합 정렬 알고리즘 사용(정렬 기준은 정의 가능, 알고리즘 변경은 불가능)
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
		reverse(arr);
		showData("역순 재배치 후", arr);
		
		sortData(arr, cc); //사용자 정의 정렬 알고리즘 구현
		showData("sortData(arr,cc) 실행후", arr);
		
		// 람다식은 익명클래스 + 익명 객체이다(한 번 해보기, 나중에 다시 설명해줄 예정): 1~3 모두 동일
		//1)-------------------------------------------------------------------------------------------------
		Comparator<Fruit4> cc_price2 = (a, b) -> a.getPrice() - b.getPrice();//람다식의 함수본체 a)
		                                                                     //-> 람다식의 함수본체를 compare 함수에 넣어서 객체를 만들어줌
		Arrays.sort(arr, cc_price2); // 람다식으로 만들어진 객체를 사용 b)
		showData("람다식 변수 cc_price2을 사용한 Arrays.sort(arr, cc) 정렬 후", arr); //c)
		
		//2)--------------------------------------------------------------------------------------------------
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit4에 compareTo()가 있어도 람다식 우선 적용 a)+b)->comparator라는 단어도 없음
		showData("람다식: (a, b) -> a.getPrice() - b.getPrice()을 사용한 Arrays.sort(arr, cc) 정렬 후", arr); //c)
		
		//3)-------------------------------------------------------------------------------------------------
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getPrice()-a2.getPrice(); //a) + b) + c)
			}
		});
		showData("\n익명클래스 객체로 정렬(가격)후 객체 배열:", arr);
//----------------------------------------------------------------------------------------------------
//		
//		System.out.println("\ncomparator 정렬()후 객체 배열: ");
//		showData("comparator_n 객체를 사용한 정렬:", arr);
//		sortData_n(arr, cc);
//		
//		System.out.println();
//		showData("comparator_e 객체를 사용한 정렬:", arr);
//		sortData_e(arr, cc);
	
		Arrays.sort(arr, new Comparator<Fruit4>() { //익명클래스 사용
			@Override
			public int compare(Fruit4  a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		showData("\n익명클래스 객체로 정렬(이름)후 객체 배열:", arr);
	
		Arrays.sort(arr, new Comparator<Fruit4>() { //익명클래스 사용
			@Override
			public int compare(Fruit4  a1, Fruit4 a2) {
				return a1.getExpire().compareTo(a2.getExpire());
			}
		});
		showData("\n익명클래스 객체로 정렬(유통기한)후 객체 배열:", arr);

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		Comparator<Fruit4> cc_name = (a, b) -> a.getName().compareTo(b.getName());
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		Comparator<Fruit4> cc_price = (a, b) -> a.getPrice() - b.getPrice();
		sortData_p(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
		result3Index = Arrays.binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);

		}



	}

