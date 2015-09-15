# Create the DB
CREATE DATABASE IF NOT EXISTS `lakeshore_market` 
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

#SELECT the DB
USE `lakeshore_market`;

#tables are ordered by dependency requirements

#create the STATUS table
CREATE TABLE `status` (
	`prefix` varchar(255) NOT NULL,
	`code` tinyint(3) NOT NULL,
	`description` tinytext NOT NULL,

	#Add PK & FK Constraints
	CONSTRAINT `status_code` PRIMARY KEY (`prefix`,`code`)
);

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
	`status_code` tinyint(3) NOT NULL,
	`method` tinytext NULL,
	`method_transaction_id` varchar(255) NULL,
	`date_paid` timestamp NULL, #we use "timestamp" over "datetime" b/c timestamp saves in UTC
	`date_refunded` timestamp NULL,
	`total_paid` decimal NULL, #we use decimal over int because decimal is better for exact monetary values (better precision)

	#Add PK & FK constraints
	CONSTRAINT `payment_id` PRIMARY KEY (`payment_id`),
	CONSTRAINT `payment_fk_1` FOREIGN KEY (`status_code`) 
		REFERENCES `status`(`status_code`)
);

#create the ADDRESS table
CREATE TABLE `address` (
	`address_id` int(8) NOT NULL AUTO_INCREMENT,
	`line1` varchar(255) NOT NULL,
	`line2` varchar(255) NULL,
	`line3` varchar(255) NOT NULL,
	`state` varchar(255) NOT NULL,
	`country` varchar(255) NOT NULL,
	`postal_code` varchar(255) NOT NULL,

	#Add PK & FK Constraitns
	CONSTRAINT `address_id` PRIMARY KEY (`address_id`)
);

#Create the CUSTOMER table
CREATE TABLE `customer` (
	`customer_id` int(8) NOT NULL AUTO_INCREMENT,
	`ship_address_id` int(8) NOT NULL,
	`bill_address_id` int(8) NOT NULL,
	`tel` int(14) NOT NULL,
	`email` varchar(255) NOT NULL,
	`name` tinytext NOT NULL,
	`title` tinytext NOT NULL,
	`password` varchar(255) NOT NULL, #this will be a password hash. In almost all cases, 255 chars is enough storage
	`paypal_cust_id` varchar(255) NULL,


	#Add PK & FK Constraitns
	CONSTRAINT `customer_id` PRIMARY KEY (`customer_id`),
	CONSTRAINT `customer_fk_1` FOREIGN KEY (`ship_address_id`) 
		REFERENCES `address`(`address_id`),
	CONSTRAINT `customer_fk_2` FOREIGN KEY (`bill_address_id`) 
		REFERENCES `address`(`address_id`)
);

#create the PARTNER table
CREATE TABLE `partner` (
	`partner_id` int(8) NOT NULL AUTO_INCREMENT,
	`name` tinytext NOT NULL,
	`contact_name` tinytext NOT NULL,
	`tel` int(14) NOT NULL,
	`email` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,

	#Add PK & FK Constraitns
	CONSTRAINT `partner_id` PRIMARY KEY (`partner_id`)
);

#create the PRODUCT table
CREATE TABLE `product` (
	`product_id` int(8) NOT NULL AUTO_INCREMENT,
	`partner_id` int(8) NOT NULL, #requires that LAKESHORE MARKET is a partner
	`taxonomy_id` int(8) NOT NULL DEFAULT 1, #set taxonomy_id 1 = uncategorized
	`cost` decimal NOT NULL,
	`price` decimal NOT NULL,
	`name` tinytext NOT NULL,
	`description` text NOT NULL DEFAULT `No description`,
	`qoh` int(8) NOT NULL DEFAULT 0,
	`active` tinyint(1) NOT NULL DEFAULT 1, #acts as a boolean

	#Add PK & FK Constraitns
	CONSTRAINT `product_id` PRIMARY KEY (`product_id`),
	CONSTRAINT `product_fk_1` FOREIGN KEY (`partner_id`) 
		REFERENCES `partner`(`partner_id`),
	CONSTRAINT `product_fk_2` FOREIGN KEY (`taxonomy_id`) 
		REFERENCES `taxonomy`(`taxonomy_id`)
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
	CONSTRAINT `product_review_pk` PRIMARY KEY (`product_review_id`),
	CONSTRAINT `product_review_fk_1` FOREIGN KEY (`product_id`) 
		REFERENCES `product`(`product_id`),
	CONSTRAINT `product_review_fk_2` FOREIGN KEY (`customer_id`) 
		REFERENCES `customer`(`customer_id`)
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
	CONSTRAINT `partner_review_pk` PRIMARY KEY (`partner_review_id`),
	CONSTRAINT `partner_review_fk_1` FOREIGN KEY (`partner_id`) 
		REFERENCES `partner`(`partner_id`),
	CONSTRAINT `partner_review_fk_2` FOREIGN KEY (`customer_id`) 
		REFERENCES `customer`(`customer_id`)
);


#create the ORDER table
CREATE TABLE `order` (
	`order_id` int(8) NOT NULL AUTO_INCREMENT,
	`status_code` int(8) NOT NULL,
	`customer_id` int(8) NOT NULL,
	`payment_id` int(8) NOT NULL,
	`date_purchased` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`date_refunded` timestamp NULL,
	`tracking_number` varchar(255) NULL,

	#Add PK & FK Constraints
	CONSTRAINT `order_id` PRIMARY KEY (`order_id`),
	CONSTRAINT `order_fk_1` FOREIGN KEY (`status_code`) 
		REFERENCES `status`(`status_code`),
	CONSTRAINT `order_fk_2` FOREIGN KEY (`customer_id`) 
		REFERENCES `customer`(`customer_id`),
	CONSTRAINT `order_fk_3` FOREIGN KEY (`payment_id`) 
		REFERENCES `payment`(`payment_id`)
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
		REFERENCES `order`(`order_id`),
	CONSTRAINT `order_line_fk_2` FOREIGN KEY (`product_id`) 
		REFERENCES `product`(`product_id`)
);


/*#create the CART table
CREATE TABLE `cart` (
	`customer_id` int(8) NOT NULL,
	
	#Add PK & FK Constraints
	CONSTRAINT `cart_pk` PRIMARY KEY (`customer_id`),
	CONSTRAINT `cart_fk_1` FOREIGN KEY (`customer_id`) 
		REFERENCES `customer`(`customer_id`)
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
		REFERENCES `customer`(`customer_id`),
	CONSTRAINT `cart_line_fk_2` FOREIGN KEY (`product_id`) 
		REFERENCES `product`(`product_id`)
);


# Inserts

INSERT INTO `partner` SET
	`name` = 'Lakeshore Market',
	`contact_name` = 'Magliotyan' #Mag[gie]-[El]liot[t]-[Ray]yan
	`tel` = 5551234567
	`email` = `epost@luc.edu`,
	`password` = 'THIS NEEDS TO BE HASHED';


INSERT INTO `taxonomy` SET
	`name` = 'Uncategorized',
	`slug` = 'uncategorized';


INSERT INTO `status` 
	(`prefix`, `code`, `description`)
	VALUES 
	('order', 0, 'Cancelled'),
	('order', 1, 'In Progress'),
	('order', 2, 'Shipped'),
	('order', 3, 'Delivered'),
	('payment', 0, 'Unpaid'),
	('payment', 1, 'Paid'),
	('payment', 2, 'Refunded');