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
}

//교재 123~129 페이지 참조하여 구현
class FruitNameComparator2 implements Comparator<Fruit4> { //comparator 인터페이스를 구현한 클래스
	public int compare(Fruit4 f1, Fruit4 f2) {//concrete class(객체 만들기 가능, 추상 클래스는 불가능)
		//함수의 body->람다식의 함수본체
	}

public class A04 {
	private static void sortData(Fruit4[] arr, Comparator<Fruit4> cc_price) {

	}
	
	static void swap(Fruit4[]arr, int ind1, int ind2) {
		Fruit4 temp = arr[ind1]; arr[ind1] = arr[ind2]; arr[ind2] = temp;
	}
	
	static void sortData(Fruit4 []arr, FruitNameComparator2 cc) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++)
				if (cc.compare(arr[i],arr[j])> 0) swap(arr, i, j); //(FruitNameComparator2에 구현된) cc객체에는 compare 메소드만 있음
		}
	}

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
		
		FruitNameComparator2 cc = new FruitNameComparator2();//cc 객체 생성
		
		System.out.println("\n comparator cc 객체를 사용:: ");
		Arrays.sort(arr, cc);
		showData("Arrays.sort(arr, cc) 정렬 후", arr);
		
		reverse(arr);
		showData("역순 재배치 후", arr);
		
		sortData(arr, cc);
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
		System.out.println("\n익명클래스 객체로 정렬(가격)후 객체 배열: ");
		Arrays.sort(arr, new Comparator<Fruit4>() {
			@Override
			public int compare(Fruit4 a1, Fruit4 a2) {
				return a1.getName().compareTo(a2.getName()); //a) + b) + c)
			}
		});
		//----------------------------------------------------------------------------------------------------
		
		System.out.println("\ncomparator 정렬(이름)후 객체 배열: ");
		showData("comparator 객체를 사용한 정렬:", arr);
	
		Comparator<Fruit4> cc_name = new Comparator<Fruit4>() {// 익명클래스 사용

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				// TODO Auto-generated method stub
				return (f1.name.compareTo(f2.name));
			}

		};
		Comparator<Fruit4> cc_price = new Comparator<Fruit4>() {

			@Override
			public int compare(Fruit4 f1, Fruit4 f2) {
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용

		};

		Fruit4 newFruit4 = new Fruit4("수박", 880, "2023-5-18");
		/*
		 * 교재 115 Arrays.binarySearch에 의한 검색
		 */
		int result3Index = Arrays.binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_name);
		System.out.println("\nbinarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);

		sortData(arr, cc_price);
		System.out.println("\ncomparator 정렬(가격)후 객체 배열: ");
		showData("comparator를 사용한 정렬후:", arr);
		
		result3Index = Arrays.binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nArrays.binarySearch([수박,880,2023-5-18]) 조회결과::" + result3Index);
		
		result3Index = binarySearch(arr, newFruit4, cc_price);
		System.out.println("\nbinarySearch() 조회결과::" + result3Index);

		}
	}



} 
