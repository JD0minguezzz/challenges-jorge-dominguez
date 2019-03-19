import MySQLdb
import db_connection as db


def get_data(columns = '*', where = '1=1'):
	data = 'no data'
	try:
		query = 'SELECT {0} FROM players WHERE {1}'.format(columns,where)
		dbConnection = db.connection()
		cursor = dbConnection.cursor()
		cursor.execute(query)
		data = cursor.fetchall()
		cursor.close()
		db.close()
	except Error as e:
		print(e)
	finally:
		return data