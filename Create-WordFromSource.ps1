$basepath = Split-Path -parent $MyInvocation.MyCommand.Definition
$path = Join-Path $basepath "export.docx"

# Required Word Variables
$wdExportFormatPDF = 17
$wdDoNotSaveChanges = 0

# Create a hidden Word window
$word = New-Object -ComObject word.application
$word.visible = $false

# Add a Word document
$doc = $word.documents.add()
$content = @()
$selection = $word.selection

$files = Get-ChildItem -Filter "*.ps1"
$files += Get-ChildItem -Filter "*.gradle" -Recurse -Exclude ".gradle"
$files += Get-ChildItem -Directory | where{$_.BaseName -eq "client" -or $_.BaseName -eq "webservice" -or $_.BaseName -eq "common"} | ForEach-Object{    
    Get-ChildItem -Recurse -Include ("*.java", "application.json") -Path (Join-Path $_ "src")
}
$files = $files | Sort-Object "BaseName"

$Selection.Style = 'Überschrift 1'
$Selection.TypeText("Deploy")
$Selection.TypeParagraph()

$files | where-Object{(Split-Path $_ -Parent) -eq $basepath} | ForEach-Object{
    $Selection.Style = 'Überschrift 2'
    $Selection.TypeText($_.BaseName + $_.Extension)
    $Selection.TypeParagraph()
    $selection.typeText($(Get-Content $_.FullName -Raw))
    $selection.InsertBreak(7)
}

$Selection.Style = 'Überschrift 1'
$Selection.TypeText("Common")
$Selection.TypeParagraph()

$files | where-Object{(Split-Path $_ -Parent) -like ($basepath + "\common*")} | ForEach-Object{
    $Selection.Style = 'Überschrift 2'
    $Selection.TypeText($_.BaseName + $_.Extension)
    $Selection.TypeParagraph()
    $selection.typeText($(Get-Content $_.FullName -Raw))
    $selection.InsertBreak(7)
}

$Selection.Style = 'Überschrift 1'
$Selection.TypeText("Webservice")
$Selection.TypeParagraph()

$files | where-Object{(Split-Path $_ -Parent) -like ($basepath + "\webservice*")} | ForEach-Object{
    $Selection.Style = 'Überschrift 2'
    $Selection.TypeText($_.BaseName + $_.Extension)
    $Selection.TypeParagraph()
    $selection.typeText($(Get-Content $_.FullName -Raw))
    $selection.InsertBreak(7)
}

$Selection.Style = 'Überschrift 1'
$Selection.TypeText("Client")
$Selection.TypeParagraph()

$files | where-Object{(Split-Path $_ -Parent) -like ($basepath + "\client*")} | ForEach-Object{
    $Selection.Style = 'Überschrift 2'
    $Selection.TypeText($_.BaseName + $_.Extension)
    $Selection.TypeParagraph()
    $selection.typeText($(Get-Content $_.FullName -Raw))
    $selection.InsertBreak(7)
}

$doc.saveas($path, $saveFormat::wdFormatDocument)
$doc.close($wdDoNotSaveChanges)
$word.Quit()
