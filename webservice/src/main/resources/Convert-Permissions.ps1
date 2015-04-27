$Data = Import-Csv .\Permissions.csv -Delimiter ";"
$Data = $Data | Group-Object -property Objekt | foreach-Object{ 
   
   ('"' + $_.Name + '":{')
   ($_.Group | Group-Object -property Berechtigung | foreach-Object{ 

        $Array = $_.Group | Group-Object -property Rolle | foreach-Object{'"' + $_.Name + '"'} 

        ('"' + $_.Name + '":[' + ($Array -join ",") + ']')
   }) -join ","
   ('},')
} 
$Data