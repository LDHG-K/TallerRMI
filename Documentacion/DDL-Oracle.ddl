CREATE USER PROYECTO IDENTIFIED BY PROYECTO;
GRANT DBA TO PROYECTO;


CREATE TABLE Participantes (id number(19, 0) NOT NULL, apodo varchar2(30) NOT NULL UNIQUE, fecha_inscripcion date NOT NULL, fecha_caducidad date NOT NULL, PRIMARY KEY (id));
CREATE TABLE Mesas (id_mesa number(19, 0) NOT NULL, localidad varchar2(150), nombre_lugar varchar2(150), PRIMARY KEY (id_mesa));
CREATE TABLE Partidos (mesa_id number(19, 0) NOT NULL, parcipante_1 number(19, 0) NOT NULL, parcipante_2 number(19, 0) NOT NULL, ganador number(10) NOT NULL, ronda number(10) NOT NULL, fecha_programada timestamp NOT NULL, hora_inicio timestamp NOT NULL, hora_fin timestamp NOT NULL, torneo varchar2(100) NOT NULL, PRIMARY KEY (mesa_id, parcipante_1, parcipante_2));
ALTER TABLE Partidos ADD CONSTRAINT FKPartidos557789 FOREIGN KEY (parcipante_1) REFERENCES Participantes (id);
ALTER TABLE Partidos ADD CONSTRAINT FKPartidos246987 FOREIGN KEY (mesa_id) REFERENCES Mesas (id_mesa);
ALTER TABLE Partidos ADD CONSTRAINT FKPartidos557788 FOREIGN KEY (parcipante_2) REFERENCES Participantes (id);

CREATE SEQUENCE seq_Participantes;
CREATE SEQUENCE seq_Mesas;
CREATE SEQUENCE seq_Partidos;






RESETEAR SECUENCIAS
ALTER SEQUENCE [schema].[sequencename] RESTART WITH [new value]; 