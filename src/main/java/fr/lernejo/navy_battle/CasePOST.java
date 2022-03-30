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

    private final Util data;

    public CasePOST(Util data) {
        super();
        this.data = data;
    }

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
            sendResponse(exchange, "", 404);
        }
        else if (IsBodyOK(exchange.getRequestBody()) != null) {
            String body = bodyOK(exchange);
            sendResponse(exchange, body, 202);
        }
        else{
            sendResponse(exchange, "Bad Request", 400);
        }
    }

    private String bodyOK(HttpExchange exchange) {
        int port = exchange.getHttpContext().getServer().getAddress().getPort();
        JSONObject object = new JSONObject();
        object.put("id", "xxx");
        object.put("url", "http://localhost:" + port);
        object.put("message", "May the force be with you !");
        return object.toString();
    }
    private String IsBodyOK(InputStream bodyRequest) {
        JSONObject json = new JSON().StringToJSON(bodyRequest);
        if (json != null) {
            try {
                String id = json.getString("id");
                String url = json.getString("url");
                String message = json.getString("message");
                this.data.addData("Next", url.split(":")[2]);
                this.data.addData("Connexion Done", "true");
                if( id != null && url != null && message != null) {
                    return url;
                }
            }catch(Exception e) {}
        }
        return null;
    }
}
