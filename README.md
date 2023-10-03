Instructions to run tests on your computer:
1. Pull MYSQL image:
docker pull mysql/mysql-server:8.0.25

2. Start the MySQL Container:
docker run --name MysqlDB \
-e MYSQL_ROOT_PASSWORD=rootpw123 \
-e MYSQL_DATABASE=birdwatchdb \
-e MYSQL_USER=birduser \
-e MYSQL_PASSWORD=birdpassword \
-p 3306:3306 \
-d mysql/mysql-server:8.0.25

3. Start tests:
Go to projectfolder /birdwatch
./gradlew test

test