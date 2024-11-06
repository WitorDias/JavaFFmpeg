import java.io.File;
import java.io.IOException;

public class OperacoesFFmpeg {
    public void converterImagemJpgParaPng() {

        String caminhoEntrada = System.getenv("caminhoEntrada");
        String caminhoSaida = System.getenv("caminhoSaida");
        File diretorioSaida = new File(caminhoSaida);

        if(!diretorioSaida.exists()) {
            diretorioSaida.mkdir();
        }

        File[] arquivos = new File(caminhoEntrada).listFiles();

        if (arquivos != null) {
            for (File file : arquivos) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    String arquivoEntrada = file.getAbsolutePath();
                    String arquivoSaida = caminhoSaida + "\\" + file.getName().replace(".jpg", ".png");
                    File arquivoSaidaConcreto = new File(arquivoSaida);
                    if (!arquivoSaidaConcreto.exists()) {
                        String[] comandoFfmpeg = {"ffmpeg", "-i", arquivoEntrada, arquivoSaida};
                        try {
                            Process process = Runtime.getRuntime().exec(comandoFfmpeg);
                            process.waitFor();

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Erro ao converter imagem " + file.getName() + ": " + e.getMessage());
                        }
                    }else {
                        System.out.println("Arquivo: "+ arquivoSaida + " já existe.");
                    }
                }
            }
        }
    }
}
