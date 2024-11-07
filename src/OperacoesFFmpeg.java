import java.io.File;
import java.io.IOException;
import java.util.List;

public class OperacoesFFmpeg {
    public void converterImagemJpgParaPng() {

        String caminhoAbsolutoEntrada = System.getenv("caminhoEntrada");
        String caminhoAbsolutoSaida = System.getenv("caminhoSaida");

        FfmpegUtils.criaDiretorioSaidaSeNaoExistir(caminhoAbsolutoSaida);

        List<File> arquivosJpg = FfmpegUtils.listarArquivosComExtensao(caminhoAbsolutoEntrada, ".jpg");

        for (File file : arquivosJpg) {
            String arquivoSaida = FfmpegUtils.montarCaminhoAbsolutoSaida(caminhoAbsolutoSaida, file, ".png");
            File arquivoSaidaConcreto = new File(arquivoSaida);

            if (!arquivoSaidaConcreto.exists()) {
                String[] comandoFfmpeg = {"ffmpeg", "-i", file.getAbsolutePath(), arquivoSaida};
                FfmpegUtils.executarFfmpeg(comandoFfmpeg);
            } else {
                System.out.println("Arquivo: " + arquivoSaida + " já existe.");
            }
        }
    }

    public void converterVideoMp4EmAudioMp3() {

        String caminhoAbsolutoEntrada = System.getenv("caminhoEntradaDeVideo");
        String caminhoAbsolutoSaida = System.getenv("caminhoSaidaDeVideo");

        FfmpegUtils.criaDiretorioSaidaSeNaoExistir(caminhoAbsolutoSaida);

        List<File> arquivosJpg = FfmpegUtils.listarArquivosComExtensao(caminhoAbsolutoEntrada, ".mp4");

        for (File file : arquivosJpg) {
            String arquivoSaida = FfmpegUtils.montarCaminhoAbsolutoSaida(caminhoAbsolutoSaida, file, ".mp3");
            File arquivoSaidaConcreto = new File(arquivoSaida);

            if (!arquivoSaidaConcreto.exists()) {
                String[] comandoFfmpeg = {"ffmpeg", "-i", file.getAbsolutePath(), "-ab", "160k", "-ac", "2", arquivoSaida};
                try {
                    Process process = Runtime.getRuntime().exec(comandoFfmpeg);

                } catch (IOException e) {
                    System.err.println("Erro ao executar o ffmpeg" + e.getMessage());
                }
            } else {
                System.out.println("Arquivo: " + arquivoSaida + " já existe.");
            }
        }
    }
}
