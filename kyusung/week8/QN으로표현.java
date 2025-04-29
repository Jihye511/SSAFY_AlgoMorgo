import java.util.*;

class QN으로표현 {
	
	public int solution(int N, int tar) {
		
		Set<Integer>[] set = new Set[8];
		
		for(int i = 0; i<8; i++) {
			set[i] = new HashSet<>();
		}

		int size = 0;							// 이전에 넣은 값
		for(int i = 1; i<9; i++) {
			size = size * 10 + N;

			if(size == tar) {
				return i;
			}

			set[i-1].add(size);
		}

		//
		for (int use = 1; use < 8; use++) {

			for (int j = 0; j < use; j++) {

				for (int a : set[j]) {
					for (int b : set[use - j - 1]) {

						int[] values = {
								a + b,
								a - b,
								a * b,
								b != 0 ? a / b : Integer.MIN_VALUE
						};

						for (int val : values) {
							if (val == tar) return use + 1;
							if (val != Integer.MIN_VALUE) {
								set[use].add(val);
							}
						}
					}
				}
			}
		}

		return -1;
	}
}
