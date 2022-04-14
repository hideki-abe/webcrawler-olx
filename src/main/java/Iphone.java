public class Iphone {

    private String titulo;
    private double valor;
    private String endereco;
    private String url;

    public Iphone() {

    }
    public Iphone(String titulo, double valor, String url) {
        this.titulo = titulo;
        this.valor = valor;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getValor() {
        return valor;
    }

    public String getUrl() {
        return url;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Iphone{" + '\n' +
                "titulo= " + titulo + '\n' +
                "valor= " + valor + '\n' +
                "endereco= " + url + '\n' +
                '}';
    }
}
