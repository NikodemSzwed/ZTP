@echo off
setlocal

:: Set the base directory for the project
set PROJECT_DIR=.


set SRC_DIR=%PROJECT_DIR%\src\main\java
set OUT_DIR=%PROJECT_DIR%\out
set MAIN_CLASS=Main

if exist %OUT_DIR% rmdir /s /q %OUT_DIR%
mkdir %OUT_DIR%

echo Compiling Java files...
javac -d %OUT_DIR% %SRC_DIR%\com\ztp\ztpproject\*.java

if %errorlevel% neq 0 (
    echo Compilation failed. Exiting...
    exit /b %errorlevel%
)

echo Running the program...
java -cp %OUT_DIR% %MAIN_CLASS%

endlocal
