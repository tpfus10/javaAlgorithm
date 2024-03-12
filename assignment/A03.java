//3장 과제1: 스트링배열합병(mergeList) 
//-> 이후에는 객체배열합병정렬 
package assignment;

/*
 * 함수(메소드)에 parameter 전달 방식을 표현하는 능력의 숙달 훈련
 * 함수(메소드) 전체를 작성하는 훈련 
 * merge() 사용법 
 */
import java.util.Arrays;
import java.util.List;
public class A03 {
    static void showList(String topic, String [] list) {
    	System.out.println(topic);
    	for(String item : list) {
    		System.out.print(item + " ");
    	}System.out.println();
    }
    
    static String[] mergeList(String[]s1, String[] s2) {
    	int i = 0, j = 0, k =0;
    	String[] s3 = new String[10];
   
    	while(i<s1.length && j<s2.length) {
    		if(s1[i].compareTo(s2[j]) < 0) {
    			s3[k++] = s1[i++]; //맨 처음에는 ++연산이 적용되지 않음
    		}
    		else {
    			s3[k++] = s2[j++];
    		}
    	}
    	
    	//두 배열 중 하나가 먼저 끝났을 때 남은 데이터를 복사
    	while(i<s1.length) {
    		s3[k++] = s1[i++];
    	}
    	
    	while(j<s2.length) {
    		s3[k++] = s2[j++];
    	}
    	
    	
    	return s3;//함수의 리턴 타입이 배열
    }
    
 //-------------------------------------------------------------main
    
    public static void main(String[] args) {
	String[] s1 = { "홍길동", "강감찬", "을지문덕", "계백", "김유신" };
	String[] s2 = {"독도", "울릉도", "한산도", "영도", "우도"};
	Arrays.sort(s1); //comparable, comparator가 없음 > comparable의 compareTo() 사용
	Arrays.sort(s2);
	
	showList("s1배열 = ", s1);
	System.out.println();
	
	showList("s2배열 = ", s2);
	System.out.println();

	//감강찬 계백 김유신 독도 영도 우도 울릉도 을지문덕 한산도 홍길동 
	//for() 말고 while() 사용하기(3개 써야 함)
	String[] s3 = mergeList(s1, s2);
	showList("스트링 배열 s3 = s1 + s2:: ", s3);
}
}
