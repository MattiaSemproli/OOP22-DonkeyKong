# Donkey Kong
## oop22-donkeykong

Il progetto si pone l'obbiettivo di ricreare un videogioco platformer ispirato al famoso gioco di genere 2D arcade, Donkey Kong (classic). 

>I giochi platformer sono un sottogenere di videogiochi d'azione dove la meccanica di gioco implica principalmente l'attraversamento di livelli costituiti da piattaforme, spesso disposte su più piani.

Componenti del gruppo:
- Semproli Mattia
- Foschi Giacomo
- Di Franco Federico

L’obiettivo del gioco `e arrivare alla principessa nel minor tempo possibile,
saltando da una piattaforma all’altra e utilizzando le scale, evitando di farsi
colpire dai barili. Il giocatore possiede 3 vite.

I comandi di gioco sono i seguenti:
- **ESC** (Escape): mette in pausa durante il gioco.
- **W o **↑**: Movimento verso l’alto sulla scala.
- **A o **←**: Movimento verso sinistra.
- **S** o **↓**: Movimento verso il basso sulla scala.
- **D** o **→**: movimento verso destra
- **SPACE**: salto.
I power-ups, che spawnano una volta per partita, sono i seguenti:
- **Stella**: rende invincibili per 3 secondi
- **Gelo**: congela donkey kong dal lancio dei barili per 4 secondi
- **Cuore**: ripristina una vita
- **Scudo**: protegge dal prossimo colpo subito
Esistono due tipi di barili:
- **Barile normale**: di colore marrone, se si viene colpiti si perde una vita.
Se si viene colpiti da un barile mentre si possiede uno scudo, non si
perdono vite.
- **Barile doppio-danno**: di colore blu, se si viene colpiti si perdon due
vite. Se si viene colpiti da un barile blu mentre si possiede uno scudo,
si perde una sola vita.
