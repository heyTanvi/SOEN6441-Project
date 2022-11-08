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
# "INSERT  INTO "+table_name+" (timestamp, time_bucket, city_name, lat, lon, weather_main, humidity, temperature, rain, wind) VALUES "
    self.db_cursor.execute("CREATE TABLE env.historical (time_bucket  varchar(255) PRIMARY KEY, timestamp TIMESTAMP , city_name varchar(255), lat varchar(255), lon varchar(255), weather_main varchar(255), humidity varchar(255), temperature varchar(255), rain varchar(255), wind varchar(255));")

    self.db_cursor.execute("CREATE TABLE env.forecast (time_bucket  varchar(255) PRIMARY KEY, timestamp TIMESTAMP , city_name varchar(255), lat varchar(255), lon varchar(255), weather_main varchar(255), humidity varchar(255), temperature varchar(255), rain varchar(255), wind varchar(255));")

    self.db_cursor.execute("CREATE TABLE env.current (time_bucket  varchar(255) PRIMARY KEY, timestamp TIMESTAMP , city_name varchar(255), lat varchar(255), lon varchar(255), weather_main varchar(255), humidity varchar(255), temperature varchar(255), rain varchar(255), wind varchar(255));")

# intialise more tables if needed

    self.db_connection.commit()


def main():
  storage_ob = storage_setup()
  storage_ob.create_database()
  storage_ob.create_table()


if __name__== '__main__':
  main()


