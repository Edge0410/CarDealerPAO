# Auto & Moto Dealer w/ Interactive Menu - Programare Avansata pe Obiecte

Proiect realizat pentru PAO - Sandu Eduard Alexandru grupa 234

## How to use?

Acest proiect a fost realizat cu scopul de a simula un dealer de masini si motociclete - printre facilitatile permise de aplicatie se numara:

- Adaugarea unei masini (Care poate fi, in particular, SEDAN/HATCHBACK/SUV)
- Adaugarea unei motociclete (Care poate fi, in particular, CRUISER/SPORTBIKE/MOTORBIKE)
- Eliminarea unei masini
- Eliminarea unei motociclete
- Adaugarea unui motor (Diesel/Benzina)
- Eliminarea unui motor
- Adaugarea unui motor la un sasiu de masina
- Vizualizarea masinilor
- Vizualizarea motocicletelor
- Vizualizarea motoarelor

Ierarhia claselor folosite este urmatoarea:
```
** Vehicle Interface ** -> Car abstract class -> Sedan / Hatchback / SUV clase derivate
                        -> Motorcycle abstract class -> Cruiser / Sportbike / Motorbike clase derivate
** Engine abstract class ** -> PetrolEngine / DieselEngine clase derivate
```

Aplicatia prezinta si un meniu interactiv pentru a fi usor de folosit de un alt utilizator.
Spre exemplu, aplicatia la pornire prezinta un meniu in felul urmator:

![image](https://user-images.githubusercontent.com/64250100/230765413-124dfe2d-3091-4a6c-8dd9-13419c4e20aa.png)

Daca dorim, spre exemplu, sa adaugam o masina, apasam tasta 1 si introducem datele cerute - daca datele sunt invalide (tipuri de date
gresite, valori nenominale - spre exemplu 5000 de cai putere la o masina sau anul fabricatiei 1600) atunci va aparea un mesaj de informare.

![image](https://user-images.githubusercontent.com/64250100/230765504-25ad4481-2121-4519-a258-121295b659bf.png)

Putem apoi vizualiza toate obiectele create foarte simplu (spre exemplu, apasam 6 pentru a vizualiza masina proaspat adaugata)

![image](https://user-images.githubusercontent.com/64250100/230765532-2eeee79d-2fb8-45b1-858d-e16569149bf4.png)

Pentru a adauga un motor la aceasta masina, apasam tasta 3 pentru a crea motorul, introducem datele necesare apoi apasam tasta 8 -
astfel prin operatii de acest gen putem gestiona orice combinatie de motoare cu masinile pe care le avem in stocul magazinului.

