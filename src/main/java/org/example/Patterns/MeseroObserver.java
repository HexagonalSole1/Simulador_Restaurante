package org.example.Patterns;
import org.example.models.actors.Mesero;
public interface MeseroObserver {
        void onMesaAsignada(Mesero mesero, int mesa);

}
