package org.example.models.databases;


import org.example.models.entities.Utente;
import org.example.models.interfaces.Dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtenteCrud implements Dao<Utente> {
    @Override
    public boolean insert(Utente utente) throws IOException, SQLException {
        String sql = "INSERT INTO public.\"UTENTE\" VALUES (?,?,?,?,?,?,?); ";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setInt(1, utente.getId());
        ps.setString(2,utente.getNome());
        ps.setString(3,utente.getCognome());
        ps.setString(4,utente.getIndirizzo());
        ps.setString(5,utente.getEmail());
        ps.setString(6,utente.getTelefono());
        ps.setInt(7,utente.getEta());
        int affectedrows = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affectedrows > 0;
    }

    @Override
    public boolean update(Utente utente) throws IOException, SQLException {
        String sql = "UPDATE public.\"UTENTE\"  " +
                " SET  nome=?, cognome=?, indirizzo=?, email=?, telefono=?, eta=? WHERE id ="+
                utente.getId() + ";";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setString(1,utente.getNome());
        ps.setString(2,utente.getCognome());
        ps.setString(3,utente.getIndirizzo());
        ps.setString(4,utente.getEmail());
        ps.setString(5,utente.getTelefono());
        ps.setInt(6,utente.getEta());
        int affectedrows = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affectedrows > 0;
    }

    @Override
    public boolean delete(Utente utente) throws IOException, SQLException {
        String sql = "DELETE FROM public.\"UTENTE\" WHERE id = ?;";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setInt(1,utente.getId());
        int affectedrows = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affectedrows > 0;
    }

    @Override
    public Optional<Utente> getById(int id) throws IOException, SQLException {
        String sql = "SELECT * FROM public.\"UTENTE\" WHERE id="+id+";";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ResultSet set_utent = ps.executeQuery();

        int id_u = 0,eta = 0;
        String name = null,surname = null,indirizzo = null ,telefono = null,email = null;

        while (set_utent.next()){
            id_u = set_utent.getInt(1);
            name = set_utent.getString(2);
            surname = set_utent.getString(3);
            indirizzo = set_utent.getString(4);
            telefono = set_utent.getString(5);
            email = set_utent.getString(6);
            eta = set_utent.getInt(7);
        }
        Utente utente = new Utente(id_u,name,surname,indirizzo,telefono,email,eta);
        Optional<Utente> opt = Optional.ofNullable(utente);
        ch.closeConnection();
        ps.close();

        if (opt.isPresent())
            return opt;
        else
            return Optional.ofNullable(null);
    }

    @Override
    public List<Utente> getAll() throws IOException, SQLException {
        String sql = "SELECT * FROM public.\"UTENTE\";";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ResultSet set_utent = ps.executeQuery();

        List<Utente> utenti = new ArrayList<>();

        while (set_utent.next()){
            int id_u = set_utent.getInt(1);
            String name = set_utent.getString(2);
            String surname = set_utent.getString(3);
            String indirizzo = set_utent.getString(4);
            String telefono = set_utent.getString(5);
            String email = set_utent.getString(6);
            int eta = set_utent.getInt(7);
            utenti.add(new Utente(id_u,name,surname,indirizzo,telefono,email,eta));
        }
        return utenti;
    }
}