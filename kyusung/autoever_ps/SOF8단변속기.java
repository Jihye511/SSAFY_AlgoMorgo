import java.io.*;
import java.util.*;

public class SOF8단변속기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[8];
        for(int i = 0; i<8; i++){
            nums[i] = sc.nextInt();
        }

        if(nums[0] == 1) {

            for(int i = 0; i < 8; i++){
                if(nums[i] != i + 1){
                    System.out.println("mixed");
                    return;
                }    
            }
            System.out.println("ascending");

        }else if(nums[0] == 8){

            for(int i = 0; i < 8; i++){
                if(nums[i] != 8 - i){
                    System.out.println("mixed");
                    return;
                }    
            }
            System.out.println("descending");
            
        }else{
            System.out.println("mixed");
        }
        
    }
}
