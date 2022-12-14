create table FACULTAD
(
    IDFACULTAD     NUMBER(19) not null
        primary key,
    DESC_FACULTAD  VARCHAR2(255 char)
        constraint UK_F3ER6OR3JMM3RMD45LQ9MXNQY
            unique,
    FECHA_REGISTRO DATE,
    ID_FACULTAD    NUMBER(19) not null
)
/
create table ESCUELA
(
    IDESCUELA            NUMBER(19) not null
        primary key,
    CALIFICACION         NUMBER(10),
    CANT_ALUMNOS         NUMBER(10),
    DESCRIPCION          VARCHAR2(255 char),
    FECHA_FEGISTRO       DATE,
    LICENCIADA           NUMBER(1),
    NOMBRE               VARCHAR2(255 char)
        constraint UK_TECN3HFL3P8MPVP5WDR0LNJS
            unique,
    RECURSO_FISCAL       FLOAT,
    FACULTAD_IDFACULTAD  NUMBER(19),
    FACULTAD_ID_FACULTAD NUMBER(19),
    ID_ESCUELA           NUMBER(19) not null,
    constraint FKJM4QR8C4ROHKOAWRYH77H1HRN
        foreign key () references FACULTAD,
    constraint FKQT6J90H7DI0AH6W5LVBU6J09E
        foreign key () references FACULTAD
)
/create table PERFIL
(
    IDPERFIL NUMBER(10) not null
        primary key,
    PERFIL   VARCHAR2(255 char)
)
/


create table USUARIO
(
    IDUSUARIO     NUMBER(10) not null
        primary key,
    ESTATUS       NUMBER(10),
    PASSWORD      VARCHAR2(255 char),
    USERNAME      VARCHAR2(255 char),
    EMAIL         VARCHAR2(255 char),
    FECHAREGISTRO TIMESTAMP(6),
    NOMBRE        VARCHAR2(255 char)
)
/

create table USUARIO_PERFIL
(
    IDUSUARIO NUMBER(10) not null
        constraint FKFHD0AMIL8CPDGY5E3S3CN22PB
            references USUARIO,
    IDPERFIL  NUMBER(10) not null
        constraint FK4KVNRQPUUR4GACPBV0YM67WEY
            references PERFIL
)
/




INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (1, 15, 34, 'Ingenieria Civil sede II', TO_DATE('2022-09-20', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Ingenieria Civil', 68900, 1);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (2, 15, 20, 'Ingenieria Sistemas sede II', TO_DATE('2022-09-21', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Ingenieria de Sistemas', 48900, 1);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (3, 14, 20, 'Ingenieria Geologica Sede Central', TO_DATE('2022-09-05', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Escuela de Ingenieria Geologica', 32, 1);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (4, 16, 23, 'Ingenieria Naval Sede II', TO_DATE('2021-06-02', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Ingenieria Naval', 2300, 1);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (5, 20, 20, 'Ingenieria Estructural Sede III', TO_DATE('2022-09-01', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Escuela de Ingenieria Estructural', 32320, 1);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (6, 15, 20, 'Ingenieria Agronoma sede II', TO_DATE('2022-09-18', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Ingenieria Sistemas', 48900, 1);

INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (7, 15, 34, 'Derecho', TO_DATE('2022-09-20', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Derecho', 68900, 2);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (8, 15, 20, 'Literatura', TO_DATE('2022-09-21', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Literatura', 48900, 2);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (9, 14, 20, 'Filosofia', TO_DATE('2022-09-05', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Filosofia', 32, 2);

INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (10, 16, 23, 'Quimica', TO_DATE('2021-09-02', 'YYYY-MM-DD HH24:MI:SS'), 0, 'Quimica', 2300, 3);
INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (11, 20, 20, 'Fisica', TO_DATE('2022-09-01', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Fisica', 32320, 3);

INSERT INTO SYS.ESCUELA (ID_ESCUELA, CALIFICACION, CANT_ALUMNOS, DESCRIPCION, FECHA_REGISTRO, LICENCIADA, NOMBRE, RECURSO_FISCAL, FACULTAD_ID_FACULTAD) VALUES (12, 15, 20, 'Medicina Humana', TO_DATE('2022-09-18', 'YYYY-MM-DD HH24:MI:SS'), 1, 'Medicina Humana', 48900, 4);




INSERT INTO SYS.FACULTAD (ID_FACULTAD, DESC_FACULTAD, FECHA_REGISTRO) VALUES (1, 'Ingenierias', TO_DATE('2022-09-22 19:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO SYS.FACULTAD (ID_FACULTAD, DESC_FACULTAD, FECHA_REGISTRO) VALUES (2, 'Letras', TO_DATE('2022-09-22 19:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO SYS.FACULTAD (ID_FACULTAD, DESC_FACULTAD, FECHA_REGISTRO) VALUES (3, 'Ciencias', TO_DATE('2022-09-22 19:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO SYS.FACULTAD (ID_FACULTAD, DESC_FACULTAD, FECHA_REGISTRO) VALUES (4, 'Medicina', TO_DATE('2022-09-22 19:00:00', 'YYYY-MM-DD HH24:MI:SS'));


INSERT INTO SYS.PERFIL (IDPERFIL, PERFIL) VALUES (1, 'ADMINISTRADOR');
INSERT INTO SYS.PERFIL (IDPERFIL, PERFIL) VALUES (2, 'USUARIO');

INSERT INTO SYS.USUARIO (IDUSUARIO, ESTATUS, PASSWORD, USERNAME, EMAIL, NOMBRE, FECHAREGISTRO) VALUES (21, 1, '$2a$10$2t0V50GlNCr3rqmTtVZo/O9Z61u0YNjpvavaB42AZSIPU4Zx58Q62', 'user', 'palmachristopher7@hotmail.com', 'Christopher Palma', TO_TIMESTAMP('2022-09-19 22:39:11.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS.USUARIO (IDUSUARIO, ESTATUS, PASSWORD, USERNAME, EMAIL, NOMBRE, FECHAREGISTRO) VALUES (41, 1, '$2a$10$15Ba7W031vm/Iwq3J.As4ObsYusXkj6PLEKKwRg4S9BZK7I/JouJ2', 'admin', 'palmachristopher7@gmail.com', 'Arnie Talaverano', TO_TIMESTAMP('2022-09-18 22:39:13.000000', 'YYYY-MM-DD HH24:MI:SS.FF6'));
INSERT INTO SYS.USUARIO (IDUSUARIO, ESTATUS, PASSWORD, USERNAME, EMAIL, NOMBRE, FECHAREGISTRO) VALUES (78, 1, '$2a$10$Fo4.UQX2vr0FWr6zklSvCu31RHAie1KH2/CJ8vl9SYwflW1Hyt.TS', 'prueba', 'prueba@hotmail.com', 'prueba', TO_TIMESTAMP('2022-09-21 00:33:09.893000', 'YYYY-MM-DD HH24:MI:SS.FF6'));

INSERT INTO SYS.USUARIO_PERFIL (IDUSUARIO, IDPERFIL) VALUES (21, 2);
INSERT INTO SYS.USUARIO_PERFIL (IDUSUARIO, IDPERFIL) VALUES (41, 1);
INSERT INTO SYS.USUARIO_PERFIL (IDUSUARIO, IDPERFIL) VALUES (78, 2);