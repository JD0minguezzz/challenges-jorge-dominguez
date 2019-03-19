import matplotlib.pyplot as plt
import numpy as np
import MySQLdb
import data as db


default_path = 'C:/Users/jdominguez/OneDrive - ENDAVA/Documents/challenges/PythonChallenge/static/plots/'


def get_list(data,index = 0):
	data_list = []
	for row in data:
		data_list.append(row[index])
	return data_list
	

def get_count_per_field(nationalities):
	dictionary = {}
	for nationality in range(0, len(nationalities)):
		if nationalities[nationality] not in dictionary:
			dictionary[nationalities[nationality]] = 1
		else:
			dictionary[nationalities[nationality]] = dictionary.get(nationalities[nationality]) + 1
	return dictionary

	
def get_keys(dictionary):
	values = []
	for key in dictionary.keys():
		values.append(key)
	return values

	
def get_values(dictionary):
	values = []
	for value in dictionary.values():
		values.append(value)
	return values

	
def get_pie(numbers, titles, plot_title = 'Pie Diagram'):
	fig1, ax1 = plt.subplots()
	ax1.pie(numbers,explode = None,labels = titles, autopct='%1.1f%%',
	shadow = True, startangle = 90)
	ax1.axis('equal')
	plt.title(plot_title)
	return plt

	
def get_comparison_pie_diagram(column_name = 'overall', id_1 = 158023, id_2 = 188545):
	statement_columns = 'name, {}'.format(column_name)
	statement_where = 'id in ({},{})'.format(str(id_1),str(id_2))
	info_data = db.get_data(statement_columns, statement_where)
	name_list = get_list(info_data,0)
	attribute_list = get_list(info_data,1)
	pie_chart = get_pie(attribute_list, name_list, 'Pie diagram for {0}'.format(column_name))
	pie_chart.savefig(default_path + id_1 + id_2 + '.png')
	return pie_chart

	
def cut_list(some_list, limit = 10):
	cutted_list = []
	for i in range(0,limit):
		cutted_list.append(some_list[i])
	return cutted_list

	
def get_pie_count_per_column(list_columns, limit = 10):
	pie_list = []
	for column in list_columns:
		data = db.get_data(column)
		data_dictionary = get_count_per_field(get_list(data))
		dictionary_keys = get_keys(data_dictionary)
		dictionary_values = get_values(data_dictionary)
		pie_list.append(get_pie(cut_list(dictionary_values,limit), cut_list(dictionary_keys,limit), column))
	return pie_list

	
def get_pie_count(column, path, limit = 10):
	data = db.get_data(column)
	data_dictionary = get_count_per_field(get_list(data))
	dictionary_keys = get_keys(data_dictionary)
	dictionary_values = get_values(data_dictionary)
	get_pie(cut_list(dictionary_values, limit), cut_list(dictionary_keys, limit), column).savefig(path)

	
def save_pie_list(pie_list):
	count = 0
	for pie in pie_list:
		pie.savefig(default_path + str(count))
		count += 1

		
def comparison_plot(id_1 = 158023, id_2 = 188545):
	data = db.get_data('shot_power, potential, sprint_speed, acceleration', 'id in ({},{})'.format(id_1, id_2))
	data_names = db.get_data('name', 'id in ({}, {})'.format(id_1, id_2))
	player_1_name = data_names[0][0]
	player_2_name = data_names[1][0]
	player_1_stats = data[0]
	player_2_stats = data[1]
	ind = np.arange(len(player_1_stats))
	width = 0.25
	fig, ax = plt.subplots()
	rects1 = ax.bar(ind - width/2, player_1_stats, width, yerr = None,
	color = 'SkyBlue', label = player_1_name)
	rects2 = ax.bar(ind + width/2, player_2_stats, width, yerr = None,
	color = 'IndianRed', label = player_2_name)
	ax.set_ylabel('Y')
	ax.set_title('Players comparison')
	ax.set_xticks(ind)
	ax.set_xticklabels(('SHOT POTENTIAL', 'POTENTIAL', 'SPRINT_SPEED', 'ACCELERATION'))
	ax.legend()
	def autolabel(rects, xpos = 'center'):
		xpos = xpos.lower()
		ha = {'center': 'center', 'right': 'left', 'left': 'right'}
		offset = {'center': 0.5, 'right': 0.57, 'left': 0.43}
		for rect in rects:
			height = rect.get_height()
			ax.text(rect.get_x() + rect.get_width() * offset[xpos], 1.01 * height,'{}'.format(height), ha = ha[xpos], va = 'bottom')
	autolabel(rects1, 'left')
	autolabel(rects2, 'right')
	plt.savefig(default_path + 'comparison_' + id_1 + id_2 + '.png')