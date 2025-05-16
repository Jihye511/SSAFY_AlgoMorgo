import java.io.*;
import java.util.*;

public class SOF금고털이 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] info = new int[N][2];
        for(int i  =0 ; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<2; j++){
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(info, (o1, o2) -> {
          return Integer.compare(o2[1], o1[1]); 
        });

        int ans = 0;
        for(int i = 0; i<N; i++){
            if(W >= info[i][0]){        // 가용 무게가 가장 높은 가성비의 전체 무게보다 크면
                ans += info[i][0] * info[i][1];
                W -= info[i][0];
            }else{
                ans += info[i][1] * W;
                W = 0;
                break;
            }
        }

        System.out.println(ans);
    }
}
