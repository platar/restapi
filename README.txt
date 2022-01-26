# restapi

http://localhost:8080/h2-console

http://localhost:8080/swagger-ui.html


CREATE USER 'youtube'@'localhost' IDENTIFIED BY 'youtube';
GRANT ALL privileges ON youtube.* TO 'youtube'@'localhost';

CREATE USER 'youtubetest'@'localhost' IDENTIFIED BY 'youtubetest';
GRANT ALL privileges ON youtubetest.* TO 'youtubetest'@'localhost';

create database youtubetest;