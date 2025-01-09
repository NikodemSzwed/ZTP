# Set the root directory of your project
$projectDir = "."

# Set the output directory for compiled .class files (e.g., "out")
$outDir = "$projectDir\out"

# Create the output directory if it doesn't exist
if (-Not (Test-Path -Path $outDir)) {
    New-Item -ItemType Directory -Path $outDir
}

# Clean the output directory (optional)
Remove-Item -Path "$outDir\*" -Force

# Find all .java files in the project and its subdirectories
$javaFiles = Get-ChildItem -Recurse -Path $projectDir -Filter "*.java"

# Compile the .java files using javac
$javacArgs = $javaFiles.FullName -join " "
javac -d $outDir $javacArgs

# Check if the compilation was successful
if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful!"

    # Run the program (assuming Main is the entry point class)
    java -cp $outDir com.ztp.ztpproject.Main
} else {
    Write-Host "Compilation failed!"
}
