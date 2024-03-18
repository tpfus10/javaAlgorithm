package ch05;

import java.util.Scanner;

public class Train3 {
	
	static void recur (int n) { 
//			while (n > 0){//if(n > 0){// n 값 출력
			while (true){
				if ( n > 0 ) { //체스판에 퀸을 배치할 수 있으면
					s.push(n); //recur(n-1);
					n =- n-1;
					continue;
				}
				if (s.isEmpty() != true) {
					n - s.pop();
					//pop한 위치를 사용해서 다음 열을 조사하고 더이상 없으면 이전 행으로 돌아가기
					System.out.println(n);
					n = n-2
					continue;
				}
			}
			recur (n-1);
			System.out.println(n);
			recur (n-2);
			
		}
	
	
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요:");
		int x = stdIn.nextInt();
		
		recur(x);
	}

}
