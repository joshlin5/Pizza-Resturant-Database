INSERT INTO TOPPING VALUES ('Pepperoni','1.25','0.2','100','2','2.75','3.5','4.5');
INSERT INTO TOPPING VALUES ('Sausage','1.25','0.15','100','2.5','3','3.5','4.25');
INSERT INTO TOPPING VALUES ('Ham','1.5','0.15','78','2','2.5','3.25','4');
INSERT INTO TOPPING VALUES ('Chicken','1.75','0.25','56','1.5','2','2.25','3');
INSERT INTO TOPPING VALUES ('Green Pepper','0.5','0.02','79','1','1.5','2','2.5');
INSERT INTO TOPPING VALUES ('Onion','0.5','0.02','85','1','1.5','2','2.75');
INSERT INTO TOPPING VALUES ('Roma Tomato','0.75','0.03','86','2','3','3.5','4.5');
INSERT INTO TOPPING VALUES ('Mushrooms','0.75','0.1','52','1.5','2','2.5','3');
INSERT INTO TOPPING VALUES ('Black Olives','0.6','0.1','39','0.75','1','1.5','2');
INSERT INTO TOPPING VALUES ('Pineapple','1','0.25','15','1','1.25','1.75','2');
INSERT INTO TOPPING VALUES ('Jalapenos','0.5','0.05','64','0.5','0.75','1.25','1.75');
INSERT INTO TOPPING VALUES ('Banana Peppers','0.5','0.05','36','0.6','1','1.3','1.75');
INSERT INTO TOPPING VALUES ('Regular Cheese','1.5','0.12','250','2','3.5','5','7');
INSERT INTO TOPPING VALUES ('Four Cheese Blend','2','0.15','150','2','3.5','5','7');
INSERT INTO TOPPING VALUES ('Feta Cheese','2','0.18','75','1.75','3','4','5.5');
INSERT INTO TOPPING VALUES ('Goat Cheese','2','0.2','54','1.6','2.75','4','5.5');
INSERT INTO TOPPING VALUES ('Bacon','1.5','0.25','89','1','1.5','2','3');

INSERT INTO DISCOUNT VALUES ('employee');
INSERT INTO PERCENTAGE VALUES ('employee','15');
INSERT INTO DISCOUNT VALUES ('Lunch Special Medium');
INSERT INTO DOLLAR_AMOUNT VALUES ('Lunch Special Medium','1');
INSERT INTO DISCOUNT VALUES ('Lunch Special Large');
INSERT INTO DOLLAR_AMOUNT VALUES ('Lunch Special Large','2');
INSERT INTO DISCOUNT VALUES ('Specialty Pizza');
INSERT INTO DOLLAR_AMOUNT VALUES ('Specialty Pizza','1.5');
INSERT INTO DISCOUNT VALUES ('Gameday special');
INSERT INTO PERCENTAGE VALUES ('Gameday special','20');

INSERT INTO BASE_PRICE VALUES ('1','small','Thin','3','0.5');
INSERT INTO BASE_PRICE VALUES ('2','small','Original','3','0.75');
INSERT INTO BASE_PRICE VALUES ('3','small','Pan','3.5','1');
INSERT INTO BASE_PRICE VALUES ('4','small','Gluten-Free','4','2');
INSERT INTO BASE_PRICE VALUES ('5','medium','Thin','5','1');
INSERT INTO BASE_PRICE VALUES ('6','medium','Original','5','1.5');
INSERT INTO BASE_PRICE VALUES ('7','medium','Pan','6','2.25');
INSERT INTO BASE_PRICE VALUES ('8','medium','Gluten-Free','6.25','3');
INSERT INTO BASE_PRICE VALUES ('9','Large','Thin','8','1.25');
INSERT INTO BASE_PRICE VALUES ('10','Large','Original','8','2');
INSERT INTO BASE_PRICE VALUES ('11','Large','Pan','9','3');
INSERT INTO BASE_PRICE VALUES ('12','Large','Gluten-Free','9.5','4');
INSERT INTO BASE_PRICE VALUES ('13','X-Large','Thin','10','2');
INSERT INTO BASE_PRICE VALUES ('14','X-Large','Original','10','3');
INSERT INTO BASE_PRICE VALUES ('15','X-Large','Pan','11.5','4.5');
INSERT INTO BASE_PRICE VALUES ('16','X-Large','Gluten-Free','12.5','6');

INSERT INTO ORDERS VALUES ('1', '11.50', '3.68');
INSERT INTO PIZZA VALUES ('1',TIMESTAMP("2020-03-05", "12:03:00"), TRUE, '11.50', '3.68', '1', '9');
INSERT INTO APPLIES_PIZZA VALUES ('Lunch Special Large', '1');
INSERT INTO CONTAINS VALUES ('1', 'Regular Cheese', TRUE);
INSERT INTO CONTAINS VALUES ('1', 'Pepperoni', FALSE);
INSERT INTO CONTAINS VALUES ('1', 'Sausage', FALSE);
INSERT INTO DINE_IN VALUES ('1', '14');
INSERT INTO SEAT_NUM VALUES ('1', '1');
INSERT INTO SEAT_NUM VALUES ('1', '2');
INSERT INTO SEAT_NUM VALUES ('1', '3');

INSERT INTO ORDERS VALUES ('2', '8.10', '3.23');
INSERT INTO DINE_IN VALUES ('2', '4');
INSERT INTO SEAT_NUM VALUES ('2', '1');
INSERT INTO PIZZA VALUES ('2',TIMESTAMP("2020-03-03", "12:05:00"), TRUE, '8.10', '3.23', '2', '7');
INSERT INTO APPLIES_PIZZA VALUES ('Lunch Special Medium', '2');
INSERT INTO APPLIES_PIZZA VALUES ('Specialty Pizza', '2');
INSERT INTO CONTAINS VALUES ('2', 'Feta Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('2', 'Black Olives', FALSE);
INSERT INTO CONTAINS VALUES ('2', 'Roma Tomato', FALSE);
INSERT INTO CONTAINS VALUES ('2', 'Mushrooms', FALSE);
INSERT INTO CONTAINS VALUES ('2', 'Banana Peppers', FALSE);

INSERT INTO ORDERS VALUES ('3', '6.75', '1.40');
INSERT INTO DINE_IN VALUES ('3', '4');
INSERT INTO SEAT_NUM VALUES ('3', '2');
INSERT INTO PIZZA VALUES ('3',TIMESTAMP("2020-03-03", "12:05:00"), TRUE, '6.75', '1.40', '3', '2');
INSERT INTO CONTAINS VALUES ('3', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('3', 'Chicken', FALSE);
INSERT INTO CONTAINS VALUES ('3', 'Banana Peppers', FALSE);

INSERT INTO CUSTOMER VALUES ('1', 'Andrew Wilkes-Krier', '864-254-5861', NULL);
INSERT INTO ORDERS VALUES ('4', '64.5', '19.8');
INSERT INTO PICK_UP VALUES ('4', '1');
INSERT INTO PIZZA VALUES ('4',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('4', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('4', 'Pepperoni', FALSE);
INSERT INTO PIZZA VALUES ('5',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('5', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('5', 'Pepperoni', FALSE);
INSERT INTO PIZZA VALUES ('6',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('6', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('6', 'Pepperoni', FALSE);
INSERT INTO PIZZA VALUES ('7',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('7', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('7', 'Pepperoni', FALSE);
INSERT INTO PIZZA VALUES ('8',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('8', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('8', 'Pepperoni', FALSE);
INSERT INTO PIZZA VALUES ('9',TIMESTAMP("2020-03-03", "09:30:00"), TRUE, '10.75', '3.30', '4', '10');
INSERT INTO CONTAINS VALUES ('9', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('9', 'Pepperoni', FALSE);

UPDATE CUSTOMER SET Address = '115 Party Blvd, Anderson SC 29621' WHERE Name = 'Andrew Wilkes-Krier';
INSERT INTO ORDERS VALUES ('5', '35.20', '16.86');
INSERT INTO APPLIES_ORDER VALUES ('Gameday special', '5');
INSERT INTO DELIVERY VALUES ('5', '1');
INSERT INTO PIZZA VALUES ('10',TIMESTAMP("2020-03-05", "19:11:00"), TRUE, '14.50', '5.59', '5', '14');
INSERT INTO CONTAINS VALUES ('10', 'Pepperoni', FALSE);
INSERT INTO CONTAINS VALUES ('10', 'Sausage', FALSE);
INSERT INTO CONTAINS VALUES ('10', 'Four Cheese Blend', FALSE);
INSERT INTO PIZZA VALUES ('11',TIMESTAMP("2020-03-05", "19:11:00"), TRUE, '15.50', '5.59', '5', '14');
INSERT INTO APPLIES_PIZZA VALUES ('Specialty Pizza', '11');
INSERT INTO CONTAINS VALUES ('11', 'Ham', TRUE);
INSERT INTO CONTAINS VALUES ('11', 'Pineapple', TRUE);
INSERT INTO CONTAINS VALUES ('11', 'Four Cheese Blend', FALSE);
INSERT INTO PIZZA VALUES ('12',TIMESTAMP("2020-03-05", "19:11:00"), TRUE, '14.00', '5.68', '5', '14');
INSERT INTO CONTAINS VALUES ('12', 'Jalapenos', FALSE);
INSERT INTO CONTAINS VALUES ('12', 'Bacon', FALSE);
INSERT INTO CONTAINS VALUES ('12', 'Four Cheese Blend', FALSE);

INSERT INTO CUSTOMER VALUES ('2', 'Matt Engers', '864-474-9953', NULL);
INSERT INTO ORDERS VALUES ('6', '15.35', '7.85');
INSERT INTO PICK_UP VALUES ('6', '2');
INSERT INTO PIZZA VALUES ('13', TIMESTAMP("2020-03-02", "17:30:00"), TRUE, '15.35', '7.85', '6', '16');
INSERT INTO APPLIES_PIZZA VALUES ('Specialty Pizza', '13');
INSERT INTO CONTAINS VALUES ('13', 'Green Pepper', FALSE);
INSERT INTO CONTAINS VALUES ('13', 'Onion', FALSE);
INSERT INTO CONTAINS VALUES ('13', 'Roma Tomato', FALSE);
INSERT INTO CONTAINS VALUES ('13', 'Mushrooms', FALSE);
INSERT INTO CONTAINS VALUES ('13', 'Black Olives', FALSE);
INSERT INTO CONTAINS VALUES ('13', 'Goat Cheese', FALSE);

INSERT INTO CUSTOMER VALUES ('3', 'Frank Turner', '864-232-8944', '6745 Wessex St Anderson SC 29621');
INSERT INTO ORDERS VALUES ('7', '13.25', '3.20');
INSERT INTO DELIVERY VALUES ('7', '3');
INSERT INTO PIZZA VALUES ('14', TIMESTAMP("2020-03-02", "18:17:00"), TRUE, '13.25', '3.20', '7', '9');
INSERT INTO CONTAINS VALUES ('14', 'Chicken', FALSE);
INSERT INTO CONTAINS VALUES ('14', 'Green Pepper', FALSE);
INSERT INTO CONTAINS VALUES ('14', 'Onion', FALSE);
INSERT INTO CONTAINS VALUES ('14', 'Mushrooms', FALSE);
INSERT INTO CONTAINS VALUES ('14', 'Four Cheese Blend', FALSE);

INSERT INTO CUSTOMER VALUES ('4', 'Milo Auckerman', '864-878-5679', '8879 Suburban Home, Anderson, SC 29621');
INSERT INTO ORDERS VALUES ('8', '20.4', '6.3');
INSERT INTO APPLIES_ORDER VALUES ('employee', '8');
INSERT INTO DELIVERY VALUES ('8', '4');
INSERT INTO PIZZA VALUES ('15', TIMESTAMP("2020-03-06", "20:32:00"), TRUE, '12', '3.75', '8', '9');
INSERT INTO CONTAINS VALUES ('15', 'Four Cheese Blend', TRUE);
INSERT INTO PIZZA VALUES ('16', TIMESTAMP("2020-03-06", "20:32:00"), TRUE, '12', '2.55', '8', '9');
INSERT INTO CONTAINS VALUES ('16', 'Regular Cheese', FALSE);
INSERT INTO CONTAINS VALUES ('16', 'Pepperoni', TRUE);
