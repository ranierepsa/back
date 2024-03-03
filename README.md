# BACKEND
Projeto backend para desafio da Pitang.
Trata-se um sistema de manutenção de Usuários seus respectivos Carros usando Spring Boot.

##  ESTÓRIAS DE USUÁRIO
0. Criar arquivos iniciais do backend.
1. Criar CRUD de Carro.
2. Criar testes para CRUD de Carro.
3. Criar CRUD de Usuario.
4. Criar testes para CRUD de Usuario.
5. Adicionar JWT.
6. Criptografar Senha.
~~7. Adicionar opção para inserir imagem de Carro e Usuário.~~
~~8. Criar testes para imagem de Carro e Usuário.~~
9. Adicionar tratamento de exceções.

## SOLUÇÃO 
Abaixo seguem as tecnologias e bibliotecas utilizadas para este desafio e suas respectivas justificativas técnicas para o seu emprego.
 - Spring Boot 3: Spring Boot é um dos requisitos mínimos do desafio. Foi utilizada a versão 3 por ser a versão mais recente do framework.
 - JDK 17: Java Developer Kit é a tecnologia base para o desenvolvimecnto de sistemas Spring Boot. Foi utilizada esta versão por ser compatível com o Spring Boot 3.
 - JPA: JPA é um dos requisitos mínimos do desafio. Usado para definir e gerenciar o relacionamento entre as entidades definidas no Java e o banco de dados.
 - Banco de Dados H2: H2 é um dos requisitos mínimos do desafio. Usado por sua simplicidade e possibilidade de armazenamento em memória.
 - Spring Security: É a parte do Spring destinada prover meios de configuração e personalização de segurança e autenticação em sistemas Spring.
 - JJWT: O uso de JWT é um dos requisitos mínimos do desafio. É uma biblioteca de segurança usada para configurar e gerenciar tokens de usuários logados no sistemas. Foi utilizada por sua simplicidade de implementação e por já possuir experiência na mesma.
 - JUnit: Biblioteca padrão do Java para implementação de testes unitários. Utilizada por sua vasta adoção e suporte pela comunidade de desenvolvedores Java.
 - Mockito: Biblioteca importante para o auxílio de testes uma vez que é usada para mocagem. Foi utilizada para mocar a camada de repositório durante os testes.
 - BCrypt: Algoritmo presente nativamente no Spring. Foi escolhida dentre outras opções por oferecer todas as 4 características fundamentais de hashing de senhas e por ser um algorítmo de desempenho ajustável de acordo com as necessidades. Foi utilizado para encriptação das senhas dos usuários para salvar em banco e evitar que fossem salvas em plaintext.
 - Insomnia: Foi utilizado para testes de API.
 
Abaixo seguem as decisões técnicas necessárias para este desafio:
 - Foi utilizada uma estrutura em camadas para separar as responsabilidades entre elas: RestControllers, Services e Repositories.
 - Para o ID das entidades foi utilizado o UUID para que seja gerado um ID aleatório tanto para Carros tanto para Usuários e remover possíveis problemas de segurança devido à geração sequencial de IDs.
 - No que tange o tratamento de exceções: 
 	- Foi utilizado um GlobalExceptionHandler para fazer o tratamento das exceções lançadas pelos endpoints.
 	- Foi necessário fazer um Controller para que fossem capturadas as exceções lançadas dentro dos Filtros, e assim, poder retornar a estrutura de erro especificada no documento do desafio. Exceções refentes a problemas com JWT passam por esse controlador.
 	- Foram criadas exceções para cada tipo definido no documento do desafio para que pudessem ser utilizadas no GlobalExceptionHandler.
 - Foram utilizadas classes DTO para o transporte de dados específicos. Para esconder alguns dados ou exibir outros dependendo da necessidade.
 - O usuário logado é salvo na classe SecurityService, após logar com sucesso, para que possa ser acessado em outros lugares do sistema.
 - Os tokens gerados estão sem assinatura para facilitar a implementação e por não ter sido um requisito do desafio. Considerar configuração para ambiente de produção.
 - O csrf foi deixado desabilitado no filtro de segurança por simplicidade e por não ter sido um requito do desafio. Considerar habilitar para ambiente de produção.

## INSTALAÇÃO

### Pré-requisitos
Como pré-requisito para rodar o servidor de backend deste desafio é necessário ter instalado os seguintes componentes:
 - Git 2.34.1
 - JDK 17.
 - Maven 3.6.3

### Execução do Projeto
O projeto do desafio pode ser rodado de duas formas:
1. Via terminal:
 - Configurar o Java 17 como variável de ambiente: $JAVA_HOME.
 - Na raiz do projeto, executar o comando mvn spring-boo:run

2. Via Spring Tools Suite:
 - Instalar o Spring Tools Suite 4.
 - Abrir o Spring Tools Suite 4 e importar o projeto como Maven Project.
 - Clicar com o botão direito do mouse sobre o projeto > Run As > Spring Boot App.

### Observações
 - Para interagir com o sistema foi utilizado o Insomnia (similar ao Postman);
 - O servidor pode ser acessado pela URL http://localhost:8080/
 - Ao iniciar o servidor, o banco de dados inicialmente estará vazio. Sem carros nem usuários.
 - Os endpoints disponíveis para uso são os mesmos definidos no documento do desafio. Sendo precedidos por http://localhost:8080/

### Roteiro de uso
Dado que o banco de dados está vazio, recomendo os passos abaixo para poder usar as funcionalidades:
1. Criar um novo usuário via POST usando o JSON definido no documento do desafio como body da requisição: 
	URL: http://localhost:8080/api/users
2. Após a criação do Usuário, pode-se usar todos os endpoints de usuário: /api/users
3. Logar no sistema com o usuário criado: POST http://localhost:8080/api/signin
	- Enviar no body da requisição as credenciais do usuário: { "login": "hello.world", "password": "h3ll0" }
	- Essa chamada resultará em um token JWT. 
4. Para usar qualquer endpoint que precise de autenticação (/api/cars e /api/me) será necessário passar o token gerado acima.
	- Exemplo para encontrar um carro pelo id (f36f935f-123a-4049-b6fe-f2e475d30b62):  
	- GET http://localhost:8080/api/cars/f36f935f-123a-4049-b6fe-f2e475d30b62
	- Adicionar um header "Authorization" com o valor igual ao Token gerado precedido por "Bearer ".






