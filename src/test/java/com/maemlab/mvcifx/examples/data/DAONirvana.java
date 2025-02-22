package com.maemlab.mvcifx.examples.data;

import java.util.List;

public class DAONirvana {
    private static DAONirvana INSTANCE;

    private DAONirvana(){}

    public static DAONirvana getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DAONirvana();
        }

        return INSTANCE;
    }

    public List<Nirvana> getPersons() {
        return List.of(new Nirvana("Kurt", "Cobain", 27),
                       new Nirvana("Dave", "Grohl", 56),
                       new Nirvana("Krist", "Novoselic", 60));
    }
}
