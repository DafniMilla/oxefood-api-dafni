package br.com.ifpe.oxefood.util.entity.exception;



public class EntregadorException extends RuntimeException {
    
    public static final String MSG_ENTREGADOR = "";

    public EntregadorException(String msg) {

	super(String.format(msg));
    }

}
