package com.api.fileoperationdemo.utility;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

    /*O método compressImage recebe um array de bytes data representando a imagem a ser comprimida.*/
    public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();  // cria um objeto Deflater para realizar a compressão
        deflater.setLevel(Deflater.BEST_COMPRESSION);  // o algoritmo de compressão tentará obter a melhor compressão possível
        deflater.setInput(data);  // define o array de bytes data como a entrada do objeto Deflater
        deflater.finish();  // inicia o processo de compressão.

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);  // cria um objeto ByteArrayOutputStream com o tamanho do array de bytes data como tamanho inicial do buffer de saída
        byte[] tmp = new byte[4 * 1024];  // cria um array de bytes tmp com tamanho 4 * 1024 para armazenar os dados descompactados temporariamente durante o processo de compressão.
        

        /*A função então itera sobre o objeto Deflater chamando o método deflate repetidamente até que o processo de compressão seja concluído. Cada vez que o método deflate é chamado, ele retorna o número de bytes descompactados no array tmp. Esses bytes são então escritos no objeto ByteArrayOutputStream usando o método write.*/
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();  // Após o processo de compressão ser concluído, a função fecha o objeto ByteArrayOutputStream e retorna o array de bytes comprimido resultante.
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }


    /*Este método descomprime uma imagem representada como um array de bytes usando o algoritmo de descompressão Inflater. Ele recebe um array de bytes data que representa a imagem comprimida a ser descomprimida.*/
    public static byte[] decompressImage(byte[] data) {
        
        Inflater inflater = new Inflater(); // cria um objeto Inflater para realizar a descompressão
        inflater.setInput(data);  // define o array de bytes data como a entrada do objeto Inflater usando o método setInput.
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length); // O método cria um objeto ByteArrayOutputStream com o tamanho do array de bytes data como tamanho inicial do buffer de saída.
        byte[] tmp = new byte[4*1024];  // Também é criado um array de bytes tmp com tamanho 4 * 1024 para armazenar os dados descomprimidos temporariamente durante o processo de descompressão.
        
        /*A função então itera sobre o objeto Inflater chamando o método inflate repetidamente até que o processo de descompressão seja concluído. Cada vez que o método inflate é chamado, ele retorna o número de bytes descomprimidos no array tmp. Esses bytes são então escritos no objeto ByteArrayOutputStream usando o método write.*/
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();  // Após o processo de descompressão ser concluído, a função fecha o objeto ByteArrayOutputStream e retorna o array de bytes descomprimido resultante.
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

}
