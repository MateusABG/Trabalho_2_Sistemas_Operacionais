import java.util.*;
import java.io.*;

public class Simulador{

    //Tipo de operação 
    public static String tipo_operacao;
    public static List<String> tipo_operacoes=new ArrayList<String>(); 

    //Tamanho da memoria
    public static int tamanho_memoria;

    //Linhas do arquivo .txt passados para uma lista para facilitar leitura.
    public static List<String> linhas_do_texto=new ArrayList<String>(); 

    

    public static void Leitura_Arquivo()throws Exception{
        //Aqui teremos o input do usuário, informando o arquivo
        //Tanto quanto o tamanho e o tipo de operação
        Scanner teclado= new Scanner(System.in);

        System.out.println("Informe as informações pedidas abaixo:\n");

        //Aqui ele informa o nome do arquivo;
        System.out.print("Arquivo:");
        String nome_texto=teclado.next();

        System.out.println("\n Informe o tipo de operacao:");
        System.out.println("Tipos possiveis sao:");
        for(int i=0;i<tipo_operacoes.size();i++){
            System.out.println("                  -"+tipo_operacoes.get(i));
        }

        System.out.print("\nTipo de Operacao:");
        tipo_operacao=teclado.next().toUpperCase();

        if(!tipo_operacoes.contains(tipo_operacao)){ 
            System.out.println("Esse nao eh um tipo valido, por favor, insira um tipo de operacao valido:"); 
            tipo_operacao=teclado.next().toUpperCase();
        }

        //Tamanho da memória fixa/buddy/variavel
        System.out.print("Tamanho da memoria:");
        tamanho_memoria=teclado.nextInt();

        /*Aqui é a região onde todas as informações dadas são registradas */
        //Cria um arquivo de leitura com o nome dado;
        File files=new File(nome_texto+".txt"); 

        //Escaneia o texto passado;
        Scanner sc=new Scanner(files); 

        while(sc.hasNext()){ 
            //Adiciona linhas do texto dentro da lista, para leitura mais tarde
            linhas_do_texto.add(sc.nextLine());
        } 
    }


    public static void IN(String linha){
        //Para pegar as informações do comando
        String nome_processo= linha.substring(linha.indexOf("(")+1,linha.indexOf(","));
        String tamanho= linha.substring(linha.indexOf(",")+1,linha.indexOf(")")).replaceAll(" ","");
        int numero_tamanho=Integer.parseInt(tamanho);

        System.out.println("Vai ser colocado o valor-->("+nome_processo+","+numero_tamanho+")");
    }

    public static void OUT(String linha){ 
        String x= linha.substring(linha.indexOf("(")+1,linha.indexOf(")"));
        System.out.println("Vai ser retirado o valor-->"+x);
    }
    public static void Metodo_Variavel(){ 
        for(int i=0;i<linhas_do_texto.size();i++){
            String linha= linhas_do_texto.get(i);
            System.out.println(linha);
            
            if(linha.toUpperCase().contains("IN")){
                IN(linha);
            }else if(linha.toUpperCase().contains("OUT")){
                OUT(linha);
            }else{
                System.out.println("ERRO! Linha invalida, por favor verifique se nao foi colocado nenhum comando diferente de IN e OUT.\n Estamos fechando o programa...");
                System.exit(1);
            } 
        }
    }

    public static void Metodo_Fixo(){
        for(int i=0;i<linhas_do_texto.size();i++){
            String linha= linhas_do_texto.get(i);
            System.out.println(linha);
            
            if(linha.toUpperCase().contains("IN")){
                IN(linha);
            }else if(linha.toUpperCase().contains("OUT")){
                OUT(linha);
            }else{
                System.out.println("ERRO! Linha invalida, por favor verifique se nao foi colocado nenhum comando diferente de IN e OUT.\n Estamos fechando o programa...");
                System.exit(1);
            } 
        }

    }

    public static void Metodo_Buddy(){
        for(int i=0;i<linhas_do_texto.size();i++){
            String linha= linhas_do_texto.get(i);
            System.out.println(linha);
            
            if(linha.toUpperCase().contains("IN")){
                IN(linha);
            }else if(linha.toUpperCase().contains("OUT")){
                OUT(linha);
            }else{
                System.out.println("\nERRO! Linha invalida, por favor verifique se nao foi colocado nenhum comando diferente de IN e OUT.");
                System.out.println("Estamos fechando o programa...");
                System.exit(1);
            } 
        } 
    }


    public static void main(String args[]) throws Exception{

        tipo_operacoes.add("BUDDY");
        tipo_operacoes.add("FIXA");
        tipo_operacoes.add("VARIAVEIS"); 
        //Lê as informações e o arquivo de texto passado;
        Leitura_Arquivo();

        System.out.println("\nArquivos lidos com sucesso!\nComecando o processo...");

        //Aqui começamos as leituras;
        switch(tipo_operacao.toUpperCase()){
            case "BUDDY":
                Metodo_Buddy();
                break;
            case "FIXA":
                Metodo_Fixo();
                break;
            case "VARIAVEIS":
                Metodo_Variavel();
                break;
        } 
    }
}