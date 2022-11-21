## Projekt - Technologie Obiektowe


**Co projekt będzie robił?**

Projekt zakłada zbudowanie aplikacji do zarządzania ruchem pociągów. Modyfikacje rozkładu jazdy, dodawanie oraz usuwanie linii (tras). 


**Z jakich wzorców będzie korzystał i w jakim celu?**

> Wzorce kreacyjne: 

- Singleton – utrzymywanie połączenie z bazą danych, instancja bazy danych. 

- Metoda wytwórcza - tworzenie obiektów mających ze sobą cechy, w szczególności pociągi z rozróżnieniem na ich rodzaje. 

> Wzorce behawioralne: 

- Command – interfejs aplikacji do zarządzania całą strukturą poleceń 

- Memento – tworzenie rozkładu będzie możliwe z operacjami undo/redo. Zatwierdzenie powoduje zapis, koniec możliwości operowania na historii.  

> Wzorce strukturalne: 

- Fasada – exportowanie pliku dla użytkownika do różnych formatów (np. Bilet – PDF, DOCS, …, PDF, JPEG) 

- Most – stosowany do podziału kodu na abstrakcję oraz implementację na przykład w przypadku abstrakcji jaką jest pociąg a implementacją jaką jest rodzaj pociągu 