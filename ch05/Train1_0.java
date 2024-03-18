//재귀 알고리즘
package ch05;

import java.util.Scanner;

public class Train1_0 {
	
	static int factorial(int n) { //팩토리얼 함수가
		if (n > 0)
			return n*factorial(n-1); //자기 자신을 부름
		else 
			return 1;			
	}
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요: ");
		int x = stdIn.nextInt();
		
		System.out.println(x + "의 팩토리얼은" + factorial(x) + "입니다.");
	}

}
