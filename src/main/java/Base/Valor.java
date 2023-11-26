package Base;

import java.io.Serializable;

public enum Valor implements Serializable {
    AS("A"),
    DOS("2"),
    TRES("3"),
    CUATRO("4"),
    CINCO("5"),
    SEIS("6"),
    SIETE("7"),
    OCHO("8"),
    NUEVE("9"),
    DIEZ("10"),
    J("J"),
    Q("Q"),
    K("K");

    private final String valorCorto;

    Valor(String valorCorto) {
        this.valorCorto = valorCorto;
    }

    public String obtenerValorCorto() {
        return valorCorto;
    }
}
