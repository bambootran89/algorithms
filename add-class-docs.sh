#!/bin/bash
# Add class-level JavaDoc to files

SRC_DIR="src"
DOCSTRINGS_ADDED=0

echo "Adding class-level documentation..."
echo ""

# Array of files to process
FILES=(
    "src/trees/Tree.java:Tree interface for tree data structures."
    "src/trees/MinHeap.java:Min Heap implementation."
    "src/trees/MaxHeap.java:Max Heap implementation."
    "src/trees/SegmentTree.java:Segment Tree for range queries."
    "src/trees/VerticalOrderBtree.java:Vertical order traversal of binary tree."
    "src/trees/TestTree.java:Test suite for tree data structures."
    "src/trees/TestHeap.java:Test suite for heap implementations."
    "src/trees/TestDataStructure.java:Test suite for basic data structures."
    "src/trees/AVL.java:Self-balancing AVL Tree implementation."
    "src/sorts/MergeSort.java:Merge Sort algorithm implementation."
    "src/sorts/QuickSort.java:Quick Sort algorithm implementation."
    "src/graph/EulerianPath.java:Eulerian path in a graph."
    "src/graph/FastDijkstras.java:Dijkstra's shortest path algorithm."
    "src/graph/Kruskal.java:Kruskal's Minimum Spanning Tree algorithm."
    "src/graph/StrongConnectedConponents.java:Strongly Connected Components algorithm."
    "src/DP/BlackJack.java:BlackJack game strategy using DP."
    "src/DP/LargestSumContiguousSubarray.java:Largest sum contiguous subarray."
    "src/DP/JobScheduling.java:Job scheduling with profit maximization."
    "src/DP/GoSightSeeing.java:Sightseeing tour optimization."
    "src/DP/Ksum.java:K-sum problem variants."
    "src/DP/SubsetSum.java:Subset sum problem."
    "src/DP/TextJustification.java:Text justification problem."
    "src/DP/VertexCoverTree.java:Vertex cover on tree using DP."
    "src/DP/LCS.java:Longest Common Subsequence problem."
    "src/Hash/itinerary.java:Airline itinerary reconstruction."
    "src/Hash/PalindromeSubstringQueries.java:Palindrome substring queries."
    "src/Hash/SmallestRangeKList.java:Smallest range from k lists."
    "src/Hash/ModInverse.java:Modular multiplicative inverse."
    "src/Hash/TestHash.java:Test suite for hash tables."
    "src/Hash/Cuckoo.java:Cuckoo Hash Table implementation."
    "src/Hash/SubarrayDistinctElements.java:Subarray with distinct elements."
    "src/Hash/Largestsubarray0Sum.java:Largest subarray with 0 sum."
    "src/Optimization/DinicBlockMaxFlow.java:Dinic maximum flow algorithm."
    "src/Optimization/PushRelabelMaximumFlow.java:Push-relabel maximum flow."
    "src/Optimization/MinCostMaxFlow.java:Minimum cost maximum flow."
    "src/Optimization/MinCostMatching.java:Minimum cost matching."
    "src/Optimization/GlobalMinCut.java:Global minimum cut algorithm."
    "src/string/SubstringCheck.java:Check if substring exists."
    "src/string/LongestCommonSubstring.java:Longest common substring."
    "src/string/LongestPalindromicSubstring.java:Longest palindromic substring."
    "src/string/kasai.java:Kasai LCP array algorithm."
    "src/string/SuffixTree.java:Suffix Tree for pattern matching."
    "src/string/SearchingAllPatterns.java:Search all pattern occurrences."
    "src/string/GeneralizedSuffixTree.java:Generalized Suffix Tree."
    "src/string/TestSuffixTree.java:Test suite for suffix trees."
    "src/string/LongestRepeatedSubstring.java:Longest repeated substring."
    "src/List/LinkedList.java:Linked List implementation."
    "src/List/TestList.java:Test suite for lists."
)

process_file() {
    local file=$1
    local description=$2
    
    if [ ! -f "$file" ]; then
        return
    fi
    
    # Check if already has JavaDoc
    if head -5 "$file" | grep -q "^/\*\*"; then
        return
    fi
    
    # Create JavaDoc
    local javadoc="/**
 * $description
 *
 * @author Algorithms Collection
 */
"
    
    # Create temp file with javadoc + original
    {
        head -1 "$file"
        echo "$javadoc"
        tail -n +2 "$file"
    } > "${file}.tmp"
    
    mv "${file}.tmp" "$file"
    echo "✓ $file"
    ((DOCSTRINGS_ADDED++))
}

# Process all files
for item in "${FILES[@]}"; do
    IFS=':' read -r file desc <<< "$item"
    process_file "$file" "$desc"
done

echo ""
echo "Added documentation to $DOCSTRINGS_ADDED files"
