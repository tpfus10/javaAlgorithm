//스트링 리스트 정렬(feat. 배열과 리스트의 변환)
//Arrays.sort()   Collections.sort()   list.sort() => 세 가지 익히기
package ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Train03 {

	//중복이 있는 요소를 제거
	public static String[] removeElement1(String[] arr, String item) {// (11+)
		//배열을 리스트로 다시 변환해서 list.remove();
		//삭제된 리스트를 다시 배열로 변환
		ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));
		list.remove(item);
		return list.toArray(String[]::new);//리스트와 배열은 길이가 자동으로 조정되지 않음
			}
	
	    
	    static void getList(List<String> list) {//(3)
			list.add("서울");	list.add("북경");
			list.add("상해");	list.add("서울");
			list.add("도쿄");	list.add("뉴욕");
			list.add("런던");	list.add("로마");
			list.add("방콕");	list.add("북경");
			list.add("도쿄");	list.add("서울");
			list.add(1, "LA");
	    }
	    
	    static void showList(String topic, List<String> list) {//(5),(9),(14)
	    	System.out.println(topic + "::");
	    	for(String st : list) {
	    		System.out.print(st + " ");
	    	}System.out.println();
	    }
	    
		static void sortList(List<String> list) {// (7)
			// 방법1: list.sort(): Comparator 인터페이스를 구현해야 사용할 수 있음(A와 B단계 통해 구현됨)
			// 방법2: list를 String 배열로 변환->array.sort()
			class Comp implements Comparator<String> {//A: Comparator<String>인터페이스 구현
				@Override
				public int compare(String d1, String d2) {//B: compare() 메서드를 오버라이딩
					// TODO Auto-generated method stub
					return d1.compareTo(d2);
				}
			}
			
			Comp cc = new Comp(); // comparator(정렬 기준을 가진 인터페이스)를 구현해야 그에 따라 sort를 할 수 있게 됨
			list.sort(cc); //
		}
	    
		//중복 여부 조사
	    static String[] removeDuplicateList(List<String> list) {//(11)
		    String cities[] = new String[0]; //cities 배열 생성
		    cities = list.toArray(cities); //리스트를 배열로 변환
		    //리스트를 배열로 변환한 후 for문으로 도시가 중복이 있는 것을 체크 
		    //compareTo 사용해서 removeElement 호출
		    for(int i = 0; i<cities.length; i++) {
		    	for(int j = i+1; j<cities.length; j++) {
		    		if(cities[i].compareTo(cities[j]) == 0) {
		    		cities = removeElement1(cities, cities[j]);//중복 제거를 위해 배열 전달하고 제거된 거 받으면 다시 저장해야 함
		    		i--;//다음 반복에서 동일한 인덱스 i에 대해 다음 요소 (j+1)와 비교를 다시 시작하는 것을 의미
		    		} 
		    	}
		    }
		    return cities;
		  }	
	   
	    
//----------------------------------------------------------------------------main
	    
		public static void main(String[] args) {
			ArrayList<String> list = new ArrayList<>();//(1)
			getList(list);//(2)
			showList("입력후", list);//(4)
			
			//sort - 오름차순으로 정렬, 내림차순으로 정렬, 중복 제거하는 코딩

			//Collections.sort(list);

//배열의 정렬
			sortList(list);//(6)
		    System.out.println();
		    showList("정렬후", list);//(8)
		    
// 배열에서 중복제거
		    System.out.println();
		    System.out.println("중복제거::");
		  
		    String[] cities = removeDuplicateList(list);//(10)
	        ArrayList<String> lst = new ArrayList<>(Arrays.asList(cities));//배열을 다시 리스트로 (12)
		    showList("중복제거후", lst);//(13)
		}
	}

