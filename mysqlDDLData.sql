DROP DATABASE IF EXISTS `mybatis_example`;
CREATE DATABASE IF NOT EXISTS `mybatis_example`;
use `mybatis_example`;

DROP TABLE IF EXISTS `mybatis_example`.`latest_electricity_consumption_lec`;
DROP TABLE IF EXISTS `mybatis_example`.`electricity_bills`;

CREATE TABLE IF NOT EXISTS `mybatis_example`.`latest_electricity_consumption_lec` (
  `user_id` INT NOT NULL,
  `latest_electricity_consumption` DECIMAL(16,2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `mybatis_example`.`electricity_bills` (
  `user_id` INT NOT NULL COMMENT 'foreignkey_of_latest_electricity_consumption_lec',
  `electricity_bills` DECIMAL(16,2) NOT NULL DEFAULT 0.00,
  UNIQUE INDEX `idelectricity_bills_UNIQUE` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mybatis_example`.`latest_electricity_consumption_lec` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);
    
DROP PROCEDURE IF EXISTS proc_initData;
DELIMITER ;;
CREATE PROCEDURE proc_initData()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i<=2000 DO
        INSERT INTO `mybatis_example`.`latest_electricity_consumption_lec` (`user_id`, `latest_electricity_consumption`) VALUES (i, '0.00');
        SET i = i+1;
    END WHILE;
    commit;
END ;;
CALL proc_initData();