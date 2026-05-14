# 📖 Manual do Usuário - AGRUPA Back-end

## 1. Visão Geral
Este manual orienta a execução do Back-end e Banco de Dados do sistema de **Gerenciamento de Grupos (AGRUPA)**. O projeto foi desenvolvido em **Java + Spring Boot**, focado na gestão de grupos e organização de fluxo em sala de aula.

---

## 2. Estrutura do Projeto
A arquitetura segue o padrão de camadas para unificar responsabilidades:

| Pacote | Descrição | Exemplo |
| :--- | :--- | :--- |
| `config` | Configurações de infraestrutura e segurança. É onde se define como a aplicação interage com o ambiente externo. | `SecurityConfig`, `SwaggerConfig` |
| `controller` | Pontos de entrada das requisições HTTP (API). Validam parâmetros básicos e delegam a execução, não devem possuir regra de negócio. | `GrupoUsuarioController` |
| `dto` | Data Transfer Object: Classes usadas exclusivamente para trafegar dados. Servem para ocultar dados sensíveis dos models. | `GrupoInformacoesDTO` |
| `form` | Variação de DTO focada na entrada de dados (Input). Representam o corpo de uma requisição e contêm anotações de validação. | `GrupoForm` |
| `models` | Onde residem as entidades de domínio, geralmente mapeadas diretamente para as tabelas do banco de dados. | `Grupo (@Entity)` |
| `repository` | Interfaces responsáveis pela comunicação com o banco de dados. Isolam a persistência do resto da aplicação. | `GrupoRepository` |
| `resource` | Este pacote pode definir de forma variável representando também controller em APIs REST).* | - |
| `service` | O coração da aplicação. Onde as regras de negócio que definem as chamadas aos repositórios. | `GrupoService` |

---

## 3. Conceitos e Ferramentas

### 3.1 Requisições HTTP
Uma requisição HTTP é uma mensagem enviada por um cliente ao servidor. O projeto utiliza os protocolos de forma harmoniosa:
* **GET**: Busca de dados sem alteração de estado.
* **POST**: Envia dados ao servidor para criar um novo recurso.
* **PUT**: Atualiza dados que já existem dentro do servidor.
* **DELETE**: Remove dados.

### 3.2 Banco de Dados em Memória (HSQLDB)
Como a aplicação atual não necessita de armazenamento a longo prazo, utilizamos o **HSQLDB (HyperSQL Database)**. 
* **Vantagem**: Praticidade ao rodar a aplicação sem instalar banco local.
* **Uso**: Ideal para registrar dados em tempo real, gerando relatórios e criando grupos dinamicamente durante a aula.

### 3.3 Data Transfer Object (DTO)
O DTO é uma boa prática utilizada para reduzir a exposição de dados e facilitar a manipulação de informações entre o back-end e o front-end. Instanciamos uma classe apenas com os atributos necessários para envio seguro.

---

## 4. Pré-requisitos
Antes de rodar o back-end, garanta que o ambiente está configurado:
1. **Java Instalado**: Versão 25.
2. **IDE**: Verifique a versão do Java no seu ambiente (ex: no IntelliJ, acesse *Project Structure*) para evitar erros de compilação.
3. **Dependências**: Sempre atualize e baixe as dependências do Maven/Gradle ao adicionar novidades ao projeto.

---

## 5. Como Rodar o Projeto

1. **Liberação de Portas**: Certifique-se de que as portas padrão da aplicação (ex: 8080) estão liberadas na sua máquina.
2. **Execução**:
   - Localize o arquivo principal da aplicação: `Tat3dsApplication.java`.
   - Selecione este arquivo para rodar o projeto (ex: Run `Tat3dsApplication`).
