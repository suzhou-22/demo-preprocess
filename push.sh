./mvnw clean
./mvnw package
docker build . -t ligandlly/demo-preprocess:latest
docker push ligandlly/demo-preprocess:latest
