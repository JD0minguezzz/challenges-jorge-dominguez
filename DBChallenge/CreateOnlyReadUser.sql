CREATE USER 'ChallengeOR'@'%' IDENTIFIED BY '1234567890';
GRANT SELECT ON challenge.* TO 'ChallengeOR'@'%';
FLUSH PRIVILEGES;
exit;