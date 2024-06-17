# 트립트룹(TRIPTROOP)

<img src="https://avatars.githubusercontent.com/u/168394855?s=200&v=4" width="150" alt="favicon">

## 규칙

- [Git, Github 커밋 가이드](https://github.com/2024-TikiTaka/be-triptroop/wiki/%EC%BB%A4%EB%B0%8B-%EA%B0%80%EC%9D%B4%EB%93%9C)

## 개발 환경

![Git](https://img.shields.io/badge/Git-F05032?style=flat-square&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat-square&logo=intellij-idea&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=flat-square&logo=notion&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-5865F2?style=flat-square&logo=discord&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=figma&logoColor=white)

![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat-square&logo=spring-security&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-000000?style=flat-square&logo=websocket&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat-square&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-000000?style=flat-square&logo=mongodb&logoColor=white)

## 팀원 소개

|                                                             팀원                                                              |                           이름                           | 담당 기능             |
|:---------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------:|:------------------|
| [<img src="https://avatars.githubusercontent.com/u/154950177?v=4"      height=75 width=75> ](https://github.com/ddalla0425) | 김다솔 <br/> [@ddalla0425](https://github.com/ddalla0425) | 신고<br> 차단<br> 관리자 |
| [<img src="https://avatars.githubusercontent.com/u/42160693?s=96&v=4"  height=75 width=75> ](https://github.com/may54ther)  |  김아현 <br/> [@may54ther](https://github.com/may54ther)  | 회원<br> 프로필<br> 매칭 |
| [<img src="https://avatars.githubusercontent.com/u/154950075?s=60&v=4" height=75 width=75> ](https://github.com/qkrquddjs1) | 박병언 <br/> [@qkrquddjs1](https://github.com/qkrquddjs1) | 여행지 소개<br> 여행 기록  |
|  [<img src="https://avatars.githubusercontent.com/u/154950170?v=4"      height=75 width=75> ](https://github.com/EUNJAE97)  |   이은재 <br/> [@EUNJAE97](https://github.com/EUNJAE97)   | 일정<br> 동행글        |
|  [<img src="https://avatars.githubusercontent.com/u/120306336?v=4"      height=75 width=75> ](https://github.com/chk2023)   |    조형기 <br/> [@chk2023](https://github.com/chk2023)    | 채팅<br> 친구         |

## 프로젝트 소개

- 트립 트룹(TripTroop)은 “여행 그룹” 을 의미하며
  TripTroop은 여행 계획 수립, 동행 찾기, 그리고 여행 경험 공유를 위한 플랫폼입니다.
  상품 판매가 아닌 순수한 정보 공유와 커뮤니티 활성화에 중점을 두어 여행자들이 보다 풍부하고 진정한 여행 경험을 할 수 있도록 돕습니다.
  시장 조사 결과 MZ세대를 주요 타깃으로 설정하였으며, 이들의 독립적이고 자유로운 여행 선호를 반영하여 서비스를 구축했습니다.

## DB설계 : 논리 ERD

![ERD](https://github.com/2024-TikiTaka/be-triptroop/assets/151039466/5d6ed41d-11be-40a6-a3e6-db7bcecadd81)

## 개발 기간

<img src="https://private-user-images.githubusercontent.com/151039466/340213381-bb8a8ecf-3c21-48fb-93b0-1c65671cda32.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTg2MDg4MjMsIm5iZiI6MTcxODYwODUyMywicGF0aCI6Ii8xNTEwMzk0NjYvMzQwMjEzMzgxLWJiOGE4ZWNmLTNjMjEtNDhmYi05M2IwLTFjNjU2NzFjZGEzMi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNjE3JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDYxN1QwNzE1MjNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT1jMzI3MzU3YWU5NjdlNTAzZTdjMzZiOTY0NTlmZjI2ZTJiMzk1MmEwY2MzOGYxZjg2MzNkMjRlYjkzNDk0MGNkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.3UKghPCt-3fKLyc885xQOt2nXolX6B6A08h089Zv3XY">




<!--

| 김다솔 | 김아현 | 박병언 | 이은재 | 조형기 |    
|:--------------:|:--------------:|:--------------:|:--------------:|:--------------:| 
| [<img src="https://avatars.githubusercontent.com/u/154950177?v=4" height=100 width=100> <br/> @ddalla0425](https://github.com/ddalla0425) |[<img src="https://avatars.githubusercontent.com/u/42160693?s=96&v=4" height=100 width=100> <br/> @may54ther](https://github.com/may54ther) | [<img src="https://avatars.githubusercontent.com/u/154950075?s=60&v=4" height=100 width=100> <br/> @qkrquddjs1](https://github.com/qkrquddjs1) | [<img src="https://avatars.githubusercontent.com/u/154950170?v=4" height=100 width=100> <br/> @EUNJAE97](https://github.com/EUNJAE97) |[<img src="https://avatars.githubusercontent.com/u/120306336?v=4" height=100 width=100> <br/> @chk2023](https://github.com/chk2023) |
| 신고, 차단, 관리자 |  회원, 매칭 | 여행지 소개, 여행 기록 | 일정, 동행글 | 채팅, 친구  |


## 주요 기능

## 프로젝트 구조

## ERD

## 개선 목표

## 트러블 슈팅

## 프로젝트 후기
-->
