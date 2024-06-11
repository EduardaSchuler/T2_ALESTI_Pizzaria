# Trabalho 2 - Algoritmos e Estruturas de Dados I 
### 2024/01

Nesse trabalho você deve implementar um programa para simular o recebimento e processamento de pedidos em uma
pizzaria. Para fins de simplificação será utilizada uma unidade de tempo fictícia que inicia em 0 e segue de 1 em 1 até
encerrar o processamento.
Cada pedido é realizado em um instante de tempo específico e leva um determinado número de unidades de tempo para
ser processado.
A pizzaria tem apenas um pizzaiolo e ele não consegue fazer mais de uma pizza ao mesmo tempo. Ou seja, se ele estiver
preparando uma pizza o próximo pedido deve aguardar em uma fila de pedido até terminar a atual.
Os dados da simulação são fornecidos em um arquivo CSV contendo o código do pedido, o sabor da pizza, o instante de
tempo do pedido e o tempo de preparo.

No instante t=1 o pedido de 75853-Calabresa é recebido e o pizzaiolo está disponível, logo entra em produção. No mesmo
instante t=1 o pedido de 85744-Portuguesa é recebido, mas o pizzaiolo está ocupado, logo esse pedido deve entrar em
uma fila de espera.
No instante t=2 o pedido 89654-Quatro Queijos aparece e entra também na fila. A fila ainda não foi alterada pois o
pizzaiolo segue fazendo a pizza de calabresa. No instante t=3 entra mais um pedido (Pepperoni) e vai para a fila.
A produção da pizza de calabresa só termina no instante t=5 (instante de tempo que iniciou o preparo + tempo de preparo).
Nesse instante a Portuguesa sai da fila e começa a ser produzida, sendo finalizada no t=8, e assim roda a simulação.

O que deve ser implementado
A leitura do arquivo de entrada da simulação. Serão disponibilizados no moodle diferentes arquivos de simulação.
O processamento da simulação passo a passo ou contínua. O programa deve iniciar a simulação em um tempo t=0 e a
cada <ENTER> que o usuário pressionar ele avança uma unidade de tempo (um ciclo). Caso o usuário digite “C” a
simulação segue de forma contínua sem interrupção produzindo os resultados.
Cada pedido processado deve ser inserido em uma Árvore Binária de Pesquisa na ordem em que eles vão sendo
produzidos.
Os resultados finais a serem gerados são:
- Total de pedidos processados
- Total de tempo executado
- Pedido mais demorado (se tiver mais de um mostrar todos)
- Um CSV com a situação da fila a cada instante t, conforme exemplo na página anterior.
- Um CSV com o caminhamento em ordem central da ABP gerada pelos pedidos que foram sendo processados,
contendo apenas o código do pedido e separado por vírgula.
Observações:
Não pode ser usado ArrayList nem qualquer outra estrutura para manipulação de listas do Java.
