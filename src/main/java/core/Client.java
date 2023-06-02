package core;

public class Client {

    private final long id;
    private final long cpf;
    private final String name;

    public Client(long id, long cpf, String name) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}
