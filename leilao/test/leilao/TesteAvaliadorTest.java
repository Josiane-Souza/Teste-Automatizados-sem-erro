package leilao;

import java.util.List;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteAvaliadorTest {    
    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    
    // novo método que cria o avaliador
    /*private void criaAvaliador() 
    {
        this.leiloeiro = new Avaliador();
    }*/
    
    public TesteAvaliadorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /*executa o método criaAvaliador() 3 vezes: 
    uma vez antes de cada método de teste!*/
    @Before   
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }   
    
    @After
    public void tearDown() {
    }
    /*
    @Test
    public void deveEntenderLancesEmOrdemAleatoria() {
        // cenário: 3 lances em ordem crescente
        Leilao leilao = new Leilao("Playstation 3 Novo");
        leilao.propoe(new Lance(jose,500.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(maria,350.0));
                      
       // executando a ação
       leiloeiro.avalia(leilao);
       
       // invocando método auxiliar
        criaAvaliador();
        leiloeiro.avalia(leilao);  
        
       assertEquals(1000,leiloeiro.getMaiorLance(), 0.0001);
       assertEquals(250,leiloeiro.getMenorLance(), 0.0001);
    }*/
   @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        Leilao leilao = new CriadorDeLeilao()
        .para("Playstation 3 Novo")
        .lance(joao, 250)
        .lance(jose, 300)
        .lance(maria, 400)
        .constroi();          
              
       // executando a ação
       leiloeiro.avalia(leilao);
       
       assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
       assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
    }
    
    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
       Leilao leilao = new Leilao("Playstation 3 Novo");
       leilao.propoe(new Lance(joao,1000.0));
       
       leiloeiro.avalia(leilao);
       
       // invocando método auxiliar
       criaAvaliador();
       leiloeiro.avalia(leilao);
       
       /* Assert.assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
       O método assertEquals() é estático, portanto, podemos importá-lo de
       maneira estática! Basta fazer uso do import static!
       */
       assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
       assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }
    
    @Test
    public void deveEncontrarOsTresMaioresLances() {
        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation 3 Novo")
            .lance(joao, 100.0)
            .lance(maria, 200.0)
            .lance(joao, 300.0)
            .lance(maria, 400.0)
            .constroi();
        
        leiloeiro.avalia(leilao);
        
        // invocando método auxiliar
        /*criaAvaliador();
        leiloeiro.avalia(leilao); */                                      
            
        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        
        //verificar o conteúdo de cada lance da lista
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }
    
    @Test(expected=RuntimeException.class)
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        
        Leilao leilao = new CriadorDeLeilao()
        .para("Playstation 3 Novo")
        .constroi();
        leiloeiro.avalia(leilao);
    }  
}
