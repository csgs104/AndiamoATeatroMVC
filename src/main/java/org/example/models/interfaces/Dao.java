package org.example.models.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    boolean insert(T entity) throws IOException, SQLException;
    boolean update(T entity) throws IOException, SQLException;
    boolean delete(T entity) throws IOException, SQLException;
    Optional<T> getById(int id) throws IOException, SQLException;
    List<T> getAll() throws IOException, SQLException;
}