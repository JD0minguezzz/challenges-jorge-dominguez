import db_connection
import variables as var
import compare_players as cp
import histogram as hst
import player_data as pd
import pie_plots as plot
from flask import Flask, render_template, request


app = Flask(__name__)
database = db_connection.connection()


@app.after_request
def add_header(response):
    response.cache_control.no_store = True
    return response


@app.route('/', methods=['GET', 'POST'])
def index():
	return render_template('index.html')


@app.route('/player_information_id', methods=['GET', 'POST'])
def input_player_id():
	return render_template('player_information_id.html', players_data = None)


@app.route('/players_compare_id', methods=['GET', 'POST'])
def input_players_id():
	return render_template('players_compare_id.html', players_data = None)


@app.route('/player_information', methods = ['GET', 'POST'])
def player_information():
	player_id = request.form['player_id']

	if not player_id:
		player_data = pd.player_information(database)
	else:
		player_data = pd.player_information(database, player_id)
	return set_template(player_data)


@app.route('/central_tendency_variables', methods = ['GET', 'POST'])
def central_tendency_var():
	stats = var.central_tendency_var()
	return render_template('stats.html', stats = stats)


@app.route('/plots', methods = ['GET', 'POST'])
def plots():
	plot.get_pie_count('nationality', 'C:/Users/jdominguez/PythonChallenge/static/plots/pie_nationality.png')
	plot.get_pie_count('club', 'C:/Users/jdominguez/PythonChallenge/static/plots/pie_club.png')
	return render_template('plots.html')


@app.route('/players_compare', methods = ['GET', 'POST'])
def players_compare():
	player1_id = request.form['player1_id']
	player2_id = request.form['player2_id']

	plot.get_comparison_pie_diagram('heading_accuracy', player1_id, player2_id)

	if (not player1_id) | (not player2_id): 
		players_data = cp.compare_players(database)
		plot.comparison_plot()
	else:
		players_data = cp.compare_players(database, player1_id, player2_id)
		plot.comparison_plot(player1_id, player2_id)
	return set_template_compare(players_data)


def set_template(data):
	if type(data) is dict:
		return render_template('player_information_id.html', player_data = data)
	else:
		return render_template('player_information_id.html', player_data = None)


def set_template_compare(data):
	if type(data) is list:
		return render_template('players_compare_id.html', players_data = data)
	else:
		return render_template('players_compare_id.html', players_data = None)
		

if __name__ == '__main__':
	app.run()