CREATE DATABASE IF NOT EXISTS `student_directory`;
USE `student_directory`;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `student` VALUES 
	(1,'student1','alpha','student1@gmail.com', '+44 7503 827565'),
	(2,'student2','beta','student2@icloud.com','+61 1502 965188'),
	(3,'student3','gamma','student3@yahoo.com', '+88 1998 575697')
