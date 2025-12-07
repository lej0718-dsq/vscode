# 변경 이력 (역순 누적)

- 문서 마지막 수정일: 2025-12-07T00:00:00+09:00

## Unreleased
- (새 변경은 이 섹션에 추가하세요. 변경 후 날짜와 간단한 설명을 붙여넣어 누적합니다.)

---

## 2025-12-07
- 주요변경
  - Bootstrap 적용: UI 개선을 위해 Bootstrap CSS/JS CDN을 도입하고 기존 스타일과 조화시킴.
  - 정적 리소스 분리: hello.html, user.html, test.html 및 /js, /css 파일로 분리하여 프론트엔드 구조화.
  - 엔드포인트 확장: /user (GET/POST) 추가 — 요청 출처 표기; /test 추가 — 숫자 * 123 계산 및 회계형식 반환.
  - Whitelabel 비활성화 및 커스텀 에러 페이지 추가: 사용자 친화적 오류 화면 제공(error.html, error/404.html, error/500.html).
  - 테스트 추가: MockMvc 기반 통합 테스트(HelloControllerTests)로 주요 엔드포인트 검증.
  - 루트 리다이렉트: "/" 요청을 /hello.html로 리다이렉트하도록 변경.
- 컨트롤러 변경
  - HelloController: "/" 리다이렉트 -> /hello.html, /user GET/POST 추가(출처 표기), /test GET 추가(숫자 * 123, 회계 형식 반환).
- 정적 리소스
  - src/main/resources/static/hello.html, user.html, test.html 추가(각 엔드포인트 테스트용 UI).
  - src/main/resources/static/js/app.js 추가(폼 제출 AJAX 처리).
  - src/main/resources/static/css/styles.css 수정(기본 스타일) 및 Bootstrap CDN 적용으로 UI 개선.
- 에러 처리
  - Whitelabel 비활성화 및 커스텀 에러 페이지 추가: static/error.html 및 상태별 error/404.html, error/500.html.
- 테스트
  - src/test/java/com/dsqd/nacf/demo/HelloControllerTests.java 추가(MockMvc 기반 통합 테스트).
  - 기존 DemoApplicationTests.java 유지(컨텍스트 로드).
- 빌드/실행 안내
  - 테스트 실행: `.\mvnw.cmd test` 또는 `./mvnw test`
  - 서버 실행: `.\mvnw.cmd spring-boot:run`

## 2025-12-02
- HelloController
  - GET "/" 엔드포인트를 정적 페이지로 리다이렉트하도록 변경 (RedirectView -> /hello.html).
  - /user GET 엔드포인트 추가: 쿼리 파라미터 출처 표시 (Source: GET parameter).
  - /user POST 엔드포인트 추가: Content-Type 검사로 폼 제출인지 판별하여 출처 표시 (Form submission / Request body).
  - /test GET 엔드포인트 추가: `value` 파라미터를 숫자로 파싱해 123을 곱한 후 회계형식으로 반환.
- 정적 리소스 추가
  - src/main/resources/static/hello.html, user.html, test.html
  - src/main/resources/static/js/app.js
  - src/main/resources/static/css/styles.css
- 테스트
  - src/test/java/com/dsqd/nacf/demo/HelloControllerTests.java 추가 (MockMvc 통합 테스트)

## 2025-12-01
- 초기 컨트롤러 스켈레톤 및 기본 테스트 추가 (예시)

---

# Git 이력(원본 커밋 로그 — 최신순, 수동/자동 삽입 가능)
- 아래 마커 사이에 `git log` 출력을 삽입하세요. 삽입은 수동 복사/붙여넣기 또는 로컬 명령으로 자동화할 수 있습니다.

<!-- GIT_HISTORY_START -->
(여기에 git log 출력이 들어갑니다 — 프로젝트 루트에서 아래 명령 중 하나를 실행해 결과를 복사해 붙여넣으세요)
<!-- GIT_HISTORY_END -->

추천 git 로그 명령(최신 커밋이 먼저 표시):
- 간단 포맷:
  git log --pretty=format:"- %ad %h %an %s" --date=iso
- 작성자 포함:
  git log --pretty=format:"- %ad %h %an %s" --date=iso

자동 삽입(예시 — 안전을 위해 백업 후 실행하세요; Windows/PowerShell은 적절히 변형):
- git log --pretty=format:"- %ad %h %an %s" --date=iso > git-history.tmp
- awk 'NR==FNR{a[++n]=$0;next} /<!-- GIT_HISTORY_START -->/{print; for(i=1;i<=n;i++) print a[i]; skip=1; next} {print}' git-history.tmp history.md > history.tmp && mv history.tmp history.md && rm git-history.tmp

# 사용 지침
- 새로운 변경을 추가할 때는 먼저 `Unreleased` 섹션에 간단히 기록하세요.
- 릴리스 또는 커밋 후에는 해당 변경을 날짜별 섹션(예: 2025-12-08)으로 옮겨 누적하세요.
