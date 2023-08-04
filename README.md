# To run the test

Parameters:
* browser ==> chrome [default], firefox, remote-chrome, remote-firefox
* devtools ==> true, false [default]


    ./gradlew run ==> devtools=false, browser=chrome

    browser=firefox devtools=true ./gradlew run ==> run with dev tools enabled using firefox

    browser=remote-chrome devtools=false ./gradlew run ==> run with dev tools disabled using chrome in docker container

# Prerequisites for running the tests using RemoteWebDriver

Use the provided docker containers having selenium-hub, firefox and chrome, 

OR, 

Start your own docker container first, before running the tests

### Starting the docker container

    ./dockerContainers.sh up

### Stopping the docker container

    ./dockerContainers.sh down

