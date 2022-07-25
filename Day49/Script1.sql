--데이터 사전을 조회하는 명령문
--인덱스 생성하는 DDL
--먼저 생성된 테이블들을 이용하여 새로운 테이블과 뷰를 생성하는 ****

--20220721 CREATE TABLE : 테이블 생성
CREATE TABLE GROUP_PORTFOLIO(
--컬럼이름 / 컬럼 타입 / 추가옵션
                                GROUP_NUMBRT	NUMBER(2)	DEFAULT	0,
                                NAME			VARCHAR(20)	NOT NULL,
                                PORTFOLIO_NAME	VARCHAR(30)

);

-----------------------------------------------------------

--DROP TABLE
DROP TABLE GROUP_PORTFOLIO;


-----------------------------------------------------------

--ALTER TABLE
ALTER TABLE GROUP_PORTFOLIO MODIFITY CONSTRAINT NAME PRIMA

-----------------------------------------------------------

    COMMIT;
--20220722
--문제1.
--조별 포트폴리오목록 이라는 테이블 작성하시고,
--그룹(NUMBER), 조장이름, 포트폴리오 제목, 포트폴리오 작업내용 이라는 컬럼을 작성하시고, 그룹(NUMBER)은 기본키(PRIMARY key) 입니다
--조원구성 이라는 테이블 작성하시고,
--그룹(NUMBER), 조원이름, 직책(PM, 백엔드..), 포트폴리오담당, 기타사항('') 이라는 컬럼을 작성하십시오.
--업무내용 이라는 테이블을 작성하시고,
--그룹(NUMBER), 조원이름 업무내용, 업데이트날짜(DATE랑 SYSDATE), 기타사항 이라는 컬럼을 작성하십시오.
--위 테이블 3개에 현재 구성하고 있는 조원과 업무 내용을 임의로 작성하시오(insert는 마음대로 쓰세요)
--(혼자이면 홀로 할 내용을 작성하시고, 데이터는 각 테이블당 5개 이상 작성하시오)








--CREATE TABLE // 테이블 이름 //
CREATE TABLE 고객(
--컬럼 이름 // 테이블 타입 //,
--NUMBER : 숫자(정수, 소수 ....)
--NUMBER(3) : 100의 자리수까지 입력가능
--NUMBER (4,3) : 1000의 자리수(정수)까지 입력가능, 소수점 3자리까지 입력가능
--CHAR : 고정길이 문자열 (컴퓨터 입장에서 길이가 정해져서 길이 검색 알고리즘 필요없어서 컴퓨터한테는 편해요, 공간은 낭비)
--CHAR(10) : 'abc' > 3자리까지 들어가고 나머지 7자리를 공백으로 채움
--VARCHAR : 가변길이 문자열(1~2000BYTE)
--VARCHAR2 : 가변길이 문자열(1~4000BYTE)
--영어는 1BYTE, 한글 3BYTE
--DATE : 날짜

                   고객아이디	VARCHAR2(20),
                   고객이름	VARCHAR2(10) NOT NULL,
                   나이		NUMBER(3),
                   등급		VARCHAR2(10) NOT NULL,
                   직업		VARCHAR2(20),
                   적립금	VARCHAR2(20) DEFAULT 0,
--	UNIQUE(사원번호),
                   PRIMARY KEY(고객아이디)

--기본키 : 최소성과 유일성 만족, NOT NULL
--대체키 : 기본키 외에 후보키(언제나 기본키를 대체 할 수 있는 컬럼의 값)
--외래키 : 다른 테이블에서 참조할 수 있는 키

-- V1.1

--JOIN : 테이블끼리 연관관계를 맺는 행위(외래키 떄문에 조인 함)

--기본키 : 유일성 + NOT NULL
--대체키 : 기본키가 되지 못한 유일성 + NOT NULL
--외래키 : 다른 테이블에서 참조하는 키
--슈퍼키 : 유일성은 갖추되, 최소성은 갖추지 못한 것

--유일성 : 그 컬럼을 식별할 수 있는 유일한 값
--최소성 : 유일성을 성립하기 위한 최소한의 기준 컬럼의 값
--	ex) 컬럼1의 유일한 값 + 컬럼2의 유일한 값을 통해 도출해 낸다면,
--	유일성은 성립하지만, 이미 컬럼1의 유일한 값으로 데이터를 구분할 수 있지만, 값을 더해서 최소성을 성립안하는 케이스
--	ex) 최소성 성립하는 유일값 : 지역번호 + 전화번호

--사원번호(유일성) + 주민등록번호(유일성) = 최소성(공간낭비)(어차피 사원번호로 유일한 튜플(ROW)
--구분할 수 있는데, 왜? 주민등록번호를 넣느냐? > 공간낭비를 하므로 최소성 성립안한다
--DB : 데이터 넣는 것, 정리하는 것, (도출해내는 것)
--학생이면서, 천안시에 시민인 사람을 = 휴먼교육센터의 학번 + 시민번호(직관적으로 데이터 도출)
);
--중복 입력 불가능
ALTER TABLE 고객테이블 ADD CONSTRAINT 고객테이블_pk PRIMARY KEY(고객아이디);
ALTER TABLE 고객테이블 DROP CONSTRAINT 고객테이블_pk;

ALTER TABLE 고객 ADD 가입날짜 DATE;
ALTER TABLE 고객 DROP COLUMN 가입날짜;

ALTER TABLE 고객 ADD CONSTRAINT 나이 CHECK(나이>=20);
ALTER TABLE 고객 DROP CONSTRAINT 나이;

SELECT * FROM 고객;

--암호화 방법
INSERT 고객 VALUES(
	'AD100', UTL_ENCODE.TEXT_ENCODE('김준석', 'KO16KSC5601', 1), 21, 'DWA','DWA');

SELECT 고객아이디, UTL_ENCODE.TEXT_DECODE(고객이름 , 'KO16KSC5601', 1) FROM 고객;

DROP TABLE 고객;

-----------------------------------------------------------
--INSERT INTO // 테이블 이름 // VALUES // ('' , 20,);
--반드시 컬럼의 생성 순서대로 작성해야 됩니다!!
INSERT INTO 고객 VALUES(
                         'apple', '정소화', 20 ,'gold','학생', 1000);

INSERT INTO 고객 VALUES(
                         'banana', '김선우', 25 ,'vip','간호사', 2500);

INSERT INTO 고객 VALUES(
                         'carrot', '고명석', 28 ,'gold','교사', 4500);

INSERT INTO 고객 VALUES(
                         'orange', '김용욱', 22 ,'silver','학생', 0);

INSERT INTO 고객 VALUES(
                         'melon', '성원용', 35 ,'gold','회사원', 5000);

INSERT INTO 고객 VALUES(
                         'peach', '오형준', null ,'silver','의사', 300); --회원가입 시 선택사항 데이터를 위한 임시공간

INSERT INTO 고객 VALUES(
                         'pear', '채광주', 31 ,'silver','회사원', 500);

--NULL 데이터가 있지만, 그것은 없는 거(데이터 공간을 만들어 놓고 대체하기 위해서 임시로 정리)
--NULL이란?
--OBJECT(자바로 따지면) = DB에서 모든 타입의 원형(INT, NUMBER, CHAR, VARCHAR, DATE.)
--NULL 값이 할당되지 않은 원형의 공간

--NULL = '' (' '<랑 다름)
--NULL은 모든 타입의 원형이므로 어떤 타입이든 연산이 가능



-----------------------------------------------------------
CREATE TABLE 제품(
                   제품번호	VARCHAR2(10),
                   제품명	VARCHAR2(20),
                   재고량	NUMBER(5),
                   단가		NUMBER(6),
                   제조업체	VARCHAR2(20),
                   PRIMARY KEY(제품번호),
                   CHECK(재고량 >=0 AND 재고량<=10000)
);

DROP TABLE 제품;

-----------------------------------------------------------

INSERT INTO 제품 values(
                         'p01', '그냥만두', 5000 , 4500, '대한식품');
INSERT INTO 제품 values(
                         'p02', '매운쫄면', 2500, 5500, '민국푸드');
INSERT INTO 제품 values(
                         'p03', '쿵떡파이', 3600, 2600, '한빛제과');
INSERT INTO 제품 values(
                         'p04', '맛난초콜릿', 1250, 2500, '한빛제과');
INSERT INTO 제품 values(
                         'p05', '얼큰라면', 2200, 1200, '대한식품');
INSERT INTO 제품 values(
                         'p06', '통통우동', 1000, 1550, '민국푸드');
INSERT INTO 제품 values(
                         'p07', '달콤비스킷', 1650, 1500, '한빛제과');

DELETE FROM 제품;

-----------------------------------------------------------

CREATE TABLE 주문(
                   주문번호	VARCHAR2(10) PRIMARY KEY,
                   주문고객	VARCHAR2(20),
                   주문제품	VARCHAR2(20),
                   수량		NUMBER(4),
                   배송지	VARCHAR2(50),
                   주문일자	DATE,
                   FOREIGN KEY(주문고객) REFERENCES 고객(고객아이디), -- 고객에 있는 고객번호에 들어있는 데이터로만 주문 테이블의 주문고객 데이터 넣을 수 있음
                   FOREIGN KEY(주문제품) REFERENCES 제품(제품번호)
--ON DELETE CASCADE,


--COMMENT 생성
--COMMENT ON TABLE TABLE_NAME IS '테이블 예제',
--COMMENT ON TABLE TABLE_사원.소속부서 IS '테이블 예제',
                       COMMENT ON TABLE 고객 IS '고객테이블';
--COMMENT ON COLUMN TABLE_NAME.name IS '테이블 예제';
);

DROP TABLE 주문;
DELETE FROM 주문;

-----------------------------------------------------------

INSERT INTO 주문 values(
                         'o01', 'apple', 'p03', 10, '서울시 마포구', '2022-01-01');
INSERT INTO 주문 values(
                         'o02', 'melon', 'p01',  5, '인천시 계양구','2022-01-10');
INSERT INTO 주문 values(
                         'o03', 'banana', 'p06', 45, '경기도 부천시','2022-01-11');
INSERT INTO 주문 values(
                         'o04', 'carrot', 'p02',  8, '부산시 금정구' ,'2022-02-01');
INSERT INTO 주문 values(
                         'o05', 'melon', 'p06', 36, '경기도 용인시','2022-02-20');
INSERT INTO 주문 values(
                         'o06', 'banana', 'p01', 19, '충청북도 보은군','2022-03-02');
INSERT INTO 주문 values(
                         'o07', 'apple', 'p03', 22, '서울시 영등포구','2022-03-15');
INSERT INTO 주문 values(
                         'o08', 'pear', 'p02', 50, '강원도 춘천시','2022-04-10');
INSERT INTO 주문 values(
                         'o09', 'banana', 'p04', 15, '전라남도 목포시','2022-04-11');
INSERT INTO 주문 values(
                         'o10', 'carrot', 'p03', 20, '경기도 안양시','2022-05-22');

/*INSERT INTO 주문 values(
	'o01', 'apple', 'p03', 10, '서울시 마포구', to_date('2022-01-01', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o02', 'melon', 'p01',  5, '인천시 계양구', to_date('2022-01-10', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o03', 'banana', 'p06', 45, '경기도 부천시', to_date('2022-01-11', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o04', 'carrot', 'p02',  8, '부산시 금정구' , to_date('2022-02-01', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o05', 'melon', 'p06', 36, '경기도 용인시', to_date('2022-02-20', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o06', 'banana', 'p01', 19, '충청북도 보은군', to_date('2022-03-02', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o07', 'apple', 'p03', 22, '서울시 영등포구', to_date('2022-03-15', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o08', 'pear', 'p02', 50, '강원도 춘천시', to_date('2022-04-10', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o09', 'banana', 'p04', 15, '전라남도 목포시', to_date('2022-04-11', 'YYYY/MM/DD'));
INSERT INTO 주문 values(
	'o10', 'carrot', 'p03', 20, '경기도 안양시', to_date('2022-05-22', 'YYYY/MM/DD'));*/

-- SYSDATE 쓰면 현재 날짜로 입력가능
INSERT INTO 주문 values(
                         'o10', 'test', 'p03', 20, '경기도 안양시', SYSDATE);


-----------------------------------------------------------

CREATE TABLE 배송업체(
                     업체번호	NUMBER(3) NOT NULL,
                     업체명	VARCHAR(20),
                     주소		VARCHAR(100),
                     전화번호	VARCHAR(20),
                     PRIMARY KEY(업체번호)
);

DROP TABLE 배송업체;


-----------------------------------------------------------





ROLLBACK;

-----------------------------------------------------------


--SLECT 절은 꼭 알아두기
--SELECT // 컬럼이름, ....(* : 모든 컬럼 조회) // FROM // 테이블이름
SELECT * FROM 고객테이블 WHERE 고객아이디 LIKE '______';
--WHERE 고객아이디 LIKE(비슷한 거 찾기) '정%'(맨 앞줄에 '정'이라는 문자가 있는 데이터를 찾기)
--WHERE 고객아이디 LIKE(비슷한 거 찾기) '%화'(어느 자리에 상관없이 '화'라는 문자가 있는 데이터를 찾기)
--like %는 순서를 지켜줘야 한다
--WHERE 고객아이디 LIKE '%a%1%' or 고객아이디 Like 'car%'
--WHERE 절 : 검색 범위를 지정해주는 구문
--WHERE NOT LIKE / LOWER(고객아이디) / LIKE '____A%' (자릿수+비슷검색)
--WHERE NOT LIKE /


--SELECT DATA : 테이블 찾아서 조회
--SELECT / * : 조회하고 싶은 컬럼( * >> 몽땅 조회) / FROM / 테이블 이름;
SELECT * FROM GROUP_PORTFOLIO;

--SELECT // 컬럼이름, ...(* : 모든 컬럼 조회) // FROM // 테이블 이름
SELECT * FROM 고객;
SELECT * FROM 제품;
SELECT * FROM 주문;

SELECT DISTINCT  제조업체 FROM 제품;
SELECT 제품명 FROM 제품 WHERE 제품명 LIKE '_____';


-----------------------------------------------------------

SELECT 제품명, 단가 AS 가격 FROM 제품;

SELECT 제품명, 재고량, 단가 FROM 제품 WHERE 제조업체 = '한빛제과';

--20220725 SELECT
--SELECT * FROM 사원 WHERE 사원 ORDER BY 사원번호 ASC;
--ORDER BY를 포함해서 조건절에서는 처음 조건에 먼저 정렬을 하고, 그 정렬 조건 안에서 다음 조건 정렬
--튜플 중에 딱 한명만 검색하는 경우 = 보통은 정렬을 해서 값 도출(검색속도 빨라요)

SELECT COUNT(*) FROM 사원;
SELECT 사원번호, 사원이름, 소속부서 FROM 사원;

SELECT MAX(사원번호) FROM 사원;
SELECT MIN(사원번호) FROM 사원;

SELECT SUM(사원번호) FROM 사원;
SELECT AVG(사원번호) FROM 사원;

SELECT * FROM 사원 ORDER BY 소속부서 DESC, 사원이름 ASC;

--ASC 오름차순 // DESC 내림차순
--NULL은 마지막에 출력(이유 데이터 타입이 다르기 때문에)


--ORDER BY 정렬 뒤 출력 (성능에 아주 영향이 있습니다)
--언제? 꼭 필요하는데 (WHERE절을 써서 범위를 축소한 뒤에 ORDER BY) 범위 최소한!

--자동으로 INDEX 컬럼을 추가하는 옵션을 GOOGLE 검색해서 넣으세요(오토인크리먼트)
--단, 컬럼이름은 INDEX_AUTO입니다.
--CREATE 문과 INSERT, SELECT문을 만드세요(예제7-26)

SELECT * FROM 사원 WHERE 사원 ORDER BY 사원번호 DESC;

SELECT * FROM 사원 WHERE 사원이름 IS NOT NULL;
SELECT * FROM 사원 WHERE 사원이름 IS NULL;
--null(임시대체), ' ' (공간을 할당)
--주소를 모를 경우(참조타입)
SELECT
--COUNT ROW가 몇개 있는지 세주는 명령어
COUNT(사원이름) *2 AS COUNT
--DISTINCT 사원이름
FROM 사원
--WHERE 사원이름 LIKE '김준석';

--현업때 많이 씀 DUAL 더미 데이터
SELECT 12*2 AS 계산결과 FROM DUAL;

SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체;

SELECT COUNT(DISTINCT 제조업체) AS "제조업체 수" FROM 제품;

SELECT 주문제품,SUM(수량) AS "총주문수량" FROM 주문 GROUP BY 주문제품;

--조인(JOIN)
--테이블끼리 엮어서 하나의 결과 데이터를 출력하는 것
--단, 특정 컬럼의 연관성을 기준으로 출력

SELECT 주문.주문제품, 주문.주문일자, 주문.주문고객, 고객.나이 FROM 고객, 주문 WHERE 고객.나이 >= 30 AND 고객.고객아이디 = 주문.주문고객;

SELECT * FROM 주문 WHERE 주문고객  = 'banana';
SELECT * FROM 제품;
--p01, p04, p06 데이터의 튜플만 가져와서 붙히면 됨
SELECT 제품번호, 제품.제품명 AS 제품테이블_제품명, 주문.주문고객 AS 주문테이블_주문고객 FROM 제품, 주문 WHERE 주문.주문고객 ='banana' AND 제품.제품번호 = 주문.주문제품;
--SELECT 제품.제품명 FROM 제품, 주문 WHERE 주문.주문고객 ='banana' AND 제품.제품번호 = 주문.주문제품;
SELECT * FROM 제품, 주문 WHERE 주문.주문고객 ='banana' AND 제품.제품번호 = 주문.주문제품;

--예제 7-34
SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체 HAVING COUNT(*)>=1; --having은 GROUP BY의 조건절

SELECT MAX(단가) FROM 제품;

SELECT 제조업체,COUNT(*) AS 제품수, MAX(단가) AS 최고가 FROM 제품 GROUP BY 제조업체 HAVING COUNT(*)>=1;

SELECT 등급, AVG(적립금) FROM 고객 GROUP BY 등급 HAVING AVG(적립금) >= 1000;

SELECT 주문제품, 주문고객, SUM(수량) FROM 주문 GROUP BY 주문제품, 주문고객;

--GROUP BY + HAVING은 세트

-----------------------------------------------------------

--INSERT DATA : 테이블에 컬럼에 맞춰서 데이터 넣기
--INSERT INTO /테이블 이름 /VALUES / (컬럼 순서에 따라 데이터 차례대로 넣기)

INSERT INTO GROUP_PORTFOLIO VALUES(0, '김준석', '슬라임게임');
INSERT INTO GROUP_PORTFOLIO VALUES(0, '김병진', '');