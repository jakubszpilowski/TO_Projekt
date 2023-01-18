## Projekt - Technologie Obiektowe


**Co projekt będzie robił?**

Projekt zakłada zbudowanie aplikacji do zarządzania ruchem pociągów. Modyfikacje rozkładu jazdy, dodawanie oraz usuwanie linii (tras). 


**Z jakich wzorców będzie korzystał i w jakim celu?**

> Wzorce kreacyjne: 

- Singleton – utrzymywanie połączenie z bazą danych, instancja bazy danych.

- Fabryka abstrakcyjna - tworzenie obiektów mających ze sobą cechy, w szczególności pociągi z rozróżnieniem na ich rodzaje. 

> Wzorce behawioralne: 

- Command – interfejs aplikacji do zarządzania całą strukturą poleceń z mechanizmem UNDO/REDO. 

- Strategia - exportowanie pliku do różnych formatów (np. Bilet – PDF, DOCS, …, PDF, JPEG) 

> Wzorce strukturalne: 

- Fasada – ukrycie abstrakcji działania aplikacji przed klientem i (interfejs menu).

- DAO - wzorzec Data Access Object (DAO) jest wzorcem strukturalnym, który pozwala nam odizolować warstwę aplikacji/biznesu. Zastosowany w celu komunikacji z bazą danych i wyciąganie z niej informacji. 
