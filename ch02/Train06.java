//정수 매트릭스 정렬
package ch02;

/*
 * 3번째 실습
 * 교재 83 - 배열 처리
 */

import java.util.Arrays;
import java.util.Random;
public class Train06 {
	public static void main(String[] args) {
		int [][]A = new int[2][3];
		int [][]B = new int[3][4];
		int [][]C = new int[2][4];

		inputData(A);inputData(B);
		int [][]D = A.clone();//교재83 - 배열 복제
		
		System.out.println("A[2][3] = ");
		showData(A);
		System.out.println("D[2][3] = ");
		showData(D);
		System.out.println();
		System.out.println("B[3][4] = ");
		showData(B);
		
		int [][]E = addMatrix(A,D); //static 메소드
		// int[][] E = A.addMatrix(B): A 클래스의 메소드
		System.out.println("E[2][3] = ");
		showData(E);
		
		C = multiplyMatrix(A,B);
		System.out.println("C[2][4] = ");
		showData(C);

		int [][]F = transposeMatrix(A);
		System.out.println("F[3][2] = ");
		showData(F);
		
		boolean result = equals(A, D);
		System.out.println("Equals(A,D) = " + result);
		
		System.out.println("F = " + Arrays.deepToString(F));//2차원 배열 처리 
	}
	
	static void inputData(int [][]data) {
		Random random = new Random();
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				data[i][j] = random.nextInt(100);
			}
		}
	}
	
	static void showData(int[][]items) {
		for (int[] item : items) {
			for (int elem : item) {
				System.out.print(elem + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean equals(int[][]a, int[][]b) {
		if (a.length != b.length || a[0].length != b[0].length) 
			return false;
		else 
			return true;
	}
	
	static int[][] addMatrix(int [][]X, int[][]Y) {
		int rows = X.length;
		int cols = X[0].length;
		int [][]Z = new int[rows][cols];  //Z = X + Y (X, Y의 값이 변하지 않도록 하기 위해)
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<cols; j++) {
				Z[i][j] = X[i][j] + Y[i][j];
			}
		}
		return Z;
	}
	
	static int[][] multiplyMatrix(int [][]X, int[][]Y) {
		int rows = X.length;
		int cols = Y[0].length;
		int [][]Z = new int[rows][cols];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<X[0].length; j++) {
				for(int k = 0; k<cols; k++) {
					Z[i][k] += X[i][j] * Y[j][k];
				}
			}
		}
		return Z;
	}
	
	static int[][] transposeMatrix(int [][]X) {
		int rows = X.length;
		int cols = X[0].length;
		int [][]Z = new int[cols][rows];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				Z[j][i] = X[i][j];
			}
		}
		return Z;
	}
}


