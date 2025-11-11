# Projeto Semana Vida Plena 2
A aplicação **Semana Vida Plena** simula a integração entre dois sistemas:  
**Eventos** (palestras, oficinas, shows) e **Restaurantes Parceiros** (com cardápios funcionais e combos saudáveis).  

O projeto aplica os **conceitos fundamentais da Programação Orientada a Objetos (POO)** — como encapsulamento, herança, polimorfismo e composição — para modelar o comportamento de cada área e realizar **análises de correlação** entre os dados dos participantes e restaurantes.

O sistema foi desenvolvido em **Java**, com código modular e organizado em pacotes independentes para cada domínio.


## ⚙️ Funcionalidades Principais

- Cadastro e gerenciamento de eventos (palestras, oficinas e shows).  
- Registro de participantes e presença.  
- Emissão e uso de vouchers de desconto para restaurantes parceiros.  
- Registro de pedidos e faturamento nos restaurantes.  
- Relatórios automáticos de análise cruzada entre eventos e restaurantes


## 🧠 Perguntas para Tomada de Decisão
(as respostas se encontram na classe "Analytics")

1. Quantos participantes de um determinado evento utilizaram o desconto no restaurante parceiro após o evento?

2. Qual é o restaurante parceiro mais frequentado pelos participantes dos eventos de bem-estar?

3. Em média, quanto tempo os participantes levam para visitar um restaurante parceiro depois de participar de um evento?

4. Qual evento gerou o maior número de visitas aos restaurantes parceiros?

5. Os participantes que consumiram combos funcionais nos restaurantes também participaram de mais de um evento?

6. Qual é o prato mais pedido entre os participantes de determinado evento?

7. Qual restaurante parceiro teve o maior faturamento proveniente de vouchers distribuídos em eventos?

8. Existe correlação entre o tipo de evento (palestra, oficina, show) e o tipo de refeição mais consumida no restaurante parceiro?

9. Quais eventos tiveram a maior taxa de conversão de participantes em clientes de restaurante parceiro?

## 🔧 Pré-requisitos
- **Java JDK 17** (ou superior) instalado.
- Terminal configurado com o comando `javac` e `java`.

## 🚀 Execução

1. **Clone o repositório**
   ```bash
   git clone https://github.com/CarlosDmsc/SemanaVidaPlena_Parte2
   cd SemanaVidaPlena/src

## 🧱 Conceitos de POO aplicados

Encapsulamento: todos os atributos são privados e acessados via getters e setters.

Herança: Lecture, Workshop e Show herdam de Event.

Polimorfismo: métodos genéricos que operam sobre a abstração Event.

Composição: EventManager gerencia objetos Event e Participant; RestaurantManager gerencia Restaurant e Order.

Coesão modular: cada pacote representa um domínio funcional isolado.


## 📊 Saída esperada (exemplo)

========= RELATÓRIOS =========
1) Quantos participantes usaram desconto: 4
2) Restaurante mais visitado: R002 (Vida Fit)
3) Tempo médio (horas): 6.25
4) Evento com mais visitas: E002 - Oficina: Alimentação Funcional
5) Consumidores de combos com vários eventos: [Ana]
6) Prato mais pedido: Combo Funcional A (2 pedidos)
7) Restaurante com maior faturamento via vouchers: Vida Fit (R$ 60.00)
8) Correlação tipo de evento ↔ tipo de refeição: WORKSHOP → PRATO: 2, COMBO: 3
9) Eventos com maior taxa de conversão: Oficina Alimentação Funcional → 75%


## 🧾 Licença

Projeto acadêmico desenvolvido para a disciplina de Programação Orientada a Objetos – 2025.2
Uso exclusivamente educacional.

## Informações para contato

Carlos Roberto - carlosmdev11@gmail.com
Bruno Cavalcanti - bruno.wanderley.cc@gmail.com
