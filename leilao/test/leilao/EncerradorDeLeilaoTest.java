package leilao;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EncerradorDeLeilaoTest {
 
    @Test
    public void deveEncerrarLeiloesQueComecaramUmaSemanaAtras() {
        
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);
        
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma").naData(antiga).constroi();
        Leilao leilao2 = new CriadorDeLeilao().para("Geladeira").naData(antiga).constroi();
        List<Leilao> leiloesAntigos = Arrays.asList(leilao1, leilao2);
        
        // criamos o mock
        LeilaoDao daoFalso = mock(LeilaoDao.class);
        // ensinamos ele a retornar a lista de leilões antigos
        when(daoFalso.correntes()).thenReturn(leiloesAntigos);
        
               
        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso);
        encerrador.encerra();
       
        assertTrue(leilao1.isEncerrado());
        assertTrue(leilao2.isEncerrado());
        assertEquals(2, encerrador.getQuantidadeDeEncerrados());
    }
    
    @Test
    public void deveAtualizarLeiloesEncerrados() {
        Calendar antiga = Calendar.getInstance();
        antiga.set(1999, 1, 20);
        Leilao leilao1 = new CriadorDeLeilao().para("TV de plasma")
            .naData(antiga).constroi();
        
        RepositorioDeLeiloes daoFalso = mock(RepositorioDeLeiloes.class);
        when(daoFalso.correntes()).thenReturn(Arrays.asList(leilao1));
        
        Carteiro carteiroFalso = mock(Carteiro.class);
        EncerradorDeLeilao encerrador = new EncerradorDeLeilao(daoFalso, carteiroFalso);
        encerrador.encerra();
        // verificando que o método atualiza foi realmente invocado!
        verify(daoFalso).atualiza(leilao1);
    }

        
}