package string;

public class Trie {
	static final int ALPHABET_SIZE =  26;
	class TrieNode{
		TrieNode[] children = new TrieNode[ALPHABET_SIZE];
		boolean isEndOfWord;
		TrieNode(){
			isEndOfWord = false;
			for (int i =0; i< ALPHABET_SIZE; i++)
				children[i] = null;
		}
		
	}
	
	TrieNode root = new TrieNode();
	
	public void insert(String key){
		int level;
		int length = key.length();
		int index;
		
		TrieNode pCrawl = root;
		for (level = 0; level < length; level++){
			index = key.charAt(level) - 'a';
			if(pCrawl.children[index] == null){
				pCrawl.children[index] = new TrieNode();
			}
			pCrawl = pCrawl.children[index];
		}
		pCrawl.isEndOfWord = true;
		
	}
	
	public boolean search(String key){
		int level;
		int length = key.length();
		int index;
		TrieNode pCrawl = root;
		
		for (level =0; level< length; level++){
			index = key.charAt(level) -'a';
			if (pCrawl.children[index] ==null)
				return false;
			pCrawl = pCrawl.children[index];
		}
		
		return (pCrawl!=null && pCrawl.isEndOfWord);
		
	}
	
	private boolean isItFreeNode(TrieNode node){
		for(int i = 0; i< ALPHABET_SIZE; i++)
			if (node.children[i] !=null)
				return false;
		return true;
	}
	
	private boolean deleteHelper(TrieNode node, String key, int level, int len){
		if(node!=null){
			if(level == len){
				if (node.isEndOfWord == true){
					node.isEndOfWord = false;
					return isItFreeNode(node);
					
				}
			}else{
				int index = key.charAt(level) - 'a';
				if(deleteHelper(node.children[index], key, level+1, len)){
					node.children[index] = null;
					
					return node.isEndOfWord == false && isItFreeNode(node);
				}
			}
			
		}
		
		return false;
	}
	
	void delete( String key){
		int len = key.length();
		if (len  > 0){
			deleteHelper(root, key, 0, len);
		}
	}
	public static void main(String[] args) {
		Trie trie = new Trie();
		String[] keys = {"she", "sells", "sea", "shore", "the", "by", "sheer"};
		for(int i = 0; i < keys.length; i++)
	    {
	        trie.insert(keys[i]);
	    }
		
		System.out.println("Is string " + keys[1] + " in trie? " + trie.search(keys[1]) );
		
		System.out.println("Is string " + "cuong" + " in trie? " + trie.search("cuong") );
		
		trie.delete(keys[1]);
		
		System.out.println("Is string " + keys[1] + " in trie? " + trie.search(keys[1]) );
	}
}
