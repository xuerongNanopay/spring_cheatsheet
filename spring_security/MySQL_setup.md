# Set up my sql using Docker

## Pull image
```
docker pull mysql:5.7
```
## Run image
```
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.7
```