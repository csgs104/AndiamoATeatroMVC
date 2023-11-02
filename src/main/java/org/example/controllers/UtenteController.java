package org.example.controllers;

import com.google.gson.Gson;
import org.example.models.databases.UtenteCrud;
import org.example.models.entities.Utente;
import org.example.utils.HttpResponse;

import java.util.List;
import static spark.Spark.*;

public class UtenteController {
    UtenteCrud userCrud = new UtenteCrud();

    public void startServices() {
        // heartbeat
        get("/", (req, res) -> "System is working");
        get("/users", (req, res) -> {
            List<Utente> result = userCrud.getAll();
            HttpResponse response = new HttpResponse("200", "OK, stampa utenti", new Gson().toJsonTree(result));
            return new Gson().toJson(response);
        });
        get("/user/:id", (req, res) -> {
            String id = req.params("id");
            Utente result = userCrud.getById(Integer.parseInt(id)).orElse(null);
            HttpResponse response = new HttpResponse("200", "OK, stampa utente", new Gson().toJsonTree(result));
            return new Gson().toJson(response);
        });
        post("/user", (req, res) -> {
            Utente utenteReq = new Gson().fromJson(req.body(), Utente.class);
            userCrud.insert(utenteReq);
            Utente utenteRes = userCrud.getById(utenteReq.getId()).orElse(null);
            HttpResponse response = new HttpResponse("200", "OK, inserito utente", new Gson().toJsonTree(utenteRes));
            return new Gson().toJson(response);
        });
        put("/user", (req, res) -> {
            Utente utenteReq = new Gson().fromJson(req.body(), Utente.class);
            userCrud.update(utenteReq);
            Utente utenteRes = userCrud.getById(utenteReq.getId()).orElse(null);
            HttpResponse response = new HttpResponse("200", "OK, aggiornato utente", new Gson().toJsonTree(utenteRes));
            return new Gson().toJson(response);
        });
        delete("/user", (req, res) -> {
            String id = req.queryParams("id");
            Utente utenteReq = new Utente();
            utenteReq.setId(Integer.parseInt(id));
            userCrud.delete(utenteReq);
            return new Gson().toJson(new HttpResponse("200", "OK, utente cancellato"));
        });
    }
}