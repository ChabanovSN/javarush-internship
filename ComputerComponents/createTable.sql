DROP SCHEMA IF EXISTS `test` ;

CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET UTF8MB4 ;

USE test;

CREATE TABLE `components` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `isNecessary` tinyint(4) NOT NULL DEFAULT '0',
  `amount` int(6) NOT NULL  DEFAULT '0',
  PRIMARY KEY (id)) 
  ENGINE=InnoDB DEFAULT CHARSET = UTF8MB4;
  
ALTER TABLE `components` ADD UNIQUE INDEX  (`name`);


INSERT INTO `components` (`name`, `isNecessary`, `amount`) VALUES
('материнская плата', 1, 789),
('звуковая карта', 0, 543),
('процессор', 1, 897),
('подсветка корпуса', 0, 111),
('HDD диск', 1, 235),
('корпус', 1, 128),
('оперативная память', 1, 4530),
('SSD диск', 0, 543),
('видеокарта', 1, 978),
('блок питания', 1, 659),
('шлейф', 0, 523),
('FDD', 0, 2),
('монитор', 1, 928),
('мышь', 1, 789),
('клавиатура', 1, 488),
('кулер', 1, 765),
('джойстик', 0, 321),
('звуковая карта2', 0, 54),
('оптический привод', 0, 991),
('термопаста', 1, 555),
('сетевой адаптер', 0, 333),
('тв-тюнер', 0, 888),
('разветвитель', 0, 111),
('принтер', 0, 222),
('роутер', 0, 444),
('web-камера', 0, 744),
('колонки', 0, 344),
('наушники', 0, 1),
('проектор', 0, 54),
('удлинитель', 0, 777);

