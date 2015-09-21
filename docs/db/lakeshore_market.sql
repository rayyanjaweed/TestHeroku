-- MySQL dump 10.13  Distrib 5.6.26, for osx10.8 (x86_64)
--
-- Host: localhost    Database: lakeshore_market
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(8) NOT NULL AUTO_INCREMENT,
  `line1` varchar(255) NOT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `line3` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `postal_code` varchar(255) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'123 Any St.',NULL,NULL,'Chicago','IL','USA','60626'),(2,'Jasper Bauer','25161 Cheshire',NULL,'Mission Viejo','California','USA','92692'),(3,'Alan Perez',NULL,'401 Harris Ave','Bellingham','Washington','USA','98225'),(4,'Amy Aotuli','105 East Kincaid Street','Unit 14','Watsonville','Alabama','USA','20392'),(5,'Mr. Kyle Cornelius','27111 Florence Way',NULL,'Colfax','Colorado','USA','37483'),(6,'Meghan Miller','3201 Smith Ave.','7N','West Sacramento','Delaware','USA','44434'),(7,'Jennie Duke','211 Railroad Ave.','Apartment H','Riverside','Montana','USA','28394'),(8,'Kim Clemens','7301 Longacres Way','Unit 17','Hayward','Missouri','USA','03948'),(9,'Brian Shore','1001 Puyallup Ave',NULL,'Bullhead City','West Virginia','USA','23443'),(10,'Venita Howes','6600 Yelm','Apartment 6C','Los Angeles','California','USA','19283'),(11,'Dr. Ariel Carr','210 Portolla',NULL,'Delano','Washington','USA','29384'),(12,'Amanda Deltoro','111 N. Kittina','Unit 4','San Fernando','Illinois','USA','11123'),(13,'Marcus King','444 South Columbia',NULL,'Allensburg','Wyoming','USA','39934'),(14,'Alan Delgado','11645 North Road','Apartment 14','Modesto','Virginia','USA','83948'),(15,'Cameron Rio','905 5th Street','PO Box 1113','Kingman','Arizona','USA','34432'),(16,'Chelsey Helding','407 N. K Street',NULL,'San Diego','California','USA','14234'),(17,'Katherine Wood','2103 San Pablo Avenue',NULL,'Williamsburg','New York','USA','00293'),(18,'Andrea Lee-Jean','3345 Santa Rosa',NULL,'Antonio','California','USA','11456'),(19,'Margaret F. Shuster','201 Pacific Street',NULL,'San Clemente','Pennsylvania','USA','92630'),(20,'Jackie Nguyen','243 Golden State Blvd',NULL,'Banning','Vermont','USA','88394'),(21,'Richard Wu','39000 Clock Tower Plaza',NULL,'Temecula','Alaska','USA','34323'),(22,'Kyle Williams','7250 Monteray Rd','Apartment 4H','Anaheim','Maine','USA','33456'),(23,'Good Stuff Sellers','24 S. Sacramento Street',NULL,'Stockton','Indiana','USA','47748'),(24,'Material Things and More','1040 Mason Ave.',NULL,'Saint George','Texas','USA','93382'),(25,'PurchaseCore','475 Rodriguez St','P.O. Box 42J','Chico','New Mexico','USA','33309'),(26,'Object Purveyors','320 S. Canyon Way',NULL,'Red Bluff','South Dakota','USA','00023'),(27,'Trading Post','3911 University Ave','34B','Santa Ana','Hawaii','USA','11369');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_line`
--

DROP TABLE IF EXISTS `cart_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_line` (
  `cart_line_id` int(8) NOT NULL AUTO_INCREMENT,
  `customer_id` int(8) NOT NULL,
  `product_id` int(8) NOT NULL,
  `qty` int(8) NOT NULL DEFAULT '1',
  PRIMARY KEY (`cart_line_id`),
  KEY `cart_line_fk_1` (`customer_id`),
  KEY `cart_line_fk_2` (`product_id`),
  CONSTRAINT `cart_line_fk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE,
  CONSTRAINT `cart_line_fk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_line`
--

LOCK TABLES `cart_line` WRITE;
/*!40000 ALTER TABLE `cart_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customer_id` int(8) NOT NULL AUTO_INCREMENT,
  `ship_address_id` int(8) NOT NULL,
  `bill_address_id` int(8) NOT NULL,
  `tel` varchar(14) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` tinytext NOT NULL,
  `title` tinytext NOT NULL,
  `password` varchar(255) NOT NULL,
  `paypal_cust_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  KEY `customer_fk_1` (`ship_address_id`),
  KEY `customer_fk_2` (`bill_address_id`),
  CONSTRAINT `customer_fk_1` FOREIGN KEY (`ship_address_id`) REFERENCES `address` (`address_id`) ON UPDATE CASCADE,
  CONSTRAINT `customer_fk_2` FOREIGN KEY (`bill_address_id`) REFERENCES `address` (`address_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `order_id` int(8) NOT NULL AUTO_INCREMENT,
  `status_prefix` varchar(255) NOT NULL DEFAULT 'order',
  `status_id` tinyint(3) NOT NULL,
  `customer_id` int(8) NOT NULL,
  `payment_id` int(8) NOT NULL,
  `date_purchased` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_refunded` timestamp NULL DEFAULT NULL,
  `tracking_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_fk_1` (`status_prefix`,`status_id`),
  KEY `order_fk_2` (`customer_id`),
  KEY `order_fk_3` (`payment_id`),
  CONSTRAINT `order_fk_1` FOREIGN KEY (`status_prefix`, `status_id`) REFERENCES `status` (`prefix`, `status_id`) ON UPDATE CASCADE,
  CONSTRAINT `order_fk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE,
  CONSTRAINT `order_fk_3` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_line`
--

DROP TABLE IF EXISTS `order_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_line` (
  `order_line_id` int(8) NOT NULL AUTO_INCREMENT,
  `order_id` int(8) NOT NULL,
  `product_id` int(8) NOT NULL,
  `qty` int(8) NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_line_id`),
  KEY `order_line_fk_1` (`order_id`),
  KEY `order_line_fk_2` (`product_id`),
  CONSTRAINT `order_line_fk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON UPDATE CASCADE,
  CONSTRAINT `order_line_fk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_line`
--

LOCK TABLES `order_line` WRITE;
/*!40000 ALTER TABLE `order_line` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner`
--

DROP TABLE IF EXISTS `partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partner` (
  `partner_id` int(8) NOT NULL AUTO_INCREMENT,
  `address_id` int(8) NOT NULL,
  `name` tinytext NOT NULL,
  `contact_name` tinytext NOT NULL,
  `tel` varchar(14) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`partner_id`),
  KEY `partner_fk_1` (`address_id`),
  CONSTRAINT `partner_fk_1` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner`
--

LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner` VALUES (1,1,'Lakeshore Market','Magliotyan','5551234567','epost@luc.edu','THIS NEEDS TO BE HASHED'),(2,23,'Good Stuff Sellers','Fatima Shaw','9337820435','info@gss.com','something'),(3,24,'Material Things and More','Courtney Bonner','8323339283','cbonner@materialt.com','password'),(4,25,'PurchaseCore','Mr. Fredrick Purchasecore','2366611123','fred@gmail.com','thebigapple'),(5,26,'Object Purveyors','Daphne Nikolaides','7684724449','dnikolai@op.com','monsoonsnubtacofixin'),(6,27,'Trading Post','Louisiana Khan','8476321322','loukhan@tradingpost.com','firefirefire');
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partner_review`
--

DROP TABLE IF EXISTS `partner_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partner_review` (
  `partner_review_id` int(8) NOT NULL AUTO_INCREMENT,
  `partner_id` int(8) NOT NULL,
  `customer_id` int(8) NOT NULL,
  `rating` tinyint(1) NOT NULL,
  `review` text NOT NULL,
  `review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`partner_review_id`),
  KEY `partner_review_fk_1` (`partner_id`),
  KEY `partner_review_fk_2` (`customer_id`),
  CONSTRAINT `partner_review_fk_1` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`partner_id`) ON UPDATE CASCADE,
  CONSTRAINT `partner_review_fk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partner_review`
--

LOCK TABLES `partner_review` WRITE;
/*!40000 ALTER TABLE `partner_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `partner_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(8) NOT NULL AUTO_INCREMENT,
  `status_prefix` varchar(255) NOT NULL DEFAULT 'payment',
  `status_id` tinyint(3) NOT NULL,
  `method` tinytext,
  `method_transaction_id` varchar(255) DEFAULT NULL,
  `date_paid` timestamp NULL DEFAULT NULL,
  `date_refunded` timestamp NULL DEFAULT NULL,
  `total_paid` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `payment_fk_1` (`status_prefix`,`status_id`),
  CONSTRAINT `payment_fk_1` FOREIGN KEY (`status_prefix`, `status_id`) REFERENCES `status` (`prefix`, `status_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `product_id` int(8) NOT NULL AUTO_INCREMENT,
  `partner_id` int(8) NOT NULL,
  `taxonomy_id` int(8) NOT NULL DEFAULT '1',
  `cost` decimal(10,0) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `name` tinytext NOT NULL,
  `description` text NOT NULL,
  `qoh` int(8) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`),
  KEY `product_fk_1` (`partner_id`),
  KEY `product_fk_2` (`taxonomy_id`),
  CONSTRAINT `product_fk_1` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`partner_id`) ON UPDATE CASCADE,
  CONSTRAINT `product_fk_2` FOREIGN KEY (`taxonomy_id`) REFERENCES `taxonomy` (`taxonomy_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,1,2,5,10,'A Tale of Two Cities','You will have the best of times reading this book',500,1),(10,1,2,7,10,'Great Expectations','You should have them about this book because it is great',450,1),(11,1,2,8,10,'David Copperfield','A poor orphan falls on hard times and meets interesting characters in an attempt to survive',35,1),(12,1,2,8,10,'Oliver Twist','Please sir I want some more OF THIS GREAT BOOK',1000,1),(13,1,2,3,10,'A Christmas Carol','God bless us every one we love this book so much',15,1),(14,1,2,2,10,'The Pickwick Papers','You should pick these papers',12,1),(15,1,2,45,10,'Hard Times','You will have a hard time putting this book down',14,1),(16,1,2,8,8,'Our Mutual Friend','I would be mutual friends with anybody reading this book. It is wonderful',4,1),(17,1,2,6,10,'Bleak House','A bleak house is any one that does not have a copy of this book',12,1),(18,1,2,14,20,'The Complete Works of Charles Dickins','Read all the greats in one gigantic book that is really difficult to carry around',12,1),(19,1,2,4,8,'Martin Chuzzlewit','A sympathetic protagonist falls on hard times and meets interesting characters in an attempt to survive in this bleak portrait of Victorian London',2,1),(20,1,2,13,13,'The Abridged Works of Charles Dickens','Read all the greats minus Bleak House',5,1),(21,1,2,13,13,'The Very Abridged Works of Charles Dickens','Oliver Twist and David Copperfield, together at last',432,1),(22,1,2,4,10,'The Biography of Charles Dickens','How could one person write so many great books? Find out now',12,1),(23,6,5,1,5,'Golden Squash - Large','Large golden squash',12,1),(24,6,5,1,5,'Jack-o-lantern Pumpkin','Not a lantern, do not ignite',3,1),(25,6,5,1,5,'Acorn Squash - Small','Small acorn squash',7,1),(26,6,5,1,5,'Glitter Pumpkin','Pumpkin covered in glitter',42,1),(27,6,5,3,5,'Decorative Gourd Variety Pack','Comes with 200 gourds',1,1),(28,3,3,15,20,'Its the Great Pumpkin, Charlie Brown','Classic Charlie Brown Halloween Movie',3,1),(29,3,3,15,20,'Its the Great Pumpkin, Charles Dickens','Classic Charles Dickens Halloween Movie',4,1),(30,3,3,15,20,'Its the Great Pumpkin, Charles Dickens - Volume 2','SQL to Volume 1',5,1),(31,3,3,15,20,'Its the Great Pumpkin','How to make your own decorative gourds',13,1),(32,2,4,11,11,'Sound Effects','Great for learning how things sound',8,1),(33,4,4,8,11,'Great Sound Effects','Better than Sound Effects Volume 1',2,1),(34,6,4,22,23,'Charles Dickens AudioBook Collection','Listen to World Famous Childrens Choirs sing the complete works of Charles Dickens',14,1),(35,6,1,5,5,'Widget','A thing that can do stuff',100,1),(36,5,6,5,5,'Deluxe Widget','Stuff that can do things',100,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_review`
--

DROP TABLE IF EXISTS `product_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_review` (
  `product_review_id` int(8) NOT NULL AUTO_INCREMENT,
  `product_id` int(8) NOT NULL,
  `customer_id` int(8) NOT NULL,
  `rating` tinyint(1) NOT NULL,
  `review` text NOT NULL,
  `review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_review_id`),
  KEY `product_review_fk_1` (`product_id`),
  KEY `product_review_fk_2` (`customer_id`),
  CONSTRAINT `product_review_fk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE,
  CONSTRAINT `product_review_fk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_review`
--

LOCK TABLES `product_review` WRITE;
/*!40000 ALTER TABLE `product_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `prefix` varchar(255) NOT NULL,
  `status_id` tinyint(3) NOT NULL,
  `description` tinytext NOT NULL,
  PRIMARY KEY (`prefix`,`status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES ('order',0,'Cancelled'),('order',1,'In Progress'),('order',2,'Shipped'),('order',3,'Delivered'),('payment',0,'Unpaid'),('payment',1,'Paid'),('payment',2,'Refunded');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taxonomy`
--

DROP TABLE IF EXISTS `taxonomy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taxonomy` (
  `taxonomy_id` int(8) NOT NULL AUTO_INCREMENT,
  `name` tinytext NOT NULL,
  `slug` varchar(255) NOT NULL,
  PRIMARY KEY (`taxonomy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taxonomy`
--

LOCK TABLES `taxonomy` WRITE;
/*!40000 ALTER TABLE `taxonomy` DISABLE KEYS */;
INSERT INTO `taxonomy` VALUES (1,'Uncategorized','uncategorized'),(2,'Books','books'),(3,'Movies','movies'),(4,'Music','music'),(5,'Decorative Wax Gourds','decorative-wax-gourds'),(6,'Stationery','stationery');
/*!40000 ALTER TABLE `taxonomy` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-17 17:25:05
