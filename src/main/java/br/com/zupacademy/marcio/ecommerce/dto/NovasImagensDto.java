package br.com.zupacademy.marcio.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class NovasImagensDto {

    @Size(min = 1, message = "No mínimo uma imagem !")
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    @Deprecated
    public NovasImagensDto(){
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
