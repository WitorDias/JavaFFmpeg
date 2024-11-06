import java.io.File;
import java.io.IOException;
import java.util.List;

public class OperacoesFFmpeg {
    public void converterImagemJpgParaPng() {

        String caminhoEntrada = System.getenv("caminhoEntrada");
        String caminhoSaida = System.getenv("caminhoSaida");

        FfmpegUtils.criaDiretorioSaidaSeNaoExistir(caminhoSaida);

        List<File> arquivosJpg = FfmpegUtils.listarArquivosComExtensao(caminhoEntrada, ".jpg");

        for (File file : arquivosJpg) {
            String arquivoSaida = FfmpegUtils.montarCaminhoSaida(caminhoSaida, file, ".png");
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

        String caminhoEntrada = System.getenv("caminhoEntradaDeVideo");
        String caminhoSaida = System.getenv("caminhoSaidaDeVideo");

        FfmpegUtils.criaDiretorioSaidaSeNaoExistir(caminhoSaida);

        List<File> arquivosJpg = FfmpegUtils.listarArquivosComExtensao(caminhoEntrada, ".mp4");

        for (File file : arquivosJpg) {
            String arquivoSaida = FfmpegUtils.montarCaminhoSaida(caminhoSaida, file, ".mp3");
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
