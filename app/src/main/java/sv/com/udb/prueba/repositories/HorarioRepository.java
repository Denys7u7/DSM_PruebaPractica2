package sv.com.udb.prueba.repositories;

import android.content.Context;

import sv.com.udb.prueba.model.Horario;
import sv.com.udb.prueba.model.Tratamiento;

public final class HorarioRepository extends AbstractRepository<Horario,Integer>{
    public HorarioRepository(Context context) {
        super(context);
    }
}
