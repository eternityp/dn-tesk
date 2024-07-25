## 프로젝트 설명
- csv 데이터처리 기능을 Spring batch를 사용하여 개발  
- https://www.data.go.kr/data/15096283/standard.do 

## 프로젝트 구성
- Java 17
- Springboot 3.3.2, Spring 6.1.11
- Spring batch 5.1.2
- Spring JPA stater
- Spring JDBC

- gradle 8.8
- mysql 8
- lombok
- jackson-databind

## 프로젝트 기획
데이터 제공자 - 지방행정인허가데이터개방사이트(www.localdata.go.kr)
데이터 받기 > 데이터활용문의 > 예제 파일 다운로드 시  제공 되는 파라미터 변수명, 조건 사항들을 참조했습니다.

유니크 키와 update_dt 기준으로 변경을 확인하여 batch update, insert가 진행됩니다. 

기본 데이터는 data 폴더 하위 full_test.csv, full_test_invalid_5.csv가 있고  
full_test_invalid_5.csv는 5개의 잘못된 데이터가 있습니다.

## 프로젝트 기능 설명
batch 관련 코드는 config/batch에 들어 있습니다.  
Reader, Processor, Writer로 구성되어 있고 각 Step와 Job의 추적하기 위한 Listener로 구성되어 있습니다. 

Database 성형 및 간단한 기능을 위해 Spring JPA를 사용했고 성능 향상을 위해 Spring JDBC를 사용했습니다.

## 설정 방법
Spring profile은 dev, test, prod 3개로 구성되어 있습니다.
1. 개발 시 스키마 생성은 JPA와 배치 기본 기능을 활용하려면  
jpa > ddl = create, batch > initialize-schema = always  
설정으로 기본 생성됩니다. (entity/Restaurant.java)


2. 데이터베이스 접속 후 init-batch.sql 직접 실행하면 됩니다.  
csv-file-path(읽을 파일)과 chunk-size(배치크기)를 환경변수로 받습니다.   
개발 profile은 data/full_test.csv, 1000이 기본값으로 설정되어 있습니다.


3. 테스트 코드는 test profile을 사용하고 BatchRunner.java 를 사용하지 않습니다.  
 batch, sql, 유효값 체크로 구성되어 있습니다. 

## 빌드 방법
```
./gradlew clean build -x test
```

build/libs/batch-0.0.1-SNAPSHOT.jar 를 사용하면 됩니다.

## 실행 방법
```
java -Dspring.profiles.active=dev -Dcsv-file-path=data/full_test.csv -Dchunk-size=5 -jar batch-0.0.1-SNAPSHOT.jar    
```
실행할 profile, file, size를 환경변수로 입력하면 됩니다.

## 참고자료
예제 파일 다운로드시 확인할 수 있는 문서입니다.
- [붙임1] 지방행정인허가데이터개방_자치단체코드목록
- [붙임2] 지방행정인허가데이터개방_상세제공항목v4.5
- [붙임3] 지방행정인허가데이터개방_업종코드_업종별영업상태코드목록v2.9
