pipeline {
	agent any         
		stages {                 
			stage('Prepare') {                         
				steps {                                 
					echo 'Preparing..'                         
					sh 'rm -rf cichallenge ; git clone https://gitlab.com/mao.11.hf/cichallenge.git'
				}                 
			}                 
			stage('Build') {                         
				steps {                                 
					echo 'Building..'                         
				}                 
			}                 
			stage('Test') {                         
				steps {                                 
					echo 'Testing...'
					//Replace CONTAINERNAME with the name given to your own image
					sh 'docker run CONTAINERNAME npm test'                         
				}                 
			}
			stage('push') {
				steps {
					echo 'pushing'
				}
			}                 
			stage('Deploy') {                         
				steps {                                 
					echo 'Deploying....'                                     					
				}                 
			}         
		} 
} 