
$user = "sb@im.ch"
$pass = "1" 
$encodedCreds = [System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("$($user):$($pass)"))
$basicAuthValue = "Basic $encodedCreds"
$Headers = @{
    Authorization = $basicAuthValue
}

# Get

$r = $null
$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp' -Headers $Headers
$r.RawContent

# Delete

Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp/3251' -ContentType application/json -Method DELETE -Headers $Headers

# Get by Id

$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp/3251' -Headers $Headers
$r.RawContent

# Login

$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/signin' -Headers $Headers
$r.RawContent

# Update

$json = '{"id":3251,"arbeitstyp":"testArbeitstypUpdated"}'
Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp' -ContentType application/json -Method PUT -Body $json  -Headers $Headers

# Persist

$json = '{"id":0,"arbeitstyp":"testArbeitstyp"}'
Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp' -ContentType application/json -Method POST -Body $json  -Headers $Headers

