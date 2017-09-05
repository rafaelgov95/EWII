#  Conteudo 
     * Oque é API!
     * Oque é WebServece.
     * Tipos de WSs  REST e SOAP.

### Documentação dos HTTP
    * Swaggerhub.ccom
### API (Application Programming Interface)
    - É como uma interface entre dois programas diferentes de modo que eles possam se comunicar um com
      o outro. 
    - Ou seja, é a forma que terceiros disponibilizam uma interface de modo que seja possível consumir um
      determinado serviço deles sem a preocupação da implementação do mesmo
    - As APIs podem usar qualquer meio de comunicação para iniciar a interação entre as aplicações. 
    
### Web Services (WS)
    - É uma interface projetada para se comunicar via rede.
    - Ou seja, é uma API que usa obrigatoriamente a rede.
    - Tipicamente, HTTP é o protocolo mais comumente usado para a comunicação.
    - Exemplos de serviços da Web, como meio de comunicação: SOAP, REST e XML-RPC. 
### Resumo API X WEBSERCES
    - Todos os WS são API, mas nem todas as API são WS.
    - WS podem não executar todas as tarefas que uma API normalmente realiza (ou pode realizar).
    - Um WS sempre precisa de uma rede para o seu funcionamento, enquanto uma API não precisa.
    - Uma API facilita a interface direta com um aplicativo, enquanto que um WS é uma aplicação.
### Exemplos API
    - Interoperabilidade entre aplicações;
    - Necessita-se construir um sistema para melhorar os processos de sua empresa. Neste sistema é
      necessário o cadastro de usuário, informações de calendário, gestão de contatos, rotas para 
      chegar a um destino e gestão de tarefas.
    - Para desenvolver tudo isto, você vai gastar muito tempo, muito dinheiro e as chances de ter problemas são grandes.
    
    - Neste mesmo cenário, imagine:
        – Cadastro de usuário feito através da integração de Login com o Google / Facebook / Twitter
        – Calendário utilizando a API de calendário do Google
        – Gestão de contatos utilizará a API do FullContact
        – Rotas ficarão resolvidas através das integrações com o Google Maps
        – Gestão de tarefas ficará integrado com o Asana
        
    - Ou seja, reduziram-se o esforço de programação e os riscos;
    - Esta é a vantagem da API: dar poder aos sistemas consumindo os serviços já consolidados e maduros e
      evoluindo suas funcionalidades.
      
    - Com a evolução da IoT:
        - Uma seguradora pode conhecer como uma pessoa dirige seu carro (através de sensores nos freios e pneus
          , etc);
        - Um convênio médico pode saber do estado de saúde do seu conveniado, através de APIs de vestíveis como
          Nike Fuel+;
        - Sensores da Nest são capazes de detectar a velocidade com que o fogo está tomando o ambiente logo no 
          início e, com uma estratégia de API pública, pode acionar os bombeiros.  
### XML
### YAML
### JSON
### Problemática
    - O Facebook cresceu ainda mais, graças a expansão do mercado mobile e a expansão de suas funcionalidades
      através de aplicações de terceiros;
    - Portanto, necessitou-se a criação de WS
    - Há momentos em que necessitamos desenvolver WS para nossas aplicações. Duas siglas vêm à nossamente:
        – SOAP / Simple Object Access Protocol: protocolo estruturado em XML para troca de informações em uma
          plataforma descentralizada e distribuída;
        – REST / Representational State Transfer: estilo arquitetural, mais simples e mais leve, usado em 
          ambientes de maior limitação de recursos de rede e processamento;

### Arquitetura monolítica
    - Pontos Possitivos
        - O deploy é fácil de ser feito, já que o banco de dados facilmente evoluirá junto para todas as
         funcionalidades e há apenas um ponto onde o deploy precisa ser feito.
        - Não há duplicidade de código e classes necessárias entre os diferentes módulos, já que todas elas
           fazem parte da mesma unidade.
    - Pontos Negativos
        - Ponto único de fallha: caso ocorra algum erro no cadastro de funcionários que deixe o sistema fora do 
          ar, isso vai levar junto todo o sistema.
        - A base de código se torna muito extensa, podendo deixar novos membros do projeto menos produtivos por
          algum tempo, já que a complexidade do código é bem maior.
    - Estrutura      
        - Portanto, os softwares eram construídos de forma monolítica, como grandes peças de softwares, depois
          foram migrados para uma estratégia SOA, mas ainda tiveram problemas. Agora estão migrando para
          microserviços.
        - O objetivo dos microserviços é desenvolver sistemas que sejam mais flexíveis, escaláveis e com 
          manutenção mais simples do que as arquiteturas de sistemas monolíticas normalmente utilizadas.
### Arquitetura Micro-servicos
    - Pontos Possitivos
        - Disponibilização de novos processos ou serviços sem impacto nos processos e serviços existentes.
        - Alterações em processos e serviços sem a necessidade de parada de todo o sistema.
        - Otimização da utilização da infraestrutura de nuvem.
        - Redução da complexidade de manutenção.
        - Manutenção e evolução dos serviços mais estáveis: os desenvolvedores tratarão de códigos que executam
          uma única função, e cada serviço individual não cresce indefinidamente com o crescimento do sistema.
        - Compatibilidade com diversos sistemass
        - Serviços com baixo nível de acoplamento e interdependência: dessa forma, a manutenção em um serviço
          não interfere diretamente em outras funcionalidades do sistema.
        - Escalabilidade do sistema: é obtida com o deploy e replicação de microserviços através da
          infraestrutura de servidores, máquinas virtuais e containers de forma independente. Isso torna
          o crescimento e a possibilidade de adaptação do sistema muito mais flexível
        - Redução de custos: como cada aplicação só utiliza os serviços de que necessita, os custos são 
          diretamente associados à funcionalidade e à carga de uso do sistema, não sendo necessário carregar
          custos associados a funcionalidades não utilizadas.
        - Flexibilidade de tecnologia: não é necessário amarrar os desenvolvedores a uma tecnologia específica
          , pois há baixo acoplamento entre os serviços. Dessa forma, pode ser utilizada a melhor tecnologia para
          atender a cada caso, além da possibilidade de evoluir o sistema continuamente, diminuindo o risco da
          obsolescência tecnológica.
        - Facilidade de colocar alterações em produção: as mudanças no sistema são feitas através das
          alterações e evoluções feitas nos serviços. Assim, não existe um sistema que precisa ser 
          reinicializado para continuar funcionando. O time de desenvolvimento que precisará acompanhar
          a mudança será o time responsável pelos serviços que estão sendo alterados.
        - Aclopamento 
           - Classes não depende de outra
        - Coesão 
           - Reliazar pocas resposabilidades.
         - Escalabidade
            - Flexivo
            
    - Pontos Negativos
        - Ao longo do tempo o sistema vai crescendo e tornando-se cada vez mais complexo, consumindo cada vez
          mais recursos. Desafios:
            - Aumento de complexidade e tamanho ao longo do tempo: o sistema torna-se tão complexo que a 
              manutenção fica cada vez mais cara e lenta, pois os desenvolvedores têm que navegar em uma 
              infinidade de código.
            - Alta dependência de componentes de código: muitas funções são interdependentes e entrelaçadas,
              de forma que a inclusão ou manutenção de componentes do sistema podem causar inconsistências ou 
              comportamentos inesperados.
            - Escalabilidade do sistema é limitada: exige que todo o sistema seja replicado mesmo que apenas parte
              de sua funcionalidade seja necessária na nova instância, ocasionando custos maiores que o esperado.
            - Falta de flexibilidade: exige que os desenvolvedores fiquem amarrados à tecnologia originalmente 
              escolhida para o sistema, mesmo que em algumas situações não seja a melhor escolha.
            - Dificuldade para colocar alterações em produção: qualquer mudança, por menor que seja, requer a
              reinicialização do sistema, incorrendo em riscos operacionais e necessitando de acompanhamento da
              equipe de desenvolvimento, de testes e de manutenção do sistema.
              
    - A arquitetura de microserviços é utilizada para desenvolver uma aplicação como um conjunto de pequenos 
      serviços, cada um funcionando em seu próprio processo. Cada serviço é desenvolvido em torno de um conjunto
      de regras de negócio específico, e é implementado de forma independente.   
### Fatores Micro-Serviços 
    - Relevancia
        - Fatores relevantes para adotar a arquitetura de microserviços:
            – Na análise de Martin Fowler, um sistema complexo que utiliza microserviços tem um custo de 
            manutenção menor do que o de aplicações com arquitetura monolítica, como exemplificado no gráfico
            a seguir:
        - Entretanto, uma das barreiras para a adoção de uma arquitetura baseada em microserviços pode ser o 
          maior custo na entrada, comparado com o custo de um sistema monolítico. Eventualmente existe a 
          necessidade de um esforço extra para a gestão dos microserviços que deve ser considerado.    
        - Os grandes players no mercado podem ver isso como uma oportunidade de fornecer ferramentas que
          auxiliem os times na gestão dos microserviços, de forma a diminuir a barreira de entrada para a
          adoção dessa arquitetura.
        - E há riscos em adotar a arquitetura de microserviços? Tratando-se de sistemas complexos, sempre 
          há riscos para qualquer arquitetura que seja escolhida. Para o caso de microserviços, é preciso
          atenção a fatores como:
            - Aumento da complexidade da coordenação.
            - Comunicação entre os microserviços.
            - Governança.
        - Portanto, para obter sucesso na adoção da arquitetura, esses fatores devem ser be planejados, 
          e sua adequada execução deve passar a fazer parte da agenda da área de tecnologia da organização.
          Faz parte do papel do arquiteto de software tomar decisões!!!    
