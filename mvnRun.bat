@echo off
echo -----------------------------------------------------
echo -----------------------------------------------------
echo --------------- Cleaning project --------------------
echo -----------------------------------------------------
echo -----------------------------------------------------
call mvn clean
echo -----------------------------------------------------
echo -----------------------------------------------------
echo --------------- Running tests -----------------------
echo -----------------------------------------------------
echo -----------------------------------------------------
call mvn test
echo -----------------------------------------------------
echo -----------------------------------------------------
echo ---------------- Building report  -------------------
echo -----------------------------------------------------
echo -----------------------------------------------------
call mvn site