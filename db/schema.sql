CREATE DATABASE OOINUZA;

CREATE TABLE `ooinuza`.`updatepacklist_packs` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `bot_id` int(12) NOT NULL,
  `number` int(12) NOT NULL,
  `name` text CHARACTER SET utf8,
  `size` varchar(8) CHARACTER SET latin1 NOT NULL,
  `episode_number` int(12) DEFAULT NULL,
  `last_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `packid` (`number`),
  KEY `botid` (`bot_id`),
  KEY `last_modified` (`last_modified`),
  CONSTRAINT `updatepacklist_packs_ibfk_1` FOREIGN KEY (`bot_id`) REFERENCES `updatepacklist_bots` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `ooinuza`.`updatepacklist_bots` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `url` varchar(200) NOT NULL,
  `type` varchar(10) NOT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `status_id` int(2) NOT NULL,
  `last_seen` datetime NOT NULL,
  `last_processed` datetime NOT NULL,
  `informative` tinyint(4) NOT NULL,
  `batchenable` tinyint(1) NOT NULL DEFAULT '1',
  `external` tinyint(1) NOT NULL DEFAULT '0',
  `parser_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `updatepacklist_bots_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `updatepacklist_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;