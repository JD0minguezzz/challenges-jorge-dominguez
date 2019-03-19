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
		

def player_information(db_connection, player_id = 20801):
	keys = open('static/query_columns.sql').read()
	values = 'SELECT * FROM players WHERE id = {}'.format(player_id)

	try:	
		with db_connection.cursor() as cursor:
			cursor.execute(keys)
			keys_list = cursor.fetchall()
			cursor.execute(values)
			values_list = cursor.fetchone()

			if is_empty(values_list):
				return 0
			else:
				player_dict = set_dict(keys_list, values_list)
				return player_dict
	finally:
		cursor.close()