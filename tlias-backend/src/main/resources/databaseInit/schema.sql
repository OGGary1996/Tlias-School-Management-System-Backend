-- MySQL dump 10.13  Distrib 8.0.42, for macos15.2 (arm64)
--
-- Host: localhost    Database: tlias_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Current Database: `tlias_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `tlias_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tlias_db`;

--
-- Table structure for table `clazz`
--

DROP TABLE IF EXISTS `clazz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clazz` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the class',
  `name` varchar(50) NOT NULL COMMENT 'Name of the class, must be unique',
  `room` varchar(20) DEFAULT NULL COMMENT 'Room number where the class is held',
  `begin_date` date NOT NULL COMMENT 'Start date of the class, format is YYYY-MM-DD',
  `end_date` date NOT NULL COMMENT 'End date of the class, format is YYYY-MM-DD',
  `master_id` tinyint unsigned DEFAULT NULL COMMENT 'ID of the class master, references employee table',
  `subject_id` tinyint unsigned NOT NULL COMMENT 'ID of the subject taught in the class, references subject table',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the class record',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the class record',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the department',
  `name` varchar(50) NOT NULL COMMENT 'Name of the department',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the department',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the department',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the employee',
  `username` varchar(20) NOT NULL COMMENT 'Name of the employee, could not be unique',
  `password` varchar(32) DEFAULT '123456' COMMENT 'Password of the employee, default is 123456',
  `name` varchar(50) NOT NULL COMMENT 'Full name of the employee',
  `gender` tinyint unsigned NOT NULL COMMENT 'Gender，Male；1 Female；0',
  `phone` char(10) CHARACTER SET ucs2 COLLATE ucs2_general_ci NOT NULL COMMENT 'Phone number of the employee, 10 digits',
  `job_title` tinyint unsigned NOT NULL COMMENT 'Job title of the employee, 0 for Professor, 1 for Lecturer, 2 for Assistant, 3 for Administrator, 4 for Counselor, 5 for Librarian, 6 for IT Specialist, 7 for HR Manager, 8 for Financial Analyst, 9 for Maintenance Staff',
  `department_id` int unsigned NOT NULL COMMENT 'ID of the department the employee belongs to, references departments such as Admissions, Academic Affairs, Student Affairs, Library Services, IT Support, etc.',
  `salary` decimal(10,2) unsigned NOT NULL COMMENT 'Salary of the employee, up to 10 digits with 2 decimal places',
  `image` varchar(500) DEFAULT 'https://i.pravatar.cc/250?u=<nobody>' COMMENT 'Image of the employee, default is default.jpg',
  `entry_date` date NOT NULL COMMENT 'Entry date of the employee, format is YYYY-MM-DD',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the employee record',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the employee record',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_history`
--

DROP TABLE IF EXISTS `employee_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_history` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key for employee history record',
  `start_date` date NOT NULL COMMENT 'Start date of previous job',
  `end_date` date NOT NULL COMMENT 'End date of previous job',
  `job_title` varchar(50) NOT NULL COMMENT 'Job title held previously',
  `company_name` varchar(100) NOT NULL COMMENT 'Name of the company previously worked at',
  `employee_id` int unsigned NOT NULL COMMENT 'Logical foreign key referencing employee table',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operation_log`
--

DROP TABLE IF EXISTS `operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operation_log` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `operate_emp_id` tinyint unsigned NOT NULL,
  `operate_emp_name` varchar(50) NOT NULL,
  `operate_time` datetime NOT NULL,
  `java_class_name` text NOT NULL,
  `java_method_name` text NOT NULL,
  `java_method_params` text,
  `java_method_return` text,
  `cost_time` int unsigned NOT NULL,
  `is_success` tinyint unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the position',
  `name` varchar(50) NOT NULL COMMENT 'Name of the position, e.g., Professor, Lecturer, etc.',
  `description` varchar(255) DEFAULT NULL COMMENT 'Description of the position, optional',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the position record',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the position record',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the student',
  `name` varchar(50) NOT NULL COMMENT 'Full name of the student',
  `account_number` varchar(20) NOT NULL COMMENT 'Unique account number for the student',
  `gender` tinyint unsigned NOT NULL COMMENT 'Gender of the student, 0 -> Female, 1 -> Male',
  `phone` char(10) NOT NULL COMMENT 'Phone number of the student, 10 digits',
  `id_card` varchar(18) NOT NULL COMMENT 'ID card number of the student, 18 digits',
  `address` varchar(255) NOT NULL COMMENT 'Residential address of the student',
  `clazz_id` tinyint unsigned NOT NULL COMMENT 'ID of the class the student belongs to, references clazz table',
  `is_college` tinyint unsigned NOT NULL DEFAULT '0' COMMENT 'Whether the student is from college, 0 -> No, 1 -> Yes',
  `degree` tinyint unsigned NOT NULL DEFAULT '0' COMMENT 'Degree type, 0 -> high school, 1 -> bachelor, 2 -> master, 3 -> PhD',
  `graduation_date` date NOT NULL DEFAULT '2020-01-01' COMMENT 'Graduation date, format is YYYY-MM-DD',
  `violation_count` tinyint unsigned DEFAULT '0' COMMENT 'Number of violations by the student, default is 0',
  `violation_score` tinyint unsigned DEFAULT '0' COMMENT 'Score for violations, default is 0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the student record',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the student record',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_number` (`account_number`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Unique identifier for the subject',
  `name` varchar(50) NOT NULL COMMENT 'Name of the subject, must be unique',
  `description` varchar(255) DEFAULT NULL COMMENT 'Description of the subject, optional',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time of the subject record',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update time of the subject record',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'tlias_db'
--

--
-- Dumping routines for database 'tlias_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-12 16:01:55
