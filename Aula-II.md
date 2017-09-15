# Cliente Servidor
### Conceitos
        É uma arquitetura onde o processamento da informação é dividido em módulos ou processos distintos. Um processo é responsável pela manutenção da informação (Servidor), enquanto que outro é responsável pela obtenção dos dados (Cliente)
        
        É uma abordagem da computação que separa os processos em plataformas independentes que interagem, permitindo que os recursos sejam compartilhados enquanto se obtém o máximo de benefício de cada dispositivo diferente, ou seja, Cliente/Servidor é um modelo lógico
        
        Sistema inovador surgido nos anos 90 e muito utilizado no meio corporativo, baseado em três componentes principais: gerenciamento de banco de dados, que funcionam como servidores; redes, que funcionam como meio de transporte de dados e, finalmente, softwares para acesso aos dados: Clientes
        
        Em uma rede de computadores existem uma ou mais máquinas que atuam como servidores, disponibilizando recursos para as demais máquinas, as quais atuam como clientes.
        
### Surgimento
         Anos 90

### Servidores
        Temos servidores para arquivos, banco de dados, e outras funções, tais como: servidores de impressão, servidores web, etc.
         
        São equipamentos com maior poder de processamento e armazenamento;
        Execução contínua;
            • Recebe e responde a solicitações dos Clientes;
            • Não se comunica com outros Servidores;
            • Presta serviços distribuídos;
            • Atende a diversos Clientes simultaneamente;
            • Hardware; exige máquinas mais robustas, com grande capacidade de processamento;
            • Software: processo sempre em execução,
            • aguardando ser chamado pelo Cliente.
### Clientes
        Geralmente microcomputadores PCs ligados em rede.
            • Inicia e termina as conversações com o servidores, solicitando serviços distribuídos;
            • Não se comunica com outros Clientes;
            • Normalmente responsável pela entrada e saída de dados e comunicação com o usuário;
            • Torna a rede ‘transparente’ ao usuário;
            • Hardware: pode ser um micro simples;
            • Software: tem início e fim definido.
### Maiframes
        Maiframes são computadores de grande porte que fazem processamento de dados de forma centralizada: modelo que até os anos 90 reinava absoluto.
            • Desvantagens
                • Serviços e recursos reduzidos;
                • Centralização de dados e recursos de processamento;
                • Inflexibilidade;
                • Inversatibilidade;
                • Custos elevados.
### Serviços em Duas Camadas.
        • Apresentacao
                - Foram utilizadas no início da implantação do modelo Cliente/ Servidor. 
                - O código que gera a interface visível do programa faz parte da aplicação Cliente. Todos os 
                  formulários, menus e demais elementos visuais nele estão contidos. Então uma alteração na
                  interface do programa requer a geração de uma nova versão para todos os computadores;
                
        • Logica de Negócio
                - São as regras que definem como os dados serão acessados e processados. Toda a Lógica do Negócio
                  fica no Cliente. Quando o programa Cliente é instalado, são instaladas todas as regras de acesso
                  ao banco de dados
                  
                - É necessária uma nova versão do programa cada vez que alterações ocorrerem e as regras mudarem.
                  Também é necessário que todos os computadores que estejam com a versão anterior recebam a nova,
                  mesmo que sejam centenas ou milhares de micros.
        •  Contribuições
                - Avanços para a empresa;
        •   Limitações
                - Dificuldade de Manutenção e gerenciamento.
### Serviços em Três Camadas
        •  Retira as Regras do Negócio do cliente e as centraliza em um determinado ponto, o qual é chamado de
           servidor de aplicações:
            - O acesso ao Banco de Dados é feito através das regras contidas no Servidor de Aplicações: facilidade
              de atualização das mesmas, pois estão centralizadas em um único ponto
              
        • Caracteristicas
            • As Regras do Negócio (lógica) foram deslocadas para o Servidor de Aplicações, então ali será
              determinada a maneira como os dados serão utilizados.
            • Todo o acesso do cliente ao Banco de Dados é feito de acordo com as regras contidas no Servidor
              de Aplicações. Não é possível o acesso ao B. de Dados sem primeiramente passar pelo Servidor de
              Aplicações.. 
            • No Servidor de B. de Dados reside toda informação necessária para o funcionamento da aplicação.
            
        • Melhoramentos:
            • Atualização mais fácil das Regras do Negócio;
            • Aumento da flexibilidade, conseqüentemente;
            • Maior segurança e controle do acesso aos dados;
            • Torna-se mais fácil manter a aplicação atualizada e
            • Garantir que todos os usuários terão acesso às alterações;
            
        • Falha:
            • Continua o problema de atualização da interface de aplicação, cada vez que sejam necessárias 
              mudanças na interface. Porém, alterações nela são menos freqüentes do que as que ocorrem nas Regras
              do Negócio.
        
### Serviços em Quatro Camadas
        • Idéia Básica      
            • Retirar a apresentação do cliente e centralizá-la em um determinado ponto, o qual na maioria dos
              casos é um Servidor web.
              
        • Idéia Básica            
            • O cliente deixa de existir como um programa que precisa ser instalado em cada micro. O acesso à
              aplicação é feito através de um navegador: Internet Explorer, Netscape e Navigator.
        • Esquema básico:     
            • Cliente quer acessar a aplicação
            • Acessa o endereço da mesma através do navegador
            • Servidor de aplicações verifica se ao cliente é permitido o acesso banco de dados liberado 
            • Servidor de Aplicações determina de que maneira os dados serão utilizados
        • Resumo
            •  Cliente: Navegador;
            • Apresentação: Servidor Web, onde serão feitas as alterações de interface; 
            • Lógica (Regras do Negócio): Servidor de Aplicações, onde serão feitas as alterações nas regras
              do negócio, quando necessárias;
            • Dados: Servidor de Banco de Dados, com todas as informações necessárias.
            
        • Vantagens:
            • Serviços
            • Recursos

        • Desvantagens:
            • Complexidade
            • Comunicação
            
        • Meio Termo 
            • Protocolos;
            • Localização;
            • Descentralização;
            • Escalabilidade;
            • Integridade.
            
## ATIVIDADE 1 diferença entre os APIs e Web Services?
### Api X WebServer
    * Necessariamente nao a API nao precisa estar na Internet.
    * Todo WebServer e uma API porem uma API não um WebServer
        * WEB SERVER
              * é uma interface projetada para se comunicar via rede.
              * tipos SOAP, REST e XML-RPC
              * WebServer não implementa tudo 
              * sempre tem que ter uma rede
              * WS é uma aplicação o API faz consulta.
       * WEB API

