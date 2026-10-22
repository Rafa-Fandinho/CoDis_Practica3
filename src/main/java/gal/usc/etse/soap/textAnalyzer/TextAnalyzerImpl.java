package gal.usc.etse.soap.textAnalyzer;

import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

@WebService(
        endpointInterface = "gal.usc.etse.soap.textAnalyzer.TextAnalyzer",
        serviceName = "TextAnalyzer"
)
public class TextAnalyzerImpl implements TextAnalyzer{

    @Override
    public int contaP(String s) {
        if(s==null || s.trim().isEmpty()){
            return 0;
        }
        return s.trim().split("[\\s+]").length; //Aquí hai que ter un coidado extra cos espazos en branco para non contar de máis, e non fai falta considerar outros separadores
    }

    @Override
    public int contaC(String s) {
        if(s == null){
            return 0;
        }
        return s.length();
    }

    @Override
    public int contaF(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }
        int i=0;
        String[] frases = s.split("[\\.\\?\\!\\n]+");
        for(String f : frases){
            if(!f.trim().isEmpty()){ //O procedemento de separación pode ser redundante nalgúns casos como ?!
                i++;
            }
        }
        return i;
    }

    @Override
    public int rep(String s, String p) {
        if(s == null || p == null || s.trim().isEmpty() || !s.contains(p)){
            return 0;
        }
        if(p.trim().contains(" ")){
            throw new IllegalArgumentException("Débense buscar palabras individuais, sen espazos");
        }
        int i = 0;
        String[] palabras = s.replaceAll("[^0-9a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]", "").split("\\s+"); //Pedinllo á IA porque é complicado escribilo
        //Basicamente, limpamos calquer caracter que non sexa unha letra ou número (signos de puntuación principalmente) e separamos por espazos
        for(String q : palabras){
            if(q.equalsIgnoreCase(p)){
                i++;
            }
        }
        return i;
    }

    @Override
    public String mais(String s) {
        if(s == null || s.trim().isEmpty()){
            throw new IllegalArgumentException("O texto analizado non pode estar baleiro");
        }
        Map<String, Integer> rep = new HashMap<>();
        String[] palabras = s.replaceAll("[^0-9a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]", "").toLowerCase().split("\\s+"); //Neste caso, pasamos a minúsculas para ignorar calquer diferencia nese sentido
        if (palabras.length == 0 || palabras[0].isEmpty()) {
            throw new IllegalArgumentException("Non se atoparon palabras válidas no texto");
        }
        for (String p : palabras) {    //Ao usar un hash map, a mesma string irá ao mesmo índice en caso de repetirse en máis dunha ocasión
            rep.put(p, rep.getOrDefault(p, 0) + 1);   //Usando get or default aforrámonos a inicialización e asegurámonos que non hai problemas por isto
        }
        String mais = palabras[0]; //Se hai un empate de varias, quedamos ca primeira delas
        int maxRep = 0;
        for(Map.Entry<String,Integer> pal : rep.entrySet()){
            if(pal.getValue()>maxRep){
                maxRep = pal.getValue();
                mais = pal.getKey();
            }
        }
        return mais;
    }

    @Override
    public String menos(String s) {
        if(s == null || s.trim().isEmpty()){
            throw new IllegalArgumentException("O texto analizado non pode estar baleiro");
        }
        Map<String, Integer> rep = new HashMap<>();
        String[] palabras = s.replaceAll("[^0-9a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]", "").toLowerCase().split("\\s+");
        if (palabras.length == 0 || palabras[0].isEmpty()) {
            throw new IllegalArgumentException("Non se atoparon palabras válidas no texto");
        }
        for (String p : palabras) {    //Ao usar un hash map, a mesma string irá ao mesmo índice en caso de repetirse en máis dunha ocasión
            rep.put(p, rep.getOrDefault(p, 0) + 1);   //Usando get or default aforrámonos a inicialización e asegurámonos que non hai problemas por isto
        }
        String menos = palabras[0]; //Se hai un empate de varias, quedamos ca primeira delas
        int minRep = Integer.MAX_VALUE;
        for(Map.Entry<String,Integer> pal : rep.entrySet()){
            if(pal.getValue()<minRep){
                minRep = pal.getValue();
                menos = pal.getKey();
            }
        }
        return menos;
    }

    @Override
    public String reemp(String s, String p, String n) {
        if(s == null || p == null || !s.contains(p)){
            return s;
        }
        return s.replaceAll(p,n);
    }
}
