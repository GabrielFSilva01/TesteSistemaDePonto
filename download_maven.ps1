$url = "https://dlcdn.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip"
$zipPath = Join-Path -Path $pwd -ChildPath "maven.zip"
$extractPath = Join-Path -Path $pwd -ChildPath ".maven"

if (-not (Test-Path $extractPath)) {
    Write-Host "Baixando o Apache Maven 3.9.16..."
    Invoke-WebRequest -Uri $url -OutFile $zipPath
    Write-Host "Extraindo os arquivos..."
    Expand-Archive -Path $zipPath -DestinationPath $extractPath
    Remove-Item $zipPath
    Write-Host "Maven configurado com sucesso na pasta .maven"
} else {
    Write-Host "Maven ja esta instalado na pasta .maven"
}
