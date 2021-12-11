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

public class Controller {
    public Button btn;
    public TextField inp;
    public Label lbl;
    public Label temp;

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


            String tmp = jse.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsString();
            temp.setText(tmp);



            is.close();
            br.close();
        }



        catch (Exception e)
        {
            System.out.println(e);
        }


    }
}
