CREATE SCHEMA IF NOT EXISTS `ariadna_items` DEFAULT CHARACTER SET utf8 ;
USE `ariadna_items` ;

DROP TABLE `discarded`;
DROP TABLE `item_return`;
DROP TABLE `delivery`;
DROP TABLE `delivery_packet`;
DROP TABLE `member`;
DROP TABLE `item_property_value`;
DROP TABLE `item_property`;
DROP TABLE `item`;
DROP TABLE `model_property_value`;
DROP TABLE `model_property`;
DROP TABLE `model`;
DROP TABLE `type`;

CREATE TABLE `type` (
    `name` VARCHAR(32) NOT NULL COMMENT 'Имя типа снаряжения',
    CONSTRAINT type_pk PRIMARY KEY (`name`)
)
ENGINE = InnoDB COMMENT = 'Тип снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `model` (
    `name` VARCHAR(64) NOT NULL COMMENT 'Имя модели',
    `vendor` VARCHAR(32) NOT NULL COMMENT 'Имя производителя',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `description` VARCHAR(256) NOT NULL DEFAULT 'Нету' COMMENT 'Описание модели',
    CONSTRAINT model_pk PRIMARY KEY (`name`, `vendor`, `type`),
    CONSTRAINT model_fk_type FOREIGN KEY (`type`)
        REFERENCES `type` (`name`)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
)
ENGINE = InnoDB COMMENT = 'Модель единицы снаряжение'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `model_property` (
    `name` VARCHAR(32) NOT NULL COMMENT 'Имя свойства модели снаряжения',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `description` VARCHAR(256) NOT NULL DEFAULT 'Нету' COMMENT 'Описание свойства снаряжения',
    CONSTRAINT model_property_pk PRIMARY KEY (`name`, `type`),
    CONSTRAINT model_property_fk_type FOREIGN KEY (`type`)
        REFERENCES `type` (`name`)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
ENGINE = InnoDB COMMENT = 'Свойства снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `model_property_value` (
    `name` VARCHAR(32) NOT NULL COMMENT 'Имя свойства модели снаряжения',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `model_name` VARCHAR(64) NOT NULL COMMENT 'Имя модели',
    `vendor` VARCHAR(32) NOT NULL COMMENT 'Имя производителя',
    `value` VARCHAR(128) NOT NULL DEFAULT 'Нету' COMMENT 'Значение свойства',
    `change_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        COMMENT 'Дата последнего изменения значения',
    CONSTRAINT model_property_value_pk PRIMARY KEY (`model_name`, `vendor`, `name`, `type`),
    CONSTRAINT model_property_value_fk_model_name_vendor_type FOREIGN KEY (`model_name`, `vendor`, `type`)
        REFERENCES `model` (`name`, `vendor`, `type`)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT model_property_value_fk_name_type FOREIGN KEY (`name`, `type`)
        REFERENCES `model_property` (`name`, `type`)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
)
ENGINE = InnoDB COMMENT = 'Значение свойства снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `item` (
    `id` INTEGER NOT NULL AUTO_INCREMENT COMMENT 'Сурогатный идентификатор единицы снаряжения',
    `number` INTEGER(32) NOT NULL COMMENT 'Порядковый номер единицы снаряжения',
    `model_name` VARCHAR(64) NOT NULL COMMENT 'Имя модели',
    `vendor` VARCHAR(32) NOT NULL COMMENT 'Имя производителя',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `receipt_date` TIMESTAMP NOT NULL DEFAULT
        CURRENT_TIMESTAMP COMMENT 'Дата поступления единицы снаряжения',
    `note` VARCHAR(256) NOT NULL DEFAULT 'Нету' COMMENT 'Примечание',
    CONSTRAINT item_pk PRIMARY KEY (`id`),
    CONSTRAINT item_uk_number_type UNIQUE (`number`, `type`),
    CONSTRAINT item_fk_model_name_vendor_type FOREIGN KEY (`model_name`, `vendor`, `type`)
        REFERENCES `model` (`name`, `vendor`, `type`)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
)
ENGINE = InnoDB COMMENT = 'Единица снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `item_property` (
    `name` VARCHAR(32) NOT NULL COMMENT 'Имя свойства единицы снаряжения',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `description` VARCHAR(256) NOT NULL DEFAULT 'Нету' COMMENT 'Описание свойства еденицы снаряжения',
    CONSTRAINT item_property_pk PRIMARY KEY (`name`, `type`),
    CONSTRAINT item_property_fk_type FOREIGN KEY (`type`)
        REFERENCES `type` (`name`)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
ENGINE = InnoDB COMMENT = 'Свойства снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `item_property_value` (
    `item_id` INTEGER NOT NULL COMMENT 'Сурогатный идентификатор единицы снаряжения',
    `name` VARCHAR(32) NOT NULL COMMENT 'Имя свойства единицы снаряжения',
    `type` VARCHAR(32) NOT NULL COMMENT 'Тип снаряжения',
    `value` VARCHAR(128) NOT NULL DEFAULT 'Нету' COMMENT 'Значение свойства',
    `change_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        COMMENT 'Дата последнего изменения значения',
    CONSTRAINT item_property_value_pk PRIMARY KEY (`item_id`, `name`, `type`),
    CONSTRAINT item_property_value_fk_item_id FOREIGN KEY (`item_id`)
        REFERENCES `item` (`id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT item_property_value_fk_name_type FOREIGN KEY (`name`, `type`)
        REFERENCES `item_property` (`name`, `type`)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
)
ENGINE = InnoDB COMMENT = 'Значение свойства снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `member` (
    `member_id` INTEGER NOT NULL COMMENT 'Сурогатный идентификатор члена клуба',
    `firstname` VARCHAR(32) NOT NULL COMMENT 'Имя',
    `lastname` VARCHAR(32) NOT NULL COMMENT 'Фамилия',
    CONSTRAINT member_pk PRIMARY KEY (`member_id`)
)
ENGINE = InnoDB COMMENT = 'Член клуба'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `delivery_packet` (
    `delivery_packet_id` INTEGER NOT NULL COMMENT 'Сурогатный идентификатор пакета выдачи снаряжения',
    `member_id` INTEGER NOT NULL COMMENT 'Член клуба',
    `event` VARCHAR(64) NOT NULL COMMENT 'Мероприятие',
    `delivery_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата выдачи снаряжения',
    `expected_return_date` TIMESTAMP NOT NULL COMMENT 'Дата примерного возврата снаряжения',
    CONSTRAINT delivery_packet_pk PRIMARY KEY (`delivery_packet_id`),
    CONSTRAINT delivery_packet_fk_member_id FOREIGN KEY (`member_id`)
        REFERENCES `member` (`member_id`)
            ON UPDATE CASCADE
            ON DELETE NO ACTION
)
ENGINE = InnoDB COMMENT = 'Пакет выдачи снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `delivery` (
    `delivery_packet_id` INTEGER NOT NULL COMMENT 'Пакет выдачи снаряжения',
    `item_id` INTEGER NOT NULL COMMENT 'Единицы снаряжения',
    CONSTRAINT delivery_pk PRIMARY KEY (`delivery_packet_id`, `item_id`),
    CONSTRAINT delivery_fk_delivery_packet_id FOREIGN KEY (`delivery_packet_id`)
        REFERENCES `delivery_packet` (`delivery_packet_id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT delivery_fk_item_id FOREIGN KEY (`item_id`)
        REFERENCES `item` (`id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
ENGINE = InnoDB COMMENT = 'Выдачи единицы снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `item_return` (
    `delivery_packet_id` INTEGER NOT NULL COMMENT 'Пакет выдачи снаряжения',
    `item_id` INTEGER NOT NULL COMMENT 'Возвращаемая единица снаряжения',
    `return_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Дата возвращение',
    CONSTRAINT return_pk PRIMARY KEY (`delivery_packet_id`, `item_id`),
    CONSTRAINT return_fk_delivery_packet_id_item_id FOREIGN KEY (`delivery_packet_id`, `item_id`)
        REFERENCES `delivery` (`delivery_packet_id`, `item_id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
ENGINE = InnoDB COMMENT = 'Возвращение единицы снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `discarded` (
    `item_id` INTEGER NOT NULL COMMENT 'Списываемая единица снаряжения',
    `cause` VARCHAR(256) NOT NULL COMMENT 'Причина',
    `discarded_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
        COMMENT 'Дата списания единицы снаряжения',
    `delivery_packet_id` INTEGER NULL DEFAULT NULL  COMMENT 'Пакет выдачи снаряжения',
    CONSTRAINT discarded_pk PRIMARY KEY (`item_id`),
    CONSTRAINT discarded_fk_item_id FOREIGN KEY (`item_id`)
        REFERENCES `item` (`id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE,
    CONSTRAINT discarded_fk_delivery_packet_id FOREIGN KEY (`delivery_packet_id`)
        REFERENCES `delivery` (`delivery_packet_id`)
            ON UPDATE CASCADE
            ON DELETE CASCADE
)
ENGINE = InnoDB COMMENT = 'Списание единицы снаряжения'
CHARACTER SET utf8 COLLATE utf8_general_ci;