package string;

/**
 * Longest palindromic substring.
 *
 * @author Algorithms Collection
 */

import java.util.HashSet;

import java.util.LinkedHashMap;

public class LongestPalindromicSubstring {
  class EndObject {
    public int value;

    public EndObject() {
      value = -1;
    }

    public EndObject(int v) {
      value = v;
    }
  }

  class Node {

    LinkedHashMap<Character, Node> children;

    Node suffixLink;
    Integer start = null;
    EndObject end = null;
    int suffixIndex;

    HashSet<Integer> reverseIndices;
    HashSet<Integer> forwardIndices;

    public Node(int s, EndObject e) {
      suffixLink = root;
      start = s;
      end = e;
      suffixIndex = -1;
      children = new LinkedHashMap<>();
      reverseIndices = new HashSet<Integer>();
      forwardIndices = new HashSet<Integer>();
    }

    int edgeLength() {
      if (end.value == -1 && start == -1)
        return 0;
      return end.value - start + 1;
    }
  }

  String text;

  Node root = null;
  Node lastNewNode = null;
  Node activeNode = null;

  int activeEdge = -1;
  int activeLength = 0;

  int remainingSuffixCount = 0;
  EndObject leafEnd = new EndObject();
  EndObject rootEnd = null;
  EndObject splitEnd = null;
  int size = -1;
  int size1;
  int maxHeight;
  int substringStartIndex;

  int reverseIndex;

  public LongestPalindromicSubstring(String str) {
    text = str + "#" + new StringBuffer(str).reverse().toString() + "$";
    size = text.length();
    size1 = str.length() + 1;

  }

  private boolean walkDown(Node currNode) {
    if (activeLength >= currNode.edgeLength()) {
      activeEdge += currNode.edgeLength();
      activeLength -= currNode.edgeLength();
      activeNode = currNode;
      return true;
    }

    return false;
  }

  protected void extendSuffixTree(int pos) {

    leafEnd.value = pos;
    remainingSuffixCount++;
    lastNewNode = null;

    while (remainingSuffixCount > 0) {

      if (activeLength == 0) // APCFALZ
        activeEdge = pos;
      if (activeNode.children.containsKey(text.charAt(activeEdge)) == false) {
        activeNode.children.put(text.charAt(activeEdge), new Node(pos, leafEnd));

        if (lastNewNode != null) {
          lastNewNode.suffixLink = activeNode;
          lastNewNode = null;
        }
      } else {

        Node next = activeNode.children.get(text.charAt(activeEdge));
        if (walkDown(next)) {
          continue;
        }

        if (text.charAt(next.start + activeLength) == text.charAt(pos)) {
          if (lastNewNode != null && activeNode != root) {
            lastNewNode.suffixLink = activeNode;
            lastNewNode = null;
          }

          activeLength++;
          break;
        }

        splitEnd = new EndObject(next.start + activeLength - 1);
        Node split = new Node(next.start, splitEnd);
        activeNode.children.put(text.charAt(activeEdge), split);

        split.children.put(text.charAt(pos), new Node(pos, leafEnd));
        next.start += activeLength;
        split.children.put(text.charAt(next.start), next);

        if (lastNewNode != null) {
          lastNewNode.suffixLink = split;
        }

        lastNewNode = split;

      }

      remainingSuffixCount--;
      if (activeNode == root && activeLength > 0) // APCFER2C1
      {
        activeLength--;
        activeEdge = pos - remainingSuffixCount + 1;
      } else if (activeNode != root) // APCFER2C2
      {
        activeNode = activeNode.suffixLink;
      }

    }

  }

  private void print(int i, int j) {

    for (int k = i; k <= j; k++) {
      System.out.print(text.charAt(k));
    }
  }

  private void setSuffixIndexByDFS(Node n, int labelHeight) {
    if (n == null)
      return;
    if (n.start != -1) {
      // print(n.start, n.end.value);
    }

    int leaf = 1;

    for (Character key : n.children.keySet()) {

      if (leaf == 1 && n.start != -1) {
        // System.out.println(" ["+ n.suffixIndex +"]");
      }

      leaf = 0;

      setSuffixIndexByDFS(n.children.get(key), 
         labelHeight + n.children.get(key).edgeLength());
      if (n != root) {
        // Add chldren's suffix indices in parent
        for (int index : n.children.get(key).forwardIndices) {
          n.forwardIndices.add(index);
        }
        for (int index : n.children.get(key).reverseIndices) {
          n.reverseIndices.add(index);
        }

      }

    }

    if (leaf == 1) {

      for (int i = n.start; i <= n.end.value; i++)
        if (text.charAt(i) == '#')
          n.end = new EndObject(i);

      n.suffixIndex = size - labelHeight;
      if (n.suffixIndex < size1) {
        n.forwardIndices.add(n.suffixIndex);
      } else {
        n.reverseIndices.add(n.suffixIndex - size1);
      }
    }

  }

  public void buildSuffixTree() {
    size = text.length();

    rootEnd = new EndObject(-1);
    root = new Node(-1, rootEnd);
    activeNode = root;
    for (int i = 0; i < size; i++) {
      extendSuffixTree(i);
    }

    int labelHeight = 0;
    setSuffixIndexByDFS(root, labelHeight);

  }

  public String getLongestPalindromicSubstring() {
    maxHeight = 0;
    substringStartIndex = 0;
    doTraversal(root, 0);
    if (maxHeight == 0)
      return "";
    else
      return text.substring(substringStartIndex, substringStartIndex + maxHeight);

  }

  private void doTraversal(Node n, int labelHeight) {
    if (n == null)
      return;

    if (n.suffixIndex < 0) { // if it is internal node
      for (Character key : n.children.keySet()) {

        doTraversal(n.children.get(key), 
           labelHeight + n.children.get(key).edgeLength());
        if (maxHeight < labelHeight && n.forwardIndices.size() > 0 
           && n.reverseIndices.size() > 0) {
          for (Integer forwardIndex : n.forwardIndices) {
            reverseIndex = (size1 - 2) - (forwardIndex + labelHeight - 1);
            if (n.reverseIndices.contains(reverseIndex)) {
              maxHeight = labelHeight;
              substringStartIndex = n.end.value - labelHeight + 1;
              break;
            }

          }
        }

      }

    }

  }

}
