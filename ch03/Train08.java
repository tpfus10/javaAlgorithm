//객체비교연산자(p126)
//comparator는 기준을 바꿔가며 비교해야 할 때 유용함
package ch03;

import java.util.Arrays;
import java.util.Comparator;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData3 {
	String name;
	int height;
	double vision;
	
	public PhyscData3(String string, int i, double d) {
		this.name = string;
		this.height = i;
		this.vision = d;
	}
}

class CompName implements Comparator<PhyscData3> { //p125 2번
	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		if(o1.name.compareTo(o2.name) < 0) return 1;
		else if (o1.name.compareTo(o2.name) > 0) return -1;
		else return 0;
	}

}

class CompHeight implements Comparator<PhyscData3> { //p125 2번
	@Override
	public int compare(PhyscData3 o1, PhyscData3 o2) {
		if(o1.height > o2.height) return 1;
		else if (o1.height < o2.height) return -1;
		else return 0;
	}

}

public class Train08 {
	static final Comparator<PhyscData3> HEIGHT_ORDER = new CompHeight();//p125 1번, comparator 인터페이스를 구현한 객체1
	static final Comparator<PhyscData3> NAME_ORDER = new CompName();//p125 1번, comparator 인터페이스를 구현한 객체2

	public static void main(String[] args) {
		PhyscData3[] data = { 
				new PhyscData3("홍길동", 162, 0.3), 
				new PhyscData3("홍동", 164, 1.3),
				new PhyscData3("홍길", 152, 0.7), 
				new PhyscData3("김홍길동", 172, 0.3), 
				new PhyscData3("길동", 182, 0.6),
				new PhyscData3("길동", 167, 0.2), 
				new PhyscData3("길동", 167, 0.5), };
		//data.binarysearch()는 안 됨 data가 객체가 아니라 참조변수이기 때문
		//Arrays.binarySearch(data, key, new CompName());
		//Arrays.binarySearch(data, key, HEIGHT_ORDER); 51번 줄에 있음
		
		showData("정렬전 객체 배열", data);
		Arrays.sort(data, NAME_ORDER);

		showData("정렬후 객체 배열", data);
		PhyscData3 key = new PhyscData3("길동", 167, 0.2);

		int idx1 = Arrays.binarySearch(data, key, NAME_ORDER);
		int idx2 = Arrays.binarySearch(data, key, HEIGHT_ORDER);
		System.out.println("\nArrays.binarySearch(): result = " + idx1);
		System.out.println("\nArrays.binarySearch(): result = " + idx2);
	}
	
	private static void showData(String string, PhyscData3[] data) {
		// TODO Auto-generated method stub
		
	}


}
