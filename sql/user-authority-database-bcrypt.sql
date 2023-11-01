USE `student_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Default passwords here are: BruceAndLarry123
--

INSERT INTO `users` 
VALUES 
('student','{bcrypt}$2a$12$kZz9oqyENUwFdPJaEekxleqVulWY5m.Lf4YBVHju78gfx8qJ9jM7K',1),
('teacher','{bcrypt}$2a$12$kZz9oqyENUwFdPJaEekxleqVulWY5m.Lf4YBVHju78gfx8qJ9jM7K',1),
('admin','{bcrypt}$2a$12$kZz9oqyENUwFdPJaEekxleqVulWY5m.Lf4YBVHju78gfx8qJ9jM7K',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('student','ROLE_STUDENT'),
('teacher','ROLE_TEACHER'),
('admin','ROLE_ADMIN');