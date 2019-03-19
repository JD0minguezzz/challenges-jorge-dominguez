import MySQLdb


def populate_db():

	connection = MySQLdb.connect('127.0.0.1', 'root', '1234', 'soccer_players')
	load_script = open('static/insert_csv.sql')

	try:
		with connection.cursor() as cursor:
			cursor.execute(load_script.read())
			print('\nDatabase successfully populated.')
	except Error:
		print('\nAn unexpected error has occurred.')
	finally:
		cursor.close()
		connection.commit()
		connection.close()

populate_db()