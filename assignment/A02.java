//2장 과제2: 객체 배열 정렬
package assignment;

import java.util.Arrays;
//구글링: comparator, comparable 차이가 무엇인지 사용예시 파악
//5번 실습 - 2장 실습 2-14를(2-10) 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData>{
	String name;
	int height;
	double vision;

	public PhyscData(String string, int i, double d) {
		this.name = string;
		this.height = i;
		this.vision = d;
	}
	
	
	
	@Override
	public String toString() { //showData의 출력 형태를 설정
		return "PhyscData{name=" + name + ", height=" + height + ", vision=" + vision + "}";
	}

	
	@Override
	public int compareTo(PhyscData p) {
		 return this.name.compareTo(p.name); //왜 this.name??????
	}

	public int equals(PhyscData p) {
		
	}
}

public class A02 {
	static void swap(PhyscData[]arr, int ind1, int ind2) {
		PhyscData t = arr[ind1]; //t의 데이터 타입은 PhyscData
		arr[ind1] = arr[ind2];
		arr[ind2] = t;
	}
	
	static void sortData(PhyscData []arr) {
		for(int i = 0; i<arr.length; i++)
			for(int j = i+1; j<arr.length; j++) {
				//if(arr[j] > arr[i]) { //compareTo() 사용하는 것
					if(arr[i].compareTo(arr[j])>0) {
						swap(arr, i , j);
					}
				}
	}

	//----------------------------------------------------------------------main
	public static void main(String[] args) {
		PhyscData[] data = { //객체 리스트
				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 0.5),
		};
		
		System.out.println("정렬 전");
		showData(data);
		sortData(data);
		
		System.out.println("정렬 후(by name)");
		//Arrays.sort(null, null);//comparator가 필요함 
		showData(data);
	}
	
	static void showData(PhyscData[]arr) {
		for(PhyscData p : arr) {
			System.out.println(p);
		}System.out.println();
	}
}
