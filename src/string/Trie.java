package string;

/**
 * Trie (Prefix Tree) Data Structure.
 *
 * <p>An efficient tree-based data structure for storing and searching strings. Each node represents
 * a character, and paths from root to leaf represent complete words. Enables O(m) time complexity
 * for insertion and search operations where m is the string length.
 *
 * <p>Time Complexity:
 * - Insert: O(m) where m is the length of the word
 * - Search: O(m)
 * - Delete: O(m)
 *
 * <p>Space Complexity: O(ALPHABET_SIZE * N) where N is total characters across all words
 *
 * @author Algorithms Collection
 */
public class Trie {
  /** Size of the alphabet (lowercase English letters). */
  static final int ALPHABET_SIZE = 26;

  /**
   * Node class representing each character in the Trie.
   */
  class TrieNode {
    /** Children nodes for each character. */
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];
    /** Marks if this node represents the end of a word. */
    boolean isEndOfWord;

    /** Initializes a Trie node. */
    TrieNode() {
      isEndOfWord = false;
      for (int i = 0; i < ALPHABET_SIZE; i++)
        children[i] = null;
    }
  }

  /** Root node of the Trie. */
  TrieNode root = new TrieNode();

  /**
   * Inserts a word into the Trie.
   *
   * @param key the word to insert
   */
  public void insert(String key) {
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
