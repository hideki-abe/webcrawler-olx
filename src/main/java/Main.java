import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String CSV_PATH = "lista_de_iphones.csv";

    public static void main(String[] args) throws IOException {

        ArrayList<Iphone> iphones = new ArrayList<>();

        String url1 = "https://go.olx.com.br/grande-goiania-e-anapolis?q=Iphone%2011";
        String url2 = "https://go.olx.com.br/grande-goiania-e-anapolis?o=2&q=Iphone%2011";
        String url3 = "https://go.olx.com.br/grande-goiania-e-anapolis?o=3&q=Iphone%2011";

        iphones.addAll(olxIphones(url1, iphones));
        iphones.addAll(olxIphones(url2, iphones));
        iphones.addAll(olxIphones(url3, iphones));

        //System.out.println(iphones);
        double media = calculaMedia(iphones);
        System.out.println(calculaMedia(iphones) + " Size da lista original: " + iphones.size());
        ArrayList<Iphone> maisBaratos = retiraAcimaDaMedia(iphones);
        System.out.println("Size da nova lista: " + maisBaratos.size());
        System.out.println(maisBaratos);

        persistencia(maisBaratos);

    }

    public static ArrayList<Iphone> olxIphones(String url, ArrayList<Iphone> list) throws IOException {

        Document doc = Jsoup.connect(url).get();

        Elements iphones = doc.getElementsByAttributeValueContaining("class", "sc-1fcmfeb-2 fvbmlV");

        iphones.forEach(iphone -> {

            Elements a = iphone.getElementsByTag("a");

            String nome = iphone.getElementsByAttributeValueContaining("class", "kgl1mq-0 iYdPim sc-ifAKCX jyXVpA").html();
            String valor = iphone.getElementsByAttributeValueContaining("class", "m7nrfa-0 cjhQnm sc-ifAKCX kaNiaQ").html();
            String endereco = iphone.getElementsByAttributeValueContaining("class", "sc-1c3ysll-1 flPYFW sc-ifAKCX cmFKIN").html();
            String urlIphone = a.attr("href");

            String valorFormatado = valor.replaceAll("[^0-9]", "");

            if(nome != null && !valorFormatado.isEmpty()){
                Iphone ip = new Iphone();
                ip.setTitulo(nome);
                ip.setValor(Double.parseDouble(valorFormatado));
                ip.setEndereco(endereco);
                ip.setUrl(urlIphone);
                list.add(ip);
            }

        });


        return list;
    }

    public static double calculaMedia(ArrayList<Iphone> list){
        double soma = 0;
        double media = 0;

        for (Iphone iphone : list) {
            soma += iphone.getValor();
        }

        media = soma/(list.size());

        return media;

    }

    public static ArrayList<Iphone> retiraAcimaDaMedia(ArrayList<Iphone> list){

        double media = calculaMedia(list);

        ArrayList<Iphone> clone = new ArrayList<>();

        for (Iphone iphone : list) {
            if(iphone.getValor() < media){
                clone.add(iphone);
            }
        }

        return clone;
    }

    public static void persistencia(ArrayList<Iphone> list){
        try{
            System.out.println("Iniciando geração do CSV");
            FileWriter fw = new FileWriter(new File(CSV_PATH));
            CSVWriter cw = new CSVWriter(fw);

            String[] headers = {"titulo", "valor", "endereco", "url"};

            List<String[]> data = new ArrayList<String[]>();
            data.add(headers);
            list.forEach(celular -> {
                String valor = String.valueOf(celular.getValor());
                String[] item = {celular.getTitulo(), valor, celular.getEndereco(), celular.getUrl()};
                data.add(item);
            });

            cw.writeAll(data);
            cw.close();
            fw.close();


            System.out.println("Escrita de arquivo finalizada!");

        } catch (Exception e){
            e.printStackTrace();
        }

    }



}
