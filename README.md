# Spring üzleti alkalmazások fejlesztése kötelező program

---

Ez egy Java Spring keretrendszer segítségével megvalósított blog alkalmazás. A frontend Thymeleaf segítségével készült.


## Funkciók:
1. Blog bejegyzések írása, szerkesztése, törlése
2. Blog bejegyzések listázása (kronológiai sorrendben)
3. Keresés a blogbejegyzések között:
    - alap keresés: blogpost címe alapján
    - részletes keresés: bejegyzés szövegében, szerző neve, dátum(tól-ig)
4. A blogbejegyzések tulajdonságai:
    - cím    
    - létrehozás dátuma    
    - blogbejegyzés szerzője   
    - kategória vagy kategóriák (külön entitásként kezelve)
5. A felhasználók tulajdonságai
    - név (kötelező)
    - email (kötelező)
    - jelszó(kötelező)
    - profilkép (opcionális)
    - születési dátum (opcionális)
    - aktív-e
6. A kategóriák csak egy névvel rendelkeznek
7. A blogbejegyzésekhez lehet kommenteket írni
7. A blogbejegyzéseket és a kommenteket lehet kedvelni/nem kedvelni    

## Szerepkörök:
1. Vendég:
    - olvasás
    - keresés
    - profil megtekintés
2. Regisztrált felhasználó:
    - bejegyzés írása, szerkesztése, törlése
    - bejegyzés és komment likeolása/dislikeolása
    - profil oldallal rendelkezik
3. Adminisztrátor:
    - bármit létrehozhat, módosíthat, törölhet, listázhat a felületen
    - REST API használat: alapműveletek támogatása (létrehozás, listázás, törlés, módosítás)

## További követelmények:
1. Regisztráció támogatása
2. Bejelentkezés támogatása
3. A jelszavak kódolva történő tárolása
4. Az autentikáció ne egyszerű HTTP Basic auth legyen
5. A fejlesztés során legyen legalább két profil alkalmazva:
    - dev (H2 DB-t használjon)
    - dev prod (MySQL, PostgreSQL használat)
6. Ahol van értelme ott tranzakcionális műveletek támogatása
7. Az adat réteget Spring Data technológiával oldjuk meg (Spring JPA, Spring JDBC)
8. Az összes entitáshoz tároljuk a létrehozás, utolsó módosítás dátumát és személyét
9. Az alkalmazás egy része legyen tesztelve (JUnit használatával)
