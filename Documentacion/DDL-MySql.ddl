CREATE TABLE Participante (id int(10) NOT NULL AUTO_INCREMENT, apodo varchar(30) NOT NULL UNIQUE, fecha_inscripcion date NOT NULL, fecha_caducidad date NOT NULL, PRIMARY KEY (id));
CREATE TABLE Mesa (id_mesa int(10) NOT NULL AUTO_INCREMENT, localidad varchar(150), nombre_lugar varchar(150), PRIMARY KEY (id_mesa));
CREATE TABLE Partido (mesa_id int(10) NOT NULL, parcipante_1 int(10) NOT NULL, parcipante_2 int(10) NOT NULL, ganador int(10) NOT NULL, ronda int(10) NOT NULL, fecha_programada timestamp NOT NULL, hora_inicio timestamp NOT NULL, hora_fin timestamp NOT NULL, torneo varchar(100) NOT NULL, PRIMARY KEY (mesa_id, parcipante_1, parcipante_2));
ALTER TABLE Partido ADD CONSTRAINT FKPartidos246987 FOREIGN KEY (mesa_id) REFERENCES Mesa (id_mesa);
ALTER TABLE Partido ADD CONSTRAINT FKPartidos100494 FOREIGN KEY (parcipante_2) REFERENCES Participante (id);
ALTER TABLE Partido ADD CONSTRAINT FKPartidos100493 FOREIGN KEY (parcipante_1) REFERENCES Participante (id);
