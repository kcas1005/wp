--자동으로 INDEX 컬럼을 추가하는 옵션을 GOOGLE 검색해서 넣으세요(오토인크리먼트)
--단, 컬럼이름은 INDEX_AUTO입니다.
--CREATE 문과 INSERT, SELECT문을 만드세요(예제7-26)

SELECT 나이+10 FROM 고객_AUTO WHERE 나이 IS NULL ORDER BY 나이 ASC;
SELECT * FROM 고객_AUTO1;

CREATE TABLE 고객_AUTO(
--	INDEX_AUTO	NUMBER(5) PRIMARY KEY ,
                        INDEX_AUTO	INT GENERATED ALWAYS AS IDENTITY ,--AUTO 숫자 증가
                        고객이름		VARCHAR2(20) NOT NULL,
                        등급			VARCHAR2(20) NOT NULL,
                        나이			INT,
                        PRIMARY KEY(INDEX_AUTO)
);

CREATE TABLE 고객_AUTO1(
                         INDEX_AUTO	NUMBER(5) /*PRIMARY KEY */,
--	INDEX_AUTO	INT GENERATED ALWAYS AS IDENTITY ,--AUTO 숫자 증가
                         고객이름		VARCHAR2(20) NOT NULL
);


DELETE FROM 고객_AUTO WHERE INDEX_AUTO = 2;

COMMIT;

DROP TABLE 고객_AUTO;
DROP TABLE 고객_AUTO1;

CREATE SEQUENCE SEQ_INDEX_AUTO INCREMENT BY 1 START WITH 1;
DROP SEQUENCE SEQ_INDEX_AUTO;

INSERT INTO 고객_AUTO(고객이름,등급,나이) VALUES(
                                          '지성진', 'VIP', NULL);
INSERT INTO 고객_AUTO(고객이름,등급,나이) VALUES(
                                          '김성진', 'VIP', NULL);
INSERT INTO 고객_AUTO(고객이름,등급,나이) VALUES(
                                          '이성진', 'VIP', NULL);
INSERT INTO 고객_AUTO(고객이름,등급,나이) VALUES(
                                          '감성진', 'VIP', NULL);
INSERT INTO 고객_AUTO(고객이름,등급,나이) VALUES(
                                          '구성진', 'VIP', NULL);


----------------------------------------시퀀스로 생성한 값 튜플에 넣기
INSERT INTO 고객_AUTO VALUES(
                              SEQ_INDEX_AUTO.NEXTVAL,'일성진', 'VIP', NULL);
INSERT INTO 고객_AUTO VALUES(
                              SEQ_INDEX_AUTO.NEXTVAL,'이성진', 'VIP', NULL);

----------------------------------------시퀀스로 생성한 값 다른테이블에 넣기
INSERT INTO 고객_AUTO1 VALUES(
                               SEQ_INDEX_AUTO.NEXTVAL,'일성진');
INSERT INTO 고객_AUTO1 VALUES(
                               SEQ_INDEX_AUTO.NEXTVAL,'이성진');
