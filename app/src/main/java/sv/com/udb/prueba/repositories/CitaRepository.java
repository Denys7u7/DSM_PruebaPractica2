package sv.com.udb.prueba.repositories;

import android.content.Context;

import sv.com.udb.prueba.model.Cita;
import sv.com.udb.prueba.model.Horario;

public final class CitaRepository extends AbstractRepository<Cita,Integer>{
    public CitaRepository(Context context) {
        super(context);
    }
}
