-- MySQL dump 10.10
--
-- Host: localhost    Database: collaborators
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1251 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `collaborators`
--

DROP TABLE IF EXISTS `collaborators`;
CREATE TABLE `collaborators` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `salary` int(11) NOT NULL DEFAULT '0',
  `hours` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `collaborators`
--


/*!40000 ALTER TABLE `collaborators` DISABLE KEYS */;
LOCK TABLES `collaborators` WRITE;
INSERT INTO `collaborators` VALUES (1,'Смирнов',0,0),(2,'Иванов',0,0),(3,'Кузнецов',0,0),(4,'Соколов',0,0),(5,'Попов',0,0),(6,'Лебедев',0,0),(7,'Козлов',0,0),(8,'Новиков',0,0),(9,'Морозов',0,0),(10,'Петров',0,0),(11,'Волков',0,0),(12,'Соловьёв',0,0),(13,'Васильев',0,0),(14,'Зайцев',0,0),(15,'Павлов',0,0),(16,'Семёнов',0,0),(17,'Голубев',0,0),(18,'Виноградов',0,0),(19,'Богданов',0,0),(20,'Воробьёв',0,0),(21,'Фёдоров',0,0),(22,'Михайлов',0,0),(23,'Беляев',0,0),(24,'Тарасов',0,0),(25,'Белов',0,0),(26,'Комаров',0,0),(27,'Орлов',0,0),(28,'Киселёв',0,0),(29,'Макаров',0,0),(30,'Андреев',0,0),(31,'Ковалёв',0,0),(32,'Ильин',0,0),(33,'Гусев',0,0),(34,'Титов',0,0),(35,'Кузьмин',0,0),(36,'Кудрявцев',0,0),(37,'Баранов',0,0),(38,'Куликов',0,0),(39,'Алексеев',0,0),(40,'Степанов',0,0),(41,'Яковлев',0,0),(42,'Сорокин',0,0),(43,'Сергеев',0,0),(44,'Романов',0,0),(45,'Захаров',0,0),(46,'Борисов',0,0),(47,'Королёв',0,0),(48,'Герасимов',0,0),(49,'Пономарёв',0,0),(50,'Григорьев',0,0),(51,'Лазарев',0,0),(52,'Медведев',0,0),(53,'Никитин',0,0),(54,'Поляков',0,0),(55,'Цветков',0,0),(56,'Данилов',0,0),(57,'Жуков',0,0),(58,'Фролов',0,0),(59,'Журавлёв',0,0),(60,'Николаев',0,0),(61,'Крылов',0,0),(62,'Максимов',0,0),(63,'Сидоров',0,0),(64,'Осипов',0,0),(65,'Белоусов',0,0),(66,'Федотов',0,0),(67,'Дорофеев',0,0),(68,'Егоров',0,0),(69,'Матвеев',0,0),(70,'Бобров',0,0),(71,'Дмитриев',0,0),(72,'Калинин',0,0),(73,'Анисимов',0,0),(74,'Петухов',0,0),(75,'Антонов',0,0),(76,'Тимофеев',0,0),(77,'Веселов',0,0),(78,'Филиппов',0,0),(79,'Марков',0,0),(80,'Большаков',0,0),(81,'Суханов',0,0),(82,'Миронов',0,0),(83,'Ширяев',0,0),(84,'Александров',0,0),(85,'Коновалов',0,0),(86,'Шестаков',0,0),(87,'Казаков',0,0),(88,'Ефимов',0,0),(89,'Денисов',0,0),(90,'Громов',0,0),(91,'Фомин',0,0),(92,'Давыдов',0,0),(93,'Мельников',0,0),(94,'Щербаков',0,0),(95,'Блинов',0,0),(96,'Колесников',0,0),(97,'Карпов',0,0),(98,'Афанасьев',0,0),(99,'Власов',0,0),(100,'Маслов',0,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `collaborators` ENABLE KEYS */;

--
-- Table structure for table `freelancers`
--

DROP TABLE IF EXISTS `freelancers`;
CREATE TABLE `freelancers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `salary` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `freelancers`
--


/*!40000 ALTER TABLE `freelancers` DISABLE KEYS */;
LOCK TABLES `freelancers` WRITE;
INSERT INTO `freelancers` VALUES (1,'Абрамов',0),(2,'Воронов',0),(3,'Мухин',0),(4,'Архипов',0),(5,'Трофимов',0),(6,'Мартынов',0),(7,'Емельянов',0),(8,'Горшков',0),(9,'Чернов',0),(10,'Овчинников',0),(11,'Селезнёв',0),(12,'Панфилов',0),(13,'Копылов',0),(14,'Михеев',0),(15,'Галкин',0),(16,'Назаров',0),(17,'Лобанов',0),(18,'Лукин',0),(19,'Беляков',0),(20,'Потапов',0),(21,'Некрасов',0),(22,'Хохлов',0),(23,'Жданов',0),(24,'Наумов',0),(25,'Шилов',0),(26,'Воронцов',0),(27,'Ермаков',0),(28,'Дроздов',0),(29,'Игнатьев',0),(30,'Савин',0),(31,'Логинов',0),(32,'Сафонов',0),(33,'Капустин',0),(34,'Кириллов',0),(35,'Моисеев',0),(36,'Елисеев',0),(37,'Кошелев',0),(38,'Костин',0),(39,'Горбачёв',0),(40,'Орехов',0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `freelancers` ENABLE KEYS */;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `position` varchar(20) NOT NULL,
  `action` varchar(40) NOT NULL,
  `rate_hour` int(10) DEFAULT NULL,
  `rate_fix` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `post` (`position`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `positions`
--


/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
LOCK TABLES `positions` WRITE;
INSERT INTO `positions` VALUES (1,'Программист','Писать код',90,0),(2,'Дизайнер','Рисовать макет',80,0),(3,'Тестировщик','Тестировать программу',70,0),(4,'Менеджер','Продавать услуги',0,8000),(5,'Бухгалтер','Составить отчетность',0,5000),(6,'Уборщик','Выполнить уборку в офисе',10,0),(7,'Директор','Давать распоряжения',0,16000);
UNLOCK TABLES;
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

