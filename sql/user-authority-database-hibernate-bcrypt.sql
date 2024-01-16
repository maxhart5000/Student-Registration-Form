-- Use the 'student_directory' database
USE `student_directory`;

-- Disable foreign key checks to allow dropping tables
SET foreign_key_checks = 0;

-- Drop existing 'user' and 'role' tables if they exist
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `users_roles`;

-- Enable foreign key checks to ensure referential integrity
SET foreign_key_checks = 1;

-- Table structure for the 'user' table
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` char(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Dumping data for the 'user' table
-- NOTE: Passwords are encrypted using BCrypt
-- Default user 'default' with the password 'BruceAndLarry123'
INSERT INTO `user` (`username`, `password`, `enabled`, `first_name`, `last_name`, `email`)
VALUES 
('default', '$2a$12$kZz9oqyENUwFdPJaEekxleqVulWY5m.Lf4YBVHju78gfx8qJ9jM7K', 1, 'default', 'one', 'default@icloud.com');

-- Table structure for the 'role' table
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

-- Dumping data for the 'role' table
INSERT INTO `role` (name)
VALUES 
('ROLE_STUDENT'), ('ROLE_TEACHER'), ('ROLE_ADMIN');

-- Disable foreign key checks to allow dropping tables
SET FOREIGN_KEY_CHECKS = 0;

-- Table structure for the 'users_roles' table (to define user roles)
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  KEY `FK_ROLE_idx` (`role_id`),
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
    REFERENCES `user` (`id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
    REFERENCES `role` (`id`) 
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Enable foreign key checks to ensure referential integrity
SET FOREIGN_KEY_CHECKS = 1;

-- Dumping data for the 'users_roles' table
-- Assign the 'ROLE_ADMIN' role to 'default' user (user_id: 1)
INSERT INTO `users_roles` (user_id, role_id)
VALUES 
(1, 3);  -- 'ROLE_ADMIN'
