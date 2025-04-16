import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        tree = new long[4 * N];

        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        build(1, 1, N);  // 세그먼트 트리 초기화

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (order == 1) {
                long diff = b - arr[a];
                arr[a] = b;
                update(1, 1, N, a, diff);
            } else {
                System.out.println(query(1, 1, N, a, (int)b));
            }
        }
    }

    // 트리 초기화
    static long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = build(node * 2, start, mid) + build(node * 2 + 1, mid + 1, end);
    }

    // 값 갱신
    static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || idx > end) return;
        tree[node] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }

    // 구간 합 조회
    static long query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) +
               query(node * 2 + 1, mid + 1, end, left, right);
    }
}
