# library-system




docker run -d   --name library-db   -e POSTGRES_USER=admin   -e POSTGRES_PASSWORD=admin123   -e POSTGRES_DB=library_db   -v D:/docker-volume/docker-postgres-data:/var/lib/postgresql/data   -p 5432:5432   postgres



docker run -d   --name library-db1   -e POSTGRES_USER=admin   -e POSTGRES_PASSWORD=admin123   -e POSTGRES_DB=library_db   -p 5431:5432   postgres
