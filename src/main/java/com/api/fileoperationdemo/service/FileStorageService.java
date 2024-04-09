package com.api.fileoperationdemo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.fileoperationdemo.entity.ImageData;
import com.api.fileoperationdemo.repository.ImageDataStorageRepository;
import com.api.fileoperationdemo.utility.ImageUtils;

@Service
public class FileStorageService {

    @Autowired
    private ImageDataStorageRepository imageDataStorageRepository;

    /*Este método tem como objetivo do método salvar uma imagem no banco de dados, armazenando o nome do arquivo, o tipo de conteúdo e os dados da imagem comprimidos.*/
    public String uploadImage(MultipartFile file) throws IOException {

        /*Cria um objeto ImageData com os detalhes do arquivo, como o nome original, o tipo de conteúdo e os dados da imagem comprimidos usando o método compressImage da classe ImageUtils.*/
        ImageData result = imageDataStorageRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (result != null) {
            return "Imagem salva no banco de dados com nome: " + file.getOriginalFilename();
        }
        return "Imagem não salva!";
    }


    /*Este método faz o download de uma imagem do banco de dados usando o nome do arquivo e retorna os dados da imagem como um array de bytes.*/
    public byte[] downloadImage(String fileName) { //  recebe um parâmetro String chamado fileName que representa o nome do arquivo da imagem a ser baixada
        
        /*Ele chama o método findByName do repositório imageDataStorageRepository para buscar a imagem no banco de dados usando o nome do arquivo. O método findByName retorna um Optional<ImageData> que contém a imagem se ela for encontrada no banco de dados.*/
        Optional<ImageData> imageFromDb = imageDataStorageRepository.findByName(fileName);
        byte[] imageInbytes = ImageUtils.decompressImage(imageFromDb.get().getImageData());  // Em seguida, o método decompressImage da classe ImageUtils é chamado para descomprimir os dados da imagem armazenados no banco de dados.
        return imageInbytes;  // O método decompressImage retorna um array de bytes que representa os dados descomprimidos da imagem.
    }

}


/* Bom Saber: Interface MultipartFile

A interface MultipartFile no Spring é uma representação de um arquivo enviado em uma requisição multipart em um aplicativo Spring. Ela é 
utilizada para lidar com uploads de arquivos em uma aplicação baseada em Spring. O conteúdo do arquivo pode ser armazenado na memória ou 
temporariamente em disco. O usuário é responsável por copiar o conteúdo do arquivo para um armazenamento de nível de sessão ou persistente 
conforme desejado. O armazenamento temporário será limpo ao final do processamento da requisição.

A interface MultipartFile fornece diversos métodos para manipular o arquivo enviado, tais como:

- getBytes(): Retorna o conteúdo do arquivo como um array de bytes.
- getInputStream(): Retorna um InputStream para ler o conteúdo do arquivo.
- getName(): Retorna o nome do parâmetro no formulário multipart.
- getOriginalFilename(): Retorna o nome original do arquivo no sistema de arquivos do cliente.
- getSize(): Retorna o tamanho do arquivo em bytes.
- isEmpty(): Indica se o arquivo enviado está vazio ou não.
- transferTo(File dest): Transfere o arquivo recebido para o arquivo de destino especificado.

A interface MultipartFile é implementada por diversas classes no Spring, incluindo MockMultipartFile, utilizada para fins de teste, e 
CommonsMultipartFile, utilizada para converter um objeto File em um objeto MultipartFile.

A interface MultipartFile faz parte do módulo Spring Web e está disponível a partir das versões 2.0. Ela é utilizada em conjunto com as classes 
MultipartHttpServletRequest e MultipartResolver para lidar com uploads de arquivos em um aplicativo baseado em Spring.
*/
