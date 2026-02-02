#!/bin/bash
# Simple Google Java Style checker

SRC_DIR="src"
ISSUES=0

echo "Running style checks..."

while IFS= read -r file; do
    if [ -z "$file" ]; then
        continue
    fi
    
    FILE_ISSUES=0
    
    # Check 1: Missing class documentation
    if grep -q "^public class" "$file"; then
        if ! grep -q "^/\*\*" "$file"; then
            echo "$file: Missing class-level JavaDoc"
            ((FILE_ISSUES++))
        fi
    fi
    
    # Check 2: Improper indentation
    if grep -q $'\t' "$file"; then
        echo "$file: Contains tabs (use spaces)"
        ((FILE_ISSUES++))
    fi
    
    # Check 3: Wildcard imports
    if grep -q "import.*\.\*" "$file"; then
        echo "$file: Avoid wildcard imports"
        ((FILE_ISSUES++))
    fi

    # Check 4: Line length > 88
    if grep -qE "^.{89,}$" "$file"; then
        echo "$file: Lines > 88 chars found"
        grep -nE "^.{89,}$" "$file" | head -n 3 | sed 's/^/  Line /'
        ((FILE_ISSUES++))
    fi
    
    ISSUES=$((ISSUES + FILE_ISSUES))
    
done < <(find "$SRC_DIR" -name "*.java" -type f)

if [ "$ISSUES" -eq 0 ]; then
    exit 0
else
    echo "Style violations found: $ISSUES"
    exit 1
fi
