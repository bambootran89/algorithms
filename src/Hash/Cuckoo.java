package Hash;

import java.util.ArrayList;
import java.util.Random;

public class Cuckoo {
	
	int MAXN = 11;
	int ver = 2;
	int[][] hashtable = new int[ver][MAXN];
	int pos[] = new int[ver];
	
	public Cuckoo(){
		initTable(hashtable);
	}
	
	private void rehashing(){
		int old_size = MAXN;
		int[][] old_hashtable = hashtable;
		int z = 2;
		while(true){
			MAXN =old_size * z;
			int[][] new_hashtable =  new int[ver][MAXN];
			int success = -1;
			initTable(new_hashtable);
			hashtable = new_hashtable;
			for (int i =0; i<old_size; i++)
				for (int j = 0; j<ver; j++){
					int key = old_hashtable[j][i];
					if (key !=Integer.MIN_VALUE){
						success = place(key, 0, 0,false);
					}
				}
			if (success < 0)
				z++;
			else
				break;
		}
	
		
	}
	
	private void initTable(int[][] hasht){
		for (int j=0; j<MAXN; j++)
	        for (int i=0; i<ver; i++)
	        	hasht[i][j] = Integer.MIN_VALUE;
	}
	
	private int hash(int function, int key){
		switch(function){
			case 1: return key%MAXN;
			case 2: return (key/MAXN)%MAXN;
			default:
				throw new RuntimeException("wrong function");
		}
	}
	
	private int place(int key, int tableID, int cnt, boolean isaccept_rehashing){
		
		if(cnt >= MAXN){
			System.out.println(key + " unpositioned");
			System.out.println("Cycle present. rehashing");
			if (isaccept_rehashing){
				rehashing();
				place(key, 0, 0, true);
				return 0;
			}else
				return -1;
		}
		
		for(int i =0 ; i< ver; i++){
			pos[i] = hash(i+1, key);
			if(hashtable[i][pos[i]] == key) {
				return 0;
			}
		}
		
		if(hashtable[tableID][pos[tableID]] !=Integer.MIN_VALUE){
			int dis = hashtable[tableID][pos[tableID]];
			hashtable[tableID][pos[tableID]] = key;
			place(dis, (tableID+1)%ver, cnt+1,true);
		}else
			hashtable[tableID][pos[tableID]] = key;
		
		return 0;
	}
	
	public void insert(int key){
		place(key, 0, 0,true);
	}
	
	public void delete(int key){
		for (int i=0; i<ver; i++)
	    {
	        pos[i] = hash(i+1, key);
	        if (hashtable[i][pos[i]] == key)
	        	hashtable[i][pos[i]] = Integer.MIN_VALUE;
	    }
		
	}
	
	public int get(int key){
		for (int i=0; i<ver; i++)
	    {
	        pos[i] = hash(i+1, key);
	        if (hashtable[i][pos[i]] == key)
	        	return hashtable[i][pos[i]];
	        	
	    }
		throw new RuntimeException("Don't have key is " + key);
		
	}
	
	public void printTable()
	{
	    System.out.println("Final hash tables: ");
	 
	    for (int i=0; i<ver; i++){
	        for (int j=0; j<MAXN; j++)
	            if (hashtable[i][j]==Integer.MIN_VALUE)
	            	System.out.println("- ");
	            else 
	            	System.out.println(hashtable[i][j]);
	        System.out.println("----");
	    }
	    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cuckoo cuckoo = new Cuckoo();
		Random rand = new Random();
		java.util.List<Integer> list = new ArrayList<Integer>();
		for (int i =0; i<3000; i++){
			int num = rand.nextInt(1000);
			list.add(num);
			cuckoo.insert(num);
		}
		
		
		System.out.println("list = " + list);
		cuckoo.printTable();
		
		for (Integer i:list){
			System.out.println(i+ " was hashed: " + (cuckoo.get(i) == i));
		}
	}

}
