CREATE DATABASE budgettracker;
USE budgettracker;

CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
);

CREATE TABLE `budgetlist` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`account_id` int(10) unsigned NOT NULL,
`date` date NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`account_id`) 
REFERENCES account(id)
 ON DELETE CASCADE);
 
CREATE TABLE `budgetitem` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`list_id` int(10) unsigned NOT NULL,
`title` varchar(255) NOT NULL,
`amount` float NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY (`list_id`)
REFERENCES budgetlist(id)
ON DELETE CASCADE);

CREATE TABLE `netincome` (
`id` int(10) unsigned NOT NULL AUTO_INCREMENT,
`account_id` int(10) unsigned NOT NULL,
`amount` float NOT NULL,
PRIMARY KEY (`id`),
FOREIGN KEY(`account_id`)
REFERENCES account(id)
ON DELETE CASCADE);