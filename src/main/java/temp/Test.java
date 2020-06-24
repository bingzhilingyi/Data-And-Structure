package temp;

import com.chris.datastructure.Stack.LinkedListStack;
import com.chris.datastructure.linkedList.LinkedList;
import com.chris.datastructure.queue.ArrayQueue;
import com.chris.datastructure.queue.LoopQueue;
import com.chris.datastructure.queue.Queue;

public class Test {

    public static void main(String[] args){
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        for(int i=0;i<5;i++){
            stack.push(i);
            System.out.println(stack);
        }

    }
}