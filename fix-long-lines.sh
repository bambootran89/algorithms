#!/bin/bash
# Fix long lines in Java files (> 100 characters)

SRC_DIR="src"
FIXED=0

echo "Fixing long lines (> 100 characters)..."
echo ""

while IFS= read -r file; do
    if [ -z "$file" ]; then
        continue
    fi
    
    # Check for lines longer than 100 chars
    if grep -q '.\{101,\}' "$file"; then
        echo "Processing: $file"
        
        # Use sed to break long comment lines at 100 chars
        sed -i '' -E 's/^(.{0,100})(.*)\* (.{30,})$/\1 *\n   * \3/g' "$file"
        
        ((FIXED++))
    fi
done < <(find "$SRC_DIR" -name "*.java" -type f)

echo ""
echo "Processed $FIXED files"
