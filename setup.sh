#!/bin/bash

# Setup script for Java Algorithms Project
# Supports macOS environment setup and compilation

set -e

echo "========================================="
echo "Java Algorithms - Environment Setup"
echo "========================================="
echo ""

# Color codes for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if Java is installed
echo "Checking Java installation..."
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Java is not installed${NC}"
    echo ""
    echo "For macOS, install Java using Homebrew:"
    echo "  brew install openjdk@17"
    echo ""
    echo "Or download from: https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

# Get Java version
JAVA_VERSION=$(java -version 2>&1 | grep version | awk '{print $3}' | tr -d '"')
echo -e "${GREEN}✓ Java found: ${JAVA_VERSION}${NC}"
echo ""

# Create build directory
BUILD_DIR="build"
if [ ! -d "$BUILD_DIR" ]; then
    echo "Creating build directory..."
    mkdir -p "$BUILD_DIR"
fi

# Compile Java source files
echo "Compiling Java source files..."
echo ""

# Find all Java files and compile them
JAVA_FILES=$(find src -name "*.java" -type f)
if [ -z "$JAVA_FILES" ]; then
    echo -e "${RED}No Java files found in src directory${NC}"
    exit 1
fi

# Compile all Java files
javac -d "$BUILD_DIR" $JAVA_FILES 2>&1 | grep -E "error|warning" || true

if [ $? -eq 0 ] || [ -n "$(find "$BUILD_DIR" -name "*.class" 2>/dev/null)" ]; then
    echo -e "${GREEN}✓ Compilation successful${NC}"
else
    echo -e "${YELLOW}⚠ Some files may have compilation warnings${NC}"
fi

echo ""
echo "========================================="
echo "Setup Complete!"
echo "========================================="
echo ""
echo "Compiled files are in: ./${BUILD_DIR}"
echo ""
echo "To run a Java class:"
echo "  java -cp ./${BUILD_DIR} package.ClassName"
echo ""
echo "Example:"
echo "  java -cp ./${BUILD_DIR} sorts.QuickSort"
echo "  java -cp ./${BUILD_DIR} trees.BST"
echo ""
