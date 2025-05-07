-- 시스템 계정에서 시작
alter session set "_ORACLE_SCRIPT"=true;

CREATE USER musthave IDENTIFIED BY 1234;

GRANT connect, resource, unlimited tablespace TO musthave;

-- 테이블 스페이스 조회 확인
SELECT tablespace_name, status, contents FROM dba_tablespaces;

-- 테이블 스페이스별 가용 공간 확인
SELECT tablespace_name, sum(bytes), max(bytes) from dba_free_space
    GROUP BY tablespace_name;
    
-- musthave 계정 사용하는 테이블 스페이스 확인
SELECT username, default_tablespace  FROM dba_users
    WHERE username in upper('musthave');

-- musthave 계정에 users 테이블 스페이스에 데이터를 입력할 수 있도록 
-- 5m 의 용량을 할당.
ALTER USER musthave QUOTA 5m ON users;

-- musthave 계정으로 시작
SELECT * FROM tab;

-- 테이블 삭제해주기 
DROP TABLE member;
DROP TABLE board;
DROP TABLE seq_board_num;

-- 회원테이블 생성 
CREATE TABLE member
(
	id VARCHAR2(10) NOT NULL,
	pass VARCHAR2(10) NOT NULL,
	name VARCHAR2(30) NOT NULL,
	regidate DATE DEFAULT SYSDATE not null,
	PRIMARY KEY (id)
);

-- 더미데이터 입력
insert into member (id, pass, name) values ('musthave', '1234', '머스트헤브');

commit;
