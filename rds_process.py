import mysql.connector
import os


HOST = os.environ.get("HOST","database-1.cc48pm4rwbrd.ap-northeast-1.rds.amazonaws.com")
USER = os.environ.get("USER_NAME", "admin")
PASSWD = os.environ.get("PASSWD", "space581")
DB_NAME = os.environ.get("DB_NAME", "env")

class rds_handler():

    def __init__(self):
        self.db_connection = mysql.connector.connect(
            host=HOST,
            user=USER,
            passwd=PASSWD,
            database=DB_NAME
        )
        self.db_cursor = self.db_connection.cursor()


    def create(self,record):
        self.__init__()
        query = "INSERT INTO " + DB_NAME + ".student (student_id,address,email) VALUES (%s,%s,%s);"
        print(query)
        print(record[0], record[1], record[2])
        val = (record[0], record[1], record[2])
        self.db_cursor.execute(query, val)
        self.db_connection.commit()

    def read(self,id):
        self.__init__()
        query = "INSERT INTO " + DB_NAME + ".student (tank_id,count) VALUES (%s,%s);"
        self.db_cursor .execute("SELECT * FROM student where id = "+ id)
        myresult = self.db_cursor .fetchall()
        for x in myresult:
            print(x)
        self.db_connection.commit()
        return  myresult

    def update(self,key,value,id):
        self.__init__()
        query = "UPDATE " + DB_NAME + ".student SET "+ key + " = \'" + value + "\' WHERE id ="+ id +";"
        print(query)
        self.db_cursor.execute(query)
        self.db_connection.commit()

    def delete(self,id):
        self.__init__()
        print("Deleting", id)
        query = "DELETE FROM " + DB_NAME + ".student WHERE id = " + id
        self.db_cursor.execute(query)
        self.db_connection.commit()



