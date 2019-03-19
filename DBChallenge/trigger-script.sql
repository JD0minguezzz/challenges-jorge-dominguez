# Add the trgr column to challenge table
ALTER TABLE challenge 
	ADD trgr INT
		AFTER sentence;
        
# Update each trgr row with the value from seq plus 100
UPDATE challenge
	SET trgr = seq + 100;
    
# Creates trigger that checks if the new value to be updated in trgr is even or odd 
# and changes that value depending on the condition
DELIMITER //
CREATE TRIGGER trigger_challenge BEFORE UPDATE ON challenge
FOR EACH ROW
BEGIN
	IF ((NEW.trgr % 2) = 0) THEN
		SET NEW.trgr = NEW.trgr + 3;
	ELSE
		SET NEW.trgr = NEW.trgr - 5;
	END IF;
END;//
DELIMITER ;