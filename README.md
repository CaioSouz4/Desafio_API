
<h1>API Rest com autenticação JWT</h1>

API para cadastro de usuários/horas

Tecnologias Utilizadas:
<ul>
   <li><a href="https://spring.io/projects/spring-boot">SpringBoot</a></li>
   <li><a href = "https://jwt.io/">JWT</a></li>
   <li><a href ="http://www.h2database.com/html/main.html">H2 Database Engine</a></li>  
</ul>
<p>Os exemplos aqui mostrados foram realizados no servidor local  <code> localhost:8080 </code> e usando a ferramenta 
<a href="https://www.getpostman.com/">Postman</a> para realizar as requisições.</p>



<h3>Usuário</h3>
<p>A entidade Usuário possui nome, horas totais e um registro de horas, além de um ID único, Sendo assim todas as requisições esperam 
um <a href="https://www.json.org/">JSON</a> que represente um usuário.
<blockquote>Exemplo:</blockquote>

```JSON
{	
	"nome":"Caio",
	"hours":12,
	"registro":[0,8,1,3]
}
```

<h3>1. Autenticação</h3>
<p>A autenticação na API é feita na url <code>/login</code></P> espera se um JSON em uma requisição <code>POST</code> com devidas credenciais que irão gerar um token que será
utilizado nas requisições de escrita no banco de dados,o token expira em um período de 3 dias.
A API possui uma conta default tem as credenciais de "admin", "admin"</p>
<blockquote>Exemplo: </blockquote>

```JSON
{
"username":"admin",
"password":"admin"
}
```
<h4>Exemplificando Autenticação com postman</h4>
<p>Enviando com método <code>POST</code>as credenciais default</p>
<img src=images_readme/API.PNG>
<blockquote>Perceba o token gerado (destacado de vermelho) na imagem abaixo</blockquote>
<img src=images_readme/API2.PNG>

<h3>2. Requisições Permitidas sem autenticação</h3>
<p>As seguintes requisições são permitidas sem autenticação:</p>

<h4>2.1 Listar Usuários cadastrados</h4>
<p>É possível verificar quais usuários estão cadastrados através de uma requisição HTTP com método <code>GET</code> na url <code>/users</code></p>
a API retornará então uma lista com todos os usuários.

<blockquote>Exemplo de retorno:</blockquote>

```JSON
[{"id":7,"nome":"Caio","hours":79,"registro":[0,5,8,16]},{"id":8,"nome":"Virna","hours":10,"registro":[0,6,1,3]},
{"id":10,"nome":"Arlindo","hours":12,"registro":[0,8,1,3]}]

```
<h4>2.2 Listar Usuários cadastrados individualmente</h4>
<p>É possível verificar um usuário específico que esteja cadastrado, através de uma requisição HTTP com método GET na url <code>/users/{id}</code>
sendo {id} o id referente ao usuário que se espera visualizar, Exemplo: <code>/users/7</code> a  API retornará então o usuário referente ao id "7".</p>
  
<blockquote>Exemplo de retorno:</blockquote>

```JSON
{"id":7,"nome":"Caio","hours":79,"registro":[0,5,8,16]}

```

<h4>2.3 Verificar registro de horas de determinado usuário</h4>
<p>É possível verificar o registro de horas de um usuário específico que esteja cadastrado, através de uma requisição HTTP com método GET na url <code>/registro/{id}</code>
sendo {id} o id referente ao usuário que se espera visualizar o registro, Exemplo: <code>/users/7</code> a  API retornará então o registro referente ao usuário com id "7".</p>
  
<blockquote>Exemplo de retorno:</blockquote>

```JSON
[0,5,8,16]
```

<h3>3. Requisições com escrita no banco</h3>
<p>As requisições de escrita no banco de dados necessitam autenticação via JWT como explicado no tópico 1</p>

<h4>3.1 Cadastro de usuário</h4>
<p>O cadastro de usuário é realizado com requisição HTTP com método <code>POST</code> na url <code>/cadastrar</code> enviando um JSON no formato
de um usuário, OBS: Se o registro não for passado no JSON o usuário receberá registro "null", se as horas totais não forem repassadas o usuário
receberá "0".

<h4>3.2 Deletar usuário</h4>
<p>O usuário poderá ser deletado com requisição HTTP com método <code>DELETE</code> na url <code>/users/{id}</code> onde o id é referente 
ao usuário que deseja-se excluir.

<h4>3.3 Cadastrar diretamente nas horas totais</h4>
<p>O cadastro de horas diretamente nas horas totais é realizado com requisição HTTP com método <code>PUT</code> na url <code>/users/{id}</code> onde o id é referente 
ao usuário que receberá as horas, é necessário repassar um JSON com o formato de um usuário mas somente as horas totais irão ser utilizadas
nessa requisição adicionando as horas repassadas às horas que o usuário já possua e ao registro do mesmo.

<h4>3.4 Cadastrar horas no registro do usuário</h4>
<p>O cadastro de horas no registro é realizado com requisição HTTP com método <code>PUT</code> na url <code>/registro/{id}</code> onde o id é referente 
ao usuário que receberá as horas no registro, é necessário repassar um JSON com o formato de um usuário mas somente o registro será utilizado
nessa requisição, adicionando as horas repassadas às horas totais que o usuário já possua, e ao registro do mesmo.


 





