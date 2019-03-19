CREATE DATABASE db_challenge;
USE db_challenge;
CREATE TABLE `challenge` (
  `seq` BIGINT NULL,
  `yn` VARCHAR(1) NULL,
  `age` INT(3) NULL,
  `birthday` DATE NULL,
  `bool` VARCHAR(6) NULL,
  `city` VARCHAR(45) NULL,
  `ccnumber` BIGINT NULL,
  `date_c` DATE NULL,
  `digit` BIGINT NULL,
  `dollar` VARCHAR(45) NULL,
  `first` VARCHAR(45) NULL,
  `chifre` DOUBLE NULL,
  `name` VARCHAR(45) NULL,
  `last` VARCHAR(45) NULL,
  `paragraph` TEXT NULL,
  `sentence` TEXT NULL
  );