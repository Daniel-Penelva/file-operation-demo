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


/* Bom saber: Classes Deflater e Inflater 

As classes Inflater e Deflater fazem parte do pacote "java.util.zip" do Java e são usadas para compressão e descompressão de dados, respectivamente.

A classe "Deflater" é usada para comprimir dados usando o algoritmo Deflate, que é um algoritmo de compressão de dados sem perdas. Ele é 
frequentemente usado para reduzir o tamanho dos dados antes de serem transmitidos por uma rede ou armazenados em um arquivo. A classe "Deflater" 
fornece métodos para definir o nível de compressão, finalizar o processo de compressão e desinflar os dados em um buffer.

A classe "Inflater" é usada para descomprimir dados que foram comprimidos usando o algoritmo Deflate. Ele é frequentemente usado para 
descomprimir dados que foram recebidos por uma rede ou carregados de um arquivo. A classe Inflater fornece métodos para definir os dados de 
entrada, finalizar o processo de descompressão e inflar os dados em um buffer.

As classes Deflater e Inflater são geralmente usadas em conjunto para compressão e descompressão de dados em uma aplicação Java. Por exemplo, um 
objeto Deflater pode ser usado para comprimir dados antes de serem enviados por uma rede, e então um objeto Inflater pode ser usado para 
descomprimir os dados no lado receptor.

A classe Inflater tem um método chamado inflate() que é usado para descomprimir os dados de entrada e preencher o buffer especificado com os 
dados descomprimidos. O método retorna o número de bytes dos dados descomprimidos. O método tem duas versões overloadadas, uma que recebe um 
array de bytes e outra que recebe um array de bytes, um deslocamento e um comprimento.

A classe Deflater tem um método chamado deflate() que é usado para comprimir os dados de entrada e preencher o buffer especificado com os dados 
compressos. O método retorna o número de bytes dos dados compressos. O método também tem duas versões overloadadas, uma que recebe um array de 
bytes e outra que recebe um array de bytes, um deslocamento e um comprimento.

As classes Inflater e Deflater podem ser configuradas com várias opções, como o nível de compressão, se usar a compressão GZIP e se envolver os 
dados compressos em um cabeçalho GZIP.

Em resumo, as classes Inflater e Deflater são ferramentas poderosas para compressão e descompressão de dados no Java. Elas fornecem uma maneira 
simples e eficiente de comprimir e descomprimir dados usando o algoritmo Deflate.
*/
