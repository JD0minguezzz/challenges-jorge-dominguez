import data
import statistics as stats


stats_table = []


def get_list(data,index = 0):
	data_list = []
	for row in data:
		data_list.append(row[index])
	return data_list

	
def table_data_list(column):
	query = data.get_data(column)
	resultset = get_list(query)
	return resultset

	
def add_stats(column = "age"):
	lst_results = []
	stats_list = table_data_list(column)
	lst_results.append(column)
	lst_results.append(str(stats.mean(stats_list)))
	lst_results.append(str(stats.median(stats_list)))
	lst_results.append(str(stats.mode(stats_list)))
	lst_results.append(str(stats.pstdev(stats_list)))
	lst_results.append(str(stats.pvariance(stats_list)))
	stats_table.append(lst_results)

	
def save_all_stats(lst_fields):
	for field in lst_fields:
		add_stats(field)

		
def central_tendency_var():
	stats_table.clear()
	lst_fields = ["age","overall","potential","international_reputation","acceleration"]
	save_all_stats(lst_fields)
	return stats_table