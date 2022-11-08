from http.server import HTTPServer, BaseHTTPRequestHandler
from rds_process import rds_handler
import json

#open json file and give it to data variable as a dictionary
with open("db.json") as data_file:
    data = json.load(data_file)

#Defining a HTTP request Handler class
class ServiceHandler(BaseHTTPRequestHandler):
    #sets basic headers for the server

# server headers
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type','text/json')
        #reads the length of the Headers
        length = int(self.headers['Content-Length'])
        #reads the contents of the request
        content = self.rfile.read(length)
        temp = str(content).strip('b\'')
        self.end_headers()
        return temp

    #GET Method Defination
    def do_GET(self):
        #defining all the headers
        if self.path == '/':
            self.send_response(200)
            self.send_header('Content-type','text/json')
            self.end_headers()
            datas ={}
            datas["health"]="200"
            #prints all the keys and values of the json file
            self.wfile.write(json.dumps(datas).encode())
        if self.path == '/api/id':
            key = self._set_headers()
            rds_ob = rds_handler()
            result = rds_ob.read(key)
            print(result)
            self.wfile.write(json.dumps(result).encode())

    #POST method defination
    def do_POST(self):
        if self.path == '/api/create':
            temp = self._set_headers()
            rds_ob = rds_handler()

            record = temp.split(",")
            print(record)
            rds_ob.create(record)


    #PUT method Defination
    def do_PUT(self):
        if self.path == '/api/update':
            temp = self._set_headers()
            data = temp.split(",")
            #seprating input into key and value
            key = data[0]
            value = data[1]
            id = data[2]
            rds_ob = rds_handler()

            rds_ob.update(key,value,id)
        #check if key is in data


    #DELETE method defination
    def do_DELETE(self):
        if self.path == '/api/delete':
            temp = self._set_headers()
            id = temp
            rds_ob = rds_handler()
            rds_ob.delete(id)

#Server Initialization
server = HTTPServer(('127.0.0.1',8080), ServiceHandler)
server.serve_forever()