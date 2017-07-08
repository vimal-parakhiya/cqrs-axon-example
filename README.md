# cqrs-axon-example
CQRS example using Axon 3

### Create DB Schema (MySQL)
```
CREATE SCHEMA axoncargoautoconfig;
CREATE USER 'axoncargousrautoconfig'@'%' IDENTIFIED BY 'axoncargopasswdautoconfig';
GRANT ALL PRIVILEGES ON axoncargoautoconfig.* TO 'axoncargousrautoconfig'@'%';
FLUSH PRIVILEGES;
```