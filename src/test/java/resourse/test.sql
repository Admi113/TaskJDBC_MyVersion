#SELECT * FROM sys.new_table;
#	insert into new_table (name) values('Someone');

#SELECT * FROM testdb.sometable;
#insert into testdb.testtable(name) values('Third');
#
#CREATE TABLE `testdb`.`testtable` (
#                                       `id` INT NOT NULL AUTO_INCREMENT,
#                                       `name` VARCHAR(45) NOT NULL DEFAULT 'Иван',
#                                       `lastName` VARCHAR(45) NOT NULL DEFAULT 'Иванов' ,
#                                       `age` TINYINT(3) NOT NULL DEFAULT 33 ,
#                                       PRIMARY KEY (`id`));
DROP TABLE `testdb`.`testtable`;
#INSERT INTO testdb.testtable(name, lastName, age) VALUES (?,?,?);
# DELETE FROM testdb. testtable WHERE id = ?;
# select * from testdb.testtable;
# delete  from testdb.testtable;