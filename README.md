# Java Algorithms

A comprehensive collection of classic algorithms and data structures implemented in Java. Most ideas come from MIT course 6.006 and GeeksforGeeks.

## Table of Contents

- [Quick Start](#quick-start)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Project Structure](#project-structure)
- [Algorithms Implemented](#algorithms-implemented)
- [Troubleshooting](#troubleshooting)
- [References](#references)

## Quick Start

### Setup (One Command)

```bash
./setup.sh
```

This will:
- Check Java installation
- Compile all source files
- Create `build` directory with `.class` files

### Run Examples

```bash
# Using direct Java command
java -cp ./build trees.TestTree

# Using run script
./run.sh DP.Knapsack

# Using Make
make test
```

---

## Installation & Setup

### Requirements

- **macOS, Linux, or Windows** (with Git Bash)
- **Java 8+** (Java 11, 17, 21 recommended)
- **Make** (optional, for convenience)

### Install Java

If Java is not installed:

**macOS (Homebrew - Recommended):**
```bash
brew install openjdk@17
```

**Other Platforms:**
Download from: https://www.oracle.com/java/technologies/downloads/

**Verify installation:**
```bash
java -version
javac -version
```

### Method 1: Quick Setup (Recommended)

```bash
chmod +x setup.sh
./setup.sh
```

This will:
- Check Java installation
- Compile all source files  
- Create `build` directory with `.class` files

### Method 2: Using Make

```bash
make setup    # Setup and compile
make compile  # Recompile only
make test     # Run all tests
make clean    # Clean build files
make help     # Show all commands
```

### Method 3: Manual Compilation

```bash
mkdir -p build
javac -d build $(find src -name "*.java")
```

### Verify Installation

```bash
java -cp ./build trees.TestTree
```

---

## How to Run

### Quick Test

```bash
make test
```

This runs all test cases and shows a summary.

### Run Test Classes

Most packages have test classes:

```bash
java -cp ./build trees.TestTree
java -cp ./build trees.TestHeap
java -cp ./build Hash.TestHash
java -cp ./build List.TestList
```

### Run Your Own Code

Create a Java file in `src/` with a `main()` method:

```java
package mypackage;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
```

Compile and run:

```bash
./setup.sh
java -cp ./build mypackage.MyClass
```

---

## Project Structure

```
/
├── src/                      # Source code
│   ├── DP/                   # Dynamic Programming
│   ├── graph/                # Graph algorithms
│   ├── trees/                # Tree data structures
│   ├── sorts/                # Sorting algorithms
│   ├── Hash/                 # Hash tables
│   ├── string/               # String algorithms
│   ├── List/                 # Linked lists
│   └── Optimization/         # Optimization algorithms
├── build/                    # Compiled .class files (auto-generated)
├── data/                     # Test data files
├── setup.sh                  # Setup script
├── run.sh                    # Run script
├── test.sh                   # Test runner script
├── Makefile                  # Build automation
├── .gitignore                # Git ignore rules
├── .github/workflows/        # GitHub Actions CI/CD
└── README.md                 # This file
```

---

## Algorithms Implemented

### Data Structures

#### Trees
- **BST** - Binary Search Tree
- **AVL** - Self-balancing binary search tree
- **Heap** - Generic heap implementation
- **MaxHeap** - Maximum heap
- **MinHeap** - Minimum heap
- **SegmentTree** - Segment tree for range queries
- **VerticalOrderBtree** - Vertical order traversal

#### Lists
- **LinkedList** - Singly linked list

#### Hash
- **Cuckoo** - Cuckoo hashing
- **ModInverse** - Modular inverse
- **PalindromeSubstringQueries** - Palindrome queries
- **SmallestRangeKList** - Smallest range from K lists
- **SubarrayDistinctElements** - Subarray with distinct elements
- **Largestsubarray0Sum** - Largest subarray with 0 sum

### Algorithms

#### Sorting
- **QuickSort** - Quicksort implementation
- **MergeSort** - Mergesort implementation

#### Graph Algorithms
- **Dijkstra** - Shortest path (non-negative weights)
- **BellmanFord** - Shortest path (negative weights)
- **Kruskal** - Minimum spanning tree
- **StrongConnectedComponents** - SCC algorithm
- **EulerianPath** - Eulerian path detection

#### String Algorithms
- **SuffixTree** - Suffix tree data structure
- **GeneralizedSuffixTree** - Generalized suffix tree
- **LongestCommonSubstring** - LCS algorithm
- **LongestPalindromicSubstring** - Longest palindrome
- **LongestRepeatedSubstring** - LRS algorithm
- **SearchingAllPatterns** - Pattern matching
- **Trie** - Trie data structure

#### Dynamic Programming
- **Knapsack** - 0/1 Knapsack problem
- **LCS** - Longest Common Subsequence
- **LargestSumContiguousSubarray** - Kadane's algorithm
- **SubsetSum** - Subset sum problem
- **JobScheduling** - Job scheduling with weights
- **TextJustification** - Text justification
- **GoSightSeeing** - Sightseeing problem
- **BlackJack** - Blackjack game DP

#### Optimization
- **MaxFlow** - Maximum flow algorithms
- **MinCostMaxFlow** - Minimum cost maximum flow
- **DinicBlockMaxFlow** - Dinic's algorithm
- **PushRelabelMaximumFlow** - Push-relabel algorithm
- **MinCostMatching** - Minimum cost matching
- **GlobalMinCut** - Global minimum cut

---

## Troubleshooting

### Error: "Java not found"

**Solution:** Install Java using Homebrew (macOS)

```bash
brew install openjdk@17
```

Or download from: https://www.oracle.com/java/technologies/downloads/

### Error: "Class not found"

**Solution:** Make sure you've run setup first

```bash
./setup.sh
java -cp ./build package.ClassName
```

### Error: "Compilation error"

**Solution:** Check your Java version and try recompiling

```bash
java -version
make clean
./setup.sh
```

### Mixed indentation issues

**Solution:** Check your code indentation manually

### Pre-commit hook not working

**Solution:** Install the hook properly

```bash
cp .git-hooks/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
```

---

## Tips & Best Practices

1. **Always run `./setup.sh` first** - This compiles all files
2. **Use the `make` commands** - Easier than typing full Java commands
3. **Check test classes** - They show how to use each implementation
4. **Add to `.gitignore`** - `build/` directory is already ignored
5. **Java 11+** - Recommended for better performance and features

---

## Contributing

Feel free to add more algorithms or improve existing implementations:

1. Create a new `.java` file in the appropriate `src/` subdirectory
2. Include a `main()` method for testing
3. Run `make test` to verify
4. Commit your changes

---

## References

- [MIT OpenCourseWare 6.006](https://ocw.mit.edu/courses/6-006-introduction-to-algorithms/)
- [GeeksforGeeks](https://www.geeksforgeeks.org/)
- [Java Documentation](https://docs.oracle.com/en/java/)

---

## License

This project is for educational purposes. Feel free to use and modify as needed.

---

## Summary of Make Commands

```bash
make help          # Show all available commands
make setup         # Setup environment and compile
make compile       # Compile Java files
make test          # Run all tests
make clean         # Remove build directory
```

Happy learning! 🎉

## Quick Start

### Setup (One Command)

```bash
./setup.sh
```

This will:
- Check Java installation
- Compile all source files
- Create `build` directory with `.class` files

### Run Examples

```bash
# Using direct Java command
java -cp ./build trees.TestTree

# Using run script
./run.sh DP.Knapsack

# Using Make
make setup && java -cp ./build graph.BellmanFord
```

---

## Installation & Setup

### Requirements

- **macOS, Linux, or Windows** (with Git Bash)
- **Java 8+** (Java 11, 17, 21 recommended)
- **Make** (optional, for convenience)

### Install Java

If Java is not installed:

**macOS (Homebrew - Recommended):**
```bash
brew install openjdk@17
```

**Other Platforms:**
Download from: https://www.oracle.com/java/technologies/downloads/

**Verify installation:**
```bash
java -version
javac -version
```

### Method 1: Quick Setup (Recommended)

```bash
chmod +x setup.sh
./setup.sh
```

This will:
- Check Java installation
- Compile all source files  
- Create `build` directory with `.class` files

### Method 2: Using Make

```bash
make setup    # Setup and compile
make compile  # Recompile only
make test     # Run all tests
make clean    # Clean build files
make help     # Show all commands
```

### Method 3: Manual Compilation

```bash
mkdir -p build
javac -d build $(find src -name "*.java")
```

### Verify Installation

```bash
java -cp ./build trees.TestTree
```

---

## How to Run

### Quick Test

```bash
make test
```

This runs all test cases and shows a summary.

### Run Test Classes

Most packages have test classes:

```bash
java -cp ./build trees.TestTree
java -cp ./build trees.TestHeap
java -cp ./build Hash.TestHash
java -cp ./build List.TestList
```

### Run Your Own Code

Create a Java file in `src/` with a `main()` method:

```java
package mypackage;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello!");
    }
}
```

Compile and run:

```bash
./setup.sh
java -cp ./build mypackage.MyClass
```

---

## Project Structure

```
/
├── src/                      # Source code
│   ├── DP/                   # Dynamic Programming
│   │   ├── Knapsack.java
│   │   ├── LCS.java
│   │   ├── JobScheduling.java
│   │   └── ...
│   ├── graph/                # Graph algorithms
│   │   ├── Dijkstra.java
│   │   ├── BellmanFord.java
│   │   ├── Kruskal.java
│   │   └── ...
│   ├── trees/                # Tree data structures
│   │   ├── BST.java
│   │   ├── AVL.java
│   │   ├── Heap.java
│   │   ├── SegmentTree.java
│   │   └── ...
│   ├── sorts/                # Sorting algorithms
│   │   ├── QuickSort.java
│   │   ├── MergeSort.java
│   │   └── ...
│   ├── Hash/                 # Hash tables
│   ├── string/               # String algorithms
│   ├── List/                 # Linked lists
│   └── Optimization/         # Optimization algorithms
├── build/                    # Compiled .class files (auto-generated)
├── data/                     # Test data files
├── setup.sh                  # Setup script
├── run.sh                    # Run script
├── test.sh                   # Test runner script
├── Makefile                  # Build automation
└── README.md                 # This file
```

---

## Algorithms Implemented

### Data Structures

#### Trees
- **BST** - Binary Search Tree
- **AVL** - Self-balancing binary search tree
- **Heap** - Generic heap implementation
- **MaxHeap** - Maximum heap
- **MinHeap** - Minimum heap
- **SegmentTree** - Segment tree for range queries
- **VerticalOrderBtree** - Vertical order traversal

#### Lists
- **LinkedList** - Singly linked list

#### Hash
- **Cuckoo** - Cuckoo hashing
- **ModInverse** - Modular inverse
- **PalindromeSubstringQueries** - Palindrome queries
- **SmallestRangeKList** - Smallest range from K lists
- **SubarrayDistinctElements** - Subarray with distinct elements
- **Largestsubarray0Sum** - Largest subarray with 0 sum

### Algorithms

#### Sorting
- **QuickSort** - Quicksort implementation
- **MergeSort** - Mergesort implementation

#### Graph Algorithms
- **Dijkstra** - Shortest path (non-negative weights)
- **BellmanFord** - Shortest path (negative weights)
- **Kruskal** - Minimum spanning tree
- **StrongConnectedComponents** - SCC algorithm
- **EulerianPath** - Eulerian path detection

#### String Algorithms
- **SuffixTree** - Suffix tree data structure
- **GeneralizedSuffixTree** - Generalized suffix tree
- **LongestCommonSubstring** - LCS algorithm
- **LongestPalindromicSubstring** - Longest palindrome
- **LongestRepeatedSubstring** - LRS algorithm
- **SearchingAllPatterns** - Pattern matching
- **Trie** - Trie data structure

#### Dynamic Programming
- **Knapsack** - 0/1 Knapsack problem
- **LCS** - Longest Common Subsequence
- **LargestSumContiguousSubarray** - Kadane's algorithm
- **SubsetSum** - Subset sum problem
- **JobScheduling** - Job scheduling with weights
- **TextJustification** - Text justification
- **GoSightSeeing** - Sightseeing problem
- **BlackJack** - Blackjack game DP

#### Optimization
- **MaxFlow** - Maximum flow algorithms
- **MinCostMaxFlow** - Minimum cost maximum flow
- **DinicBlockMaxFlow** - Dinic's algorithm
- **PushRelabelMaximumFlow** - Push-relabel algorithm
- **MinCostMatching** - Minimum cost matching
- **GlobalMinCut** - Global minimum cut

---

## Usage Examples

### Example 1: Run Tree Tests

```bash
./setup.sh
java -cp ./build trees.TestTree
```

Output shows BST and AVL tree operations with height comparison.

### Example 2: Run Hash Tests

```bash
java -cp ./build Hash.TestHash
```

### Example 3: Run List Tests

```bash
java -cp ./build List.TestList
```

### Example 4: Using run.sh Script

```bash
./run.sh DP.Knapsack
./run.sh trees.BST
./run.sh sorts.QuickSort
```

---

## Troubleshooting

### Error: "Java not found"

**Solution:** Install Java using Homebrew (macOS)

```bash
brew install openjdk@17
```

Or download from: https://www.oracle.com/java/technologies/downloads/

### Error: "Class not found"

**Solution:** Make sure you've run setup first

```bash
./setup.sh
java -cp ./build package.ClassName
```

### Error: "Compilation error"

**Solution:** Check your Java version and try recompiling

```bash
java -version
make clean
./setup.sh
```

---

## Tips & Best Practices

1. **Always run `./setup.sh` first** - This compiles all files
2. **Use the `run.sh` script** - Easier than typing full Java commands
3. **Check test classes** - They show how to use each implementation
4. **Add to `.gitignore`** - Add `build/` directory to version control ignore
5. **Java 11+** - Recommended for better performance and features

---

## Contributing

Feel free to add more algorithms or improve existing implementations:

1. Create a new `.java` file in the appropriate `src/` subdirectory
2. Include a `main()` method for testing
3. Run `./setup.sh` to compile
4. Test your implementation

---

## References

- MIT OpenCourseWare 6.006: https://ocw.mit.edu/courses/6-006-introduction-to-algorithms/
- GeeksforGeeks: https://www.geeksforgeeks.org/
- Java Documentation: https://docs.oracle.com/en/java/

---

## License

This project is for educational purposes. Feel free to use and modify as needed.

---

## Need Help?

1. Check the **Installation & Setup** section above for setup instructions
2. Review **How to Run** section for examples
3. Check test classes in each package for usage examples
4. Review source code comments for algorithm explanations

Happy learning! 🎉

Algorithms:
+ Tree

    +AVL
	
    +BST
	
    +Heap
	
    +MaxHeap
	
    +MinHeap
	
    +SegmentTree
	
    +Tree
	
    +VerticalOrderBtree
	
+ LinkedList

    +LinkedList
	
+ Sorts

    +MergeSort
	
    +QuickSort
	
+ Hash

    +Cuckoo
	
    +itinerary
	
    +Largestsubarray0Sum
	
    +ModInverse
	
    +PalindromeSubstringQueries
	
    +SmallestRangeKList
	
    +SubarrayDistinctElements
	
+ Graph

    +BellmanFord
	
    +EulerianPath
	
    +FastDijkstras
	
    +Kruskal
	
    +StrongConnectedConponents
	
+ Optimization

    +DinicBlockMaxFlow
	
    +GlobalMinCut
	
    +MinCostMatching
	
    +MinCostMaxFlow
	
    +PushRelabelMaximumFlow
	
+ String

    +GeneralizedSuffixTree
	
    +kasai
	
    +LongestCommonSubstring
	
    +LongestPalindromicSubstring
	
    +LongestRepeatedSubstring
	
    +SearchingAllPatterns
	
    +SubstringCheck
	
    +SuffixTree
	
    +TestSuffixTree
	
    +Trie
	
+ DP:

    +BlackJack
	
    +GoSightSeeing
	
    +JobScheduling
	
    +Knapsack
	
    +Ksum
	
    +LargestSumContiguousSubarray
	
    +LCS
	
    +SubsetSum
	
    +TextJustification
	
    +VertexCoverTree
	
