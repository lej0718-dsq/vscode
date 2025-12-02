## 목적

이 리포지토리는 간단한 Spring Boot 데모 애플리케이션입니다. 이 파일은 AI 코딩 에이전트가 빠르게 생산적으로 작업하도록 프로젝트 구조, 빌드/실행/테스트 워크플로, 그리고 코드베이스에서 자주 보이는 패턴을 요약합니다.

## 한줄 요약

- Java 21 기반 Spring Boot 애플리케이션
- 빌드: Maven (프로젝트에 Maven wrapper 포함)
- 주요 패키지: `com.dsqd.nacf.demo`
- 진입점: `src/main/java/com/dsqd/nacf/demo/DemoApplication.java`

## 빠른 시작 (Windows PowerShell)

1) 빌드

```powershell
.\mvnw.cmd clean package
```

2) 실행 (개발용 — 클래스 재시작이 필요하면 IDE에서 `DemoApplication.main` 실행 권장)

```powershell
.\mvnw.cmd spring-boot:run
```

3) 테스트

```powershell
.\mvnw.cmd test
```

4) (옵션) 실행가능 JAR으로 실행

```powershell
java -jar target\demo-0.0.1-SNAPSHOT.jar
```

5) 원격 디버그 예제

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

## 아키텍처 & 주요 컴포넌트

- 단일 모듈의 Spring Boot 애플리케이션입니다. (`pom.xml` 참조)
- Entry point: `DemoApplication.java` — SpringApplication.run으로 서비스 시작
- HTTP 엔드포인트 예시: `HelloController.java`
  - `@GetMapping("/")` => returns "hello vscode"
  - `@PostMapping("/")` => POST form/body에서 `name` 파라미터를 `WebRequest`로 읽음 (요청 처리 방식 참조)

## 코드/패턴 관찰 (에이전트가 알아야 할 것)

- POST 파라미터를 처리할 때 보통 `@RequestParam` 대신 `WebRequest`를 직접 사용합니다.
  - 파일: `src/main/java/com/dsqd/nacf/demo/HelloController.java`
  - 예: `String name = request.getParameter("name");` — null/빈 값 처리 로직이 내장되어 있음.
- Lombok이 `pom.xml`에 optional로 선언되어 있고, annotation processor가 설정되어 있습니다. (하지만 현재 소스에서는 Lombok 어노테이션 사용이 없음)
- `spring-boot-starter-web-services`와 `spring-boot-starter-actuator`가 포함되어 있음: SOAP/WebService 또는 액추에이터 엔드포인트가 필요할 수 있으나 현재 코드는 간단한 REST 컨트롤러만 포함함.
- `pom.xml`에 부모 POM( Spring Boot 3.5.6 )을 사용하고 있으며, `HELP.md`는 부모 상속의 일부 오버라이드 설명(license/developers) 포함.

## 빌드/테스트/디버깅 관행 (프로젝트 특이사항)

- 항상 Maven wrapper(`mvnw.cmd`) 사용 권장 — CI/ML 환경에서 동일한 Maven 버전 보장
- Java 21 필요 — 로컬 개발 환경과 CI에 동일한 JDK가 설치되어야 함
- 도커 기반 이미지 생성은 Spring Boot Maven Plugin의 `build-image`를 사용 가능 (`.\mvnw.cmd spring-boot:build-image`) — Docker 필요

## 변경/확장 시 유의사항

- `pom.xml`의 parent/overrides: 라이선스나 개발자 정보를 변경할 때 빈 override가 남아있을 수 있으니 주의
- 컨트롤러 패턴을 따를 때, POST 파라미터를 `WebRequest`에서 직접 읽는 기존 코드와 스타일 일관성을 유지하세요.

## 참고 파일

- `pom.xml` — 의존성, Java 버전, 플러그인 설정
- `HELP.md` — 빌드/문서 관련 참고
- `src/main/java/com/dsqd/nacf/demo/DemoApplication.java` — 진입점
- `src/main/java/com/dsqd/nacf/demo/HelloController.java` — 간단한 HTTP 엔드포인트 예시
- `src/test/java/.../DemoApplicationTests.java` — 기본 테스트 스켈레톤

## 작업 제안 (에이전트용)

- 코드 변경을 제안할 때는 관련 컨트롤러(`HelloController`)와 테스트(`src/test/...`)를 함께 수정하세요.
- 새로운 REST 엔드포인트는 기존의 `WebRequest` 스타일 대신 `@RequestParam` 또는 DTO를 사용하는 리팩토링 제안 시, 기존 POST 처리와 호환되는지 확인해야 합니다.

---

더 추가했으면 하는 항목(예: CI 설정, 로컬 환경 스크립트, 더 많은 예제 요청)이 있으면 알려주세요. 불명확한 부분을 구체적으로 지적해주시면 바로 보강하겠습니다.
