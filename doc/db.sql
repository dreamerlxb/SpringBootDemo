-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: lxb_forum
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (8);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_article`
--

DROP TABLE IF EXISTS `t_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_count` bigint(20) DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `edit_mode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsgqpqfl0p7olcr7694a3pjl0q` (`user_id`),
  CONSTRAINT `FKsgqpqfl0p7olcr7694a3pjl0q` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_article`
--

LOCK TABLES `t_article` WRITE;
/*!40000 ALTER TABLE `t_article` DISABLE KEYS */;
INSERT INTO `t_article` (`id`, `comment_count`, `content`, `create_time`, `edit_mode`, `image`, `title`, `update_time`, `user_id`) VALUES (1,0,'——习近平主席致第五届世界互联网大会贺信引发中外人士共鸣\r\n\r\n察看托老所 关注老有所养\r\n\r\n11月6日上午，习近平来到虹口区市民驿站嘉兴路街道第一分站，与居民和社区工作者亲切交谈，并祝他们生活愉快。\r\n\r\n嘉兴路街道第一分站，是虹口区设置的35个市民驿站之一。为满足居民社区生活各方面需求，虹口区努力打造“15分钟社区生活服务圈”。\r\n\r\n在托老所老年人日间照护中心，几位年龄在79至92岁的老年居民正在制作手工艺品，总书记亲切向他们问好，并与他们一一握了手。老人们激动地握着总书记的手，向总书记描述自己的幸福晚年生活。有位老爷爷和总书记握了三次手。\r\n\r\n习近平指出，我国已经进入老龄社会，让老年人老有所养、生活幸福、健康长寿是我们的共同愿望。党中央高度重视养老服务工作，要把政策落实到位，惠及更多老年人。','2018-11-08 04:13:10','MARKDOWN',NULL,'让网络空间命运共同体更具生机活力','2018-11-08 04:13:10',1);
/*!40000 ALTER TABLE `t_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_authority`
--

DROP TABLE IF EXISTS `t_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `auth_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_authority`
--

LOCK TABLES `t_authority` WRITE;
/*!40000 ALTER TABLE `t_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_authority_resource`
--

DROP TABLE IF EXISTS `t_authority_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_authority_resource` (
  `resource_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FK335m26liaj4l992g72tom888q` (`authority_id`),
  KEY `FK2rsmv2vvpc2cp3mt7ndpu1gng` (`resource_id`),
  CONSTRAINT `FK2rsmv2vvpc2cp3mt7ndpu1gng` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `FK335m26liaj4l992g72tom888q` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_authority_resource`
--

LOCK TABLES `t_authority_resource` WRITE;
/*!40000 ALTER TABLE `t_authority_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_authority_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_comment`
--

DROP TABLE IF EXISTS `t_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `article_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlsvvc2ob8lxg2m9qqry15ru0y` (`article_id`),
  KEY `FKtamaoacctq4qpko6bvtv0ke1p` (`user_id`),
  CONSTRAINT `FKlsvvc2ob8lxg2m9qqry15ru0y` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`),
  CONSTRAINT `FKtamaoacctq4qpko6bvtv0ke1p` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_comment`
--

LOCK TABLES `t_comment` WRITE;
/*!40000 ALTER TABLE `t_comment` DISABLE KEYS */;
INSERT INTO `t_comment` (`id`, `content`, `create_time`, `update_time`, `article_id`, `user_id`) VALUES (1,'<p><img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/smiley.png\" class=\"emoji\" title=\":smiley:\" alt=\":smiley:\"> <img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/anguished.png\" class=\"emoji\" title=\":anguished:\" alt=\":anguished:\"> <img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/open_mouth.png\" class=\"emoji\" title=\":open_mouth:\" alt=\":open_mouth:\"> <img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/grimacing.png\" class=\"emoji\" title=\":grimacing:\" alt=\":grimacing:\"> <img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/neckbeard.png\" class=\"emoji\" title=\":neckbeard:\" alt=\":neckbeard:\"> <img src=\"https://www.webpagefx.com/tools/emoji-cheat-sheet/graphics/emojis/green_heart.png\" class=\"emoji\" title=\":green_heart:\" alt=\":green_heart:\"></p>\n<p>测试评论</p>\n','2018-11-08 04:13:56','2018-11-08 04:13:56',1,1);
/*!40000 ALTER TABLE `t_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dict`
--

DROP TABLE IF EXISTS `t_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dict_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dict_value` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `child_num` tinyint(4) DEFAULT '0' COMMENT '直接子节点个数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict`
--

LOCK TABLES `t_dict` WRITE;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
INSERT INTO `t_dict` (`id`, `p_id`, `code`, `dict_key`, `dict_value`, `name`, `child_num`, `create_time`, `update_time`, `is_parent`) VALUES (1,NULL,'0','ROOT','根','根节点',2,'2018-01-25 09:56:52','2018-05-25 09:56:59',NULL),(2,1,'0-0',NULL,NULL,'0-0节点',2,'2019-01-25 09:57:52','2019-01-25 09:57:55',NULL),(3,1,'0-1',NULL,NULL,'0-1节点',0,'2019-01-25 09:58:52','2019-01-25 09:58:54',NULL),(4,2,'0-0-0',NULL,NULL,'0-0-0节点',0,'2019-01-25 10:00:11','2019-01-25 10:00:08',NULL),(5,2,'0-0-1',NULL,NULL,'0-0-1节点',0,'2019-01-25 10:07:56','2019-01-25 10:07:59',NULL);
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_file_model`
--

DROP TABLE IF EXISTS `t_file_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_file_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mimeType` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `uploadDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_file_model`
--

LOCK TABLES `t_file_model` WRITE;
/*!40000 ALTER TABLE `t_file_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_file_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_parent` bit(1) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `res_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_index_parent_id` (`p_id`),
  KEY `FKn04fvxnaojjtmag4lkrw9mk45` (`res_id`),
  CONSTRAINT `FKn04fvxnaojjtmag4lkrw9mk45` FOREIGN KEY (`res_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_post`
--

DROP TABLE IF EXISTS `t_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_ci,
  `create_time` datetime DEFAULT NULL,
  `edit_mode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reply_count` bigint(20) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK18f01t655nkcuptq9oxp04sgn` (`user_id`),
  CONSTRAINT `FK18f01t655nkcuptq9oxp04sgn` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
INSERT INTO `t_post` (`id`, `content`, `create_time`, `edit_mode`, `image`, `reply_count`, `title`, `type`, `update_time`, `user_id`) VALUES (1,':bowtie: :stuck_out_tongue: :persevere: :innocent:\r\n是大V你不是酒店女就是你的V领是','2018-11-08 04:15:48','MARKDOWN',NULL,0,'让网络空间命运共同体更具生机活力','ANSWER','2018-11-08 04:15:48',1);
/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reply`
--

DROP TABLE IF EXISTS `t_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_reply` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKax4v67ap6gkf3oxh750ql5ryi` (`post_id`),
  KEY `FKslt6r79iw1p9cbxns09erjv6v` (`user_id`),
  CONSTRAINT `FKax4v67ap6gkf3oxh750ql5ryi` FOREIGN KEY (`post_id`) REFERENCES `t_post` (`id`),
  CONSTRAINT `FKslt6r79iw1p9cbxns09erjv6v` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reply`
--

LOCK TABLES `t_reply` WRITE;
/*!40000 ALTER TABLE `t_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_resource`
--

DROP TABLE IF EXISTS `t_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_resource`
--

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;
INSERT INTO `t_resource` (`id`, `enabled`, `name`, `remark`, `url`, `url_key`) VALUES (1,'','测试资源','测试增删改','/sadcnsd/sadcnsjdvks','a_b_c');
/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_key` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_authority`
--

DROP TABLE IF EXISTS `t_role_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_role_authority` (
  `role_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL,
  KEY `FK7thtwmk7w5yay0k6q7nob0t6t` (`authority_id`),
  KEY `FKej7eeolya4yimf2c163rnnoi9` (`role_id`),
  CONSTRAINT `FK7thtwmk7w5yay0k6q7nob0t6t` FOREIGN KEY (`authority_id`) REFERENCES `t_authority` (`id`),
  CONSTRAINT `FKej7eeolya4yimf2c163rnnoi9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_authority`
--

LOCK TABLES `t_role_authority` WRITE;
/*!40000 ALTER TABLE `t_role_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_setting`
--

DROP TABLE IF EXISTS `t_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `edit_mode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrmy0hvxg3jbpbo993sbhnckqo` (`user_id`),
  CONSTRAINT `FKrmy0hvxg3jbpbo993sbhnckqo` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_setting`
--

LOCK TABLES `t_setting` WRITE;
/*!40000 ALTER TABLE `t_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_star`
--

DROP TABLE IF EXISTS `t_star`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_star` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `target_id` bigint(20) DEFAULT NULL,
  `target_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa3mxmqaibrutqm4ftbxfi1tur` (`user_id`),
  CONSTRAINT `FKa3mxmqaibrutqm4ftbxfi1tur` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_star`
--

LOCK TABLES `t_star` WRITE;
/*!40000 ALTER TABLE `t_star` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_star` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `last_pwd_update_date` datetime DEFAULT NULL,
  `nickname` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_pwd_reset_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKf18xnr9jlt8h4y23nb4fnh9u` (`username`,`phone`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `avatar`, `create_time`, `email`, `enabled`, `last_pwd_update_date`, `nickname`, `password`, `phone`, `update_time`, `username`, `last_pwd_reset_date`) VALUES (1,NULL,'2018-07-16 16:29:08','test@test.com','',NULL,'test1','$2a$10$dLXNqmo2nztQSLKQSbckjOORvv.chNWTqKOWyZi7JtcGAaDw.0cD6',NULL,'2018-07-16 16:29:08','test1',NULL),(2,NULL,'2018-07-16 16:29:08','test1@163.com','',NULL,'test2','$2a$10$A.yzCG7sJSYssfY.6z4bauDxfj1jll0IS//Blu2/j2jJixk7cvPWi','13465425935','2018-07-16 16:29:08','test2','2018-06-27 15:33:46'),(3,NULL,'2018-07-16 16:29:08','test3@test.com','',NULL,'test3',NULL,NULL,'2018-07-16 16:29:08','test3',NULL),(4,NULL,'2018-07-16 16:31:15','test4@test.com','',NULL,'test4',NULL,NULL,'2018-07-16 16:31:15','test4',NULL),(5,NULL,'2018-07-16 16:47:08','test5@test.com','',NULL,'test5','123456',NULL,'2018-07-16 16:47:08','test5',NULL),(6,NULL,'2018-07-17 17:59:10','test5@test.com','',NULL,'test6','123456',NULL,'2018-07-17 17:59:10','test6',NULL),(7,NULL,'2018-07-17 18:10:59','test.com','',NULL,'test7','123456',NULL,'2018-07-17 18:10:59','test7',NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FKq5un6x7ecoef5w1n39cop66kl` (`user_id`),
  KEY `FKa9c8iiy6ut0gnx491fqx4pxam` (`role_id`),
  CONSTRAINT `FKa9c8iiy6ut0gnx491fqx4pxam` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKq5un6x7ecoef5w1n39cop66kl` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-10 14:26:59
