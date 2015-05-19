function create-7zip([String] $aDirectory, [String] $aZipfile){
    [string]$pathToZipExe = "C:\Program Files\7-zip\7z.exe";
    [Array]$arguments = "a", "-tzip", "$aZipfile", "$aDirectory";
    & $pathToZipExe $arguments;
}

git checkout master
gradle shadowjar
gradle assemble
create-7zip ".\lib\*"  "mysql.zip"

git checkout postgres
gradle shadowjar
gradle assemble
create-7zip ".\lib\*"  "postgres.zip"