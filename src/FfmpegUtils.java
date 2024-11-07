import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FfmpegUtils {
    public static void criaDiretorioSaidaSeNaoExistir(String caminhoSaida) {
        File diretorioSaida = new File(caminhoSaida);
        if (!diretorioSaida.exists()) {
            diretorioSaida.mkdir();
        }
    }

    public static void executarFfmpeg(String[] parametrosFfmpeg) {
        try {
            Process process = Runtime.getRuntime().exec(parametrosFfmpeg);
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao executar o ffmpeg" + e.getMessage());
        }
    }

    public static List<File> listarArquivosComExtensao(String caminhoEntrada, String extensao) {
        File[] arquivos = new File(caminhoEntrada).listFiles();
        List<File> arquivosFiltrados = new ArrayList<>();

        if (arquivos != null) {
            for (File file : arquivos) {
                if (file.isFile() && file.getName().endsWith(extensao)) {
                    arquivosFiltrados.add(file);
                }
            }
        }
        return arquivosFiltrados;
    }

    public static String montarCaminhoAbsolutoSaida(String caminhoSaida, File arquivoEntrada, String novaExtensao) {
        return caminhoSaida + "\\" + arquivoEntrada.getName().replaceFirst("\\.[^.]+$", novaExtensao);
    }
}
