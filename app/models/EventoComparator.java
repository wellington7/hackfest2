package models;

import java.util.Comparator;

public class EventoComparator implements Comparator<Evento> {
	
	@Override
    public int compare(Evento m1, Evento m2) {
       return Integer.compare(m1.getTotalDeParticipantes(), m2.getTotalDeParticipantes());
    }
}