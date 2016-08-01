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
INSERT INTO `collaborators` VALUES (1,'�������',0,0),(2,'������',0,0),(3,'��������',0,0),(4,'�������',0,0),(5,'�����',0,0),(6,'�������',0,0),(7,'������',0,0),(8,'�������',0,0),(9,'�������',0,0),(10,'������',0,0),(11,'������',0,0),(12,'��������',0,0),(13,'��������',0,0),(14,'������',0,0),(15,'������',0,0),(16,'������',0,0),(17,'�������',0,0),(18,'����������',0,0),(19,'��������',0,0),(20,'��������',0,0),(21,'Ը�����',0,0),(22,'��������',0,0),(23,'������',0,0),(24,'�������',0,0),(25,'�����',0,0),(26,'�������',0,0),(27,'�����',0,0),(28,'������',0,0),(29,'�������',0,0),(30,'�������',0,0),(31,'������',0,0),(32,'�����',0,0),(33,'�����',0,0),(34,'�����',0,0),(35,'�������',0,0),(36,'���������',0,0),(37,'�������',0,0),(38,'�������',0,0),(39,'��������',0,0),(40,'��������',0,0),(41,'�������',0,0),(42,'�������',0,0),(43,'�������',0,0),(44,'�������',0,0),(45,'�������',0,0),(46,'�������',0,0),(47,'������',0,0),(48,'���������',0,0),(49,'��������',0,0),(50,'���������',0,0),(51,'�������',0,0),(52,'��������',0,0),(53,'�������',0,0),(54,'�������',0,0),(55,'�������',0,0),(56,'�������',0,0),(57,'�����',0,0),(58,'������',0,0),(59,'�������',0,0),(60,'��������',0,0),(61,'������',0,0),(62,'��������',0,0),(63,'�������',0,0),(64,'������',0,0),(65,'��������',0,0),(66,'�������',0,0),(67,'��������',0,0),(68,'������',0,0),(69,'�������',0,0),(70,'������',0,0),(71,'��������',0,0),(72,'�������',0,0),(73,'��������',0,0),(74,'�������',0,0),(75,'�������',0,0),(76,'��������',0,0),(77,'�������',0,0),(78,'��������',0,0),(79,'������',0,0),(80,'���������',0,0),(81,'�������',0,0),(82,'�������',0,0),(83,'������',0,0),(84,'�����������',0,0),(85,'���������',0,0),(86,'��������',0,0),(87,'�������',0,0),(88,'������',0,0),(89,'�������',0,0),(90,'������',0,0),(91,'�����',0,0),(92,'�������',0,0),(93,'���������',0,0),(94,'��������',0,0),(95,'������',0,0),(96,'����������',0,0),(97,'������',0,0),(98,'���������',0,0),(99,'������',0,0),(100,'������',0,0);
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
INSERT INTO `freelancers` VALUES (1,'�������',0),(2,'�������',0),(3,'�����',0),(4,'�������',0),(5,'��������',0),(6,'��������',0),(7,'���������',0),(8,'�������',0),(9,'������',0),(10,'����������',0),(11,'�������',0),(12,'��������',0),(13,'�������',0),(14,'������',0),(15,'������',0),(16,'�������',0),(17,'�������',0),(18,'�����',0),(19,'�������',0),(20,'�������',0),(21,'��������',0),(22,'������',0),(23,'������',0),(24,'������',0),(25,'�����',0),(26,'��������',0),(27,'�������',0),(28,'�������',0),(29,'��������',0),(30,'�����',0),(31,'�������',0),(32,'�������',0),(33,'��������',0),(34,'��������',0),(35,'�������',0),(36,'�������',0),(37,'�������',0),(38,'������',0),(39,'��������',0),(40,'������',0);
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
INSERT INTO `positions` VALUES (1,'�����������','������ ���',90,0),(2,'��������','�������� �����',80,0),(3,'�����������','����������� ���������',70,0),(4,'��������','��������� ������',0,8000),(5,'���������','��������� ����������',0,5000),(6,'�������','��������� ������ � �����',10,0),(7,'��������','������ ������������',0,16000);
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

