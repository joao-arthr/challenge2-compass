CREATE USER 'springsoda'@'%' IDENTIFIED BY 'springsoda';

GRANT ALL PRIVILEGES ON * . * TO 'springsoda'@'%';

DROP USER if exists 'springsoda'@'%' ;