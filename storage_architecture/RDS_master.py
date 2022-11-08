import mysql.connector
import os

HOST = os.environ.get("HOST","database-1.cc48pm4rwbrd.ap-northeast-1.rds.amazonaws.com")
USER = os.environ.get("USER_NAME", "admin")
PASSWD = os.environ.get("PASSWD", "space581")
DB_NAME = os.environ.get("DB_NAME", "env")



class storage_setup():
  def __init__(self):
    self.db_connection = mysql.connector.connect(
      host=HOST,
      user=USER,
      passwd=PASSWD,
    )
    self.db_cursor = self.db_connection.cursor()
    print('init')


  def create_database(self):
    # self.db_cursor.execute("CREATE DATABASE "+DB_NAME)
    self.db_connection = mysql.connector.connect(
      host=HOST,
      user=USER,
      passwd=PASSWD,
      database=DB_NAME
    )
    self.db_cursor = self.db_connection.cursor()


  def create_table(self):

    #  table mortality

    self.db_cursor.execute("CREATE TABLE student (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, student_id VARCHAR(255),address VARCHAR(255),email VARCHAR(255));")

    # intialise more tables if needed

    self.db_connection.commit()


def main():
  storage_ob = storage_setup()
  storage_ob.create_database()
  storage_ob.create_table()





if __name__== '__main__':
  main()


  # db_connection = mysql.connector.connect(
  #   host="database-1.cxls4ffibx78.us-east-1.rds.amazonaws.com",
  #   user="admin",
  #   passwd="space581",
  #   database="my_first_db"
  # )
  # print(db_connection)
  # self.db_cursor = db_connection.cursor()
  #-------mortality table----------
  #self.db_cursor.execute("CREATE TABLE mortality (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, tank_id VARCHAR(255), count INT(6))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.mortality (tank_id,count) VALUES ('1Z',60)")
  #db_connection.commit()
  #employee_sql_query = " INSERT INTO employee (id, name, salary) VALUES (01, 'John', 10000)"

  #--------food

  #self.db_cursor.execute("CREATE TABLE food_consumption (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,fish_category VARCHAR(255), food_size VARCHAR(255), amount_used DECIMAL(6,2))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.food_consumption (fish_category,food_size,amount_used) VALUES ('prawns','.1mm',2.5)")
  #db_connection.commit()
  #employee_sql_query = " INSERT INTO employee (id, name, salary) VALUES (01, 'John', 10000)"

  #self.db_cursor.execute("CREATE TABLE food_purchased (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,fish_category VARCHAR(255), food_size VARCHAR(255), amount_purchased DECIMAL(6,2))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.food_purchased (fish_category,food_size,amount_purchased) VALUES ('prawns','.5mm',10.5)")
  #db_connection.commit()


  #self.db_cursor.execute("CREATE TABLE fuel_consumption (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, fuel_used INT)")
  # self.db_cursor.execute("INSERT INTO  my_first_db.fuel_consumption (fuel_used) VALUES (2)")
  # db_connection.commit()

  #self.db_cursor.execute("CREATE TABLE fuel_purchased (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, fuel_purchased INT,amount DECIMAL(6,2))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.fuel_purchased (fuel_purchased,amount) VALUES (14,14000)")
  #db_connection.commit()

  # self.db_cursor.execute("CREATE TABLE electricity_consumption (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, unit_used INT, amount_paid DECIMAL(6,2))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.electricity_consumption (unit_used,amount_paid) VALUES (142,2200)")
  #db_connection.commit()


  #self.db_cursor.execute("CREATE TABLE construction_expense (id INT AUTO_INCREMENT PRIMARY KEY, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, item_name INT,item_quantity INT,unit VARCHAR(100),amount DECIMAL(6,2))")
  #self.db_cursor.execute("INSERT INTO  my_first_db.construction_expense (item_name,item_quantity,unit,amount) VALUES ('gitti',9000,'ft',15000)")
  #db_connection.commit()
