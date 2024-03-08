package ch02;


//메소드에 배열 전달 실습부터
//실습 설명한다 
//매개변수로 배열 전달
import java.util.Random;
public class Train04 {

	public static void main(String[] args) {
		int []data = new int[10];
		inputData(data);
		showData(data);
		
		int max = findMax(data);
		System.out.println("\nmax = " + max);
		
		boolean existValue = findValue(data, 3);
		System.out.println("찾는 값 = " + 3 + ", 존재여부 = " + existValue);
	}
	static void showData(int[]data) { //p59 메서드의 매개변수로 배열 사용
		for (int num: data) {//확장형 for문
			System.out.print("[" + num + "]");
		}
	}
	public static void inputData(int []data) {//p63 - 난수의 생성
		Random random = new Random();
		for(int i = 0; i<data.length; i++) {
			data[i] = random.nextInt(100); //nextInt()메서드는 random 클래스에 있음
		}
	}
	static int findMax(int []items) {
		int max = 0;
		for(int i = 0; i < items.length; i++) {
			if(items[i] > max) {
				max = items[i];
			}
		} return max;
	}
	static boolean findValue(int []items, int value) {
		//items[]에 value 값이 있는지를 찾는 알고리즘 구현
		boolean result = false;
		for(int i = 0; i<items.length; i++) {
			if(items[i] == value) {
				result = true;
				break;
			} else {
				result = false;
			}
		} return result;

	}

}
