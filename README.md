# ms-app-medica-historial-db

Capa **DB** del módulo **Historial** (historial clínico). Expone un CRUD REST sobre la entidad `Historial` y persiste en MySQL (`app_medica_historial`) vía Spring Data JPA + Hibernate. El esquema se gestiona con **Liquibase** (changelog declarativo).

| | |
|---|---|
| **Puerto** | `8095` |
| **Patrón** | Controller → Service → Repository (CSR) |
| **Ruta base** | `/api/v1/historiales` |
| **Persistencia** | MySQL `app_medica_historial` (JPA/Hibernate) |
| **Migraciones** | **Liquibase** — `src/main/resources/db/changelog/db.changelog-master.yaml` |
| **Pruebas** | `HistorialServiceTest` (JUnit 5 + Mockito) |
| **Swagger** | `http://localhost:8095/swagger-ui.html` |

No llama a ningún otro microservicio.

## Ejecución

```bash
# Con todo el ecosistema (recomendado), desde app-medica-et-fullstack-1/
docker compose up --build

# Individual
./mvnw spring-boot:run     # mvnw.cmd en Windows
./mvnw test
```
