import mysql.connector
import os
import datetime
import psycopg2

HOST = os.environ.get("HOST","database-1.cc48pm4rwbrd.ap-northeast-1.rds.amazonaws.com")
USER = os.environ.get("USER_NAME", "admin")
PASSWD = os.environ.get("PASSWD", "space581")
DB_NAME = os.environ.get("DB_NAME", "env")
PORT = "5432"

class rds_handler():


    def __init__(self):
        self.db_connection = mysql.connector.connect(
            host=HOST,
            user=USER,
            passwd=PASSWD,
            database=DB_NAME
        )
        self.db_cursor = self.db_connection.cursor()
# timestamp, time_bucket, city_name, lat, lon, weather_main, humidity, temperature, rain, wind
    def update_inventory_buy(self, dict_list, table_name):
        query = "INSERT  INTO "+table_name+" (timestamp, time_bucket, city_name, lat, lon, weather_main, humidity, temperature, rain, wind) VALUES "
        val = ""
        for data in dict_list:
            val += "(\'" + data['timestamp'] + "\'," + "\'" + str(data["time_bucket"])+ "\'"+","+"\'" + str(data["city_name"])+ "\'"+","+"\'" + str(data["lat"])+ "\'"+","+"\'" + str(data["lon"])+ "\'"+","+"\'" + str(data["weather_main"])+ "\'"+","+"\'" + str(data["humidity"])+ "\'"+","+"\'" + str(data["temperature"])+ "\'"+","+"\'" + str(data["rain"])+ "\'"+","+"\'" + str(data["wind"])+ "\'"+"),\n"

        query = str(query)+val[:-2]+";"
        print(query)
        self.db_cursor.execute(query)
        self.db_connection.commit()

#     def check_existing(self, from_time, to_time, city_name,table_name, days=0):
#         if days == 0:
#             from_time_ob = datetime.datetime.strptime(from_time, '%Y-%m-%d')
#             to_time_ob = datetime.datetime.strptime(to_time, '%Y-%m-%d')
#             delta = to_time_ob-from_time_ob
#             if delta.days < 0:
#                 print("INVALID DATA")
#                 exit()
#             total_rows = delta.days*24
#         else:
#             from_time = datetime.datetime.now().date()
#             to_time = datetime.datetime.now().date() + datetime.timedelta(days-1)
#             total_rows = days*24
#         query = f"select * from {table_name} where timestamp between \"{from_time}\" and \"{to_time}\" and city_name = \'{city_name}\'"
#         print(query)
#         self.db_cursor.execute(query)
#         data = self.db_cursor.fetchall()
#         self.db_connection.commit()
#         if len(data) >= total_rows:
#             print("DATA EXIST ")
#             exit(0)
#         else:
#             print("downloading data ")
#             return 0
#
#
# # (self, timestamp, time_bucket, city_name, lat, lon, weather_main, humidity, temperature, rain, wind):
# ob = rds_handler()
# hist_dict={}
# hist_list = []
# hist_dict["city_name"]= "Delhi"
# hist_dict["weather_main"] = "cloud"
# hist_dict["timestamp"] ="2022-07-29 23"
# hist_dict["lat"] = "lat"
# hist_dict["lon"] = "lon"
# hist_dict["hour_duration"] = "01"
# hist_dict["humidity"] = "humidity"
# hist_dict["temperature"] = "temperature"
# hist_dict["rain"] = "rain"
# hist_dict["wind"] = "wind"
# hist_dict["time_bucket"] = '2022-07-29_22_5.50_Asia/Kolkata'
# hist_list.append(hist_dict.copy())
# # ob.check_existing("2015-01-21","2015-01-24",)
# ob.update_inventory_buy(hist_list, "historical")
