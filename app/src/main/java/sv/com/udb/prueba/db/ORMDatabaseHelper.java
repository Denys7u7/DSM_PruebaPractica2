package sv.com.udb.prueba.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.sql.SQLException;

import sv.com.udb.prueba.R;

public class ORMDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "pruebapractica";
    private static final int DATABASE_VERSION = 1;
    private String queries;

    public ORMDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        final InputStream inputStream = context.getResources().openRawResource(R.raw.db);
        try{
            this.queries = IOUtils.toString(inputStream);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed to read file");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        for(String query: queries.split(";")){
            try {
                database.execSQL(query);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Failed to execute query: "+query);
            }
        }
    }

    public <T,K> Dao<T,K> getGenericDao(Class<T> clazz) throws SQLException {
        try {
           return getDao(clazz);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw throwables;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // ignored
    }
}
