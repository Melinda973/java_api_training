package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

public class FireGame implements HttpHandler {

    private final Util data;
    private final Jeu jeu;
    private final Game game = new Game();

    public FireGame(Util data, Jeu jeu) {
        super();
        this.data = data;
        this.jeu = jeu;

    }

    public void sendResponse(HttpExchange exchange, String body, int rCode) throws IOException {
        exchange.getResponseHeaders().set("Accept", "application/json");
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(rCode, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());
        }
    }

    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, "", 404);
        }
        else if (IsBodyOK(exchange)) {
            String body = buildBody("miss", false);
            sendResponse(exchange, body, 202);
        }
        else{
            sendResponse(exchange, "", 400);
        }
    }

    private String buildBody(String consequence , boolean shipLeft) {
        JSONObject object = new JSONObject();
        object.put("consequence", consequence);
        object.put("shipLeft", shipLeft);
        return object.toString();
    }

    private boolean IsBodyOK(HttpExchange exchange) {
        Map<String, String> params = new BuildMap().transform(exchange.getRequestURI().getQuery());
        if(params.get("cell") != null) {
            try {
                String col = params.get("cell").substring(0, 1); //from A to J
                int line = Integer.parseInt(params.get("cell").substring(1, params.get("cell").length())); //from 1 to 10
                return true;
            }catch (Exception e){}
        }
        return false;
    }
}
