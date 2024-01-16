<br>
</br>

# JIRA / JQL
> - jira :  글로벌 시장 점유율 1위의 프로젝트 관리 시스템  
> - JQL(JIRA Query Language) : JIRA에서 쓰이는 언어 중 하나. SQL과 비슷  
> * [튜토리얼 링크](https://www.lesstif.com/jira/jql-jira-query-language-jira-issue-18220188.html)
<br><hr></br>

## issue
> JIRA내에서 완료되어야 하는 모든 작업  
> https://www.atlassian.com/ko/software/jira/guides/issues/overview#what-is-an-issue

<br>

### 그렇다면, JQL을 공부해야 하는 이유는?
> - JIRA의 issue를 효율적으로 빠르게 검색하기 위해서  
> - JQL을 활용한 해당 검색 기능은 백엔드에서 동작

<br><hr></br>

# git
> 버전 관리, 협업을 위한 툴  
> git history를 참고하여 코드의 유지보수에 용이(git checkout 명령어로 버그 발생 시점 파악)


## 커밋 유저 이름, 이메일 설정
- git config --global user.name "장태수"
- git config --global email "awf@gmail.com"


## commit message
- 다른 개발자들의 작업 내역, 변경 사항 파악

* conventional commits
   - 프로젝트 내에서 명확한 커밋 히스토리를 위한 간단한 규칙  

      ex) <타입>[적용범위(선택)]: <설명>
         본문
         꼬리말
   
## git branch
![](../img/git_1.PNG)
- 독립적인 개발 공간 제공
- 계획없이 만들면 관리가 어려워져 브랜치 사용 전략이 필요 ex) gitflow

## git stash
- 급한 작업을 위해 새로운 브랜치를 생성하는 경우
- 브랜치의 이동
- 작업 중인 내용을 stash 공간으로 이동

<br><hr></br>

## Next.js 폴더 구조 확인
출처 : [https://miriya.net/blog/cliz752zc000lwb86y5gtxstu](https://miriya.net/blog/cliz752zc000lwb86y5gtxstu)

- 린터를 활용(코드의 가독성 혹은 통상적인 사용적합성 체크에 용이)
  
- 배포를 위한 env 파일 내의 endpoint 주소 설정 / env 파일 gitignore 체크
  
- public 폴더 구성 설정
  
- app 폴더 내의 구성([https://nextjs.org/docs/app/building-your-application/routing/pages-and-layouts](https://nextjs.org/docs/app/building-your-application/routing/pages-and-layouts))
  
- src/components : 여러 페이지에서 사용하는 컴포넌트를 담아둠.

- src/constants : 여러 페이지에서 사용하는 상수값을 담아둠

- src/hooks : 여러 페이지에서 사용되는 공통 hook을 담아둠

- src/libs: 외부 라이브러리를 담아둠

- src/services : API 요청들을 담아둠

- src/states : 여러 페이지에서 사용하는 state를 담아둠. 전역 상태관리를 담당하며, 적게 작성되는 것이 유지보수에 용이

- src/styles : 스타일시트 관련 요소들을 정리한 곳

- src/types : 타입스크립트 타입 정의가 들어가는 곳

- src/utils : 유틸성 함수들을 관리