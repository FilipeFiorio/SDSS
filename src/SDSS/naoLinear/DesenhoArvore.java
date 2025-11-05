package SDSS.naoLinear;

import aesd.algorithms.tree.TraversalTypes;
import aesd.ds.interfaces.BinaryTree;
import br.com.davidbuzatto.jsge.core.Camera2D;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


 // @author gusta
 
public class DesenhoArvore {

    private final Color corLigacao = Color.BLACK;
    private final Color corNo = new Color(30, 30, 30);
    private final Color corTexto = Color.WHITE;

    private static class Ponto {
        double x, y;
        String texto;
        Ponto pai;
        Ponto(double x, double y, String texto, Ponto pai) {
            this.x = x;
            this.y = y;
            this.texto = texto;
            this.pai = pai;
        }
    }

    public void desenhar(BinaryTree<?, ?> arvore, EngineFrame frame, Camera2D camera) {

        frame.beginMode2D(camera);

        // Caso a árvore esteja vazia
        if (arvore == null || arvore.isEmpty()) {
            frame.drawText("Árvore vazia",
                    frame.getScreenWidth() / 2 - 60,
                    frame.getScreenHeight() / 2,
                    24, Color.GRAY);
            frame.endMode2D();
            return;
        }

        List<String> elementos = new ArrayList<>();
        for (BinaryTree.Entry<?, ?> e : arvore.traverse(TraversalTypes.INORDER)) {
            elementos.add(e.getKey() + ":" + e.getValue());
        }

        int n = elementos.size();
        double largura = frame.getScreenWidth();
        double altura = frame.getScreenHeight();
        double espacamentoX = largura / (n + 1);
        double espacamentoY = 100;

        List<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = (i + 1) * espacamentoX;
            double y = altura / 3.0;
            pontos.add(new Ponto(x, y, elementos.get(i), null));
        }

        for (int i = 1; i < pontos.size(); i++) {
            Ponto anterior = pontos.get(i - 1);
            Ponto atual = pontos.get(i);
            frame.drawLine(anterior.x, anterior.y, atual.x, atual.y, corLigacao);
        }

        for (Ponto p : pontos) {
            frame.fillCircle(p.x, p.y, 20, corNo);
            frame.drawCircle(p.x, p.y, 20, Color.WHITE);
            double textX = p.x - frame.measureText(p.texto, 14) / 2.0;
            frame.drawText(p.texto, (int) textX, (int) (p.y + 5), 14, corTexto);
        }

        frame.endMode2D();
    }    
}
