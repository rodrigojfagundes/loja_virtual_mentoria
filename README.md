# loja_virtual_mentoria

#### Desenvolvimento completo do back-end de um marketplace utilizando Java e Spring Boot, permitindo o cadastro de lojas virtuais com seus fornecedores, estoques e produtos. Além disso, é possível se cadastrar como cliente e realizar compras nessas lojas virtuais, devido às integrações com APIs de logística (Melhor Envio) e pagamentos (Asaas), com deploy realizado na Amazon AWS.

#### Swagger OpenAPI: https://api2.lojavirtualrodrigo.com.br/loja_virtual_mentoria/swagger-ui/index.html
#### para estudos versão com comentarios: https://github.com/rodrigojfagundes/loja_virtual_mentoria_comentado

## Backend
- Java 11
- Spring Boot 2.5.6
- Spring Data JDBC
- Spring Data JPA/Hibernate
- Spring Boot Starter Web
- Spring Security
- Spring Boot Starter Test
- JUnit
- Mockito
- FlyWay DB
- Spring Thymeleaf
- Integração com API's
  - Receita WS
  - Melhor Envio
  - Juno API (desativada)
  - Asaas  
- PostgreSQL (9.5 e 14)
 


## 1 - Diagrama UML 
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/1-Diagrama%20UML.png" />

## 2 -  Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/2-Swagger.png" />

## 3 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/3-Swagger.png" />

## 4 -  Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/4-Swagger.png" />

## 5 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/5-Swagger.png" />

## 6 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/6-Swagger.png" />

## 7 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/7-Swagger.png" />

## 8 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/8-Swagger.png" />

## 9 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/9-Swagger.png" />

## 10 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/10-Swagger.png" />

## 11 - Swagger API
<img src="https://github.com/rodrigojfagundes/loja_virtual_mentoria/blob/main/imagens/11-Swagger.png" />





### Gerenciamento de acesso para usuário e cadastro  (Gerência o sistema – Loja)
- O sistema deve ter um usuário máster para o Desenvolvedor gerenciar a loja virtual.(Dev)
- Deve ser possível adicionar vários acessos/papel/role para o usuário (Acesso a relatório, acesso a cadastros,    acesso a nota)
- Os usuários devem ficar amarrados a empresa que trabalham.
- Os usuários devem acessar com login e senha
- A senha deve ser criptografada no banco.
- Não pode cadastrar usuário com mesmo CPF e mesmo login e mesmo e-mail.
- Senhas devem trocadas a cada 90 dias.




### Cadastro de empresa
- Ao cadastrar um nova empresa, gerar o usuário padrão para ela e enviar por e-mail.
- O cadastro inicial do usuário para empresa tem acesso full/admin
- Ao cadastrar empresa tem que validar o CNPJ e validar se já existe no banco e não gravar duplicado.
- Deve ser informado todos os dados basicos fiscais como: CNPJ, razão soccial, nome fantasia, responsável, e-email, endereço completo.
- Inscrição estadual, categoria.
- Validar inscrição estadual não gravar duplicado.



###	Cadastro de categorias de produtos
- Cadastrar a descrição da categoria
- Ter definido no banco categorias padrões.
- Nao permitir cadastrar categoria com mesma descrição



### Cadastro de fornecedor/marca
- Deve-se cadastrar o fornecedos com seus dados fiscais, CNPJ, Insricao estadual, dados de endereco, dados de contado.
- Não cadastrar fornecedor com mesmo CNPJ e mesmo nome.
- Os dados de endereço e dados fiscais devem ser buscado em API externas.



### Cadastro de produto
- Nome do produto de forma correta.
- Deve ser informado o tipo da unidade (UN, Peça, Kilo, CX, Litro)
- Nao deixar cadastrar produto com nome igual.
- Ao cadastrar o produto é obrigatório associar a uma categoria.
- Não permitir cadastrar o produto com menos de 10 letras.
- Cadastrar uma lista de imagens para o produto
- Cadastrar descrição completa que pode ser mais de 2000 mil caracteres.
- Ao cadastrar as fotos para o produto, deve ser feito o redimensionamento da imagem para  ficar com tamanho de  600x800
- Validar a imagem deve ter no máximo 1MB para fazer o upload.
- O produto deve ser associado ao fornecedor na hora do cadastro.
- Também deve ser associado a uma marca
- Cadastrar a imagem em tamanho real e também em tamanho miniatura.
- Limite mínimo de 3 imagens e no máximo 6.
- Informar a quantidade em estoque.
- Informar os gerentes/usuário do sistema que o produto está com estoque baixo.
- Poder cadastrar um quantidade de estoque baixo e/ou esgotado para dar alerta.
- Validar  o estoque antes da venda para ver se possível vender. (Na Venda)
- Poder cadastrar um video do Youtube (Link)


### Cadastro de cliente (Quem compra pelo sistema – Loja)
- Dados de endereço completo, podemos usar o para facilitar a pesquisa de CEP.
- O cliente sempre deverá ter um login e senha.
- Nome, cpf, telefone, e-mail


###	Contas a receber
- Clientes que geraram boleto
- Entra todas as vendas e valores de produtor vendidos


### Contas a pagar
- Ex: Pagar os fornecedores de produtos.
- Entra com os dados da nota fiscal do produtos.
- Associar o fornecedor.
- Campo para informar um valor total.
- Campo para informar descontos.
- Campo para informar a data de vencimento.
- Campo para informar a forma de pagamento (Cheque, pix, cartao, boleto.)

### Realizar Vendas
- O cliente informar os dados básicos, nome, cpf, e-mail, telefone.
	(Cliente de cadastrar com Facebook ou Google
- Essa atutenticao é feita com Auth2,)
-	O cliente informa o CEP e o endereço completo de entrega.
-	Pode ter a opção de infromar o cupom de desconto.
-	Obrigatorio informar o numero da casa, ou predio
-	Seleciona a forma de pagamento (Cartao, Boleto, PIX)
-	Deve ser feito o calculo do frete  de acordo com o cep do cliente.
-	O valor do frente deve ser incluído na venda.
-	Mostrar para o cliente o numero de dias que o produto demora para ser entregue.
-	Finaliza a venda.
- Dar baixo no estoque de todos os produtos comprados, após a autorização de cobrança do cartao de credito.
-	Envia e-mail para o cliente dizendo que a compra foi realizada com sucesso, enviar numero do pedido.
-	Se a venda for por boleto então o estoque deve ser baixado quando o sistema receber o pagamento do boleto.
-	O responsavel pela loja virtual recebe o e-mail de venda realizada.



### Item de venda
- Ligado com o produto
- Será salvo no banco a quantidade que foi vendida.

###	Gerenciamento/Controle de estoque
- Atualizar o estoque do produto de forma manual pela tela do cadastro de produto.
- Atualizar a quantidade de produto quando cadastrar uma nota fiscal de compra de produtos para a loja.


### Cadastro de formas de pagamento
- Cadastrar a  forma em texto (Cheque, boleto, cartao, PIX, transferencia.)


###	Envio de ofertas da loja por e-mail
- (GetResponse e-mail marketing – Até 2000 e-mail é gratis.)

###	Castrado de cupons de descontos
- No cadastro de cupom deve ser informado a descricao do cupom e o valor de desconto.

###	Histórico de compras
- Relatorio das compras de produtos feitas com o fornecedores da loja virtual.
- Intervalo de datas.



###	Relatórios de produtos vendidos
- Relatorio de produto vendidos pela loja virtual.
- Ter a opção de intervalo de datas.
- Poder informar um descricao de produto.


###	Nota fiscal do cliente
- Enviar a nota fiscal do cliente para o e-mail dele, após  ter recebido o pagamento da venda.


###	Recuperação de senha
- O cliente deve informar o e-mail e a senha deve ser enviada para ele no e-mail


###	Chat do cliente com o suporte.
- Para ter um chat profissional vamos usar o JivoChat

###	Cadastro de marca
- Deve ser informada a descrição da marca do produto

###	Relatórios de estoque baixo (Reposição)
- Poder ter o relatório por intervalo de datas
- Poder selecionar o produto para imprimir relatório

###	Devolução / Troca de produtos

###	Avaliação de produtos
- O cliente deve estar logado
- O cliente tem que ter comprado o produto realmente para poder avaliar o produto.
- O cliente pode escrever a avaliação do produto em nossa página.
- Depois que a pessao receber o produto em casa será enviado um e-mail de pedido de avaliação do produto.



###	Relatório de compra cancelada.
- Esse relatório serve para a equipe da loja virtual entrar em contato com os clientes para finalizar a compra porque se foi cancelada é porque o cliente teve problema para finalizar a compra.

###	Relatório de carrinho/checkout abandonado
- Esse relatório é para a equipe entrar em contato com o cliente para finalizar a compra

###	Integração com logística
- Vamos usar o correios ou outra API de lojistica.

###	Relatórios de produto em evidencia (Mais clicados, mais comprados, favoritos)
- Gravar a quantidade de clique que os clientes dao em cima do produto.

###	Parte de SAC
- É nescessario que mostrar os dados de contato no site para oferecer o antendimento ao Cliente
- Fluxo de caixa
-	Abertura e fechamento de caixa
-	Mapear vendas de produto por região
-	Relatórios por marcas
-	Bônus para fidelidade
-	Pesquisar sobre: Melhor Envio



