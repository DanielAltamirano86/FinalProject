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


    public void onfunc(ActionEvent actionEvent) {

//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter city name: ");
        String city = inp.getText();


        try{
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&units=metric&appid=08232c2bf7d348f840b8569a26df03dc");

            // open a connection
            InputStream is =url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            JsonElement jse = new JsonParser().parse(br);
//            System.out.println(jse.toString());

            if (jse!=null) {

                String locLabel = "Location";
                locLbl.setText(locLabel);

                String location = jse.getAsJsonObject().get("name").getAsString();
                loc.setText(location);

                String tempLabel = "Temperature";
                tempLbl.setText(tempLabel);

                String tmp = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsString();
                temp.setText(tmp + " °C");

                String maxLabel = "Max Temperature";
                maxLbl.setText(maxLabel);

                String max = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp_max").getAsString();
                Max.setText(max + " °C");

                String minLabel = "Min Temperature";
                minLbl.setText(minLabel);

                String min = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp_min").getAsString();
                Min.setText(min + " °C");

                String feelsLabel = "Feels Like";
                feelsLbl.setText(feelsLabel);

                String feels = jse.getAsJsonObject().get("main").getAsJsonObject().get("feels_like").getAsString();
                FeelsLike.setText(feels + " °C");

                String descLabel = "Description";
                descLbl.setText(descLabel);

                String desc = jse.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                Desc.setText(desc);

                String speedLabel = "Wind Speed";
                speedLbl.setText(speedLabel);

                String speed = jse.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsString();
                Speed.setText(speed + " km/h");

                String humLabel = "Humidity";
                humLbl.setText(humLabel);

                String humid = jse.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsString();
                Humid.setText(humid + "%");

                String latitude = jse.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString();
                String longitude = jse.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString();

                try {

                    URL url2 = new URL("https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&units=metric&appid=9a14131d21314caaf8e9208f49b174d4");

                    InputStream is2 = url2.openStream();
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));

                    JsonElement jse2 = new JsonParser().parse(br2);
//                    System.out.println(jse2.toString());

                    if (jse2!=null) {

                        String TimeHeader = "Time";
                        TimeVar.setText(TimeHeader);

                        String TempHeader = "Temperature";
                        TempVar.setText(TempHeader);

                        Calendar now = Calendar.getInstance();
                        int currTime = now.get(Calendar.HOUR_OF_DAY);
                        String hour = Integer.toString(currTime);

                        String currTemp = "Now";
                        CurrentTemp.setText(currTemp);

                        String hour0temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsString();
                        sub_temp0.setText(hour0temp + " °C");

    //                    System.out.println("Current temperature is " + hour0temp);

                        int i  = 1;
                        int base24Time = currTime;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }
                        String HourOneTemp = Integer.toString(base24Time + i);
                        OneHourTemp.setText(HourOneTemp + ":00");

                        String hour1temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(1).getAsJsonObject().get("temp").getAsString();
                        sub_temp1.setText(hour1temp + " °C");
    //                    System.out.println("Temperature in 1 hour is " + hour1temp);

                        i  = 2;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }
                        String HourTwoTemp = Integer.toString(base24Time + i);
                        TwoHourTemp.setText(HourTwoTemp + ":00");

                        String hour2temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(2).getAsJsonObject().get("temp").getAsString();
                        sub_temp2.setText(hour2temp + " °C");
    //                    System.out.println("Temperature in 2 hours is " + hour2temp);


                        i  = 3;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }
                        String HourThreeTemp = Integer.toString(base24Time + i);;
                        ThreeHourTemp.setText(HourThreeTemp + ":00");

                        String hour3temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(3).getAsJsonObject().get("temp").getAsString();
                        sub_temp3.setText(hour3temp + " °C");

                        i  = 4;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }
                        String HourFourTemp = Integer.toString(base24Time + i);;
                        FourHourTemp.setText(HourFourTemp + ":00");

                        String hour4temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(4).getAsJsonObject().get("temp").getAsString();
                        sub_temp4.setText(hour4temp + " °C");

                        i  = 5;
                        if(currTime + i >= 24){
                            base24Time = currTime - 24;
                        }
                        String HourFiveTemp = Integer.toString(base24Time + i);;
                        FiveHourTemp.setText(HourFiveTemp + ":00");

                        String hour5temp = jse2.getAsJsonObject().get("hourly").getAsJsonArray().get(5).getAsJsonObject().get("temp").getAsString();
                        sub_temp5.setText(hour5temp + " °C");

                        String DayHeader = "Day";
                        DayVar.setText(DayHeader);

                        String TempsHeader = "Temperature";
                        TempsVar.setText(TempsHeader);

                        String todayTemp = "Today";
                        TodayTemp.setText(todayTemp);

                        String TempToday = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(0).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempToday.setText(TempToday + " °C");

                        String tomorrowTemp = "Tomorrow";
                        TomorrowTemp.setText(tomorrowTemp);

                        String TempTomorrow = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(1).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempTomorrow.setText(TempTomorrow + " °C");

                        String twoDayTemp = "Day after tomorrow";
                        TwoDayTemp.setText(twoDayTemp);

                        String TempTwoDay = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(2).getAsJsonObject().get("temp").getAsJsonObject().get("day").getAsString();
                        sub_tempTwoDay.setText(TempTwoDay + " °C");


                        String DescHeader = "Description";
                        DescVar.setText(DescHeader);

                        String DescToday = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(0).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descToday.setText(DescToday);

                        String DescTomorrow = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(1).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descTomorrow.setText(DescTomorrow);

                        String DescTwoDay = jse2.getAsJsonObject().get("daily").getAsJsonArray().get(2).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
                        sub_descTwoday.setText(DescTwoDay);






                    }

                    is2.close();
                    br2.close();
                }

                catch (Exception e)
                {
                    System.out.println(e);
                }


            }

            is.close();
            br.close();
        }



        catch (Exception e)
        {
            System.out.println(e);
        }


    }
}
