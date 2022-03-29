package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

public class CasePOST implements HttpHandler{

    public void sendResponse(HttpExchange exchange, String body, int rCode) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"POST".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, "Not Found", 404);
        }
        else if (IsBodyOK(exchange.getRequestBody())) {
            String body = bodyOK(exchange);
            sendResponse(exchange, "OK", 200);
        }
        else{
            sendResponse(exchange, "Bad Request", 400);
        }
        exchange.close();
    }

    private String bodyOK(HttpExchange exchange) {
        int port = exchange.getHttpContext().getServer().getAddress().getPort();
        JSONObject object = new JSONObject();
        object.put("id", "xxx");
        object.put("url", "http://localhost:" + port);
        object.put("message", "May the force be with you !");
        return object.toString();
    }
    private boolean IsBodyOK(InputStream bodyRequest) {
        JSONObject json = inputStringTOJSON(bodyRequest);
        if (json != null) {
            String id = json.getString("id");
            String url = json.getString("url");
            String message = json.getString("message");
            if( id != null && url != null && message != null) {
                return true;
            }
        }
        return false;
    }

    public JSONObject inputStringTOJSON(InputStream inputStream) {
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
