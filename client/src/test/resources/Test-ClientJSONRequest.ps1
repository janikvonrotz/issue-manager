
$user = "sb@im.ch"
$pass = "1" 
$encodedCreds = [System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("$($user):$($pass)"))
$basicAuthValue = "Basic $encodedCreds"
$Headers = @{
    Authorization = $basicAuthValue
}

# Get

$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/sachbearbeiter' -Headers $Headers
$r.RawContent

# Delete

Invoke-WebRequest -Uri 'http://localhost:8080/webservice/employer/3' -ContentType application/json -Method DELETE -Headers $Headers

# Get by Id

$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/arbeitstyp/3103' -Headers $Headers
$r.RawContent

# Login

$r = Invoke-WebRequest -Uri 'http://localhost:8080/webservice/signin' -Headers $Headers
$r.RawContent

# Update

$json = '{"id":272,"name":"Obie","email":"georgianna.jaskolski@hotmail.com","password":"ycggqp","role":"Administratorrrr"}'
Invoke-WebRequest -Uri 'http://localhost:8080/webservice/user' -ContentType application/json -Method PUT -Body $json  -Headers $Headers


