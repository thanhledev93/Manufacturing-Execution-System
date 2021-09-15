cd C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\workspace\Manufacturing-Execution-System-Pipeline\.github\workflows
docker-compose -f docker-compose-v3.yml down

:: Folder info
set "$folderName=MESMO Spring"
set "$createNewFolder=true"
set "$appendDateTimeInFolderName=true"
set "$assigneeUser=5f8eb8199c31840076da94cf"
set "$mandatoryFields={"reporter":{"label":"Thanh Le","name":"Thanh Le","id":"5f8eb8199c31840076da94cf"}}"