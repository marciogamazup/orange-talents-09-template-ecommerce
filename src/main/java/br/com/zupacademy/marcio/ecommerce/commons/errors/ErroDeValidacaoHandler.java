package br.com.zupacademy.marcio.ecommerce.commons.errors;

import br.com.zupacademy.marcio.ecommerce.commons.errors.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {

        List<ErroDeFormularioDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach( e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {

        String fieldDescription = e.getMessage();
        String errorDescription = e.getLocalizedMessage();
        if(errorDescription == null) errorDescription = e.toString();

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto(fieldDescription,errorDescription);
        return new ResponseEntity<>(erroDeFormularioDto, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ProdutoInexistenteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto handleProdutoInexistenteException(ProdutoInexistenteException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("idProduto","Produto não encontrado !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {EstoqueInsuficienteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto handleEstoqueInsuficienteException(EstoqueInsuficienteException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("quantidade","Estoque insuficiente para essa quantidade !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {UsuarioInexistenteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto handleUsuarioInexistenteException(UsuarioInexistenteException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("emailComprador","Usuário não está cadastrado em nossa base !");

        return  erroDeFormularioDto;
    }

    @ExceptionHandler(value = {CompraInexistenteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto handleCompraInexistenteException(CompraInexistenteException e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("compraId", "Id para essa compra, não está cadastrada em nossa base !");

        return erroDeFormularioDto;
    }

    @ExceptionHandler(value = {ExceptionPagamentoJaEfetuado.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormularioDto handlePagamentoJaEfetuado(ExceptionPagamentoJaEfetuado e, WebRequest request) {

        ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto( "Pagamento", "Pagamento já efetuado para essa compra !");

        return erroDeFormularioDto;

    }




}
