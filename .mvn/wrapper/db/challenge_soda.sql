CREATE DATABASE  IF NOT EXISTS `Soda_Store`;
USE `Soda_Store`;

DROP DATABASE IF EXISTS `Soda_Store`;

CREATE TABLE `Soda` (
  `sodaId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `soda_name` varchar(45) DEFAULT NULL,
  `price` decimal(10, 2) NOT NULL,
  `quantity` int NOT NULL
);

select * from Soda;

drop table Soda;