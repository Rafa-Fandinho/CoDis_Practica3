package gal.usc.etse.soap.textAnalyzer;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface TextAnalyzer {
    @WebMethod(operationName = "word_count")
    @WebResult(name = "resultado")
    int contaP(@WebParam(name = "texto") String s);

    @WebMethod(operationName = "char_count")
    @WebResult(name = "resultado")
    int contaC(@WebParam(name = "texto") String s);

    @WebMethod(operationName = "phrase_count")
    @WebResult(name = "resultado")
    int contaF(@WebParam(name = "texto") String s);

    @WebMethod(operationName = "word_rep")
    @WebResult(name = "resultado")
    int rep(@WebParam(name = "texto") String s, @WebParam(name = "palabra") String p) throws IllegalArgumentException;

    @WebMethod(operationName = "most_word")
    @WebResult(name = "resultado")
    String mais(@WebParam(name = "texto") String s) throws IllegalArgumentException;

    @WebMethod(operationName = "least_word")
    @WebResult(name = "resultado")
    String menos(@WebParam(name = "texto") String s) throws IllegalArgumentException;

    @WebMethod(operationName = "replace_word")
    @WebResult(name = "resultado")
    String reemp(@WebParam(name = "texto") String s, @WebParam(name = "palabra_reemplazada") String p, @WebParam(name = "palabra_nova") String n);
}
