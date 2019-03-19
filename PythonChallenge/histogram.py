import numpy as np
import matplotlib.pyplot as plt
import MySQLdb

default_path = 'C:/Users/jdominguez/OneDrive - ENDAVA/Documents/challenges/PythonChallenge/static/plots/'
dbConnection = MySQLdb.connect('127.0.0.1', 'root', '1234', 'soccer_players')


def get_histogram():
	ageQuery = 'SELECT age FROM players'
	ratingQuery = 'SELECT overall FROM players'
	ages = []
	overall = []

	try:
		with dbConnection.cursor() as cursor:
			cursor.execute(ageQuery)
			agesTuple = list(cursor)
			cursor.execute(ratingQuery)
			ratingTuple = list(cursor)
			for a, r in zip(agesTuple, ratingTuple):
				ages.append(a[0])
				overall.append(r[0])
	except Error as e:
		print(e)
	finally:
		cursor.close()

	bins = np.linspace(15, 100)
	plt.hist([ages, overall], bins, label = ['Ages', 'Overall'])
	plt.legend(loc = 'upper right')
	plt.savefig(default_path + 'histogram_overall_ages.png')
	return plt

get_histogram()