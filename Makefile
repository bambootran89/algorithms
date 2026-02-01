BUILD_DIR := build
SRC_DIR := src
TESTS_DIR := tests
JAVA_FILES := $(shell find $(SRC_DIR) -name "*.java") $(shell find $(TESTS_DIR) -name "*.java")

.PHONY: setup compile test clean style help

help:
	@echo "Java Algorithms Build Targets:"
	@echo ""
	@echo "  make setup      - Setup environment and compile all Java files"
	@echo "  make compile    - Compile all Java source files"
	@echo "  make test       - Run all test classes"
	@echo "  make style      - Check code style (Google Java Style guidelines)"
	@echo "  make clean      - Remove build directory"
	@echo "  make help       - Show this help message"
	@echo ""
	@echo "Example usage:"
	@echo "  make setup      # Initial setup"
	@echo "  make test       # Run all tests"
	@echo "  make style      # Check code style compliance"
	@echo ""

setup: compile
	@echo ""
	@echo "========================================="
	@echo "Setup Complete!"
	@echo "========================================="
	@echo ""
	@echo "Compiled files are in: ./$(BUILD_DIR)"
	@echo ""
	@echo "To run tests:"
	@echo "  make test"

compile: $(BUILD_DIR)
	@echo "Compiling Java files..."
	javac -d $(BUILD_DIR) $(JAVA_FILES)
	@echo "✓ Compilation successful"

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

test: compile
	@echo "Running test suite..."
	@./test.sh

style:
	@echo "Checking code style (Google Java Style)..."
	@echo "Verifying:"
	@echo "  • Class and method documentation"
	@echo "  • Naming conventions (camelCase, PascalCase for classes)"
	@echo "  • Line length (max 100 characters)"
	@echo "  • Indentation (2-space)"
	@echo "  • Import organization"
	@echo ""
	@./style-check.sh

clean:
	@echo "Cleaning build directory..."
	rm -rf $(BUILD_DIR)
	@echo "✓ Build directory cleaned"

.DEFAULT_GOAL := help
