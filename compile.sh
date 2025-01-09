#!/bin/bash

# Set directories
SRC_DIR="src/main/java"
OUT_DIR="out"

rm -rf "$OUT_DIR"

# Step 1: Compile all Java files
echo "Compiling Java files..."
javac -d "$OUT_DIR" $(find "$SRC_DIR" -name "*.java")
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi
echo "Compilation successful."

# Step 2: Run the program
MAIN_CLASS="com.ztp.ztpproject.Main"
echo "Running the program..."
java -cp "$OUT_DIR" $MAIN_CLASS
