package leilao;

import java.util.List;

public abstract class RepositorioDeLeiloes {
    
    public abstract void atualiza( Leilao leilao);
    
    public abstract List<Leilao>correntes();
}
