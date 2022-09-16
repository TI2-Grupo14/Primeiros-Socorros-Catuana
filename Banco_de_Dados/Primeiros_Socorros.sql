CREATE SCHEMA IF NOT EXISTS `Primeiro_Socorros` DEFAULT CHARACTER SET utf8 ;
USE `Primeiro_Socorros` ;

-- -----------------------------------------------------
-- Table `Primeiro_Socorros`.`Acidente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Primeiro_Socorros`.`Acidente` (
  `Codigo` INT NOT NULL,
  `Nome` VARCHAR(20) NOT NULL,
  `Descricao` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`Codigo`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Primeiro_Socorros`.`Noticia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Primeiro_Socorros`.`Noticia` (
  `id` INT NOT NULL,
  `Titulo` VARCHAR(50) NOT NULL,
  `Jornal` VARCHAR(50) NOT NULL,
  `Corpo` VARCHAR(1000) NOT NULL,
  `Data` CHAR(11) NOT NULL,
  `Url` VARCHAR(50) NULL,
  `CPF_Usuario` CHAR(11) NOT NULL,
  `Aprova` TINYINT NOT NULL,
  PRIMARY KEY (`id`, `CPF_Usuario`),
  INDEX `fk_Noticia_Usuario1_idx` (`CPF_Usuario` ASC) VISIBLE,
  CONSTRAINT `fk_Noticia_Usuario1`
    FOREIGN KEY (`CPF_Usuario`)
    REFERENCES `Primeiro_Socorros`.`Usuario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Primeiro_Socorros`.`Postagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Primeiro_Socorros`.`Postagem` (
  `Id` INT NOT NULL,
  `Titulo` VARCHAR(20) NULL,
  `Site` VARCHAR(50) NULL,
  `Video` VARCHAR(80) NULL,
  `CPF_Medico` CHAR(11) NOT NULL,
  `Descricao` VARCHAR(200) NULL,
  `Codigo_Acidente` INT NOT NULL,
  `Aprova` TINYINT NOT NULL,
  PRIMARY KEY (`Id`, `CPF_Medico`, `Codigo_Acidente`),
  INDEX `fk_Postagem_Acidente1_idx` (`Codigo_Acidente` ASC) VISIBLE,
  CONSTRAINT `fk_Postagem_Usuario`
    FOREIGN KEY (`CPF_Medico`)
    REFERENCES `mydb`.`Usuario` (`CPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Postagem_Acidente1`
    FOREIGN KEY (`Codigo_Acidente`)
    REFERENCES `mydb`.`Acidente` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Primeiro_Socorros`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Primeiro_Socorros`.`Usuario` (
  `CPF` CHAR(11) NOT NULL,
  `Nome` VARCHAR(80) NOT NULL,
  `Celular` CHAR(11) NULL,
  `Email` VARCHAR(50) NULL,
  `Senha` VARCHAR(50) NOT NULL,
  `Tipo` CHAR(1) NOT NULL,
  PRIMARY KEY (`CPF`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC) VISIBLE,
  UNIQUE INDEX `Celular_UNIQUE` (`Celular` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB;
