package br.com.ifpe.oxefood.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

public class Util {

    public static final String LOCAL_ARMAZENAMENTO_IMAGENS = "/home/roberto/git/oxefood-web/public/";

    public static String fazerUploadImagem(MultipartFile imagem) {

        boolean sucessoUpload = false;
        String nomeArquivoComDataHora = null;

        if (imagem != null && !imagem.isEmpty()) {

            String nomeOriginalArquivo = imagem.getOriginalFilename();
            nomeArquivoComDataHora = LocalDateTime.now().toString() + " - " + nomeOriginalArquivo;

            try {

                // Criando o diretório para armazenar o arquivo
                String imagens_projetos = LOCAL_ARMAZENAMENTO_IMAGENS + "./oxefood-api-dafni/src/main/java/br/com/ifpe/oxefood/public";
                File dir = new File(imagens_projetos);
    if (!dir.exists()) {
                    dir.mkdirs();
                }

                // Criando o arquivo no diretório
                //cria uma cópia do arquio selecionado
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivoComDataHora);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Arquivo armazenado em:" + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo " + nomeOriginalArquivo + " com sucesso");
                sucessoUpload = true;

            } catch (Exception e) {
                System.out.println("Você falhou em carregar o arquivo " + nomeOriginalArquivo + " => " + e.getMessage());
            }

        } else {
            System.out.println("Você falhou em carregar o arquivo porque ele está vazio ");
        }

        if (sucessoUpload) {
            return nomeArquivoComDataHora;
        } else {
            return null;
        }
    }

}
