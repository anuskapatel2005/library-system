# library-system

## Running db by using docker command
```
# Docker command with volume
docker run -d   --name library-db   -e POSTGRES_USER=admin   -e POSTGRES_PASSWORD=admin123   -e POSTGRES_DB=library_db   -v D:/docker-volume/docker-postgres-data:/var/lib/postgresql/data   -p 5432:5432   postgres

# Docker command without volume
docker run -d   --name library-db1   -e POSTGRES_USER=admin   -e POSTGRES_PASSWORD=admin123   -e POSTGRES_DB=library_db   -p 5432:5432   postgres

```

## Running db by using docker compose command

```
# Start container
docker compose up -d

# Stop container
docker compose down
```
