`application.properties` 파일에서 로컬 영역의 H2 데이터베이스와 AWS 환경의 RDS를 사용하는 방법을 설명하겠습니다.

### 로컬 영역의 H2 데이터베이스 사용

로컬 영역에서 H2 데이터베이스를 사용하려면 다음과 같이 `application.properties` 파일을 설정합니다:

```ini
# H2 In-Memory DB Connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.settings.web-allow-others=true
spring.datasource.initialization-mode=always
```

이 설정은 H2 인메모리 데이터베이스를 사용하며, H2 콘솔을 통해 웹에서 접근할 수 있도록 합니다.

### AWS 환경의 RDS 사용

AWS 환경에서 RDS를 사용하려면 다음과 같이 `application.properties` 파일을 설정합니다:

```ini
# AWS RDS PostgreSQL DB Connection
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://<your-rds-endpoint>:5432/<your-database-name>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

이 설정은 AWS RDS PostgreSQL 데이터베이스를 사용합니다. `<your-rds-endpoint>`, `<your-database-name>`, `<your-username>`, `<your-password>` 부분을 실제 RDS 인스턴스의 정보로 대체해야 합니다.

이렇게 설정하면 로컬에서는 H2 데이터베이스를, AWS 환경에서는 RDS를 사용할 수 있습니다. 필요에 따라 프로파일을 사용하여 환경별로 설정을 분리할 수도 있습니다.