BUILD_DIR := build
SRC_DIR := src
TESTS_DIR := tests
JAVA_FILES := $(shell find $(SRC_DIR) -name "*.java") $(shell find $(TESTS_DIR) -name "*.java")

.PHONY: setup compile test clean style help

help:
	@echo "Java Algorithms Build Targets:"
	@echo "  make setup      - Setup environment and compile"
	@echo "  make compile    - Compile Java source files"
	@echo "  make test       - Run tests"
	@echo "  make style      - Check code style"
	@echo "  make clean      - Remove build directory"

setup: compile
	@echo "Setup complete. Compiled files in ./$(BUILD_DIR)"

compile: $(BUILD_DIR)
	@echo "Compiling..."
	@javac -d $(BUILD_DIR) $(JAVA_FILES)

$(BUILD_DIR):
	mkdir -p $(BUILD_DIR)

test: compile
	@echo "Running tests..."
	@./test.sh

style:
	@echo "Checking code style..."
	@./style-check.sh

clean:
	@echo "Cleaning..."
	@rm -rf $(BUILD_DIR)

.DEFAULT_GOAL := help
