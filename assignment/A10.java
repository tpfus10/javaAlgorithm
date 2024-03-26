//Test_객체merge정렬 : 정수 버전을 객체 버전으로 바꾸기
package assignment;

/*
 * 6장 구현 실습과제1 
 */

class PhyscData implements Comparable<PhyscData>{
    String name;              // 이름
    int    height;            // 키
    double vision;            // 시력
	
	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public int compareTo(PhyscData o) {
		if(name.compareTo(o.name) > 0) return 1;
		else if(name.compareTo(o.name) < 0) return -1;
		else return 0;
	}

}
public class A10 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a, int lefta, int righta, int leftb, int rightb ) {
		PhyscData temp [] = new PhyscData[30];
		int ix=0;
		int p = lefta, q =leftb;
		while(p <= righta && q <rightb) {
			if(a[p].compareTo(a[q]) < 0) temp[ix++] = a[p++];
			else if (a[p].compareTo(a[q]) > 0) temp[ix++] = a[q++];
			else {
				temp[ix++] = a[p++]; temp[ix++] = a[q++]; //같은 건 두 번 넣어주기
			}
		}
		while (p > righta && q <= rightb) temp[ix++] = a[q++];
		while (q > rightb && p <= righta) temp[ix++] = a[p++];
		System.out.println();
		for(int j = 0; j < ix; j++) {//배열 a에 temp값 옮기기
			a[lefta + j] =temp[j];
			System.out.println(" " + a[lefta + j]);
		}
		System.out.println();
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid); //mergesort메소드로 왼쪽 정렬
		MergeSort(a, mid+1, right); //mergesort메소드로 오른쪽 정렬
		merge(a, left, mid, mid+1, right); //merge메소드로 왼쪽과 오른쪽 병합정렬
		return;
	}

//------------------------------------------------------main
	
	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
		int nx = x.length;

		   MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬->여기에서 시작
			System.out.println("오름차순으로 정렬했습니다.");
		     System.out.println("■ 신체검사 리스트 ■");
		     System.out.println(" 이름     키  시력");
		     System.out.println("------------------");
		     for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
