CREATE TABLE `users` (
  `id`        INT         NOT NULL    AUTO_INCREMENT,
  `version`   INT         NOT NULL,
  `user_name` VARCHAR(45) NOT NULL,
  `balance`   INT         NOT NULL    DEFAULT     0,
  `created`   DATETIME    NULL        DEFAULT     now(),
  `modified`  DATETIME    NULL        DEFAULT     now(),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`user_name` ASC));


CREATE TABLE `games` (
  `id`                INT                 NOT NULL  AUTO_INCREMENT,
  `bet_amount`        INT                 NOT NULL,
  `user_id`           INT                 NOT NULL,
  `game_over_status`  ENUM('WON', 'LOST') NOT NULL,
  `game_over_amount`  INT                 NOT NULL,
  `created`           DATETIME            NULL      DEFAULT   now(),
  `modified`          DATETIME            NULL      DEFAULT   now(),
  PRIMARY KEY  (`id`),
  CONSTRAINT  `user_id`
  FOREIGN KEY  (`user_id`)
  REFERENCES  `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
