import MySQLdb


def connection():
	connection = MySQLdb.connect('127.0.0.1', 'root', '1234', 'soccer_players')
	return connection