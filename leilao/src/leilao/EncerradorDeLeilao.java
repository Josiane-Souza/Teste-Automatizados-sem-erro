package leilao;

import java.util.Calendar;
import java.util.List;

public class EncerradorDeLeilao {
    private int total = 0;
    private final RepositorioDeLeiloes dao;
    private final Carteiro carteiro;
    
    public EncerradorDeLeilao(RepositorioDeLeiloes dao,Carteiro carteiro) {
        this.dao = dao;
        // guardamos o carteiro como atributo da classe
        this.carteiro = carteiro;
    }
    
    EncerradorDeLeilao(LeilaoDao dao) {
        this.dao = dao;
        this.carteiro = null;
    }
       
    public void encerra(){
        List<Leilao> todosLeiloesCorrentes = dao.correntes();
    
        for(Leilao leilao : todosLeiloesCorrentes) {
            try {
                if(comecouSemanaPassada(leilao)) {
                    leilao.encerra();
                    total++;
                    dao.atualiza(leilao);
                    // agora enviamos por email tambem!
                    carteiro.envia(leilao);
                }   
            }
            catch(Exception e) {
                // salvo a exceção no sistema de logs
                // e o loop continua!
            }
        }
    }

    private boolean comecouSemanaPassada(Leilao leilao) {
        return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
    }
    private int diasEntre(Calendar inicio, Calendar fim) {
        Calendar data = (Calendar) inicio.clone();
        int diasNoIntervalo = 0;
        while (data.before(fim)) {
            data.add(Calendar.DAY_OF_MONTH, 1);
            diasNoIntervalo++;
        }
        return diasNoIntervalo;
    }
    public int getTotalEncerrados() {
        return total;
    }
    public int getQuantidadeDeEncerrados(){
        return getTotalEncerrados ();
    }

}