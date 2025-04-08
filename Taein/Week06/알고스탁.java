import java.util.*;
import java.io.*;

/*
 * 문제 해결 아이디어: DP + Knapsack
 *
 * 1. 상태 정의:
 *    dp[i] = i개월차 *시작 시점* (월별 불입금 Ma가 추가된 후)에 보유 가능한 최대 현금
 *
 * 2. 기본 케이스:
 *    dp[0] = Ms (0개월차 시작 시점에는 초기 예치금만 있음)
 *
 * 3. 점화식 (상태 전이):
 *    dp[i+1]을 계산하기 위해 i개월차에 어떤 투자를 할지 결정해야 함.
 *    i개월차 시작 시점의 현금은 dp[i]이다.
 *    이 현금(dp[i])을 가지고 i개월차에 주식을 매수 (가격: costs[stock][i]) 하고,
 *    i+1개월차에 해당 주식을 매도 (가격: costs[stock][i+1]) 하여 최대 이익을 내야 함.
 *
 *    이 과정은 '무한(Unbounded) 냅색 문제'와 동일함:
 *    - 가방의 용량(W): 현재 보유 현금 (dp[i])
 *    - 아이템(주식): 각 주식 종목 j
 *    - 아이템의 무게(w): 주식 j의 i개월차 매수 가격 (costs[j][i])
 *    - 아이템의 가치(v): 주식 j를 i개월차에 사서 i+1개월차에 팔 때의 순수익 (costs[j][i+1] - costs[j][i])
 *                     (단, 이익이 양수일 때만 고려)
 *    - 목표: 가방 용량 내에서 아이템 가치의 합(총 순수익)을 최대화
 *
 *    따라서, i개월차에 얻을 수 있는 최대 투자 수익(max_profit_in_month_i)을 냅색 알고리즘으로 계산.
 *
 *    다음 달 시작 시점의 현금은 다음과 같이 계산됨:
 *    dp[i+1] = (i개월차 시작 시점 현금) + (i개월차 투자로 얻은 최대 순수익) + (다음 달 월별 불입금)
 *    dp[i+1] = dp[i] + max_profit_in_month_i + Ma
 *
 * 4. 최종 결과:
 *    L개월간의 투자가 끝나고 L개월차 종료 시점 (즉, L+1개월차 시작 시점 전)의 최대 현금은 dp[L] 이다.
 *    총 투자 원금 = 초기 예치금(Ms) + L개월간의 총 월별 불입금(Ma * L)
 *    최대 수익 = dp[L] - (Ms + Ma * L)
 *
 * 5. 주의사항:
 *    - 현금 보유액이 커질 수 있으므로 dp 배열은 long 타입 사용 고려. (문제 제약조건 12 확인)
 *    - 냅색 DP 배열 크기는 최대 예상 현금 보유액 + 1 이어야 함. (제약조건 12: 100,000 미만)
 *    - 매달 냅색 계산을 새로 해야 하므로, 냅색 DP 배열은 매달 초기화 또는 루프 내 선언.
 *    - 주식 매수 가격(냅색 아이템 무게)이 0이거나 음수일 경우는 없지만, 이익이 0 이하인 경우는 투자하지 않음.
 */
public class Solution {

    // N: 종목 수, L: 과거 데이터 기간 (개월)
    // costs[stock_idx][month_idx]: stock_idx 종목의 month_idx 월의 가격 (stock_idx: 1~N, month_idx: 0~L)
    static int Ms, Ma, N, L;
    static int[][] costs; 
    // dp[i]: i개월차 *시작 시점* (바로 직전 달의 Ma가 추가된 상태) 보유 최대 현금
    static long[] dp;     
    // knapsack_dp[c]: 예산 c로 얻을 수 있는 최대 *순수익* (매도금액 - 매수금액)
    static int[] knapsack_dp; 

    public static void main(String[] args) throws Exception {
        // 입력 처리 효율화를 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        // 출력 효율화를 위한 StringBuilder 사용
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            // 테스트 케이스별 입력 읽기
            StringTokenizer st = new StringTokenizer(br.readLine());
            Ms = Integer.parseInt(st.nextToken()); // 초기 예치금
            Ma = Integer.parseInt(st.nextToken()); // 월별 불입금

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 종목 수
            L = Integer.parseInt(st.nextToken()); // 기간 (개월)

            // 주식 가격 정보 입력 (1번 종목부터 N번 종목, 0개월차부터 L개월차)
            costs = new int[N + 1][L + 1]; 
            for (int i = 1; i <= N; i++) { 
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j <= L; j++) { 
                    costs[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // dp 배열 초기화: dp[i]는 i개월차 시작 시점의 최대 현금
            dp = new long[L + 1];
            dp[0] = Ms; // 0개월차 시작 시점에는 초기 예치금 Ms만 보유

            // 제약조건 12: 원금과 수익 합계가 100,000 미만 -> 최대 현금도 100,000 미만
            // 넉넉하게 100001 크기 할당 (인덱스 100000까지 사용 가능)
            int maxPossibleCash = 100001; 
            knapsack_dp = new int[maxPossibleCash]; // 매달 재사용될 냅색 DP 배열

            // 각 월별로 최대 수익 계산 (0개월차 ~ L-1개월차 동안 투자 결정)
            // month : 투자를 결정하는 시점 (해당 월)
            for (int month = 0; month < L; month++) { 
                
                // 현재 (month개월차 시작 시점) 보유 현금으로 투자 진행
                long current_cash = dp[month]; 
                
                // 투자 가능한 최대 예산 (int 범위 및 배열 크기 확인)
                int budget = (int) current_cash;
                if (budget < 0) budget = 0; // 방어 코드
                // 예산이 knapsack_dp 배열 크기보다 크면 최대 크기로 제한
                if (budget >= maxPossibleCash) { 
                    budget = maxPossibleCash - 1; 
                }

                // 냅색 DP 배열 초기화 (0 ~ budget 까지)
                // Arrays.fill은 end index를 포함하지 않으므로 budget + 1
                Arrays.fill(knapsack_dp, 0, budget + 1, 0);

                // 냅색 계산: month 시점에 사서 month+1 시점에 팔 때의 최대 *순수익* 계산
                for (int stock_idx = 1; stock_idx <= N; stock_idx++) {
                    int buy_price = costs[stock_idx][month];     // month 시점 매수 가격
                    int sell_price = costs[stock_idx][month + 1]; // month+1 시점 매도 가격
                    int profit_per_share = sell_price - buy_price; // 주당 순수익

                    // 이익이 있고(profit > 0), 매수 가격이 0보다 크며 예산 내에서 살 수 있을 때만 고려
                    if (profit_per_share > 0 && buy_price > 0) {
                        // Unbounded Knapsack: 예산 c를 사용해서 얻는 최대 이익 업데이트
                        for (int c = buy_price; c <= budget; c++) {
                            // knapsack_dp[c] : 예산 c를 사용했을 때의 최대 이익
                            // knapsack_dp[c - buy_price] : 현재 주식을 1주 사기 전, 남은 예산으로 얻은 최대 이익
                            knapsack_dp[c] = Math.max(
                                knapsack_dp[c], // 현재 주식을 사지 않는 경우
                                knapsack_dp[c - buy_price] + profit_per_share // 현재 주식 1주를 사는 경우
                            );
                        }
                    }
                }
                
                // 이번달 투자로 얻은 최대 순수익 (budget 만큼의 예산을 사용했을 때)
                int max_profit_this_month = (budget >= 0) ? knapsack_dp[budget] : 0;

                // 다음달(month+1) 시작 시점의 현금 계산
                // dp[month+1] = 현재 현금(dp[month]) + 이번달 투자 최대 순수익 + 다음달 월별 불입금(Ma)
                dp[month + 1] = dp[month] + max_profit_this_month + Ma; 
            }

            // 최종 결과 계산
            // dp[L]은 L개월차 시작 시점의 현금 (즉, L-1개월까지의 투자 결과 + L개월차 Ma 불입금)
            long final_cash = dp[L];
            // 총 투자 원금 = 초기 예치금 + L개월간의 월별 불입금 총액
            long total_investment = Ms + (long)Ma * L;
            long max_total_profit = final_cash - total_investment;

            // 이론상 최대 수익은 음수가 될 수 없음 (최소한 저축만 해도 원금 보존)
            if (max_total_profit < 0) max_total_profit = 0; 

            // 결과 포맷에 맞게 출력 문자열 구성
            sb.append("#").append(t).append(" ").append(max_total_profit).append("\n");
        }
        // 모든 테스트 케이스 결과 한 번에 출력
        System.out.print(sb.toString());
    }
}
