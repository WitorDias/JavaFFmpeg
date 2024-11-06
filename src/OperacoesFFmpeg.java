import java.io.File;
import java.io.IOException;

public class OperacoesFFmpeg {
    public void converterImagens() {
        String caminhoEntrada = System.getenv("caminhoEntrada");
        String caminhoSaida = System.getenv("caminhoSaida");
        File diretorioSaida = new File(caminhoSaida);
        diretorioSaida.mkdir();
        File[] arquivos = new File(caminhoEntrada).listFiles();
        if (arquivos != null) {
            for (File file : arquivos) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    String arquivoEntrada = file.getAbsolutePath();
                    String arquivoSaida = caminhoSaida + "\\" + file.getName().replace(".jpg", ".png");
                    String[] comandoFfmpeg = {"ffmpeg", "-i", arquivoEntrada, arquivoSaida};
                    try {
                        Process process = Runtime.getRuntime().exec(comandoFfmpeg);
                        process.waitFor();

                    } catch (IOException | InterruptedException e) {
                        System.err.println("Erro ao converter imagem " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
