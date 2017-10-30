package List;

public class LinkedList {
	private class Node{
		int data;
		Node next;
		public Node(int d){
			data = d;
			next = null;		
		}
	}
	Node head;
	int count;
	
	public LinkedList(){
		head = null;
		count = 0;
	}
	
	public void push(int data){
		Node new_node = new Node(data);
		new_node.next = head;
		head = new_node;
		count+=1;
	}
	
	public void insertAfter(Node prev_node,int data){
		if (prev_node == null){
			return;
		}
		Node new_node = new Node(data);
		new_node.next = prev_node.next;
		prev_node.next = new_node;
		count+=1;
	}
	
	public void append(int data){
		Node new_node = new Node(data);
		count+=1;
		if (head == null) {
			head = new_node;
			return;
		}
		Node cur = head;
		while(cur.next!=null) {
			cur = cur.next;
		}
		cur.next = new_node;
		
		
	}
	
	public void deleteValue(int data){
		Node tmp = head;
		Node prev = null;
		
		if (head != null && head.data == data){
			head = head.next;
			count-=1;
			return;
		}
		
		while(tmp!=null && tmp.data != data){
			prev = tmp;
			tmp = tmp.next;
		}
		
		if (tmp == null)
			return;
		
		prev.next = tmp.next;
		count-=1;
	}
	
	public boolean search(int data){
		Node tmp = head;
		while(tmp!=null){
			if (tmp.data == data)
				return true;
			tmp = tmp.next;
		}
		return false;
		
	}
	
	public int get(int position){
		if (head == null || position <0 || position > count-1){
			throw new RuntimeException("Out of bound");
		}
		Node tmp = head;
		for (int i = 0; i<count && tmp!=null; i++){
			
			if (i == position)
				break;
			tmp = tmp.next;
			
		}
		return tmp.data;
		
	}
	public void removeAt(int position){
		
		if (head == null)
			return;
		if (head != null && position ==0){
			head = head.next;
			count-=1;
			return;
		}
		
		Node tmp = head;
		Node prev = null;
		
		for (int i = 0; i<position && tmp!=null; i++){
			prev = tmp;
			tmp = tmp.next;
		}
		
		if (tmp == null)
			return;
		
		prev.next = tmp.next;
		count-=1;
	}
	
	
	
	public void printList()
    {
        Node tnode = head;
        while (tnode != null)
        {
            System.out.print(tnode.data+" ");
            tnode = tnode.next;
        }
    }
	
	public int length(){
		return count;
	}
	
	public void reverse(){
		if (head == null || head.next == null)
			return;
		
		Node cur = head;
		Node prev = null;
		Node next = null;
		while(cur!=null){
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		head = prev;
	}
	

}
