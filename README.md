# cqrs-axon-example
CQRS example using Axon 3

### Create Database Schema (MySQL)
```
CREATE SCHEMA axoncargo;
CREATE USER 'axonuser'@'%' IDENTIFIED BY 'axonpassword';
GRANT ALL PRIVILEGES ON axoncargo.* TO 'axonuser'@'%';
FLUSH PRIVILEGES;
```