package modelo;

import excecao.ExplosaoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampoTest {

    private Campo campo;

    @BeforeEach
    void iniciarCampo() {

        campo = new Campo(3, 3);
    }

    @Test
    void testeVizinhoRealDistancia1() {

        Campo vizinho = new Campo(3, 2);
        boolean reultado = campo.adicionarVizinho(vizinho);
        assertTrue(reultado);
    }

    @Test
    void testeVizinhoRealDistancia2() {

        Campo vizinho = new Campo(2, 4);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertTrue(resultado);
    }

    @Test
    void testeNaoVizinho() {

        Campo vizinho = new Campo(1, 1);
        boolean resultado = campo.adicionarVizinho(vizinho);
        assertFalse(resultado);
    }

    @Test
    void testeValorPadraoAtributoMarcado() {

        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacao() {
        campo.alternarMarcacao();
        assertTrue(campo.isMarcado());
    }

    @Test
    void testeAlternarMarcacaoDuasChamadas() {
        campo.alternarMarcacao();
        campo.alternarMarcacao();
        assertFalse(campo.isMarcado());
    }

    @Test
    void testeAbrirNaoMinadoNaoMarcado() {
        assertTrue(campo.abrir());
    }

    @Test
    void testeAbrirNaoMinadoMarcado() {
        campo.alternarMarcacao();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoMarcado() {
        campo.alternarMarcacao();
        campo.minar();
        assertFalse(campo.abrir());
    }

    @Test
    void testeAbrirMinadoNaoMarcado() {
        campo.minar();

        assertThrows(ExplosaoException.class, () -> {
           campo.abrir();
        });
    }

    @Test
    void testeAbrirComVizinhos1() {

        Campo campo11 = new Campo(1, 1);
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && campo11.isAberto());
    }

    @Test
    void testeAbrirComVizinhos2() {

        Campo campo11 = new Campo(1, 1);
        Campo campo12 = new Campo(1, 2);
        campo12.minar();
        Campo campo22 = new Campo(2, 2);

        campo22.adicionarVizinho(campo11);
        campo22.adicionarVizinho(campo12);

        campo.adicionarVizinho(campo22);

        campo.abrir();

        assertTrue(campo22.isAberto() && !campo11.isAberto());
    }

    @Test
    void testeObjetivoAlcancadoCampoAberto() {

        campo.abrir();

        assertTrue(campo.objetivoAlcancado());

    }

    @Test
    void testeObjetivoAlcancadoCampoMarcado() {

        campo.minar();
        campo.alternarMarcacao();

        assertTrue(campo.objetivoAlcancado());

    }

    @Test
    void testeObjetivoNaoAlcancado() {

        assertFalse(campo.objetivoAlcancado());
    }

    @Test
    void testeQuantidadeVizinhosMinados() {

        Campo vizinho = new Campo(3, 4);
        vizinho.minar();

        campo.adicionarVizinho(vizinho);

        assertEquals(1, campo.minasNaVizinhanca());
    }

    @Test
    void testeMetodoToString() {

        campo.alternarMarcacao();

        assertEquals("x", campo.toString());

    }

    @Test
    void testePegarLinha() {

        assertEquals(3, campo.getLinha());
    }

    @Test
    void testePegarColuna() {

        assertEquals(3, campo.getColuna());
    }
}