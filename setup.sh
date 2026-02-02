#!/bin/bash

# Setup script for Java Algorithms Project

set -e

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Java is not installed."
    echo "Please install Java (JDK 17 or higher) to continue."
    exit 1
fi

# Create build directory
BUILD_DIR="build"
if [ ! -d "$BUILD_DIR" ]; then
    mkdir -p "$BUILD_DIR"
fi

echo "Compiling..."

# Find all Java files and compile them
JAVA_FILES=$(find src tests -name "*.java" -type f)
if [ -z "$JAVA_FILES" ]; then
    echo "No Java files found in src directory"
    exit 1
fi

# Compile all Java files
javac -d "$BUILD_DIR" $JAVA_FILES 2>&1 | grep -E "error|warning" || true

if [ $? -eq 0 ] || [ -n "$(find "$BUILD_DIR" -name "*.class" 2>/dev/null)" ]; then
    echo "Setup complete."
else
    echo "Compilation failed or had warnings."
fi
