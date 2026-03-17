# UnoVersion2.0
Aquí tienes el README actualizado, incluyendo fallas del diseño inicial y mejoras implementadas de forma clara y tranquila.
Juego UNO en Java
Descripción
Este proyecto es una implementación del juego de cartas UNO desarrollada en Java para ejecutarse en la consola.
El juego permite que un jugador compita contra la computadora, siguiendo las reglas básicas del juego original.
Esta versión es una mejora de un diseño inicial, en el cual el juego tenía una lógica más simple y algunas limitaciones en el manejo de reglas y cartas especiales.
Diseño inicial del proyecto
El diseño inicial del juego estaba compuesto por las siguientes clases:
Card → representa una carta del juego.
Deck → representa el mazo de cartas.
Hand → representa la mano de un jugador.
Game → controla la lógica del juego.
Main → inicia la ejecución del programa.
El juego ya incluía:
cartas numéricas
cartas especiales básicas
turnos entre jugador y computadora
efectos de cartas especiales
Sin embargo, el diseño inicial tenía algunas limitaciones en la lógica del juego.
Fallas del diseño inicial
Durante el desarrollo se detectaron algunos problemas en la primera versión del proyecto:
1. Falta de la regla de decir UNO
Cuando un jugador quedaba con una sola carta, el programa solo mostraba:
UNO!
pero no permitía decidir si el jugador decía UNO ni aplicaba penalización si no lo hacía.
2. Falta de penalización por no decir UNO
En el juego original, si un jugador no dice UNO cuando tiene una carta, recibe una penalización.
El diseño inicial no implementaba esta regla, por lo que el juego no seguía completamente las reglas oficiales.
3. Lógica de cambio de color repetida
La lógica para cambiar el color de las cartas COMODÍN y ROBA 4 estaba mezclada dentro del método aplicarEfecto.
Esto hacía que:
el código fuera más difícil de mantener
se repitiera lógica en el mismo método
4. Manejo limitado de cartas comodín
Las cartas ROBA 4 en el diseño inicial:
hacían que el oponente robara cartas
pero el cambio de color no estaba separado claramente en la lógica del juego.
Mejoras implementadas
Para solucionar estos problemas se realizaron varias mejoras en el diseño.
1. Implementación de la regla UNO
Se agregó una nueva variable en la clase Game:
private boolean jugadorDijoUNO = false;
Esto permite registrar si el jugador decidió decir UNO o no cuando le queda una sola carta.
2. Confirmación para decir UNO
Cuando el jugador se queda con una carta, ahora el juego pregunta:
Tienes una carta. Deseas decir UNO? (s/n)
Dependiendo de la respuesta:
si responde s, el jugador dice UNO
si responde n, el jugador no lo dice
3. Penalización por no decir UNO
Se agregó el método:
verificarPenalizacionUNO()
Este método revisa si:
el jugador tiene una sola carta
y no dijo UNO
Si ocurre esto, se aplica una penalización:
Penalizacion: robas dos cartas
Esto hace que el juego sea más fiel a las reglas originales.
4. Método para cambiar color
Se creó un nuevo método para manejar el cambio de color de cartas comodín:
cambiarColor(Card carta, boolean esJugador)
Este método tiene dos comportamientos:
Si juega el jugador
El jugador elige el color:
Elige nuevo color (rojo, azul, verde, amarillo)
Si juega la computadora
La computadora elige un color aleatoriamente.
5. Mejora en el efecto de cartas especiales
Se modificó el método aplicarEfecto para que:
ROBA 4 haga robar cartas al oponente
luego llame al método cambiarColor
Esto separa mejor la lógica del juego y hace el código más organizado y reutilizable.
Funcionamiento del juego
Cada jugador recibe 7 cartas.
Se coloca una carta inicial en la mesa.
Los jugadores se turnan para jugar cartas.
Las cartas deben coincidir por:
color
número
tipo de carta especial
El primer jugador que se quede sin cartas gana.
Cartas especiales implementadas
El juego incluye las siguientes cartas especiales:
SALTO → el siguiente jugador pierde turno.
REVERSA → cambia el orden del juego (con 2 jugadores funciona como salto).
ROBA 2 → el oponente roba 2 cartas.
COMODÍN → permite elegir un nuevo color.
ROBA 4 → el oponente roba 4 cartas y se cambia el color.
Ejecución del programa
Para ejecutar el proyecto:
Abrir el proyecto en un IDE de Java.
Ejecutar la clase:
Main
Seguir las instrucciones en la consola para jugar.
