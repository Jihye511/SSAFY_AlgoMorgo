import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Q알고스탁 {

	public static class Node{
		int price;
		double profit;
		
		public Node(int price, double profit) {
			this.price = price;
			this.profit = profit;
		}
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			double now_fund = 0;
			
			int Ms = sc.nextInt();
			int Ma = sc.nextInt();
			
			int N = sc.nextInt();
			int L = sc.nextInt();
			
			// 주식 별 시세
			int[][] stocks = new int[N][L+1];
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<L+1; j++) {
					stocks[i][j] = sc.nextInt();
				}
			}
			
			// 시세 비율 추이
			double [][] ratio = new double [N][L];
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<L; j++) {
					ratio[i][j] = (double) stocks[i][j+1] / (double) stocks[i][j];
				}
			}
			
			// 시뮬
			now_fund += Ms;
			double prev_profit = 0;
			
			for(int m = 0; m <= L; m++) {
				if(m > 0) now_fund += Ma;
				
				// 지난달 투자금 회수 + 이윤
				now_fund += prev_profit;
				prev_profit = 0;
				
				// 주식 구매
				PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
					return Double.compare(o2.profit, o1.profit);
				});
				
				if(m < L) {
					for(int i = 0; i<N; i++) {
						pq.add(new Node(stocks[i][m], ratio[i][m]));
					}
				}

				// 자금을 넘지 않는 선에서 최대한 구매
				while(!pq.isEmpty()) {
					Node stock = pq.poll();
					
					if(stock.profit <= 1.0 ) continue;		// 구매해도 이윤이 남지 않는 경우
					if(stock.price > now_fund) continue;	// 돈이 부족해서 못 사는 경우
										
					int num_to_buy = (int) now_fund / stock.price;	// 현재 가진 돈 / 가격 만큼 구매
					now_fund -= num_to_buy * stock.price;					// 지갑에서 돈 빼감
					prev_profit += num_to_buy * (stock.price * stock.profit);	// 다음에 받을 돈
				}
			}
			
			double invest = Ms + L * Ma;
			int total_benef = (int) Math.round(now_fund - invest);
			
			sb.append("#"+ test_case + " " + total_benef).append('\n');
		}
		
		System.out.println(sb.toString());
	}

}
