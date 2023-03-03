-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mycms
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_account`
--

DROP TABLE IF EXISTS `sys_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_account` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_account`
--

LOCK TABLES `sys_account` WRITE;
/*!40000 ALTER TABLE `sys_account` DISABLE KEYS */;
INSERT INTO `sys_account` VALUES (1,'admin','123456'),(2,'user1','1234'),(3,'test','000000');
/*!40000 ALTER TABLE `sys_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_comments`
--

DROP TABLE IF EXISTS `sys_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_comments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `create_time` bigint DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_comments`
--

LOCK TABLES `sys_comments` WRITE;
/*!40000 ALTER TABLE `sys_comments` DISABLE KEYS */;
INSERT INTO `sys_comments` VALUES (2,'qwe',1676256492206,'null'),(3,'a',1676263441688,'null'),(5,'哈哈哈哈哈哈哈哈哈哈',1676304145933,'我是谁？');
/*!40000 ALTER TABLE `sys_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_image_code`
--

DROP TABLE IF EXISTS `sys_image_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_image_code` (
  `id` varchar(100) NOT NULL,
  `create_time` bigint DEFAULT NULL,
  `expire_time` bigint DEFAULT NULL,
  `text` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_image_code`
--

LOCK TABLES `sys_image_code` WRITE;
/*!40000 ALTER TABLE `sys_image_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_image_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_students`
--

DROP TABLE IF EXISTS `sys_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_students` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `number` bigint NOT NULL,
  `password` varchar(100) NOT NULL DEFAULT 'eru@123',
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_students`
--

LOCK TABLES `sys_students` WRITE;
/*!40000 ALTER TABLE `sys_students` DISABLE KEYS */;
INSERT INTO `sys_students` VALUES (1,202303011823,'eru@123','李二'),(2,202303011824,'eru@123','林四'),(3,202303011825,'eru@123','张三'),(4,202303011826,'eru@123','廖一');
/*!40000 ALTER TABLE `sys_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_upload_files`
--

DROP TABLE IF EXISTS `sys_upload_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_upload_files` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `fileName` varchar(100) NOT NULL,
  `filePath` varchar(200) NOT NULL,
  `create_time` bigint DEFAULT NULL,
  `fileType` varchar(100) NOT NULL,
  `fileSize` bigint DEFAULT NULL,
  `unid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_upload_files`
--

LOCK TABLES `sys_upload_files` WRITE;
/*!40000 ALTER TABLE `sys_upload_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_upload_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_userinfo`
--

DROP TABLE IF EXISTS `sys_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_userinfo` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `userID` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_userinfo_FK` (`userID`),
  CONSTRAINT `sys_userinfo_FK` FOREIGN KEY (`userID`) REFERENCES `sys_account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_userinfo`
--

LOCK TABLES `sys_userinfo` WRITE;
/*!40000 ALTER TABLE `sys_userinfo` DISABLE KEYS */;
INSERT INTO `sys_userinfo` VALUES (2,'18888888888','深圳市',1),(3,'15688888888','北京市',2),(4,'15688888888','上海市',3);
/*!40000 ALTER TABLE `sys_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'mycms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-03 17:47:19
