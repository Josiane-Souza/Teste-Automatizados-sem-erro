package leilao;

public class Lance {
    private Usuario usuario;
    private double valor;
    
    
    Lance(Usuario usuarioRecebido, double valorRecebido) {
        this.usuario = usuarioRecebido;
        this.valor = valorRecebido;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    double getValor() {
        return valor;
    }   
}
