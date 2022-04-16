package sv.com.udb.prueba.repositories;

import android.content.Context;

import sv.com.udb.prueba.model.Marca;

public final class MarcasRepository extends AbstractRepository<Marca,Integer> {
    public MarcasRepository(Context context) {
        super(context);
    }
}
