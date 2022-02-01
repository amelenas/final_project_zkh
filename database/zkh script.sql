-- MySQL Script generated by MySQL Workbench
-- Tue Feb  1 20:13:36 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ZKH
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ZKH
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ZKH` DEFAULT CHARACTER SET utf8 ;
USE `ZKH` ;

-- -----------------------------------------------------
-- Table `ZKH`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`roles` (
  `id_role` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_role`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`user_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`user_statuses` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`users` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(64) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `surname` VARCHAR(60) NULL,
  `phone` VARCHAR(15) NOT NULL,
  `user_status` INT NOT NULL,
  `id_role` INT NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE,
  INDEX `user_role_idx` (`id_role` ASC) VISIBLE,
  INDEX `status_idx` (`user_status` ASC) VISIBLE,
  CONSTRAINT `user_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `ZKH`.`roles` (`id_role`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_status`
    FOREIGN KEY (`user_status`)
    REFERENCES `ZKH`.`user_statuses` (`id_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`order_statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`order_statuses` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `order_status_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_order`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`orders` (
  `registration_number_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `street` VARCHAR(45) NULL,
  `house_number` VARCHAR(45) NULL,
  `scope_of_work` VARCHAR(60) NULL,
  `desirable_time_of_work` TIMESTAMP NULL,
  `opening_date` TIMESTAMP NOT NULL,
  `closing_date` TIMESTAMP NULL,
  `order_status` INT NOT NULL DEFAULT 1,
  `additional_information` VARCHAR(400) NULL,
  `picture` VARCHAR(200) NULL,
  `is_private` TINYINT NOT NULL DEFAULT 1,
  `mark` INT NULL,
  PRIMARY KEY (`registration_number_id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `status_idx` (`order_status` ASC) VISIBLE,
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ZKH`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `status_order`
    FOREIGN KEY (`order_status`)
    REFERENCES `ZKH`.`order_statuses` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`types_of_works`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`types_of_works` (
  `id_type_of_work` INT NOT NULL AUTO_INCREMENT,
  `name_of_work` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id_type_of_work`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`sites_of_work`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`sites_of_work` (
  `id_site_of_work` INT NOT NULL,
  `name_of_region` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_site_of_work`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`run_works`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`run_works` (
  `registration_number` INT NOT NULL,
  `type_of_work` INT NOT NULL,
  `id_user` INT NOT NULL,
  `site_of_work` INT NOT NULL,
  INDEX `type_of_work_idx` (`type_of_work` ASC) VISIBLE,
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  INDEX `site_of_work_fk_idx` (`site_of_work` ASC) VISIBLE,
  CONSTRAINT `registration_number`
    FOREIGN KEY (`registration_number`)
    REFERENCES `ZKH`.`orders` (`registration_number_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `type_of_work`
    FOREIGN KEY (`type_of_work`)
    REFERENCES `ZKH`.`types_of_works` (`id_type_of_work`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_user_fk`
    FOREIGN KEY (`id_user`)
    REFERENCES `ZKH`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `site_of_work_fk`
    FOREIGN KEY (`site_of_work`)
    REFERENCES `ZKH`.`sites_of_work` (`id_site_of_work`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ZKH`.`employee_works`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ZKH`.`employee_works` (
  `id_user` INT NOT NULL,
  `id_type_of_works` INT NOT NULL,
  `site of work` INT NOT NULL,
  `is_active` TINYINT(1) NOT NULL,
  INDEX `id_user_idx` (`id_user` ASC) VISIBLE,
  INDEX `site_of_work_idx` (`site of work` ASC) VISIBLE,
  CONSTRAINT `id_type_of_works`
    FOREIGN KEY (`id_type_of_works`)
    REFERENCES `ZKH`.`types_of_works` (`id_type_of_work`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `ZKH`.`users` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `site_of_work`
    FOREIGN KEY (`site of work`)
    REFERENCES `ZKH`.`sites_of_work` (`id_site_of_work`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;