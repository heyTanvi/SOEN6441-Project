import json
import requests
import datetime
import rds_test
import boto3
import psycopg2
s3 = boto3.client("s3")

rds = rds_test.rds_handler()
ACCESS_KEY = '3c0bfa6bfe43cec606d71ea41b842c97'


def historical_data(city_name, from_time, to_time):
    #coordinates_lat, coordinates_long,
    #query = coordinates_lat+","+coordinates_long
    date_string = ""
    from_time_ob = datetime.datetime.strptime(from_time, '%Y-%m-%d')
    to_time_ob = datetime.datetime.strptime(to_time, '%Y-%m-%d')
    while from_time_ob <= to_time_ob:
        date_string += from_time_ob.strftime('%Y-%m-%d')+";"
        from_time_ob = from_time_ob + datetime.timedelta(days=1)
    date_string = date_string[:-1]
    # if rds.check_existing(from_time, to_time, city_name) == 1:
    #     return 0

    historical_date = from_time+";"+to_time
    params = {
        'access_key': ACCESS_KEY,
        'query': city_name,
        'historical_date': date_string,
        'hourly': '1',
        'interval': '1'
    }
    api_result = requests.get('https://api.weatherstack.com/historical', params)
    api_response = api_result.json()
    # print(api_response)
    hist_list = []
    hist_dict = {}
    lat = api_response["location"]["lat"]
    lon = api_response["location"]["lon"]
    city_name = api_response["location"]["name"]
    zone_id = api_response["location"]["timezone_id"]
    utc_offset = api_response["location"]["utc_offset"]



    for data in api_response["historical"]:
        date = api_response["historical"][data]["date"]
        for hour in  api_response["historical"][data]["hourly"]:
            hour_duration = hour["time"]
            humidity = hour["humidity"]
            temperature = hour["temperature"]
            rain = hour["chanceofrain"]
            wind = hour["wind_speed"]


            hist_dict["city_name"]= city_name
            hist_dict["weather_main"] = hour["weather_descriptions"][0]
            hist_dict["timestamp"] = date+" "+hour_duration[:-2]
            hist_dict["lat"] = lat
            hist_dict["lon"] = lon
            hist_dict["hour_duration"] = hour_duration
            hist_dict["humidity"] = humidity
            hist_dict["temperature"] = temperature
            hist_dict["rain"] = rain
            hist_dict["wind"] = wind
            hist_dict["time_bucket"] = date+"_"+hour_duration+"_"+utc_offset+"_"+zone_id

            hist_list.append((hist_dict.copy()))
    rds.update_inventory_buy(hist_list,"historical")
    return hist_list, city_name, "historical",from_time

def forecast_data(city_name):

    params = {
        'access_key': ACCESS_KEY,
        'query': city_name,
        'forecast_days': '2',
        'hourly':'1',
        'interval': '1'
    }
    api_result = requests.get('https://api.weatherstack.com/forecast', params)
    api_response = api_result.json()
    # print(api_response)
    hist_list = []
    hist_dict = {}
    lat = api_response["location"]["lat"]
    lon = api_response["location"]["lon"]
    city_name = api_response["location"]["name"]
    zone_id = api_response["location"]["timezone_id"]
    utc_offset = api_response["location"]["utc_offset"]


    for data in api_response["forecast"]:
        date = api_response["forecast"][data]["date"]
        for hour in  api_response["forecast"][data]["hourly"]:
            hour_duration = hour["time"]
            humidity = hour["humidity"]
            temperature = hour["temperature"]
            rain = hour["chanceofrain"]
            wind = hour["wind_speed"]

            hist_dict["city_name"]= city_name
            hist_dict["weather_main"] = hour["weather_descriptions"][0]
            hist_dict["timestamp"] = date+" "+hour_duration[:-2]
            hist_dict["lat"] = lat
            hist_dict["lon"] = lon
            hist_dict["hour_duration"] = hour_duration
            hist_dict["humidity"] = humidity
            hist_dict["temperature"] = temperature
            hist_dict["rain"] = rain
            hist_dict["wind"] = wind
            hist_dict["time_bucket"] = date+"_"+hour_duration+"_"+utc_offset+"_"+zone_id

            hist_list.append((hist_dict.copy()))
    rds.update_inventory_buy(hist_list,"forecast")

    return hist_list,city_name,"forecast",date

def current_data(city_name):

    # if rds.check_existing(0, 0, city_name,1) == 1:
    #     return 0

    params = {
        'access_key': ACCESS_KEY,
        'query': city_name,
    }
    api_result = requests.get('https://api.weatherstack.com/current', params)
    api_response = api_result.json()
    print(api_response)
    hist_list = []
    hist_dict = {}
    lat = api_response["location"]["lat"]
    lon = api_response["location"]["lon"]
    city_name = api_response["location"]["name"]
    zone_id = api_response["location"]["timezone_id"]
    utc_offset = api_response["location"]["utc_offset"]
    timestamp = api_response["location"]["localtime"][:13]

    humidity = api_response["current"]["humidity"]
    temperature = api_response["current"]["temperature"]
    rain = "na"
    wind = api_response["current"]["wind_speed"]

    hist_dict["city_name"]= city_name
    hist_dict["weather_main"] = api_response["current"]["weather_descriptions"][0]
    hist_dict["timestamp"] = timestamp
    hist_dict["lat"] = lat
    hist_dict["lon"] = lon
    # hist_dict["hour_duration"] = hour_duration
    hist_dict["humidity"] = humidity
    hist_dict["temperature"] = temperature
    hist_dict["rain"] = rain
    hist_dict["wind"] = wind
    hist_dict["time_bucket"] = timestamp+"_"+utc_offset+"_"+zone_id

    hist_list.append((hist_dict.copy()))
    rds.update_inventory_buy(hist_list,"current")

    return hist_list,city_name,"current",timestamp

# hist_list = historical_data("New York","2015-01-21","2015-01-25","1")

# hist_list = current_data("Delhi")
# for item in hist_list:
#     print(item)
# def upload_s3(file_name, city_name,types,from_time):
#     s3.upload_file(
#         Filename=file_name,
#         Bucket="weatherjsondump",
#         Key=types+"_"+from_time+"_"+city_name+".json"
#     )


def loader(event):
    type = event["type"]
    if type=="historical":
        json_list, city_name,types,from_time = historical_data(event["city_name"],event["from_time"],event["to_time"])
    if type=="forecast":
        json_list, city_name,types,from_time = forecast_data(event["city_name"])
    if type=="current":
        json_list, city_name,types,from_time = current_data(event["city_name"])

    with open('/tmp/result.json', 'w') as fp:
        json.dump(json_list, fp)
    # upload_s3('/tmp/result.json',city_name,types,from_time)

def main():
    event = {}
    event["type"]= "forecast"
    event["city_name"] = "New York"
    event["from_time"] ="2015-01-21"
    event["to_time"] = "2015-02-21"
    print(event)
    loader(event)



    # TODO implement
    return {
        'statusCode': 200,
        'body': json.dumps('SUCCESS')
    }

if __name__ == "__main__":
    main()
