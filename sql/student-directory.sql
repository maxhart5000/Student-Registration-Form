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
  `sex` varchar(45) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `student` VALUES 
	(1,'John','Snow','Male','Mathematics','johnsnow@gmail.com', '+44 7503827523'),
	(2,'Cersei','Lannister','Female','Physics','cerseilannister@icloud.com','+61 1502965142'),
	(3,'Rob','Stark','Male','History','robstark@yahoo.com','+88 9957569752')
