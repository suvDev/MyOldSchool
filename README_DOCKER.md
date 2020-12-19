**docker container ls** =>  _to see running containers_

**docker images** => _to see all images in docker_

**docker rmi imagename** => _to delete any image_

**docker rm id** => _to delete container reference_

**docker kill imageid** => _to kill any running container_

**docker logs imagename** => _to see logs of any running container_


**docker run -p 5000:5000 --link mysql-spring-docker suvdev/spring-boot-docker** => _to run docker image of spring project and mapping
docker port 5000 running eureka server with server/pc port 5000 to make the API's accessible from outside environment and linking it
with mysql image container._
Note: Here if we are following monolithic architecture then we can map the docker port say 8080 by any port of our server/pc like 3000:8080 


**docker build -t anyname .** => _to create image of your spring/java project in docker. This is to be run from the same folder of your
Dockerfile in your java project._


**docker run -e MYSQL_DATABASE="db_test" -e MYSQL_USER=root -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql:latest** => _to run the existing image of
mysql named mysql:latest (where latest is the tag) in container and creating a db db_test with username root with no password(MYSQL_ALLOW_EMPTY_PASSWORD=yes)._
Note: -e is required before every database configuration we write.
