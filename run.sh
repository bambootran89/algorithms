#!/bin/bash

# Run script for Java Algorithms Project
# Usage: ./run.sh ClassName [arguments]

BUILD_DIR="build"

# Check if build directory exists
if [ ! -d "$BUILD_DIR" ]; then
    echo "Build directory not found. Please run ./setup.sh"
    exit 1
fi

# Check if class name is provided
if [ -z "$1" ]; then
    echo "Usage: $0 ClassName [arguments]"
    echo ""
    echo "Examples:"
    echo "  $0 sorts.QuickSort"
    echo "  $0 trees.BST"
    echo ""
    echo "Available classes:"
    find "$BUILD_DIR" -name "*.class" | sed 's|'$BUILD_DIR'/||g; s|/|.|g; s|\.class||g' | sort
    exit 1
fi

# Run the Java class
echo "Running: $1"
java -cp "./$BUILD_DIR" "$@"
