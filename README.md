# Análise de Dados Históricos da BOVESPA (1994-2020)

Este projeto é focado na análise e processamento de dados históricos da BOVESPA, com o objetivo de realizar transformações, filtragens e ordenações dos dados de ações negociadas entre os anos de 1994 e 2020. O projeto utiliza diversos algoritmos de ordenação para a análise de desempenho, considerando diferentes cenários (melhor, médio e pior caso).

## Funcionalidades

- **Transformação de datas**: Converte o formato de data de YYYY-MM-DD para DD/MM/YYYY.
- **Filtragem de registros**: Filtra o maior volume negociado por dia e também os registros com volume acima da média diária.
- **Ordenação de dados**: Ordena as ações com base em três critérios: 
  - Ticker (nome do papel negociado)
  - Volume negociado
  - Variações diárias (diferença entre o maior e menor valor do dia)
- **Análise de desempenho**: Implementação de diversos algoritmos de ordenação (Insertion Sort, Selection Sort, etc.) e geração de arquivos de saída para cada cenário (melhor, médio, pior caso).

## Estrutura do Projeto

- `b3stocks_T1.csv`: Arquivo com as datas transformadas.
- `b3stocks_F1.csv`: Arquivo com o maior volume negociado por dia.
- Arquivos de ordenação gerados para cada algoritmo e cenário:
  - Exemplo: `b3stocks_ticker_insertionSort_medioCaso.csv`

## Requisitos

- Java 8 ou superior
- Maven 3.6.0 ou superior

## Instalação e Configuração

1. Clone este repositório:

```bash
git clone https://github.com/seu-usuario/projeto-bovespa.git
```

2. Acesse o diretório do projeto:

```bash
cd projeto-bovespa
```

3. Compile e instale as dependências usando Maven:

```bash
mvn clean install
```

4. Execute o projeto:

```bash
mvn exec:java -Dexec.mainClass="com.B3.application_layer.Main"
```

## Estrutura dos Dados

A base de dados utilizada contém as seguintes colunas:

- `datetime`: Data da transação (no formato YYYY-MM-DD, a ser convertido para DD/MM/YYYY).
- `ticker`: Código da ação negociada.
- `open`: Valor de abertura do dia.
- `close`: Valor de fechamento do dia.
- `high`: Maior valor no dia.
- `low`: Menor valor no dia.
- `volume`: Volume negociado.

## Dependências

O projeto utiliza a biblioteca **oshi-core** para monitoramento de desempenho e coleta de métricas de hardware, que está especificada no arquivo `pom.xml`:

```xml
<dependency>
    <groupId>com.github.oshi</groupId>
    <artifactId>oshi-core</artifactId>
    <version>6.6.4</version>
</dependency>
```

## Relatório Completo

Para mais detalhes sobre a implementação, algoritmos de ordenação utilizados, e análise de desempenho, consulte o relatório completo neste [link](https://github.com/TarcioDiniz/B3StructAnalyzer/blob/master/src/main/resources/Bolsa%20de%20Valores3.0.pdf) ou no arquivo `src/main/resources/Bolsa de Valores1.0.pdf`.
