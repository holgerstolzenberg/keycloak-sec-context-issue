# Keycloak Issue Showcase
Link: [KEYCLOAK-12260](https://issues.redhat.com/browse/KEYCLOAK-12260)

# Setup
Use scripts provided under src/docker to create sample infrastructure.
```bash
cd src/docker
./create-machine.sh
docker-compose up -d
```

## Postgres
User: admin
Password: pgadm

## Keycloak
User: admin
Password: kcadmin

Edit /etc/hosts
```
// replace with your docker machine IP
192.168.99.123 keycloak-showcase
``` 

URL: http://keycloak-showcase:8080/auth/
