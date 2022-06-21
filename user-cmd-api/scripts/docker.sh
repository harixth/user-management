docker build -t user-cmd-api
docker run -d -p 8081:8081 user-cmd-api-service --network springNet \
-e "SPRING_PROFILES_ACTIVE=docker" --restart always user-cmd-api