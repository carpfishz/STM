# STM
###Research Advisor - 유용석<br>
###Teaching Assistant - 최유진<br>
###Project Member - 진현두, 안연상, 이현석, 이상민, 조완기, 서주연<br><br>
###일정
|Week|Content|
|:---:|--------------------------------------------|
|1주|Concept model, Use Case, Stroyboards 작성|
|2주|Concept model, Use Case, Storyboards 수정, 구체화|
|3주|작업 전, Coding Convention 정하기|
|4주|팀별 기능 구현|
|5주|피드백 확인, 구현|
|6주|구현 및 최종 테스트|
|7주|최종 발표|

### [Coding Convention][coding]
[coding]: http://source.android.com/source/code-style.html

###포인트 - 이상민, 조완기
###지도 - 안연상, 서주연
###이벤트 - 이현석, 진현두
<br />
###프로젝트 import 및 실행 준비과정
1. 프로젝트 열기
   * 안드로이드 스튜디오 클릭 -> import project -> git clone한 MyApplication 경로 선택, 클릭

2. sdk25, google play store 설치
   1. windows 기준 : user directory -> AppData -> local -> android -> sdk -> SDK Manager.exe 실행
   2. Tools -> Android SDK Tools, Android SDK Platform-tools, Android SDk build-tools 체크(25버전)
   3. Android 7.1.1(API 25) -> SDK platform 체크
   4. Extras -> Google Play services, Google Repository 체크
   5. 오른쪽 하단 설치 클릭

3. 안드로이드 스튜디오, Gradle 최신버전 설치
   * 안드로이드 스튜디오 2.2.2 설치
   * gradle version 2.14.1 설치
   * 버전확인 : help -> about

4. 프로젝트 버전 확인
   * file -> project structure -> project

5. instant run 해제
   * file -> setting -> build, execution, deployment -> instant run -> enable ~~ 해제
