package leilao;
public class TesteDoAvaliador{

    public static void main(String[] args) {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        
        Leilao leilao = new Leilao("Playstation 3 Novo");
        
       leilao.propoe(new Lance(maria,250.0));
       leilao.propoe(new Lance(joao,300.0));
       leilao.propoe(new Lance(jose,400.0));
        
       // executando a ação
       Avaliador leiloeiro = new Avaliador();
       leiloeiro.avalia(leilao);
       
        // comparando a saída com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;
        
        System.out.println(maiorEsperado ==
            leiloeiro.getMaiorLance());
        System.out.println(menorEsperado ==
            leiloeiro.getMenorLance());
    }

   
}
   
    

