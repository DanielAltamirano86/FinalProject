import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;

public class Controller {
    public Button btn;
    public TextField inp;
    public Label temp;
    public Label Max;
    public Label Min;
    public Label FeelsLike;
    public Label Desc;
    public Label Speed;
    public Label Humid;
    public Label tempLbl;
    public Label descLbl;
    public Label feelsLbl;
    public Label maxLbl;
    public Label minLbl;
    public Label speedLbl;
    public Label humLbl;
    public Label locLbl;
    public Label loc;
    public Label sub_temp0;
    public Label sub_temp1;
    public Label sub_temp2;
    public Label CurrentTemp;
    public Label OneHourTemp;
    public Label TwoHourTemp;
    public Label TempVar;
    public Label TimeVar;
    public Label ThreeHourTemp;
    public Label sub_temp3;
    public Label FourHourTemp;
    public Label sub_temp4;
    public Label FiveHourTemp;
    public Label sub_temp5;
    public Label DayVar;
    public Label TodayTemp;
    public Label sub_tempToday;
    public Label TomorrowTemp;
    public Label sub_tempTomorrow;
    public Label TwoDayTemp;
    public Label sub_tempTwoDay;
    public Label TempsVar;
    public Label DescVar;
    public Label sub_descToday;
    public Label sub_descTomorrow;
    public Label sub_descTwoday;

//    the onfunc method is used to define the operations taking place when the button is clicked in the GUI
    public void onfunc(ActionEvent actionEvent) {

//        the city name entered by the user is saved to a String variable
        String city = inp.getText();

//        we use try and catch to call the APIs
        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=08232c2bf7d348f840b8569a26df03dc");

            // open a connection
            InputStream is =url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            JsonElement jse = new JsonParser().parse(br);
//            System.out.println(jse.toString());

            if (jse!=null) {
                //Label for location
                String locLabel = "Location";
                locLbl.setText(locLabel);

                //Value for location
                String location = jse.getAsJsonObject().get("name").getAsString();
                loc.setText(location);

                //Label for temperature
                String tempLabel = "Temperature";
                tempLbl.setText(tempLabel);

                //Value for temperature
                String tmp = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsString();
                temp.setText(tmp + " °C");

                //Label for maximum temperature
                String maxLabel = "Max Temperature";
                maxLbl.setText(maxLabel);

                //Value for maximum temperature
                String max = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp_max").getAsString();
                Max.setText(max + " °C");

                //Label for minimum temperature
                String minLabel = "Min Temperature";
                minLbl.setText(minLabel);

                //Value for minimum temperature
                String min = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsString();
                Min.setText(min + " °C");

                //Label for feels like temperature
                String feelsLabel = "Feels Like";
                feelsLbl.setText(feelsLabel);

                //Value for feels like temperature
                String feels = jse.getAsJsonObject().get("main").getAsJsonObject().get("feels_like").getAsString();
                FeelsLike.setText(feels + " °C");

                //Label for description
                String descLabel = "Description";
                descLbl.setText(descLabel);

                //Value for description
                String desc = jse.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                Desc.setText(desc);

                //Label for wind speed
                String speedLabel = "Wind Speed";
                speedLbl.setText(speedLabel);

                //Value for wind speed
                String speed = jse.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsString();
                Speed.setText(speed + " km/h");

                //Label for humidity
                String humLabel = "Humidity";
                humLbl.setText(humLabel);

                //Value for humidity
                String humid = jse.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsString();
                Humid.setText(humid + "%");

                //Variables created to store the latitude and longitude of the city searched
                String latitude = jse.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString();
                String longitude = jse.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString();

                //try and catch statement to call second API
                try {

                    URL url2 = new URL("https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&units=metric&appid=9a14131d21314caaf8e9208f49b174d4");

                    InputStream is2 = url2.openStream();
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));

                    JsonElement jse2 = new JsonParser().parse(br2);
//                    System.out.println(jse2.toString());

                    if (jse2!=null) {

                        //Label for time
                        String TimeHeader = "Time";
                        TimeVar.setText(TimeHeader);

                        //Label for temperature
                        String TempHeader = "Temperature";
                        TempVar.setText(TempHeader);

                        //Using calendar object to retrieve the current time of the day
                        Calendar now = Calendar.getInstance();
                        int currTime = now.get(Calendar.HOUR_OF_DAY);
                        String hour = Integer.toString(currTime);

                        //Label for current time
                        String currTemp = "Now";
                        CurrentTemp.setText(currTemp);

                        //Value for current time
                        String hour0temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsString();
                        sub_temp0.setText(hour0temp + " °C");

    //                    System.out.println("Current temperature is " + hour0temp);

                        //To get the correct future time when time + i hours are over 23 we need to first check if the addition is over 23 and then use a new base time which will be the current time - 24
                        int i  = 1;
                        int base24Time = currTime;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }

                        //Label for temperature in 1 hour
                        String HourOneTemp = Integer.toString(base24Time + i);
                        OneHourTemp.setText(HourOneTemp + ":00");

                        //Value for temperature in 1 hour
                        String hour1temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(1).getAsJsonObject().get("temp").getAsString();
                        sub_temp1.setText(hour1temp + " °C");
    //                    System.out.println("Temperature in 1 hour is " + hour1temp);

                        i  = 2;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }

                        //Label for temperature in 2 hours
                        String HourTwoTemp = Integer.toString(base24Time + i);
                        TwoHourTemp.setText(HourTwoTemp + ":00");

                        //Value for temperature in 2 hours
                        String hour2temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(2).getAsJsonObject().get("temp").getAsString();
                        sub_temp2.setText(hour2temp + " °C");
    //                    System.out.println("Temperature in 2 hours is " + hour2temp);


                        i  = 3;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }

                        //Label for temperature in 3 hours
                        String HourThreeTemp = Integer.toString(base24Time + i);;
                        ThreeHourTemp.setText(HourThreeTemp + ":00");

                        //Value for temperature in 3 hours
                        String hour3temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(3).getAsJsonObject().get("temp").getAsString();
                        sub_temp3.setText(hour3temp + " °C");

                        i  = 4;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }

                        //Label for temperature in 4 hours
                        String HourFourTemp = Integer.toString(base24Time + i);;
                        FourHourTemp.setText(HourFourTemp + ":00");

                        String hour4temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(4).getAsJsonObject().get("temp").getAsString();
                        sub_temp4.setText(hour4temp + " °C");

                        i  = 5;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }

                        //Label for temperature in 5 hours
                        String HourFiveTemp = Integer.toString(base24Time + i);;
                        FiveHourTemp.setText(HourFiveTemp + ":00");

                        //Value for temperature in 5 hours
                        String hour5temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(5).getAsJsonObject().get("temp").getAsString();
                        sub_temp5.setText(hour5temp + " °C");

                        //Label for day
                        String DayHeader = "Day";
                        DayVar.setText(DayHeader);

                        //Label for temperature
                        String TempsHeader = "Temperature";
                        TempsVar.setText(TempsHeader);

                        //Label for today
                        String todayTemp = "Today";
                        TodayTemp.setText(todayTemp);

                        //Value for today's temperature
                        String TempToday = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempToday.setText(TempToday + " °C");

                        //Label for tomorrow
                        String tomorrowTemp = "Tomorrow";
                        TomorrowTemp.setText(tomorrowTemp);

                        //Value for tomorrow's temperature
                        String TempTomorrow = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(1).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempTomorrow.setText(TempTomorrow + " °C");

                        //Label for the day after tomorrow
                        String twoDayTemp = "Day after tomorrow";
                        TwoDayTemp.setText(twoDayTemp);

                        //Value for the day after tomorrow's temperature
                        String TempTwoDay = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(2).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempTwoDay.setText(TempTwoDay + " °C");

                        //Label for description
                        String DescHeader = "Description";
                        DescVar.setText(DescHeader);

                        //Value for today's weather description
                        String DescToday = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(0).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descToday.setText(DescToday);

                        //Value for tomorrow's weather description
                        String DescTomorrow = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(1).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descTomorrow.setText(DescTomorrow);

                        //Value for the day after tomorrow's weather description
                        String DescTwoDay = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(2).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descTwoday.setText(DescTwoDay);






                    }
                    //Closing the connection to the second API
                    is2.close();
                    br2.close();
                }

                catch (Exception e)
                {
                    System.out.println(e);
                }


            }
            //Closing the connection to the first API
            is.close();
            br.close();
        }



        catch (Exception e)
        {
            System.out.println(e);
        }


    }
}
