SET DATABASE SQL SYNTAX ORA TRUE;

CREATE SEQUENCE ID_GENERATOR START WITH 1000000000 INCREMENT BY 1;


CREATE TABLE CALCULATION (
    id NUMBER(19,0) NOT NULL PRIMARY KEY,
    calc_date DATE  NOT NULL,   
    annual_salary NUMBER(19,2) NOT NULL
 );
   
   
   
   