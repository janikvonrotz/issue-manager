
$user = "sb@im.ch"
$pass = "1" 
$encodedCreds = [System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("$($user):$($pass)"))
$basicAuthValue = "Basic $encodedCreds"
$Headers = @{
    Authorization = $basicAuthValue
}

# Get

$r = $null
$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/mangel' -Headers $Headers
$r.RawContent
$r = $r | Convertfrom-json
$r.count

# Delete

Invoke-WebRequest -Uri 'http://localhost:8080/webservice/bauleiter/9673' -ContentType application/json -Method DELETE -Headers $Headers

# Get All by Property

$r = $null
$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/getbyproperty/rolle?propertyname=bezeichnung&propertyvalues=Sachbearbeiter&propertyvalues=Bauleiter' -Headers $Headers
$r.RawContent

# Get by Id

$r = $null
$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/mangel/9747' -Headers $Headers
$r.RawContent

# Login

$r = $null
$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/signin' -Headers $Headers
$r.RawContent

# Update

$json = '{"id":3251,"arbeitstyp":"testArbeitstypUpdated"}'
Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp' -ContentType application/json -Method PUT -Body $json  -Headers $Headers

# Persist

$json = '{"id":0,"arbeitstyp":"testArbeitstyp"}'
Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp' -ContentType application/json -Method POST -Body $json  -Headers $Headers

