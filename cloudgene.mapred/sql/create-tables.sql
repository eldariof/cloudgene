CREATE TABLE USER( 
	ID          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	USERNAME	VARCHAR2(100) NOT NULL,
	PASSWORD	VARCHAR2(100) NOT NULL,
	FULL_NAME	VARCHAR2(100) NULL,
	MAIL		VARCHAR2(100) NULL,
	ROLE		VARCHAR2(100) NULL,
	AWS_KEY	VARCHAR2(200) NULL,
	AWS_SECRET_KEY	VARCHAR2(200) NULL,
	SAVE_KEYS BOOLEAN,
	EXPORT_TO_S3 BOOLEAN,
	S3_BUCKET	VARCHAR2(200) NULL,
	EXPORT_INPUT_TO_S3 BOOLEAN
);

CREATE TABLE JOB( 
	ID		VARCHAR2(100) NOT NULL PRIMARY KEY,
	STATE		INTEGER NOT NULL,
	START_TIME	BIGINT NOT NULL,
	END_TIME	BIGINT NOT NULL,
	NAME 		VARCHAR2(300),
	S3_URL		VARCHAR2(300),
	TYPE		INTEGER,
	USER_ID INTEGER NOT NULL REFERENCES USER(ID) ON DELETE CASCADE
);

CREATE TABLE PARAMETER( 
	ID			INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	NAME		VARCHAR2(100) NOT NULL,
	VALUE		VARCHAR2(200) NOT NULL,
	TYPE		VARCHAR2(25) NOT NULL,
	INPUT		BOOLEAN,
	DOWNLOAD	BOOLEAN,
	VARIABLE	VARCHAR2(100) NOT NULL,
	JOB_ID		VARCHAR2(100) NOT NULL REFERENCES JOB(ID) ON DELETE CASCADE
);