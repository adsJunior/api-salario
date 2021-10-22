# Api-salario
Uma REST API simples para fazer o cálculo do salário líquido CLT recebendo as seguintes informações: O salário bruto, número de dependentes, e o valor dos seguintes descontos: Vale Transporte, Vale Refeição, Previdência privada e Assistência médica.

O objetivo de desenvolver essa API é, por meio dela fazer uma apresentação com os conceitos básicos de testes unitários automatizados usando o JUnit 5 com o Spring Framework. 

# Dependências
Esta api foi desenvolvida usando as seguintes tecnologias:

- Java 11;
- Spring boot;
- JUnit 5.
## Recomendações
A api foi desenvolvida utilizando o Spring Tool Suite 4. Além disso, foi utilizada a extensão ECLEmma para visualizar a cobertura de testes no código implementado. O ECLEmma utiliza o Jacoco para fazer o cálculo da cobertura de código e a mostra na própria IDE, sendo uma ferramenta de auxilio muito boa para os desenvolvedores.
# Instalação
Após clonar o repositório para a sua máquina, faça a instalação das dependências utilizando o maven. Para fazer isso, basta navegar até a pasta do projeto e executar o comando:
`mvn clean install package`. Caso você utilize o Spring Tool Suite, basta apenas abrir o projeto clicando em "File" - "Import..." - procurar a opção "Existing Maven Projects", clicar em "Next", selecionar a pasta do projeto na opção "Root directory", clicar em "select all" e depois em "finish".
# Testando a api
A api está subindo na porta padrão do Spring (8080), porém isso pode ser facilmente modificado no arquivo "application.properties".

Para testar a api, ponha o projeto para executara, depois utilize alguma ferramenta para testar REST apis (como o Postman). A url a ser testada é localhost:8080/salario/{valor} onde "{valor}" é o valor numérico do salário bruto. Além disso, há alguns parâmetros a serem informados no corpo da requisição, como mencionado anteriormente. O corpo da requisição deve ser enviado com o formato JSON, e deve ter a seguinte estrutura:

`{
    "numeroDependentes": 0,
    "valeTransporte": 0.0,
    "valeAlimentacao": 0.0,
    "assistenciaMedica": 0.0,
    "previdenciaPrivada": 0.0
}`

# Testes unitários
Para fins de aprendizado, é recomendado que o desenvolvedor faça os testes unitários sozinho, por isso a branch master não possui nenhum teste implementado. Porém, a branch **feature/implementacao-testes** possui implementação dos testes unitários para o salário service com uma cobertura de testes de 81,4% em todo o projeto. Essa branch pode ser utilizada para consultas.

# Dúvidas
Em caso de dúvidas ou necessidade de ajuda, podem me procurar aqui no GitHub.
