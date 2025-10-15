package br.com.ifpe.oxefood.util.entity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ClienteException extends RuntimeException {

    public static final String MSG_LETRA_MINIMA = "Não é permitido inserir ";

    public ClienteException(String msg) {

	super(String.format(msg));
    }
}
