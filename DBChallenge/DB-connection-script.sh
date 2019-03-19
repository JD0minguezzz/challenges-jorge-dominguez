#!/bin/bash

mysql -h localhost -u root -p1234 --execute "
	USE db_challenge;
	SET @mean = 0;
	SET @median = 0;
	SET @md = 0;
	SET @min = 0;
	SET @max = 0;
	CALL calculus(@mean, @median, @md, @min, @max);
	SELECT @mean, @median, @md, @min, @max; 
	exit();
	"

: '
	mysql -h mysqlchallenge.cm6hb8mfglop.us-east-1.rds.amazonaws.com -u Team2 -pEndava12345! 
	--execute "
	USE db_challenge;
	SET @mean = 0;
	SET @median = 0;
	SET @md = 0;
	SET @min = 0;
	SET @max = 0;
	CALL calculus(@mean, @median, @md, @min, @max);
	SELECT @mean, @median, @md, @min, @max; 
	"

for i in {1..99}
do
	mysql -h mysqlchallenge.cm6hb8mfglop.us-east-1.rds.amazonaws.com -u Team2 -pEndava12345! 
	USE challenge

	SET @mean = 0;
	SET @median = 0;
	SET @md = 0;
	SET @min = 0;
	SET @max = 0;

	CALL calculus(@mean, @median, @md, @min, @max);

	SELECT @mean, @median, @md, @min, @max; 

	if [ $((i%2)) -ne 0 ]
	then
    	echo $i
	fi
done '