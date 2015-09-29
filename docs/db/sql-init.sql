/*
CHANGES:
* customer name is now split to first_name, last_name
* added active bool for partner, customer
* Removed status code
* Added additional fields to CUSTOMER and ORDER
* Removed the CART table (it had no use since it had no unique key)
	* CART will likely still need to be an object in Java
* Added additional fields to PARTNER
* Added `city` to ADDRESS
* Fixed semantic error in FK to STATUS (now using both fields and changed status_code to status_id and status_code is now the PRIMARY KEY name)

ADDITIONAL CHANGES:
* Added product ID and quantity to the order table.

*/

DROP DATABASE `lakeshore_market`;

# Create the DB
CREATE DATABASE IF NOT EXISTS `lakeshore_market`
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

#SELECT the DB
USE `lakeshore_market`;

#tables are ordered by dependency requirements


#create the TAXONOMY table
CREATE TABLE `taxonomy` (
	`taxonomy_id` int(8) NOT NULL AUTO_INCREMENT,
	`name` tinytext NOT NULL,
	`slug` varchar(255) NOT NULL, #would be used for building a unique pretty URI

	#Add PK & FK Constraints
	CONSTRAINT `taxonomy_id` PRIMARY KEY (`taxonomy_id`)
);


#Create the PAYMENT table
CREATE TABLE `payment` (
	`payment_id` int(8) NOT NULL AUTO_INCREMENT,
	`status_id` tinyint(3) NOT NULL,
	`method` tinytext NULL,
	`method_transaction_id` varchar(255) NULL,
	`date_paid` timestamp NULL, #we use "timestamp" over "datetime" b/c timestamp saves in UTC
	`date_refunded` timestamp NULL,
	`total_paid` decimal NULL, #we use decimal over int because decimal is better for exact monetary values (better precision)

	#Add PK & FK constraints
	CONSTRAINT `payment_id` PRIMARY KEY (`payment_id`)
);

#create the ADDRESS table
CREATE TABLE `address` (
	`address_id` int(8) NOT NULL AUTO_INCREMENT,
	`line1` varchar(255) NOT NULL,
	`line2` varchar(255) NULL,
	`line3` varchar(255) NULL,
	`city` varchar(255) NOT NULL,
	`state` varchar(255) NOT NULL,
	`country` varchar(255) NOT NULL,
	`postal_code` varchar(255) NOT NULL,

	#Add PK & FK Constraints
	CONSTRAINT `address_id` PRIMARY KEY (`address_id`)
);

#Create the CUSTOMER table
CREATE TABLE `customer` (
	`customer_id` int(8) NOT NULL AUTO_INCREMENT,
	`ship_address_id` int(8) NOT NULL,
	`bill_address_id` int(8) NOT NULL,
	`tel` varchar(14) NOT NULL,
	`email` varchar(255) NOT NULL,
	`first_name` tinytext NOT NULL,
	`last_name` tinytext NOT NULL,
	`title` tinytext NOT NULL,
	`password` varchar(255) NOT NULL, #this will be a password hash. In almost all cases, 255 chars is enough storage
	`paypal_cust_id` varchar(255) NULL,
	`active` tinyint(1) NOT NULL DEFAULT 1, #acts as a boolean


	#Add PK & FK Constraints
	CONSTRAINT `customer_id` PRIMARY KEY (`customer_id`),
	CONSTRAINT `customer_fk_1` FOREIGN KEY (`ship_address_id`)
		REFERENCES `address`(`address_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `customer_fk_2` FOREIGN KEY (`bill_address_id`)
		REFERENCES `address`(`address_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);

#create the PARTNER table
CREATE TABLE `partner` (
	`partner_id` int(8) NOT NULL AUTO_INCREMENT,
	`address_id` int(8) NOT NULL,
	`name` tinytext NOT NULL,
	`contact_name` tinytext NOT NULL,
	`tel` varchar(14) NOT NULL,
	`email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`active` tinyint(1) NOT NULL DEFAULT 1, #acts as a boolean

	#Add PK & FK Constraints
	CONSTRAINT `partner_id` PRIMARY KEY (`partner_id`),
	CONSTRAINT `partner_fk_1` FOREIGN KEY (`address_id`)
		REFERENCES `address`(`address_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);

#create the PRODUCT table
CREATE TABLE `product` (
	`product_id` int(8) NOT NULL AUTO_INCREMENT,
	`partner_id` int(8) NOT NULL, #requires that LAKESHORE MARKET is a partner
	`taxonomy_id` int(8) NOT NULL DEFAULT 1, #set taxonomy_id 1 = uncategorized
	`cost` decimal NOT NULL,
	`price` decimal NOT NULL,
	`name` tinytext NOT NULL,
	`description` text NOT NULL,
	`qoh` int(8) NOT NULL DEFAULT 0,
	`active` tinyint(1) NOT NULL DEFAULT 1, #acts as a boolean

	#Add PK & FK Constraints
	CONSTRAINT `product_id` PRIMARY KEY (`product_id`),
	CONSTRAINT `product_fk_1` FOREIGN KEY (`partner_id`)
		REFERENCES `partner`(`partner_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `product_fk_2` FOREIGN KEY (`taxonomy_id`)
		REFERENCES `taxonomy`(`taxonomy_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);


#create the PRODUCT_REVIEW table
CREATE TABLE `product_review` (
	`product_review_id` int(8) NOT NULL AUTO_INCREMENT,
	`product_id` int(8) NOT NULL,
	`customer_id` int(8) NOT NULL,
	`rating` tinyint(1) NOT NULL,
	`review` text NOT NULL,
	`review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,

	#Add PK & FK Constraints
	CONSTRAINT `product_review_id` PRIMARY KEY (`product_review_id`),
	CONSTRAINT `product_review_fk_1` FOREIGN KEY (`product_id`)
		REFERENCES `product`(`product_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `product_review_fk_2` FOREIGN KEY (`customer_id`)
		REFERENCES `customer`(`customer_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);

#create the PARTNER_REVIEW table
CREATE TABLE `partner_review` (
	`partner_review_id` int(8) NOT NULL AUTO_INCREMENT,
	`partner_id` int(8) NOT NULL,
	`customer_id` int(8) NOT NULL,
	`rating` tinyint(1) NOT NULL,
	`review` text NOT NULL,
	`review_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,

	#Add PK & FK Constraints
	CONSTRAINT `partner_review_id` PRIMARY KEY (`partner_review_id`),
	CONSTRAINT `partner_review_fk_1` FOREIGN KEY (`partner_id`)
		REFERENCES `partner`(`partner_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `partner_review_fk_2` FOREIGN KEY (`customer_id`)
		REFERENCES `customer`(`customer_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);


#create the ORDER table
CREATE TABLE `order` (
	`order_id` int(8) NOT NULL AUTO_INCREMENT,
	`customer_id` int(8) NOT NULL,
	`payment_id` int(8) NOT NULL,
	`product_id` int(8) NOT NULL,
	`qty` int(8) NOT NULL DEFAULT 1,
	`status_id` tinyint(3) NOT NULL,
	`date_purchased` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`date_refunded` timestamp NULL,
	`tracking_number` varchar(255) NULL,

	#Add PK & FK Constraints
	CONSTRAINT `order_id` PRIMARY KEY (`order_id`),
	CONSTRAINT `order_fk_1` FOREIGN KEY (`customer_id`)
		REFERENCES `customer`(`customer_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `order_fk_2` FOREIGN KEY (`payment_id`)
		REFERENCES `payment`(`payment_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `order_fk_3` FOREIGN KEY (`product_id`)
		REFERENCES `product` (`product_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);

#create the ORDER table
CREATE TABLE `order_line` (
	`order_line_id` int(8) NOT NULL AUTO_INCREMENT,
	`order_id` int(8) NOT NULL,
	`product_id` int(8) NOT NULL,
	`qty` int(8) NOT NULL DEFAULT 1,

	#Add PK & FK Constraints
	CONSTRAINT `order_line_id` PRIMARY KEY (`order_line_id`),
	CONSTRAINT `order_line_fk_1` FOREIGN KEY (`order_id`)
		REFERENCES `order`(`order_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `order_line_fk_2` FOREIGN KEY (`product_id`)
		REFERENCES `product`(`product_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);


/*#create the CART table
CREATE TABLE `cart` (
	`customer_id` int(8) NOT NULL,

	#Add PK & FK Constraints
	CONSTRAINT `cart_id` PRIMARY KEY (`customer_id`),
	CONSTRAINT `cart_fk_1` FOREIGN KEY (`customer_id`)
		REFERENCES `customer`(`customer_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);*/

#create the CART_LINE table
CREATE TABLE `cart_line` (
	`cart_line_id` int(8) NOT NULL AUTO_INCREMENT,
	`customer_id` int(8) NOT NULL,
	`product_id` int(8) NOT NULL,
	`qty` int(8) NOT NULL DEFAULT 1,

	#Add PK & FK Constraints
	CONSTRAINT `cart_line_id` PRIMARY KEY (`cart_line_id`),
	CONSTRAINT `cart_line_fk_1` FOREIGN KEY (`customer_id`)
		REFERENCES `customer`(`customer_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	CONSTRAINT `cart_line_fk_2` FOREIGN KEY (`product_id`)
		REFERENCES `product`(`product_id`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);


# Inserts


#begin Maggie's inserts
LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'123 Any St.',NULL,NULL,'Chicago','IL','USA','60626'),(2,'Jasper Bauer','25161 Cheshire',NULL,'Mission Viejo','California','USA','92692'),(3,'Alan Perez',NULL,'401 Harris Ave','Bellingham','Washington','USA','98225'),(4,'Amy Aotuli','105 East Kincaid Street','Unit 14','Watsonville','Alabama','USA','20392'),(5,'Mr. Kyle Cornelius','27111 Florence Way',NULL,'Colfax','Colorado','USA','37483'),(6,'Meghan Miller','3201 Smith Ave.','7N','West Sacramento','Delaware','USA','44434'),(7,'Jennie Duke','211 Railroad Ave.','Apartment H','Riverside','Montana','USA','28394'),(8,'Kim Clemens','7301 Longacres Way','Unit 17','Hayward','Missouri','USA','03948'),(9,'Brian Shore','1001 Puyallup Ave',NULL,'Bullhead City','West Virginia','USA','23443'),(10,'Venita Howes','6600 Yelm','Apartment 6C','Los Angeles','California','USA','19283'),(11,'Dr. Ariel Carr','210 Portolla',NULL,'Delano','Washington','USA','29384'),(12,'Amanda Deltoro','111 N. Kittina','Unit 4','San Fernando','Illinois','USA','11123'),(13,'Marcus King','444 South Columbia',NULL,'Allensburg','Wyoming','USA','39934'),(14,'Alan Delgado','11645 North Road','Apartment 14','Modesto','Virginia','USA','83948'),(15,'Cameron Rio','905 5th Street','PO Box 1113','Kingman','Arizona','USA','34432'),(16,'Chelsey Helding','407 N. K Street',NULL,'San Diego','California','USA','14234'),(17,'Katherine Wood','2103 San Pablo Avenue',NULL,'Williamsburg','New York','USA','00293'),(18,'Andrea Lee-Jean','3345 Santa Rosa',NULL,'Antonio','California','USA','11456'),(19,'Margaret F. Shuster','201 Pacific Street',NULL,'San Clemente','Pennsylvania','USA','92630'),(20,'Jackie Nguyen','243 Golden State Blvd',NULL,'Banning','Vermont','USA','88394'),(21,'Richard Wu','39000 Clock Tower Plaza',NULL,'Temecula','Alaska','USA','34323'),(22,'Kyle Williams','7250 Monteray Rd','Apartment 4H','Anaheim','Maine','USA','33456'),(23,'Good Stuff Sellers','24 S. Sacramento Street',NULL,'Stockton','Indiana','USA','47748'),(24,'Material Things and More','1040 Mason Ave.',NULL,'Saint George','Texas','USA','93382'),(25,'PurchaseCore','475 Rodriguez St','P.O. Box 42J','Chico','New Mexico','USA','33309'),(26,'Object Purveyors','320 S. Canyon Way',NULL,'Red Bluff','South Dakota','USA','00023'),(27,'Trading Post','3911 University Ave','34B','Santa Ana','Hawaii','USA','11369');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `taxonomy` WRITE;
/*!40000 ALTER TABLE `taxonomy` DISABLE KEYS */;
INSERT INTO `taxonomy` VALUES (1,'Uncategorized','uncategorized'),(2,'Books','books'),(3,'Movies','movies'),(4,'Music','music'),(5,'Decorative Wax Gourds','decorative-wax-gourds'),(6,'Stationery','stationery');
/*!40000 ALTER TABLE `taxonomy` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `partner` WRITE;
/*!40000 ALTER TABLE `partner` DISABLE KEYS */;
INSERT INTO `partner`
	(`partner_id`, `address_id`, `name`, `contact_name`, `tel`, `email`, `password` )
VALUES (1,1,'Lakeshore Market','Magliotyan','5551234567','epost@luc.edu','THIS NEEDS TO BE HASHED'),(2,23,'Good Stuff Sellers','Fatima Shaw','9337820435','info@gss.com','something'),(3,24,'Material Things and More','Courtney Bonner','8323339283','cbonner@materialt.com','password'),(4,25,'PurchaseCore','Mr. Fredrick Purchasecore','2366611123','fred@gmail.com','thebigapple'),(5,26,'Object Purveyors','Daphne Nikolaides','7684724449','dnikolai@op.com','monsoonsnubtacofixin'),(6,27,'Trading Post','Louisiana Khan','8476321322','loukhan@tradingpost.com','firefirefire');
/*!40000 ALTER TABLE `partner` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (9,1,2,5,10,'A Tale of Two Cities','You will have the best of times reading this book',500,1),(10,1,2,7,10,'Great Expectations','You should have them about this book because it is great',450,1),(11,1,2,8,10,'David Copperfield','A poor orphan falls on hard times and meets interesting characters in an attempt to survive',35,1),(12,1,2,8,10,'Oliver Twist','Please sir I want some more OF THIS GREAT BOOK',1000,1),(13,1,2,3,10,'A Christmas Carol','God bless us every one we love this book so much',15,1),(14,1,2,2,10,'The Pickwick Papers','You should pick these papers',12,1),(15,1,2,45,10,'Hard Times','You will have a hard time putting this book down',14,1),(16,1,2,8,8,'Our Mutual Friend','I would be mutual friends with anybody reading this book. It is wonderful',4,1),(17,1,2,6,10,'Bleak House','A bleak house is any one that does not have a copy of this book',12,1),(18,1,2,14,20,'The Complete Works of Charles Dickins','Read all the greats in one gigantic book that is really difficult to carry around',12,1),(19,1,2,4,8,'Martin Chuzzlewit','A sympathetic protagonist falls on hard times and meets interesting characters in an attempt to survive in this bleak portrait of Victorian London',2,1),(20,1,2,13,13,'The Abridged Works of Charles Dickens','Read all the greats minus Bleak House',5,1),(21,1,2,13,13,'The Very Abridged Works of Charles Dickens','Oliver Twist and David Copperfield, together at last',432,1),(22,1,2,4,10,'The Biography of Charles Dickens','How could one person write so many great books? Find out now',12,1),(23,6,5,1,5,'Golden Squash - Large','Large golden squash',12,1),(24,6,5,1,5,'Jack-o-lantern Pumpkin','Not a lantern, do not ignite',3,1),(25,6,5,1,5,'Acorn Squash - Small','Small acorn squash',7,1),(26,6,5,1,5,'Glitter Pumpkin','Pumpkin covered in glitter',42,1),(27,6,5,3,5,'Decorative Gourd Variety Pack','Comes with 200 gourds',1,1),(28,3,3,15,20,'Its the Great Pumpkin, Charlie Brown','Classic Charlie Brown Halloween Movie',3,1),(29,3,3,15,20,'Its the Great Pumpkin, Charles Dickens','Classic Charles Dickens Halloween Movie',4,1),(30,3,3,15,20,'Its the Great Pumpkin, Charles Dickens - Volume 2','SQL to Volume 1',5,1),(31,3,3,15,20,'Its the Great Pumpkin','How to make your own decorative gourds',13,1),(32,2,4,11,11,'Sound Effects','Great for learning how things sound',8,1),(33,4,4,8,11,'Great Sound Effects','Better than Sound Effects Volume 1',2,1),(34,6,4,22,23,'Charles Dickens AudioBook Collection','Listen to World Famous Childrens Choirs sing the complete works of Charles Dickens',14,1),(35,6,1,5,5,'Widget','A thing that can do stuff',100,1),(36,5,6,5,5,'Deluxe Widget','Stuff that can do things',100,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
