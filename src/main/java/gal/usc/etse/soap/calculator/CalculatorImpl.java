package gal.usc.etse.soap.calculator;

import javax.jws.WebService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebService(
        endpointInterface = "gal.usc.etse.soap.calculator.Calculator",
        serviceName = "Calculator"
)
public class CalculatorImpl implements Calculator {
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public int sum(int[] nums) {
        return Arrays.stream(nums).sum();
    }

    @Override
    public int resta(int a, int b) {
        return a-b;
    }

    @Override
    public int prod(int a, int b) {
        return a*b;
    }

    @Override
    public int prod(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else {
            return Arrays.stream(nums).reduce(1, (a, b) -> a * b); //Aqui non hai unha operación como sum entón hai que usar reduce e unha función lambda
        }
    }

    @Override
    public int div(int a, int b) {
        if(b!=0){
            return a/b;
        }
        else{
            throw new ArithmeticException("O denominador da división debe ser distinto de 0");
        }
    }

    @Override
    public double pow(int a, int b) {
        return Math.pow(a,b);
    }

    @Override
    public double sqrt(int a) {
        if(a>=0){
            return Math.sqrt(a);}
        else{
            throw new ArithmeticException("O número indicado para a raíz cadrada debe ser non negativo");
        }
    }

    @Override
    public double ln(int a) {
        if(a>0){
        return Math.log(a);}
        else{
            throw new ArithmeticException("O número indicado para o logaritmo debe ser estritamente positivo");
        }
    }

    @Override
    public int max(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        return Arrays.stream(nums).max().orElse(0); //Este or else nunca se vai a activar, xa que xestionamos aparte o array baleiro, pero é necesario por sintaxe
    }

    @Override
    public int min(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        return Arrays.stream(nums).min().orElse(0);
    }

    @Override
    public double media(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        return Arrays.stream(nums).average().orElse(0.0);
    }

    @Override
    public double med(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        //Non atopei ningunha función de arrays que calcule a mediana entón toca facer o procedemento a man
        Arrays.sort(nums);
        int pmedio = nums.length/2;
        if(nums.length % 2 == 1){   //Se a cantidade de elementos é impar, pmedio é o índice do elemento medio exacto
            return nums[pmedio];
        }
        else{ //Se é par, non hai elemento central e fai falta calcular a media entre os dous máis próximos
            return (double) (nums[pmedio - 1] + nums[pmedio])/2;    //Retornar double aquí pode ser algo esaxerado porque hai como moito un decimal, pero o resultado non ten por que ser enteiro
        }
    }

    @Override
    public int moda(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        Map<Integer, Integer> rep = new HashMap<>();
        for (int i : nums) {    //Ao usar un hash map, o mesmo número irá ao mesmo índice en caso de repetirse en nums
            rep.put(i, rep.getOrDefault(i, 0) + 1);   //Usando get or default aforrámonos a inicialización e asegurámonos que non hai problemas por isto
        }
        int moda = nums[0]; //Se hai un empate de varios, quedamos co primeiro deles
        int maxRep = 0;
        for(Map.Entry<Integer,Integer> num : rep.entrySet()){
            if(num.getValue()>maxRep){
                maxRep = num.getValue();
                moda = num.getKey();
            }
        }
        return moda;
    }

    @Override
    public double sd(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new IllegalArgumentException("A lista de números non pode ser baleira");
        }
        double media = media(nums);
        double var = Arrays.stream(nums).mapToDouble(n -> Math.pow(n-media,2)).sum() / nums.length; //Escrito bastante longo pero é a fórmula da varianza, xa co sumatorio, diferenza de cadrados e a división final pola cantidade total
        return Math.sqrt(var); //non podemos usar a función local de sqrt porque neste caso o argumento non é un enteiro. A desviación típica é a raiz cadrada da varianza.
    }
}
