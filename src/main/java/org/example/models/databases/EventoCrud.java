package org.example.models.databases;

import org.example.models.entities.Evento;
import org.example.models.interfaces.Dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventoCrud implements Dao<Evento> {
    @Override
    public boolean insert(Evento entity) throws IOException, SQLException {
        String sql = "INSERT INTO public.\"EVENTO\" VALUES (?,?,?,?,?,?,?);";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setInt(1, entity.getId());
        ps.setString(2,entity.getNome());
        ps.setString(3,entity.getGenere());
        ps.setDouble(4,entity.getPrezzo());
        ps.setString(5, entity.getDurata());
        ps.setDate(6, entity.getData());
        ps.setInt(7,  entity.getSala());
        int affected = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affected > 0;
    }

    @Override
    public boolean update(Evento entity) throws IOException, SQLException {
        String sql = "UPDATE public.\"EVENTO\" SET " +
                "nome=?, genere=?, prezzo=?, durata=?, data=?, sala=? " +
                "WHERE id=?;";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setString(1,entity.getNome());
        ps.setString(2,entity.getGenere());
        ps.setDouble(3,entity.getPrezzo());
        ps.setString(4, entity.getDurata());
        ps.setDate(5, entity.getData());
        ps.setInt(6,  entity.getSala());
        ps.setInt(7,  entity.getId());
        int affected = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affected > 0;
    }

    @Override
    public boolean delete(Evento entity) throws IOException, SQLException {
        String sql = "DELETE FROM public.\"EVENTO\" WHERE id=?;";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ps.setInt(1,entity.getId());
        int affected = ps.executeUpdate();
        ch.closeConnection();
        ps.close();
        return affected > 0;
    }

    @Override
    public Optional<Evento> getById(int id) throws IOException, SQLException {
        String sql = "SELECT * FROM public.\"EVENTO\" WHERE id=" + id +";";
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ResultSet r = ps.executeQuery();
        Evento evento = null;
        while (r.next())
            evento = new Evento(r.getInt(1), r.getString(2), r.getString(3), r.getDouble(4), r.getString(5),r.getDate(6),r.getInt(7));
        ch.closeConnection();
        ps.close();
        return Optional.of(evento);
    }

    @Override
    public List<Evento> getAll() throws IOException, SQLException {
        String sql = "SELECT * FROM public.\"EVENTO\"";
        List<Evento> lista= new ArrayList<>();
        ConnectionHandler ch = new ConnectionHandler();
        PreparedStatement ps = ch.getPreparedStatement(sql);
        ResultSet r = ps.executeQuery();
        while (r.next()){
            lista.add(new Evento(r.getInt(1), r.getString(2), r.getString(3), r.getDouble(4), r.getString(5),r.getDate(6),r.getInt(7)));
        }
        return lista;
    }

    public static void main(String[] args) throws SQLException, IOException {
        Evento ev= new Evento(100,"I promessi sposi", "Commedia" , 12.50,"4:00:00", new Date(2024,12,12),1);
        System.out.println(ev);
        EventoCrud evc= new EventoCrud();
        //evc.insert(ev);
        ev.setNome("Anna dai capelli rossi");
        evc.update(ev);
        evc.delete(ev);
        evc.insert(ev);
        evc.getById(100);
        System.out.println(evc.getAll());
    }
}
