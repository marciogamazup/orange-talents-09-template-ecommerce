package br.com.zupacademy.marcio.ecommerce.commons.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {

    Set<String> envia(List<MultipartFile> imagens);
}
