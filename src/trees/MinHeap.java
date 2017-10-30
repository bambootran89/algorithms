package trees;

public class MinHeap extends Heap{

	@Override
	void heapify(int i) {
		// TODO Auto-generated method stub
		if (i<0 || i>=heap.size()){
			return;
		}
		int left = left(i);
		int right = right(i);
		int min = i;
		if (left < heap.size() && heap.get(left) < heap.get(min))
			min = left;
		if (right < heap.size() && heap.get(right) < heap.get(min))
			min = right;
		if (min !=i){
			swap(min,i);
			heapify(min);
		}
	}

	@Override
	void decreaseKey(int index, int new_value) {
		// TODO Auto-generated method stub
		if (new_value > heap.get(index) || index < 0 || index >=heap.size())
			return;
		heap.set(index, new_value);
		while (index !=0 && heap.get(index) < heap.get(parent(index))){
			swap(index, parent(index));
			index = parent(index);
		}
	}

	@Override
	int getType() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

}
