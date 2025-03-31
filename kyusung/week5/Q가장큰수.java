import java.util.*;
class Q가장큰수{
        public static String solution(int[] numb) {
            String[] str_arr = new String[numb.length];
            String str = "";

            for(int i =0; i< numb.length; i++){
                str_arr[i] = String.valueOf(numb[i]);
            }

            Arrays.sort(str_arr, (o1, o2) -> {
                int a = Integer.parseInt(o1+o2);
                int b = Integer.parseInt(o2+o1);
                return b-a;
            });

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< str_arr.length; i++){
                sb.append(str_arr[i]);
            }

            return sb.charAt(0)== '0' ? "0": sb.toString();
        }
    }