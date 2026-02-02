#!/bin/bash
# Fix long lines in Java files (> 88 characters)

SRC_DIR="src"
FIXED=0

echo "Fixing long lines (> 88 characters)..."

while IFS= read -r file; do
    if [ -z "$file" ]; then
        continue
    fi
    
    # Check for lines longer than 88 chars
    if grep -q '.\{89,\}' "$file"; then
        echo "Processing: $file"
        
        # Use sed to break long comment lines at 88 chars
        # Note: This is a simplistic fix for comments only
        sed -i '' -E 's/^(.{0,88})(.*)\* (.{30,})$/\1 *\n   * \3/g' "$file"
        
        ((FIXED++))
    fi
done < <(find "$SRC_DIR" -name "*.java" -type f)

echo "Processed $FIXED files"
