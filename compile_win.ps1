$projectDir = "."
$outDir = "out"
chcp 65001

if (-Not (Test-Path -Path $outDir)) {
    New-Item -ItemType Directory -Path $outDir
}

Remove-Item -Path "$outDir\*" -Force -ErrorAction SilentlyContinue


$javaFiles = Get-ChildItem -Recurse -Path $projectDir -Filter "*.java"

$javaFilePaths = $javaFiles.FullName | ForEach-Object { "`"$_`"" }

$command = "javac -d $outDir $javaFilePaths"
Invoke-Expression $command

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful!"

    java -cp $outDir com.ztp.ztpproject.Main
} else {
    Write-Host "Compilation failed!"
}
