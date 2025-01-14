$projectDir = "."
$outDir = "out"
chcp 65001

# Tworzenie folderu wyjściowego, jeśli nie istnieje
if (-Not (Test-Path -Path $outDir)) {
    New-Item -ItemType Directory -Path $outDir
}

# Usuwanie poprzednich plików w folderze wyjściowym
Remove-Item -Path "$outDir\*" -Force -ErrorAction SilentlyContinue


# Pobieranie wszystkich plików .java w projekcie
$javaFiles = Get-ChildItem -Recurse -Path $projectDir -Filter "*.java"

# Tworzenie tablicy ścieżek do plików z odpowiednimi cudzysłowami
$javaFilePaths = $javaFiles.FullName | ForEach-Object { "`"$_`"" }

# Kompilacja plików Java
$command = "javac -d $outDir $javaFilePaths"
Invoke-Expression $command

if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful!"

    # Uruchomienie aplikacji
    java -cp $outDir com.ztp.ztpproject.Main
} else {
    Write-Host "Compilation failed!"
}
