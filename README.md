server
======

Game Server for refactoring

Manual yopta:
======
заходим под рутом в мускул

    CREATE USER 'checkers'@'localhost' IDENTIFIED BY 'QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P';
 
    GRANT ALL PRIVILEGES ON *.* TO 'checkers'@'localhost' WITH GRANT OPTION;
 
    DROP SCHEMA IF EXISTS `checkers` ;
    CREATE SCHEMA IF NOT EXISTS `checkers` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
    USE `checkers` ;

    -- -----------------------------------------------------
    -- Table `checkers`.`Users`
    -- -----------------------------------------------------
    DROP TABLE IF EXISTS `checkers`.`Users` ;

    CREATE  TABLE IF NOT EXISTS `checkers`.`Users` (
      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
      `nickname` VARCHAR(20) NOT NULL ,
      `password` CHAR(64) NOT NULL,
      `last_visit` TIMESTAMP NOT NULL,
      `registration_date` TIMESTAMP NOT NULL,
      `rating` SMALLINT NOT NULL DEFAULT 500,
      `win_quantity` INT UNSIGNED NOT NULL DEFAULT 0,
      `lose_quantity` INT UNSIGNED NOT NULL DEFAULT 0,
      PRIMARY KEY (`id`) ,
      INDEX `full_idx` (`nickname` ASC, `password` ASC, `id` ASC, `rating` ASC, `win_quantity` ASC, `lose_quantity` ASC),
      UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) )
    ENGINE = InnoDB;

    USE `checkers` ;


настройка nginx - добавить в свой конфиг или скопировать https://github.com/valyukov/server/blob/master/nginx.conf
====
    upstream backend_1 {
		server localhost:8000;
	}

	server {
		listen *:8008;
		server_name localhost 172.16.51.25 172.20.10.10;

		location ~* ^.+\.(js|css|jpeg|jpg|png|gif|txt|woff|ttf|eot|svg|map)$ {
			root 		/home/max/IdeaProjects/server/static;#your path is here
			expires		30d;
		}

		location / {
			proxy_pass			http://backend_1;
			proxy_set_header Host		$host;
			proxy_set_header X-Real-IP	$remote_addr;
		}

		location ~ /\.ht {
			deny all;
		}
	root	html;
	}
-----
а так же обязательно надо замутить папочку statistic в папке проекта


--------
Code coverage
=======

Для тестирования были добавлены:
 * testng
 * guice
 * cobertura

Что делать:

1. "View" --> "Tool Buttons"
2. Maven Projects
3. java-server --> Lifecycle
4. Run 'java-sever[site]' (Ctrl-Shift-F10)


Результат смотреть в

```
[project root]/target/site/index.html
```
