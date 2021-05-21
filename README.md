### Weather-Forecast API URL
http://[ClusterIP]:30007/weather.html

1. ClusterIP - This is external IP/DNS/FQDN
2. hostfile mapping - ClusterIP weatherforecast.demo.com 
In our example:
172.18.94.56 weatherforecast.demo.com
3. 30007 is nodeport

### Build
1. Run `mvn clean install -Dmaven.test.skip=true` to compile the code and produce the jar
Path: /weather-forecast/target/weather-forecast-0.0.1-SNAPSHOT.jar

### Deploy
#### Prerequisites 
 
1. Install Docker Desktop https://www.docker.com/products/docker-desktop
2. Minikube https://kubernetes.io/docs/tasks/tools/install-minikube/
3. Setup kubectl https://kubernetes.io/docs/tasks/tools/install-kubectl/

#### Deployment on minikube
1. Run ```docker build -t navdeeps02/weatherforecast .``` build image with latest tag
2. Run ```docker push navdeeps02/weatherforecast``` push image to cloud registry - dockerhub
3. Run ```kubectl apply -f deployment.yaml``` apply deployment
4. Run ```minikube service --url weatherforecast-api``` expose service

## Jenkins

### Deploy Build
1. Run jenkins job's using `jenkins-job-config.xml` file present in project source
Illustration Diagram :
![weather-forecast run pipeline](https://github.com/navdeeps02/WeatherForecast/blob/feature/integrate-jenkins/docs/images/Pipeline-Execution.PNG)

### Test (JUnit and Selenium)
1. Run `mvn test surefire-report:report` to test the code and generate surefire-report
Illustration Diagram :
![surefire report](https://github.com/navdeeps02/WeatherForecast/blob/feature/integrate-jenkins/docs/images/surefire-report.PNG)
![selenium automated test report](https://github.com/navdeeps02/WeatherForecast/blob/feature/integrate-jenkins/docs/images/Seleniumtest.PNG)
