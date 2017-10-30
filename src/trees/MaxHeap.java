package trees;

public class MaxHeap extends Heap {

	@Override
	void decreaseKey(int index, int new_value) {
		if (new_value < heap.get(index) || index < 0 || index >=heap.size())
			return;
		heap.set(index, new_value);
		while (index !=0 && heap.get(index) > heap.get(parent(index))){
			swap(index, parent(index));
			index = parent(index);
		}
	}

	@Override
	void heapify(int i) {
		if (i<0 || i>=heap.size()){
			return;
		}
		int left = left(i);
		int right = right(i);
		int max = i;
		if (left < heap.size() && heap.get(left) > heap.get(max))
			max = left;
		if (right < heap.size() && heap.get(right) > heap.get(max))
			max = right;
		if (max !=i){
			swap(max,i);
			heapify(max);
		}
	}

	@Override
	int getType() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}
		
	
}
