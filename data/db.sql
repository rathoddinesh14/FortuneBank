/*!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19-11.4.2-MariaDB, for osx10.17 (x86_64)
--
-- Host: localhost    Database: fortunebankdatabase
-- ------------------------------------------------------
-- Server version	11.4.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `aid` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `landmark` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `pincode` int(11) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `accountnumber` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `UK_c29k1p0rbffh2cr8oa8wtx72o` (`userid`),
  KEY `FKefhcrgyigeqfjr49b5u4bnxyw` (`accountnumber`),
  CONSTRAINT `FKefhcrgyigeqfjr49b5u4bnxyw` FOREIGN KEY (`accountnumber`) REFERENCES `user_details` (`account_number`),
  CONSTRAINT `FKjswwla6bkr9ksa1wxlawjunpm` FOREIGN KEY (`userid`) REFERENCES `user_details` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES
(1,NULL,'Apple building','road 1','street 2',560103,'Karnataka',NULL,NULL,6),
(2,NULL,'Apple building','road 1','street 2',560103,'Karnataka',NULL,NULL,6),
(3,NULL,'tesla towers','elon nagar','musk street',560103,'Karnataka',NULL,NULL,7),
(4,NULL,'tesla towers','elon nagar','musk street',560103,'Karnataka',NULL,NULL,7),
(5,'Bengaluru','mc square','road albert','einstein street',560103,'Karnataka',NULL,'Temporary',8),
(6,'Bengaluru','kaverappa layout, marathahalli','room no b05','saveria winston',560103,'Karnataka',NULL,'Permanent',8),
(7,'Bengaluru','mc square','road albert','einstein street',560103,'Karnataka',NULL,'Temporary',10),
(8,'Bengaluru','kaverappa layout, marathahalli','room no b05','saveria winston',560103,'Karnataka',NULL,'Permanent',10),
(9,'Bengaluru','marie kondo','marie','kondo',560103,'Karnataka',NULL,'Temporary',11),
(10,'Bengaluru','marie kondo','marie','kondo',560103,'Karnataka',NULL,'Temporary',11),
(11,'Goa','Jennifer towers','road no 1','lane no 2',123456,'Goa',NULL,'Temporary',12),
(12,'Hyderabad','Goa Church','road no 2','lane no 5',500001,'Telangana',NULL,'Permanent',12),
(15,'Bengaluru','Kaverappa Layout, kadubeesanahalli','Flat no B5','Saveria Winston',560103,'Karnataka',NULL,'Temporary',15),
(16,'Bengaluru','Kaverappa Layout, kadubeesanahalli','Flat no B5','Saveria Winston',560103,'Karnataka',NULL,'Temporary',15);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficiary`
--

DROP TABLE IF EXISTS `beneficiary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beneficiary` (
  `bid` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `payee_account_number` bigint(20) DEFAULT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `FKg9gfksl0fdcwyg7ue8ujcvu7a` (`payee_account_number`),
  KEY `FK1j4h1qsuobqvd33romu5qwhho` (`account_number`),
  CONSTRAINT `FK1j4h1qsuobqvd33romu5qwhho` FOREIGN KEY (`account_number`) REFERENCES `user_details` (`account_number`),
  CONSTRAINT `FKg9gfksl0fdcwyg7ue8ujcvu7a` FOREIGN KEY (`payee_account_number`) REFERENCES `user_details` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficiary`
--

LOCK TABLES `beneficiary` WRITE;
/*!40000 ALTER TABLE `beneficiary` DISABLE KEYS */;
INSERT INTO `beneficiary` VALUES
(1,'Marie','Kondo',11,15);
/*!40000 ALTER TABLE `beneficiary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `net_banking_user`
--

DROP TABLE IF EXISTS `net_banking_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `net_banking_user` (
  `account_number` bigint(20) NOT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `transaction_password` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  UNIQUE KEY `UK_7d4l5j5mfkpg3cwfqnoenm97a` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `net_banking_user`
--

LOCK TABLES `net_banking_user` WRITE;
/*!40000 ALTER TABLE `net_banking_user` DISABLE KEYS */;
INSERT INTO `net_banking_user` VALUES
(1,'yoyo','transactionpass456','1'),
(2,'pass','pass','2'),
(10,'yoyo','pass','10'),
(11,'yoyo','pass','11'),
(15,'pass','pass','15');
/*!40000 ALTER TABLE `net_banking_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `maturity_instructions` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `from_account_number` bigint(20) DEFAULT NULL,
  `to_account_number` bigint(20) DEFAULT NULL,
  `transaction_type` enum('DEPOSIT','TRANSFER','WITHDRAWAL') DEFAULT NULL,
  `transaction_mode` enum('IMPS','NEFT','RTGS','SELF') DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `FK5xnct3uk6swx8fnf3afc6wei1` (`from_account_number`),
  KEY `FKm07jop9ub81uxbd7x4st2vnjd` (`to_account_number`),
  CONSTRAINT `FK5xnct3uk6swx8fnf3afc6wei1` FOREIGN KEY (`from_account_number`) REFERENCES `user_details` (`account_number`),
  CONSTRAINT `FKm07jop9ub81uxbd7x4st2vnjd` FOREIGN KEY (`to_account_number`) REFERENCES `user_details` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES
(1,1000,'2023-08-20 10:40:49.044854','No','Payment for services',1,2,'TRANSFER',NULL),
(2,1000,'2023-08-20 10:41:09.562024','No','Payment for services',1,2,'TRANSFER',NULL),
(3,1000,'2023-08-20 10:41:27.630546','No','Payment for services',2,1,'TRANSFER',NULL),
(4,200,'2023-08-22 18:23:55.588145','No','UPI',1,2,'TRANSFER',NULL),
(5,100,'2023-08-23 20:05:49.862937','No','paratha',11,10,'TRANSFER',NULL),
(6,50,'2023-08-23 20:08:51.668576','No','frooti',11,10,'TRANSFER',NULL),
(7,20,'2023-08-23 20:20:23.284254','No','coca cola',11,10,'TRANSFER',NULL),
(8,10,'2023-08-23 20:23:06.696666','No','Oreo biscuit',11,10,'TRANSFER',NULL),
(9,20,'2023-08-23 20:28:29.795891','No','Dosa',11,10,'TRANSFER',NULL),
(10,10,'2023-08-23 20:33:09.718226','No','Tea',10,11,'TRANSFER',NULL),
(11,20,'2023-08-23 21:17:20.891908','No','Pani puri',10,11,'TRANSFER',NULL),
(12,170,'2023-08-24 19:26:50.979405','No','Deposit',11,11,'DEPOSIT',NULL),
(13,1000,'2023-08-24 19:28:19.047193','No','Withdrawal',11,11,'WITHDRAWAL',NULL),
(14,1000,'2023-08-24 19:29:17.866043','No','Deposit',11,11,'DEPOSIT',NULL),
(15,1000,'2023-08-24 19:30:50.557427','No','Withdrawal',11,11,'WITHDRAWAL',NULL),
(16,1000,'2023-08-24 19:31:05.918943','No','Deposit',11,11,'DEPOSIT',NULL),
(17,2000,'2023-08-24 19:42:30.075409','No','Mangoes',11,10,'TRANSFER',NULL),
(18,-100,'2023-08-27 21:28:02.975453','No','Deposit',10,10,'DEPOSIT',NULL),
(19,100,'2023-08-27 21:30:09.808921','No','Deposit',10,10,'DEPOSIT',NULL),
(20,100,'2023-08-27 21:30:16.289616','No','Withdrawal',10,10,'WITHDRAWAL',NULL),
(21,100,'2023-08-27 21:30:20.316368','No','Deposit',10,10,'DEPOSIT',NULL),
(22,30,'2023-08-27 23:44:17.701729','No','Deposit',10,10,'DEPOSIT',NULL),
(23,100,'2023-08-28 18:31:16.510441','','',10,11,'TRANSFER',NULL),
(24,100,'2023-08-28 18:56:10.513641','','Pani Puri',10,11,'TRANSFER',NULL),
(25,200.23,'2023-08-28 19:14:53.448446','No','Deposit',10,10,'DEPOSIT',NULL),
(26,0.77,'2023-08-28 19:19:55.782385','No','Deposit',10,10,'DEPOSIT',NULL),
(27,5000,'2023-08-28 19:20:46.886146','No','Deposit',10,10,'DEPOSIT',NULL),
(28,5,'2023-08-28 21:32:42.140049','','Checking IMPS',10,11,'TRANSFER','IMPS'),
(29,4,'2023-08-28 21:35:01.645521','No','Deposit',10,10,'DEPOSIT','SELF'),
(30,4,'2023-08-28 21:35:28.224239','No','Withdrawal',10,10,'WITHDRAWAL','SELF'),
(31,6,'2023-08-28 21:38:09.071223','No','Withdrawal',10,10,'WITHDRAWAL','SELF'),
(32,6,'2023-08-28 21:38:18.104239','No','Deposit',10,10,'DEPOSIT','SELF'),
(33,10000,'2023-08-28 23:21:56.612143','No','Deposit',15,15,'DEPOSIT','SELF'),
(34,500,'2023-08-28 23:34:36.431425','','Samosa',15,11,'TRANSFER','NEFT');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `account_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `aadhar_number` varchar(255) DEFAULT NULL,
  `account_type` int(11) NOT NULL,
  `balance` double NOT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `father_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `account_status` enum('DISABLED','ENABLED') DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  UNIQUE KEY `UK_963f6be8f012f3f0bcimig7da` (`aadhar_number`),
  UNIQUE KEY `UK_4d9rdl7d52k8x3etihxlaujvh` (`email`),
  UNIQUE KEY `UK_hwi9icwrbompowjo1l8mqqu49` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES
(1,'1234-5678-9012',0,0,'1990-05-15','john.doe@example.com','Michael Smith','John','Smith','Doe','1234567890','ENABLED'),
(2,'374004225945',0,0,'1997-05-14','7rathod7@gmail.com','','Dinesh','Rathod','','7045800407','ENABLED'),
(6,'37736487',0,0,'0012-12-12','tim@cook.com','david','tim','cook','','1341','ENABLED'),
(7,'938479',0,0,'0013-12-13','elon@musk.com','yo mush','elon','musk','','543453','ENABLED'),
(8,'403890',0,0,'0412-12-14','albert@einstein.com','goldstein','albert','einstein','','456453','ENABLED'),
(10,'84738',0,7196,'1997-08-10','ganesh@landge.com','','ganesh','landge','','342112','ENABLED'),
(11,'123123123123',0,998705,'0013-12-13','marie@kondo.com','','marie','kondo','','6767676767','ENABLED'),
(12,'213443121234',0,0,'0012-12-12','jennifer@lawrence.com','thomas','jennifer','lawrence','','1000000000','ENABLED'),
(15,'123443211234',0,9500,'1978-05-13','abdul@kalam.com','Kalam','Abdul','Kalam','','9848012123','ENABLED');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_occupation`
--

DROP TABLE IF EXISTS `user_occupation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_occupation` (
  `gross_annual_income` double NOT NULL,
  `income_source` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `account_number` bigint(20) NOT NULL,
  PRIMARY KEY (`account_number`),
  CONSTRAINT `FK3hkh732mdyed1xx7vgysa8ppa` FOREIGN KEY (`account_number`) REFERENCES `user_details` (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_occupation`
--

LOCK TABLES `user_occupation` WRITE;
/*!40000 ALTER TABLE `user_occupation` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_occupation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2024-07-07 19:35:07
