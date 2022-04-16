package sv.com.udb.prueba.repositories;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import sv.com.udb.prueba.model.Favoritos;

public class FavoritoRepository extends AbstractRepository<Favoritos,Integer>{
    public FavoritoRepository(Context context) {
        super(context);
    }

    public List<Favoritos> getFavoritiesByUserId(Integer userId){
        try {
            return genericDao.queryForEq("fk_favoritos_automovil_usuario_idx",userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Failed to recover favorities");
        }
    }
}
