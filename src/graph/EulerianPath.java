package graph;

import java.util.ArrayList;

class Node<T>{
	T data;
	Node<T> next;
	Node<T> prev;
	public Node(T d){
		data = d;
		next = null;	
		prev = null;
	}
}

class LinkedList<T> {
	
	Node<T> head;
	int count;
	
	public LinkedList(){
		head = null;
		count = 0;
	}
	
	public Node<T> addFirst(T data){
		Node<T> new_node = new Node<T>(data);
		count+=1;
		if (head == null){
			head = new_node;
			return head;
		}	
		new_node.next = head;
		head.prev = new_node;
		
		head = new_node;
		
		return head;
	}
	
	public void removeFirst(){
		if (head==null)
			return;
		
		head = head.next;
		
		if (head !=null)
			head.prev = null;
		count-=1;
			
	}
	public void remove(Node<T> atNode){
		if (atNode == null){
			return;
		}
		
		if (atNode ==  head){
			head = head.next;
			if (head !=null)
				head.prev = null;
			
			count -= 1;
			return;
		}
		
			
		atNode.prev.next = atNode.next;
		
		if (atNode.next!=null)
			atNode.next.prev = atNode;
		
		count-=1;
	}
	
	
	public Node<T> getFirst(){
		return head;
	}
	
	public int length(){
		return count;
	}
	
}

public class EulerianPath {
	private class Edge{
		int next_vertex;
		Node<Edge> reverse_edge;
		public Edge(int n_ver){
			next_vertex = n_ver;
		}
	}
	
	// should be setup in constructor
	int num_vertices;
	LinkedList<Edge>[] adj;
	
	ArrayList<Integer> path;
	
	
	public EulerianPath(int num_ver){
		num_vertices = num_ver;
		adj = new LinkedList[num_vertices];
		
		for (int i = 0 ; i< num_vertices; i++)
			adj[i] =  new LinkedList<>();
		path = new ArrayList<>();
	}
	void find_path(int v){
		
		while(adj[v].length()> 0){
			
			Node<Edge> node = adj[v].getFirst();
			Edge e = node.data;
			int vn = e.next_vertex;
			adj[vn].remove(node.data.reverse_edge);
        	adj[v].removeFirst();

        	find_path(vn);
		}
		
		path.add(v);
	}
	
	void add_edge(int a, int b){
		Node<Edge> ita = adj[a].addFirst(new Edge(b));
		
		Node<Edge> itb = adj[b].addFirst(new Edge(a));
		
		ita.data.reverse_edge = itb;
		itb.data.reverse_edge = ita;		
	}
	
     
	
	public static void main(String[] args)
    {
		EulerianPath euler = new EulerianPath(6);
		euler.add_edge(0, 1);
		euler.add_edge(0, 2);
		euler.add_edge(0, 4);
		euler.add_edge(1, 2);
		euler.add_edge(1, 3);
		euler.add_edge(2, 3);
		euler.add_edge(2, 5);
		euler.add_edge(3, 1);
		euler.add_edge(3, 5);
		euler.add_edge(4, 5);
		
		euler.find_path(0);
		System.out.println("Path = " + euler.path);
		
		
    }
}
