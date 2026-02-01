#!/bin/bash
# Simple Google Java Style checker using grep and basic rules

SRC_DIR="src"
ISSUES=0
CHECKED=0

echo "Analyzing Java files for Google Style compliance..."
echo ""

# Check all Java files
while IFS= read -r file; do
    if [ -z "$file" ]; then
        continue
    fi
    
    ((CHECKED++))
    FILE_ISSUES=0
    
    # Check 1: Missing class documentation
    if grep -q "^public class" "$file"; then
        if ! grep -q "^/\*\*" "$file"; then
            echo "❌ $file: Missing class-level JavaDoc"
            ((FILE_ISSUES++))
        fi
    fi
    
    # Check 2: Improper indentation (tabs instead of spaces)
    if grep -q $'\t' "$file"; then
        echo "❌ $file: Contains tabs (use spaces for indentation)"
        ((FILE_ISSUES++))
    fi
    
    # Check 3: Import organization (avoid wildcards)
    if grep -q "import.*\.\*" "$file"; then
        echo "❌ $file: Avoid wildcard imports (import.*.*)"
        ((FILE_ISSUES++))
    fi
    
    ISSUES=$((ISSUES + FILE_ISSUES))
    
done < <(find "$SRC_DIR" -name "*.java" -type f)

echo ""
echo "========================================="
echo "Style Check Summary:"
echo "  Files checked: $CHECKED"
echo "  Issues found: $ISSUES"
echo "========================================="

if [ "$ISSUES" -eq 0 ]; then
    echo "✓ All critical style checks passed!"
    exit 0
else
    echo "⚠️  Please fix the critical style issues above"
    exit 1
fi
