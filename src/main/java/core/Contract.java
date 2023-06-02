package core;

import java.time.LocalDate;

public class Contract {
    private long id;
    private String redacao;
    private LocalDate ultimaAtualizacao;
    private long clientId; // Nova propriedade para o ID do cliente

    public Contract(long id, String redacao, LocalDate ultimaAtualizacao, long clientId) {
        this.id = id;
        this.redacao = redacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.clientId = clientId;
    }

    public long getId() {
        return id;
    }

    public String getRedacao() {
        return redacao;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public long getClientId() {
        return clientId;
    }
}
