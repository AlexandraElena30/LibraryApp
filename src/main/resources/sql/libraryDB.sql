
CREATE DATABASE `libraryDB`;

USE `libraryDB`;

/*DROP TABLE IF EXISTS `products`;*/

CREATE TABLE `products` (
  `productID` int(10) NOT NULL,  /* in intelliJ trebuie sa fie long*/
  `name` varchar(50) NOT NULL,
  `category` varchar(30) NOT NULL,
  `quantity` int(4) NOT NULL,
  `description` varchar(300),
  `price` decimal(8,2) NOT NULL,
  `shippingDays` int(3) NOT NULL,
  `rating` decimal(3,2) NOT NULL,
  `totalrating` int(10) NOT NULL,
  PRIMARY KEY (`productID`)
);

/*Data for the table `products` */

insert  into `products`(`productID`,`name`,`category`,`quantity`,`description`,`price`,`shippingDays`,`rating`,`totalrating`) values 
(1000,'Calculator of bamboo','Office supplies',20,'A calculator made of bamboo wood','12.50',2,'0.00',0),

(1001,'Circular Paper 100','Office supplies',50,'Recycled Paper 80 gsm A4','20.60',2,'0.00',0),

(1002,'Homo Deus','Book',40,'Sapiens showed us where we came from. Homo Deus shows us where we are going. War is obsolete. ','59.50', 2,'0.00',0),

(1003,'When Breath Becomes Air','Book',20,'At the age of thirty-six, on the verge of completing a decade is training as a neurosurgeon, Paul Kalanithi was diagnosed with inoperable lung cancer.','65.00',2,'0.00',0),

(1004,'Thinking, Fast and Slow','Book',25,'Looks at the way our minds work, and how we make decisions. This book reveals how our minds are tripped up by error and prejudice','80.00', 2,'0.00',0),

(1005,'Skincare: The ultimate no-nonsense guide','Book',30,'Caroline Hirons is the authority in skincare – and for the first time, she is sharing her knowledge with the world.','72.00', 2,'0.00',0),

(1006,'Pride and Prejudice','Book',40,'Emma has long played matchmaker for her friends and believes her own heart immune from the lures of love.','69.99', 2,'0.00',0),

(1007,'Stabilo Boss original highlighter pens','Office supplies',60,'High-quality workmanship, ink and writing comfort.','8.22', 2,'0.00',0),

(1008,'False Nail Presentation Nail Tips','Cosmetics',60,'It can be used as a beginner is nail practice stick. Can be used as a first semester exercise table for engraving and painting.','12.00', 2,'0.00',0),

(1009,'Rimmel London 60 Seconds Super Shine','Cosmetics',30,'Super-glossy, ultra-shiny nails are just one sweep away.','8.00', 2,'0.00',0),

(1010,'Vintage Journal Notebook','Office supplies',30,'HANDMADE JOURNAL DETAILS - The bullet journal come with embossed hard cover.','59.00', 2,'0.00',0),

(1011,'BIC Cristal Original Ballpoint Pens','Office supplies',20,'The classic BIC Cristal Original pen is the world is best-selling ballpoint pen and this box of 50.','21.00', 2,'0.00',0),

(1012,'CRAYOLA 58-5057-E-000 SuperTips Washable','Office supplies',40,'They give a smooth intense colour laydown, no squeaks or drags.','32.50', 2,'0.00',0),

(1013,'Sense 10308 Dual Tip','Office supplies',20,'They give a smooth intense colour laydown.','12.50', 2,'0.00',0),

(1014,'Giraffes Can not Dance','Book',10,'Number One bestseller Giraffes Can not Dance from author Giles Andreae has been delighting children for over 15 years. ','65.99', 2,'0.00',0),

(1015,'Peace Talks: The Dresden Files, Book Sixteen','Book',30,'When the Supernatural nations of the world meet up to negotiate an end to ongoing hostilities, Harry Dresden, Chicago is only professional wizard, joins the White Council is security team to make sure the talks stay civil.','99.99', 2,'0.00',0),

(1016,'The War of the Spanish Succession: The History of the Conflict Between the Bourbons and Habsburgs that Engulfed Europe','Book',40,'Includes a bibliography for further reading','125.00', 2,'0.00',0),

(1017,'Austerity: When It Works and When It Does not ','Book',20,'A timely and incisive look at austerity measures that succeed-and those that do not Fiscal austerity is hugely controversial.','88.00', 2,'0.00',0),

(1018,'The Fed and Lehman Brothers','Book',20,'The bankruptcy of the investment bank Lehman Brothers was the pivotal event of the 2008 financial crisis and the Great Recession that followed.','99.00', 2,'0.00',0),

(1019,'Good Vibes, Good Life: How Self-Love Is the Key to Unlocking Your Greatness','Book',40,'Be the best version of you that YOU can be. ','69.90', 2,'0.00',0),

(1020,'The Boy, The Mole, The Fox and The Horse','Book',30,'A wonderful work of art and a wonderful window into the human heart Richard Curtis.','80.00', 2,'0.00',0);


/*Table structure for table `user` */

/*DROP TABLE IF EXISTS `user`;*/

CREATE TABLE `users` (
  `userID` int(10) AUTO_INCREMENT NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `balance` decimal(10,2) NOT NULL,
  `userType` int(10) NOT NULL,
  `bonusPoints` int(15) NOT NULL,
  `address` varchar(100) NOT NULL,
  `basket` varchar(200) NOT NULL,
  `phone` varchar(10) NOT NULL,
	
  PRIMARY KEY (`userID`)

);


/*Data for the table `users` */

insert  into `users`(`userID`,`lastName`,`firstName`,`password`,`email`,`balance`,`userType`,`bonusPoints`,`address`,`basket`,`phone`) values 
(100,'Murphy','Diane','635bGGS_AA','dmurphy@libraryDB.com','0',0,0,'Mississippi,Ridgeland,1866  Lena Lane,39157','','601-810-3901'),
(101,'Patterson','Mary','234dfDDDasdfQA','mpatterso@libraryDB.com','0',0,0,'Mississippi,Ridgeland,1901  Big Joe,23412','','601-555-3421');
/*Table structure for table `orders` */

/*DROP TABLE IF EXISTS `orders`; */

CREATE TABLE `orders` (
  `idCode` int(10) AUTO_INCREMENT NOT NULL,
  `userID` int(10) NOT NULL,
  `price` decimal(11,2) NOT NULL,
  `orderDate` date NOT NULL,
  `shippingDate` date NOT NULL,
  `orderProducts` varchar(200) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
 
  PRIMARY KEY (`idCode`),
  FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
);
/*bagam un foreign key pe user id conectat cu tabelul users
Data for the table `products` */

/*insert into `orders` (`idCode`,`userID`,`price`,`orderDate`,`shippingDate`,`orderProducts`,`status`) values  */




/*Table structure for table `bookProducts` */

/* DROP TABLE IF EXISTS `bookProducts`; */

CREATE TABLE `bookProducts` (
  `bookID` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publisher` varchar(20) NOT NULL,
  `pages` int(5) NOT NULL,
  `cover` int(1) NOT NULL,
  `publishedYear` date NOT NULL,
  `languages` varchar(10) NOT NULL,
  
  PRIMARY KEY (`bookID`)

); 

/*Data for the table `bookProducts` */

insert  into `bookProducts`(`bookID`,`title`,`author`,`publisher`,`pages`,`cover`,`publishedYear`,`languages`) values 
(1002,'Homo Deus','Yuval Noah Harari','Penguin',235, 1,'2003-02-08','english'),

(1003,'When Breath Becomes Air','Paul Kalanithi','Harper',400, 2,'2018-01-23','english'),

(1004,'Thinking, Fast and Slow','Daniel Kahneman','Harper',240, 1,'2008-05-30','english'),

(1006,'Pride and Prejudice','Jane Austen','Harper',1233, 1,'2002-10-10', 'romanian'),

(1014,'Giraffes Can not Dance','Giles Andreae','Vintage',144, 1,'2020-02-02', 'english'),

(1015,'Peace Talks: The Dresden Files, Book Sixteen','Laurence M. Ball','Harper',500, 2,'2018-09-18','romanian'),

(1016,'The War of the Spanish Succession: The History of the Conflict Between the Bourbons and Habsburgs that Engulfed Europe','Charles River Editors','Vintage',510,2,'2007-01-08', 'english'),

(1017,'Austerity: When It Works and When It Does not','Alberto Alesina ','Harper',210, 1,'2020-12-29', 'english'),

(1018,'The Fed and Lehman Brothers','Laurence M. Ball','Harper',150, 1,'2016-04-01','english'),

(1019,'Good Vibes, Good Life: How Self-Love Is the Key to Unlocking Your Greatness','Vex King','Penguin',380, 1,'2015-07-25','english'),

(1020,'The Boy, The Mole, The Fox and The Horse','Charlie Mackesy','Penguin',280, 1,'2019-03-06','romanian');


/*Table structure for table `promotion` */

/* DROP TABLE IF EXISTS `promotion`; */

CREATE TABLE `promotion` (
  `code` varchar(20) NOT NULL,
  `promotionType` int(1) NOT NULL,
  `amount` int(5) NOT NULL

  PRIMARY KEY (`code`)

);

insert  into `promotion`(`code`,`promotionType`,`amount`,`category`) values 

('SUMMER20',1,20,'Book');



