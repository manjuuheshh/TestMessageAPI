***********Functional Testing Defects******
1. if there is no user ids given for the sender and receiver (i.e "id": ""), api is still returning 200 response and creating a message id.
2. API not restricting the creation of empty messages
3. API not allowing the edit of the sent messages i.e., put Method is not working, returning 404 error code.
4. When invalid message id is used for get call, API should return any meaningful error message to state that the resource is not available

*********Automation framework*************
Executed each of the functional Test case in postman and converted the functional tests to Automation scripts.
Created Maven project to build, write and execute tests along with TestNG.
There are 4 Test files created to test Message API >> PostMessagesTests, GetMessageTests, DeleteMessageTests, UpdateMessageTests 
Each test file contains their respective tests with combination of test data.
In addition to Test files, 5 helper classes where created to map the api response, Base class to have all the functions in the Tests.

Through automation following validation are done.
	1. Request and Response payload validation
	2. API behavior on different data sets
	3. Response code and Status text validation
	

**********Steps to execute the automation tests*********
1. clone the repository
2. Right click to testng.xml >> Run as >> TestNG Suite
3. the above step will execute all the test files configured in the testng.xml.