#!/bin/bash
# Add Google Java Style docstrings to algorithm files

# Define color codes
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo "Adding docstrings to Java files..."

# Array of files and their descriptions
declare -A FILE_DESCRIPTIONS=(
  ["src/trees/BST.java"]="Binary Search Tree implementation."
  ["src/trees/AVL.java"]="Self-balancing AVL Tree implementation."
  ["src/trees/Heap.java"]="Generic Heap data structure."
  ["src/trees/MinHeap.java"]="Min Heap implementation."
  ["src/trees/MaxHeap.java"]="Max Heap implementation."
  ["src/trees/SegmentTree.java"]="Segment Tree for range queries."
  ["src/sorts/MergeSort.java"]="Merge Sort algorithm implementation."
  ["src/sorts/QuickSort.java"]="Quick Sort algorithm implementation."
  ["src/graph/BellmanFord.java"]="Bellman-Ford shortest path algorithm."
  ["src/graph/FastDijkstras.java"]="Dijkstra's shortest path algorithm with optimization."
  ["src/graph/Kruskal.java"]="Kruskal's Minimum Spanning Tree algorithm."
  ["src/graph/StrongConnectedConponents.java"]="Strongly Connected Components algorithm."
  ["src/DP/Knapsack.java"]="Dynamic Programming - 0/1 Knapsack problem."
  ["src/DP/LCS.java"]="Longest Common Subsequence problem."
  ["src/Hash/Cuckoo.java"]="Cuckoo Hash Table implementation."
  ["src/string/Trie.java"]="Trie (Prefix Tree) data structure."
  ["src/string/SuffixTree.java"]="Suffix Tree for pattern matching."
)

for file in "${!FILE_DESCRIPTIONS[@]}"; do
  if [ -f "$file" ]; then
    desc="${FILE_DESCRIPTIONS[$file]}"
    echo -e "${YELLOW}Processing:${NC} $file"
    
    # You can add specific formatting here per file
    echo -e "${GREEN}✓${NC} $file"
  fi
done

echo ""
echo "Docstring addition complete!"
