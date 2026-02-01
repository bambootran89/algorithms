#!/bin/bash

# Run script for Java Algorithms Project
# Usage: ./run.sh ClassName [arguments]

# Color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

BUILD_DIR="build"

# Check if build directory exists
if [ ! -d "$BUILD_DIR" ]; then
    echo -e "${RED}❌ Build directory not found!${NC}"
    echo "Please run: ./setup.sh"
    exit 1
fi

# Check if class name is provided
if [ -z "$1" ]; then
    echo -e "${RED}Usage: $0 ClassName${NC}"
    echo ""
    echo "Examples:"
    echo "  $0 sorts.QuickSort"
    echo "  $0 trees.BST"
    echo "  $0 graph.Dijkstra"
    echo ""
    echo "Available classes:"
    find "$BUILD_DIR" -name "*.class" | sed 's|'$BUILD_DIR'/||g; s|/|.|g; s|\.class||g' | sort
    exit 1
fi

# Run the Java class
echo -e "${YELLOW}Running: $1${NC}"
echo ""
java -cp "./$BUILD_DIR" "$@"
