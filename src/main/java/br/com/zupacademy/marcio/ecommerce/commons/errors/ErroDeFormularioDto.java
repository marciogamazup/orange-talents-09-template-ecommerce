package br.com.zupacademy.marcio.ecommerce.commons.errors;

public class ErroDeFormularioDto {

    private String campo;
    private String erro;

    @Deprecated
    public ErroDeFormularioDto() {
    }

    public ErroDeFormularioDto (String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
