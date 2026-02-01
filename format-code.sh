#!/bin/bash
# Auto-format Java files to Google Style

SRC_DIR="src"
FORMATTED=0

echo "Auto-formatting Java files to Google Style..."
echo ""

# Convert tabs to 2 spaces in all Java files
while IFS= read -r file; do
    if [ -z "$file" ]; then
        continue
    fi
    
    # Convert tabs to 2 spaces
    if grep -q $'\t' "$file"; then
        sed -i '' $'s/\t/  /g' "$file"
        echo "✓ Converted tabs to spaces: $file"
        ((FORMATTED++))
    fi
done < <(find "$SRC_DIR" -name "*.java" -type f)

echo ""
echo "Formatted $FORMATTED files"
