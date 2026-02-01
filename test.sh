#!/bin/bash

# Test runner for Java Algorithms
# Runs all available test classes via the master TestRunner

set -e

# Color codes
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

BUILD_DIR="build"

echo -e "${BLUE}"
echo "========================================="
echo "Java Algorithms - Test Suite"
echo "========================================="
echo -e "${NC}"
echo ""

# Check if build directory exists
if [ ! -d "$BUILD_DIR" ]; then
    echo -e "${RED}❌ Build directory not found!${NC}"
    echo "Running setup first..."
    ./setup.sh
    echo ""
fi

# Run the master TestRunner
echo -e "${YELLOW}Executing Master Test Runner...${NC}"
echo ""

if java -cp "./$BUILD_DIR" tests.TestRunner > /tmp/test_output.txt 2>&1; then
    cat /tmp/test_output.txt
    EXIT_CODE=0
else
    cat /tmp/test_output.txt
    EXIT_CODE=1
fi

exit $EXIT_CODE
