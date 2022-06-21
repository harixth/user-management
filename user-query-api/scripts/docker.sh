docker build -t user-query-api
docker run -d -p 8084:8084 user-query-api-service --network springNet \
-e "SPRING_PROFILES_ACTIVE=docker" --restart always user-query-ap
i