//Test_다항식merge연산실습
//MergeSort다항식정렬

package assignment;

class Polynomial3 implements Comparable<Polynomial3> {
	double coef; // 계수
	int exp; // 지수

	Polynomial3() {
	}

	// --- 생성자(constructor) ---//
	Polynomial3(double coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}

	// --- 신체검사 데이터를 문자열로 반환 --//
	@Override
	public String toString() {
		return String.format("%.1f", coef) + "x**" + exp + " ";
	}

	@Override
	public int compareTo(Polynomial3 d2) { // 지수가 같으면 계수로 비교
		return d2.exp - exp;
	}
}

public class A11 {

	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(Polynomial3[] a, int lefta, int righta, int leftb, int rightb) {
		Polynomial3 temp[] = new Polynomial3[30];
		int ix = 0;
		int p = lefta;
		int q = leftb;
		while (p <= righta && q <= rightb) { // mergesort에서 x.length - 1해줬기 때문에 =을 넣어줘야 함
			if (a[p].compareTo(a[q]) < 0)
				temp[ix++] = a[p++];
			else if (a[p].compareTo(a[q]) > 0)
				temp[ix++] = a[q++];
			else {
				temp[ix].coef = a[p].coef + a[q].coef;
				temp[ix].exp = a[p].exp;
				p++;
				q++;
				ix++;
			}
		}
		while (q <= rightb)
			temp[ix++] = a[q++];
		while (p <= righta)
			temp[ix++] = a[p++];

		for (int i = 0; i < ix; i++) {
			a[lefta + i] = temp[i];
		}
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(Polynomial3[] a, int left, int right) {
		int mid = (left + right) / 2;
		if (left == right)
			return;
		MergeSort(a, left, mid);
		MergeSort(a, mid + 1, right);
		merge(a, left, mid, mid + 1, right);
		return;
	}

	static void ShowPolynomial(String str, Polynomial3[] x, int count) {
		// str 변수는 다항식 이름으로 스트링이다
		// count가 -1이면 다항식 x의 length로 계산하고 -1이 아니면 count가 다항식 항의 숫자이다
		// 정렬후 다항식 x = 2.5x**7 + 3.8x**5 + 3.1x**4 + 1.5x**3 + 3.3x**2 + 4.0x**1 +
		// 2.2x**0
		int n = 0;
		if (count < 0)
			n = x.length;
		else
			n = count;
		// 구현코드
		System.out.print(str);
		for (int i = 0; i < n; i++) {
			if (i > 0)
				System.out.print("+ ");
			System.out.print(x[i]);
		}
		System.out.println();
	}

	static int AddPolynomial(Polynomial3[] x, Polynomial3[] y, Polynomial3[] z) {
		// z = x + y, 다항식 덧셈 결과를 z로 주고 z의 항의 수 terms을 리턴한다
		int p = 0, q = 0, r = 0;
		int terms = 0;

		while (p < x.length && q < y.length) {
			if (x[p].exp == y[q].exp) {
				z[r].coef = x[p].coef + y[q].coef;
				z[r].exp = x[p].exp;
				r++;
				terms++;
				p++;
				q++;
			} else if (x[p].exp > y[q].exp) {
				z[r++] = x[p++];
				terms++;
			} else {
				z[r++] = y[q++];
				terms++;
			}
		}

		while (p < x.length) {
			z[r++] = x[p++];
			terms++;
		}

		while (q < y.length) {
			z[r++] = y[q++];
			terms++;
		}

		return terms;

	}

	static int addTerm(Polynomial3[] z, Polynomial3 term, int terms) {
		// 다항식 z에 새로운 항 term을 추가한다. 지수가 같은 항이 있으면 계수만 합한다
		// 추가된 항의 수를 count하여 terms으로 리턴한다.
		for (int i = 0; i <= terms; i++) {
			if (term.exp == z[i].exp) {
				// 계수만 증가
				z[i].coef += term.coef;
				return terms; //계수가 같은 경우를 찾으면 for문을 나갈 수 있도록 return을 넣어야 함
			}
		}
		// 항을 추가
		z[terms] = term;
		terms++;
		return terms;
	}

	static int MultiplyPolynomial(Polynomial3[] x, Polynomial3[] y, Polynomial3[] z) {
		// z = x * y, 다항식 z의 항의 수는 terms으로 리턴한다
		// terms = addTerm(z, term, terms);사용하여 곱셈항을 추가한다.
		int p = 0, q = 0, r = 0; // p는 x의 인덱스, q는 y의 인덱스 r은 z의 인덱스
		int terms = 0;
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < y.length; j++) {
				Polynomial3 newTerm = new Polynomial3();
				newTerm.coef = x[i].coef * y[j].coef;
				newTerm.exp = x[i].exp + y[j].exp;
				terms = addTerm(z, newTerm, terms);
			}
		return terms;
	}

	static double EvaluatePolynomial(Polynomial3[] z, int zTerms, int value) {
		// zTerms는 다항식 z의 항의 수, value는 f(x)를 계산하기 위한 x 값
		// 다항식 계산 결과를 double로 리턴한다
		double result = 0.0;
		for(int i = 0; i <=zTerms; i++) {
			result += z[i].coef*Math.pow(value, z[i].exp);//Math.pow(): 제곱 계산을 해주는 함수
		}
		return result;
	}

	// ---------------------------------------------------------------------------------------------main

	public static void main(String[] args) {
		Polynomial3[] x = { new Polynomial3(1.5, 3), new Polynomial3(2.5, 7), new Polynomial3(3.3, 2),
				new Polynomial3(4.0, 1), new Polynomial3(2.2, 0), new Polynomial3(3.1, 4), new Polynomial3(3.8, 5), };

		Polynomial3[] y = { new Polynomial3(1.5, 1), new Polynomial3(2.5, 2), new Polynomial3(3.3, 3),
				new Polynomial3(4.0, 0), new Polynomial3(2.2, 4), new Polynomial3(3.1, 5), new Polynomial3(3.8, 6), };
		int nx = x.length;

		ShowPolynomial("다항식 x = ", x, -1);
		ShowPolynomial("다항식 y = ", y, -1);

		MergeSort(x, 0, x.length - 1); // 배열 x를 퀵정렬
		MergeSort(y, 0, y.length - 1); // 배열 x를 퀵정렬
		ShowPolynomial("정렬후 다항식 x = ", x, -1);
		ShowPolynomial("정렬후 다항식 y = ", y, -1);

		Polynomial3[] z = new Polynomial3[20];
		for (int i = 0; i < z.length; i++)
			z[i] = new Polynomial3();

		int zTerms = AddPolynomial(x, y, z);// 다항식 덧셈 z = x + y
		ShowPolynomial("덧셈후 다항식 z = ", z, zTerms);

		zTerms = MultiplyPolynomial(x, y, z);// 다항식 곱셈 z = x * y
		MergeSort(z, 0, zTerms); // 배열 x를 퀵정렬
		ShowPolynomial("곱셈후 다항식 z = ", z, zTerms);

		 double result = EvaluatePolynomial(z, zTerms, 2);//다항식 값 계산 함수 z(10) 값 계산한다
		 System.out.println(" result = " + result );
	}
}
