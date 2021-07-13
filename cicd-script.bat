
:: =========================================
::  This .bat file demonstrates how to create or update an automation task in Zephyr for Jira Cloud, run this task, and publish test results to Zephyr.
::  Author: SmartBear Software
:: =========================================

:: =========================================
::  Zephyr base URL.
::   DON'T CHANGE THE CONSTANT BELOW. KEEP IT AS IT IS.
:: =========================================
set "$zephyrBaseUrl=https://prod-api.zephyr4jiracloud.com/connect" 

:: =========================================
::  Access and secret keys, and user id needed for connection to Zephyr for Jira. 
::  Replace the constants below with values relevant to your project and account.
:: =========================================
:: The accessKey and secretKey to access your project. You can find them in your Jira project: Zephyr > API Keys.
set "$accessKey=NzI2YWI2N2EtMTZhMS0zZWU4LTljYzYtNmUzZmQ5MDNjZmIzIDVmOGViODE5OWMzMTg0MDA3NmRhOTRjZiBVU0VSX0RFRkFVTFRfTkFNRQ"
set "$secretKey=ICqzsYgd6Cy4pjsUn4mm_x8Xtbqi9bjWT9a9hE_PMTk"
:: Id of the user who will create the automation task. You can find it in Jira.
set "$accountId=5f8eb8199c31840076da94cf"

:: =========================================
::  Create a JSON Web Token  (required to access Zephyr for Jira).
::  Keep this section as it is.
:: =========================================
echo "Generating a JSON Web Token ..."
for /F "tokens=*" %%i in (' curl -s -X POST -H "Content-Type: application/json" --data "{ \"accessKey\":\"%$accessKey%\", \"secretKey\":\"%$secretKey%\",\"accountId\":\"%$accountId%\",\"zephyrBaseUrl\": \"%$zephyrBaseUrl%\",\"expirationTime\":360000}" "https://prod-vortexapi.zephyr4jiracloud.com/api/v1/jwt/generate" ')  do set jwtgenerated=%%i
set "$finaljwt=%jwtgenerated%"
echo %$finaljwt%

:: =========================================
::  Define properties of the automation task.
::  Replace the values below with data relevant to your project.

:: Task info
:: set "$taskName=SearchMPStepDefs"
:: set "$taskDescription=New update"
:: set "$automationFramework=Cucumber"
:: set "$projectKey=MESMO"
:: set "$versionName=Version 1.0"

:: Cycle info
:: set "$cycleName=RegressionTesting"
:: set "$createNewCycle=false"
:: set "$appendDateTimeInCycleName=false"

:: Folder info
:: set "$folderName=MaintenancePlanning"
:: set "$createNewFolder=false"
:: set "$appendDateTimeInFolderName=true"
:: set "$assigneeUser=5f8eb8199c31840076da94cf"
:: set "$mandatoryFields={"reporter":{"label":"QA","name":"QA",
:: "id":"5f8eb8199c31840076da94cf"}}"

:: Fully-qualitified name of the test result file
set "$resultPath=@target\cucumber.json"
:: =========================================


:: =========================================
::  Create an automation task, run it, send test results to Zephyr.
::  Keep this section as it is.
:: =========================================
:: echo "Creating and running an automation task ..."
:: curl -v -X POST  https://prod-vortexapi.zephyr4jiracloud.com/api/v1/automation/job/saveAndExecute  -H "Content-Type: multipart/form-data" -H "Content-Type: application/json" -H "accessKey: %$accessKey%" -H "jwt: %$finaljwt%" -F "jobName=%$taskName%" -F "jobDescription=%$taskDescription%" -F "automationFramework=%$automationFramework%" -F "projectKey=%$projectKey%" -F "versionName=%$versionName%" -F "cycleName=%$cycleName%" -F "createNewCycle=%$createNewCycle%" -F "appendDateTimeInCycleName=%$appendDateTimeInCycleName%" -F "folderName=%$folderName%" -F "createNewFolder=%$createNewFolder%" -F "appendDateTimeInFolderName=%$appendDateTimeInFolderName%" -F "assigneeUser=%$assigneeUser%" -F "file=%$resultPath%" -F "mandatoryFields=$mandatoryFields"


:: =========================================
::  Update the properties of automation task and  run it, send test results to Zephyr.
:: 
::  The code below uses "task properties" values defined in lines 38-56.
::  Update them to change task properties.
:: 
::  To run the code below, first comment out the lines 62-63 above that create your automation task, 
::  and then uncomment in the lines below.
:: =========================================

:: Update test result for MESMO Spring 1
 set "$taskId=82E7D09C80957EF6FE47D461313593FE5490AE0C9E6CE358AEB9361CFCE795A0"

:: echo "Running the automation task"
curl -v -X PUT  https://prod-vortexapi.zephyr4jiracloud.com/api/v1/automation/job/updateAndExecute  -H "Content-Type: multipart/form-data" -H "Content-Type: application/json" -H "accessKey: %$accessKey%" -H "jwt: %$finaljwt%"  -F "jobId=%$taskId%" -F "file=%$resultPath%"
