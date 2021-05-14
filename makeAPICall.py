import requests


url ="http://192.168.1.100:8000/apiendpoint/todo/api/v1.0/tasks/mvn"

r = requests.post(url, json={"apiKey": "start"})
