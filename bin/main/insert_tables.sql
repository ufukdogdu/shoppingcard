
INSERT INTO `campaign` (`version`,`campaign_end_date`,`campaign_start_date`,`description`) VALUES (0,'2020-02-28','2020-01-01','Kış Kampanyası');
INSERT INTO `campaign` (`version`,`campaign_end_date`,`campaign_start_date`,`description`) VALUES (0,'2020-05-30','2020-04-01','Bahar Kampanyası');
INSERT INTO `campaign` (`version`,`campaign_end_date`,`campaign_start_date`,`description`) VALUES (0,'2020-08-31','2020-06-01','Yaz Kampanyası');
INSERT INTO `campaign` (`version`,`campaign_end_date`,`campaign_start_date`,`description`) VALUES (0,'2020-12-31','2020-09-01','Güz Kampanyası');

INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',10.00,NULL, NULL,1,1);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',20.00,NULL, NULL,2,1);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',30.00,NULL, NULL,3,1);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',50.00,NULL, NULL,-1,1);

INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,10.00,'TL',1,2);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,20.00,'TL',2,2);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,30.00,'TL',3,2);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,50.00,'TL',-1,2);

INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',10.00,NULL,NULL,1,4);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',20.00,NULL,NULL,2,4);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',30.00,NULL,NULL,3,3);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'RATE',50.00,NULL,NULL,-1,3);

INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,10.00,'TL',1,3);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,20.00,'TL',2,3);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,30.00,'TL',3,4);
INSERT INTO `campaign_discount` (`version`,`discount_type`,`discount_rate`,`discount_amount`,`discount_amount_currency`,`product_count`,`campaign_id`) VALUES (0,'AMOUNT',NULL,50.00,'TL',-1,4);


INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,NULL,'Elektronik',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,1,'Bilgisayar',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,1,'Cep Telefonu',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,NULL,'Giyim',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,4,'Erkek Giyim',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,4,'Kadın Giyim',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,5,'Pantalon',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,5,'Kaban',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,5,'Gömlek',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,6,'Etek',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,6,'Çanta',4);
INSERT INTO `category` (`version`,`parent_category_id`,`title`,`campaign_id`) VALUES (0,6,'Ayakkabı',4);

INSERT INTO `coupon` (`version`,`code`,`discount_amount`,`discount_amount_currency`,`min_card_amount`,`min_card_amount_currency`, `coupon_status`) VALUES (0,'DEF1234',50.00,'TL',250.00,'TL', 'ACTIVE');
INSERT INTO `coupon` (`version`,`code`,`discount_amount`,`discount_amount_currency`,`min_card_amount`,`min_card_amount_currency`, `coupon_status`) VALUES (0,'ABC32434',100.00,'TL',400.00,'TL', 'ACTIVE');

INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,60.00,'TL','COUNT','Defacto Gömlek Mavi',9, 'Defacto');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,70,'TL','COUNT','Defacto Gömlek Yeşil',9, 'Defacto');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,150,'TL','COUNT','Adidas Ayakkabı RunF',12,'Adidas');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,260.00,'TL','COUNT','Adidas Ayakkabı BasketBall',12, 'Adidas');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,180.00,'TL','COUNT','LCW Kaban 1',8, 'LCW');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,340.00,'TL','COUNT','LCW Kaban 2',8, 'LCW');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,260.00,'TL','COUNT','Pierre Cardin Çanta 1',1,'Pierre Cardin');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,560.00,'TL','COUNT','Pierre Cardin Çanta 2',1, 'Pierre Cardin');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,155.00,'TL','COUNT','Mudo Pantalon 1',7, 'Mudo');
INSERT INTO `product` (`version`,`price_amount`,`price_amount_currency`,`quantity_type`,`title`,`category_id`, `firm_name`) VALUES (0,255.00,'TL','COUNT','XX Etek 1',10, 'Mudo');