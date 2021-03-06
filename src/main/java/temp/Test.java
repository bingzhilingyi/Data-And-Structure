package temp;

import com.chris.datastructure.Array;
import com.chris.datastructure.Stack.LinkedListStack;
import com.chris.datastructure.queue.ArrayQueue;
import com.chris.datastructure.queue.LoopQueue;
import com.chris.datastructure.queue.Queue;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args){
        int[] a = {1,2,2,1};
        int[] b = {2};
        Arrays.stream(intersect(a,b)).forEach(n->System.out.println(n));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> nums1Map = new TreeMap<>();
        for(int num:nums1){
            if(nums1Map.containsKey(num)){
                nums1Map.put(num,nums1Map.get(num)+1);
            }else {
                nums1Map.put(num,1);
            }
        }

        ArrayList<Integer> re = new ArrayList<>();
        for(int num:nums2){
            if(nums1Map.containsKey(num)){
                re.add(num);
                nums1Map.put(num,nums1Map.get(num)-1);
                if(nums1Map.get(num)==0){
                    nums1Map.remove(num);
                }
            }
        }

        return re.stream().mapToInt(Integer::new).toArray();
    }
}

