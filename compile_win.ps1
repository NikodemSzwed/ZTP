$projectDir = "."
$outDir = "out"

if (-Not (Test-Path -Path $outDir)) {
    New-Item -ItemType Directory -Path $outDir
}

Remove-Item -Path "$outDir\*" -Force

$javaFiles = Get-ChildItem -Recurse -Path $projectDir -Filter "*.java"
$javaFilePaths = $javaFiles.FullName | ForEach-Object { "`"$_`"" }
javac -d $outDir $javaFilePaths


if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful!"

    java -cp $outDir com.ztp.ztpproject.Main
} else {
    Write-Host "Compilation failed!"
}
