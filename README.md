#test_project

### Requirements
* Java version 11
* Maven 3.5+
* Docker (not mandatory)
  
### Run tests locally
By default, tests are run in docker container. 
If need to run locally you need to change 'web.driver.mode' property to 'local' in test.properties (src/test/resources/test.properties) file.