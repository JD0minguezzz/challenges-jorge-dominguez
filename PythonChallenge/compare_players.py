def set_column_names(column_list):
	field_list = []
	for k in column_list:
		field_list.append(k[0])
	return field_list


def set_dict(column_list, value_list):
	field_list = set_column_names(column_list) 
	player_dict = {}
	for k, v in zip(field_list, value_list):
		player_dict[k] = v
	return player_dict


def is_empty(any_structure):
	if any_structure:
		return False
	else:
		return True
		

def compare_players(db_connection, player1_id = 20801, player2_id = 177003):
	keys = open('static/query_columns.sql').read()
	player1_values = 'SELECT * FROM players WHERE id = {}'.format(player1_id)
	player2_values = 'SELECT * FROM players WHERE id = {}'.format(player2_id)

	try:	
		with db_connection.cursor() as cursor:
			cursor.execute(keys)
			keys_list = cursor.fetchall()
			cursor.execute(player1_values)
			player1_values_list = cursor.fetchone()
			cursor.execute(player2_values)
			player2_values_list = cursor.fetchone()

			if (is_empty(player1_values_list)) | (is_empty(player2_values_list)):
				return 0
			else:
				player1_dict = set_dict(keys_list, player1_values_list)
				player2_dict = set_dict(keys_list, player2_values_list)
				return [player1_dict, player2_dict]
	finally:
		cursor.close()