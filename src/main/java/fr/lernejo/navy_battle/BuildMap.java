package fr.lernejo.navy_battle;

import java.util.HashMap;
import java.util.Map;

public class BuildMap {

    public Map<String, String> transform(String str) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String param : str.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                }
            }
        }catch (Exception e) {}
        return result;
    }

    public HashMap<String, String> jsonToMap(String json) {
        HashMap<String, String> result = new HashMap<>();
        try {
            json = json.replace("{", "");
            json = json.replace("}", "");
            json = json.replace("\"", "");
            for (String param : json.split(",")) {
                String[] entry = param.split(":");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                }
            }
        }catch (Exception e) {}
        return result;
    }
}
