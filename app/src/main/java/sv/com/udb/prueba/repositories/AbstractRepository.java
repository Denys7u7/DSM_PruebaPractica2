package sv.com.udb.prueba.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.dao.Dao;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import sv.com.udb.prueba.db.ORMDatabaseHelper;
import sv.com.udb.prueba.exceptions.EntityNotCreatedException;
import sv.com.udb.prueba.exceptions.EntityNotFoundException;
import sv.com.udb.prueba.exceptions.InvalidRepositoryException;

abstract class AbstractRepository<T,K> {

    private Class<T> genericType;
    protected volatile Dao<T,K> genericDao;
    protected static volatile SQLiteDatabase database;
    private static volatile ORMDatabaseHelper ormDatabaseHelper;

    public AbstractRepository(Context c){
        this.genericType = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        getInstance(c);
        try {
            this.genericDao = ormDatabaseHelper.getGenericDao(genericType);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidRepositoryException();
        }
    }

    public final List<T> findAll() throws SQLException {
        return genericDao.queryForAll();
    }

    public final T getOne(K key) throws SQLException {
        T payload = genericDao.queryForId(key);
        if(payload == null){
            throw new EntityNotFoundException();
        }
        return payload;
    }

    public final void create(T payload) throws EntityNotCreatedException, SQLException {
        int affectedRows = genericDao.create(payload);
        if (affectedRows != 1){
            throw new EntityNotCreatedException();
        }
    }

    public final void update(T payload) throws SQLException {
        genericDao.update(payload);
    }

    public final void deleteById(K key) throws SQLException {
        genericDao.deleteById(key);
    }

    public final void delete(T payload) throws SQLException {
        genericDao.delete(payload);
    }

    private void getInstance(Context c){
        if(ormDatabaseHelper == null){
            ormDatabaseHelper = new ORMDatabaseHelper(c);
        }
        if(database == null) {
            database = ormDatabaseHelper.getWritableDatabase();
        }
    }

}
