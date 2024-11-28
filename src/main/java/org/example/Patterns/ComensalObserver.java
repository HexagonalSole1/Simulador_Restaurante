package org.example.Patterns;

import org.example.models.actors.Comensal;

public interface ComensalObserver {
    void onMesaAsignada(Comensal comensal, int mesa);
}
