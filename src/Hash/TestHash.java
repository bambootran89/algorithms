package Hash;

import java.util.ArrayList;
import java.util.Random;

import trees.VerticalOrderBtree;

public class TestHash {
	public static void main(String[] args) {
		VerticalOrderBtree vTree = new VerticalOrderBtree();
		java.util.List<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		
		for (int i =0; i< 20; i++){
			Integer num = rand.nextInt(1000);
			list.add(num);
			vTree.insert(num);
			
		}
		System.out.println(list);
		vTree.printVerticalOrder();
	}
}
