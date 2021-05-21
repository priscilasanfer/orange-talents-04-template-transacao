package br.com.zupacademy.priscila.transacao.cartao;

public class CartaoResponse {
    private String id;
    private String email;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Cartao toModel(){
        return new Cartao(id, email);
    }
}
