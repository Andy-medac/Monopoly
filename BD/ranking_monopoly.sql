DROP DATABASE IF EXISTS ranking_monopoly;
CREATE DATABASE ranking_monopoly;
USE ranking_monopoly;

CREATE TABLE ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    dinero INT
);

select * from ranking;