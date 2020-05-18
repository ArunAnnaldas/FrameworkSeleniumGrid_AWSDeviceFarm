1) Start Hub
java -jar selenium-server-standalone-3.141.59.jar -role hub

2) Start Node
java -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://10.0.75.1:4444/grid/register  -port 5566

3) You can specify browsers as mentioned below: 
java –Dwebdriver.ie.driver=geckodriver.exe -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://10.0.75.1:4444/grid/register -browser browserName=firefox -port 5566
java –Dwebdriver.ie.driver=IEDriverServer.exe -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://10.0.75.1:4444/grid/register -browser browserName=chrome -port 5567
java –Dwebdriver.chrome.driver=chromedriver.exe -jar selenium-server-standalone-3.141.59.jar -role webdriver -hub http://10.0.75.1:4444/grid/register -browser browserName=ie -port 5568

4) Execute this step to check the console. 
http://localhost:4444/grid/console