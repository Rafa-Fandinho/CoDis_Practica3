package gal.usc.etse.soap.calculator;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Calculator {
    @WebMethod(operationName = "suma")
    @WebResult(name = "resultado")
    int sum(@WebParam(name = "sumando1") int a, @WebParam(name = "sumando2") int b);
    int sum(int[] nums);

    @WebMethod(operationName = "resta")
    @WebResult(name = "resultado")
    int resta(@WebParam(name = "minuendo") int a, @WebParam(name = "subtraendo") int b);

    @WebMethod(operationName = "producto")
    @WebResult(name = "resultado")
    int prod(@WebParam(name = "factor1") int a, @WebParam(name = "factor2") int b);
    int prod(int[] nums);   //No caso da suma e o producto deixo a opción do array pola conmutatividade

    @WebMethod(operationName = "división")
    @WebResult(name = "resultado")
    int div(@WebParam(name = "numerador") int a, @WebParam(name = "denominador") int b) throws ArithmeticException;

    @WebMethod(operationName = "potencia")
    @WebResult(name = "resultado")
    double pow(@WebParam(name = "base") int a, @WebParam(name = "expoñente") int b);

    @WebMethod(operationName = "raíz")
    @WebResult(name = "resultado")
    double sqrt(@WebParam(name = "num1") int a) throws ArithmeticException;

    @WebMethod(operationName = "logaritmo")
    @WebResult(name = "resultado")
    double ln(@WebParam(name = "num1") int a) throws ArithmeticException;

    @WebMethod(operationName = "máximo")
    @WebResult(name = "resultado")
    int max(@WebParam(name = "lista") int[] nums) throws IllegalArgumentException;    //Para estas operacións estadísticas, tenme máis sentido deixar só a opción do array

    @WebMethod(operationName = "mínimo")
    @WebResult(name = "resultado")
    int min(@WebParam(name = "lista") int[] nums) throws IllegalArgumentException;

    @WebMethod(operationName = "media")
    @WebResult(name = "resultado")
    double media(@WebParam(name = "dataset") int[] nums) throws IllegalArgumentException;

    @WebMethod(operationName = "mediana")
    @WebResult(name = "resultado")
    double med(@WebParam(name = "dataset") int[] nums) throws IllegalArgumentException;

    @WebMethod(operationName = "moda")
    @WebResult(name = "resultado")
    int moda(@WebParam(name = "dataset") int[] nums) throws IllegalArgumentException;

    @WebMethod(operationName = "desviación_típica")
    @WebResult(name = "resultado")
    double sd(@WebParam(name = "dataset") int[] nums) throws IllegalArgumentException;
}
