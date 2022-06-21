docker build -t user-auth
docker run -d -p 8084:8084 user-auth-service --network springNet \
-e "SPRING_PROFILES_ACTIVE=docker" --restart always user-auth