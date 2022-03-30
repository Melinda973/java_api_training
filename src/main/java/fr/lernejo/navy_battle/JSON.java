package fr.lernejo.navy_battle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    public JSONObject StringToJSON(InputStream inputStream) {
        if (inputStream != null) {
            try {
                BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                return jsonObject;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
