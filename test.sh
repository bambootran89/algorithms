#!/bin/bash

# Test runner

set -e

BUILD_DIR="build"

if [ ! -d "$BUILD_DIR" ]; then
    echo "Build directory not found. Running setup..."
    ./setup.sh
fi

echo "Running tests..."

TEST_OUT="test_output.txt"
if java -cp "./$BUILD_DIR" tests.TestRunner > "$TEST_OUT" 2>&1; then
    cat "$TEST_OUT"
    EXIT_CODE=0
else
    cat "$TEST_OUT"
    EXIT_CODE=1
fi

rm -f "$TEST_OUT"
exit $EXIT_CODE
