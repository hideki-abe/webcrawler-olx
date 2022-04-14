# webcrawler-olx - LUCAS HIDEKI ABE

<h3> Projeto Introdutório trilha (K1-T9): Web Crawler </h3>
O projeto é do programa de Aceleração da ZG Soluções que consistia no aprendizado de Web Crawler.

Para este projeto, seu objetivo é desenvolver um Web Crawler para coletar dados do site OLX. Deve ser criado um bot/script para automatizar o seguinte processo:

    1. Entrar no site da OLX;
    2. Pegar os dados das primeiras 3 páginas do produto abaixo ordenado por menor preço:
        Nome: iPhone 11
        Região: Goiás
        DDD: 62 - Grande Goiânia e Anápolis
    3. Extrair as seguintes informações do anúncio: título, valor, endereço e URL do anúncio;
    4. Calcular o valor médio dos iPhones do conjunto de dados do passo 3;
    5. Remover todos os produtos que estiverem acima da média calculada no passo 4;
    6. Gerar um arquivo CSV e inserir os produtos que restaram no passo 5, com as colunas: título do anúncio, valor, endereço, URL do anúncio.

Para executar o programa, basta rodar o arquivo Main que o arquivo com a lista de iphones com preços abaixo da média será criado.
