# Query that lists all the tables in the current schema
SELECT TABLE_NAME 
FROM information_schema.tables
WHERE table_type = 'base table' AND table_schema = 'challenge';

# Query that lists all the columns from all the tables in the current schema
SELECT COLUMN_NAME
FROM information_schema.columns
WHERE table_schema = 'challenge';

# Which fields should be selected???
SELECT a.yn, b.yn, a.seq, b.seq
FROM 
	challenge a 
		INNER JOIN 
	challenge b ON a.yn != b.yn
WHERE a.age = b.age;

