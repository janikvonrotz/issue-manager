$path = "C:\OneDrive\Shared\GitHub\issue-manager\export.docx"
$PDFpath = "C:\OneDrive\Shared\GitHub\issue-manager\export.pdf"

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

Get-ChildItem -Recurse -Filter "*.java" | ForEach-Object{
    $Selection.Style = 'Überschrift 1'
    $Selection.TypeText($_.BaseName)
    $Selection.TypeParagraph()
    $selection.typeText($(Get-Content $_.FullName -Raw))
    $selection.InsertBreak(7)
}

$doc.saveas($path, $saveFormat::wdFormatDocument)
#$doc.ExportAsFixedFormat($PDFpath,$wdExportFormatPDF)
$doc.close($wdDoNotSaveChanges)
$word.Quit()
