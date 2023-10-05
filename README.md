***Build, run app and run tests***

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

3. Run unit tests:
Navigate to projectfolder /birdwatch
./gradlew test

4. Run integration tests:
   Navigate to projectfolder /birdwatch
   ./gradlew integrationTest

5. Run system tests (need to detach or run test from 2nd terminal):
   a. build jar: ./gradlew build -x test 
   b. run jar: java -jar build/libs/birdwatch-0.0.1-SNAPSHOT.jar
   c. run system tests: ./gradlew systemTest

6. Build jar:
   Navigate to projectfolder /birdwatch
   build jar: ./gradlew build

7. Start Application & Database with Docker compose:
   a. Make sure nothing is running on port 3306: netstat -tuln | grep 3306 (should return nothing)
   b. Navigate to projectfolder /birdwatch
   c. Run docker compose: docker-compose up

8.    Test different URLs in the webbrowser when running application:
      http://localhost:8080/bird/all
      http://localhost:8080/bird/type/Rovfågel
      http://localhost:8080/birdwatcher/all
      http://localhost:8080/observation/all

9. Use requestTemplates.http to make manual REST testing for endpoints

***Checkstyle Commands***
./gradlew checkstyleMain
./gradlew checkstyleTest
./gradlew checkstyleintTest
./gradlew checkstylesysTest
./gradlew checkstyleMain checkstyleTest checkstyleintTest checkstylesysTest

***jacoco***
Create the Jacoco code coverage reports
(build/reports/jacoco/test)
./gradlew jacocoTestReport


****Github Commands***

Check the status of your files: Lists modified, staged, and untracked files:
->git status

Create a new feature branch from master:
->git checkout master          # Ensure you are on the master branch
->git pull                     # Update local master to match remote
->git checkout -b feature-name # Create and switch to a new branch

To add specific file:
->git add filename.ext

To add all changes:
->git add .

Commit your changes:
->git commit -m "Your commit message here"

Push your changes to GitHub (first time pushing):
->git push -u origin feature-name

Otherwise:
->git push

Pull latest changes from GitHub:
->git pull

Switch between branches:
->git checkout branch-name

Merge a branch into another: 
->git checkout master           # Switch to the branch you want to merge into
->git merge feature-name        # Merge the feature branch into the current branch

Delete a branch:
Locally: -> git branch -d feature-name
Remote: -> git push origin --delete feature-name

Fetch branches from GitHub:
to retrieve any new branches or updates to branches 
without changing your current branch.
-> git fetch

View commit history:
->git log

View details in commit:
git show <git-sha>
