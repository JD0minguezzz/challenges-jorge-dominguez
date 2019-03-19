LOAD DATA LOCAL INFILE 'C:/Users/ccristancho/Documents/Challenges/DB_Challenge/demofile.csv'
INTO TABLE challenge
COLUMNS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
ESCAPED BY '"'
LINES TERMINATED BY '\n'
(seq, yn, age, @birthday, bool, city, ccnumber, @date_c, digit, dollar, first, chifre, name, last, paragraph, sentence)
SET birthday = str_to_date(@birthday, '%c/%d/%Y'), `date_c` = str_to_date(@date_c, '%c/%d/%Y');
