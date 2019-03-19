# Index creation to optimize stored procedure call
create index ccnumber_opt
on challenge (ccnumber);

# Stored procedure to calculate mean, median, mode, 
# min and max value based on the ccnumber column
DROP PROCEDURE IF EXISTS calculus;
DELIMITER //
CREATE PROCEDURE calculus (OUT mean BIGINT(20), OUT median BIGINT(20), OUT md BIGINT(20), OUT min BIGINT(20), OUT max BIGINT(20))
BEGIN
	BEGIN
		DECLARE curMean CURSOR FOR
			SELECT AVG(ccnumber) FROM challenge;
		OPEN curMean;
			FETCH curMean INTO mean;
		CLOSE curMean;
	END;
	BEGIN
	  DECLARE curMn CURSOR FOR
			SELECT AVG(cc.ccnumber) as median_val
			FROM (
				SELECT c.ccnumber, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum
				FROM challenge c, (SELECT @rownum:=0) r
				WHERE ccnumber is NOT NULL
				ORDER BY c.ccnumber
			) as cc
			WHERE cc.row_number IN (FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2));
		OPEN curMn;
			FETCH curMn INTO median;
		CLOSE curMn;
	END;
	BEGIN
		DECLARE curMd CURSOR FOR
			SELECT ccnumber
			FROM (
				SELECT ccnumber
				FROM challenge
				GROUP BY ccnumber
				LIMIT 1) T1;
		OPEN curMd;
			FETCH curMd INTO md;
		CLOSE curMd;
	END;
	BEGIN
		DECLARE curMin CURSOR FOR
			SELECT MIN(ccnumber) FROM challenge;
		OPEN curMin;
			FETCH curMin INTO min;	
		CLOSE curMin; 
	END;
	BEGIN
		DECLARE curMax CURSOR FOR
			SELECT MAX(ccnumber) FROM challenge;
		OPEN curMax;
			FETCH curMax INTO max;	
		CLOSE curMax; 
	END;
END;//
DELIMITER ;

set @mean = 0;
set @median = 0;
set @md = 0;
set @min = 0;
set @max = 0;

CALL calculus(@mean, @median, @md, @min, @max);

select @mean, @median, @md, @min, @max;
    
# Query that calculates the median
/* SELECT AVG(cc.ccnumber) as median_val
FROM (
SELECT c.ccnumber, @rownum:=@rownum+1 as `row_number`, @total_rows:=@rownum
  FROM challenge c, (SELECT @rownum:=0) r
  WHERE ccnumber is NOT NULL
  ORDER BY c.ccnumber
) as cc
WHERE cc.row_number IN (FLOOR((@total_rows+1)/2), FLOOR((@total_rows+2)/2)); */

# Another median calculation query (slow)
/* SELECT c.ccnumber from challenge c, challenge y
GROUP BY c.ccnumber
HAVING SUM(SIGN(1-SIGN(y.ccnumber-c.ccnumber)))/COUNT(*) > .5
LIMIT 1 */