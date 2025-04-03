import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1463 {
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		recursive(N, 0);
		System.out.println(result);
	}

	public static void recursive(int n, int cnt) {
		int count = cnt;
		if (n == 1) {
			result = Math.min(result, count);
			return;
		}
		if (n < 1) {
			return;
		}

		count++;
		if (result > count) {

			if (n % 3 == 0) {
				System.out.println("1    " + n / 3);
				recursive(n / 3, count);
			}
			if (n % 2 == 0) {
				System.out.println("2    " + n / 2);
				recursive(n / 2, count);
			}
			if (n > 2) {
				System.out.println("3    " + (n - 1));
				recursive(n - 1, count);
			}

		} else {
			return;
		}

	}

}
