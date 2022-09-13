# BE-settimana-11-progetto
Progetto "Catalogo Libri" tecnologie utilizzate -> Rest, PostgreSQL, Swagger-3, SpringSecurityAdvConfig, JsonWebToken e Lombok

URL Swagger: http://localhost:8080/swagger-ui.html

Una volta giunti sulla UI di Swagger3 effettuare il login come "userName" : "user" e "password" : "user" / oppure "userName" : "admin", "password" : "admin"
(In formato Json) --> esempio:
    
    {
      "userName" : "admin",
      "password" : "admin"
    }


(è possibile effettuare il login attraverso l'endpoint: http://localhost:8080/auth/login ).
Effettuato il login copiare su un file di testo il JWT (JsonWebToken) presente nella risposta SENZA la parola "Bearer" e senza lo spazio che lo segue.
esempio Response Body del login: 
            
            {
        "type": "Bearer",
        "token":            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0NjQyNzUyOSwiZXhwIjoxNjQ3MjkxNTI5fQ.FSUXtbMOsN5Gvn9hnj6F1OICjNps9eXu6qTVJEkAE6sT9mU3VlMwnmxop9VFI2j3MxAYEh1Z1BkXYmSdPzbKEA",
        "roles": [
             "ROLE_USER",
             "ROLE_ADMIN"
                 ]
            }

JsonWebToken di Esempio: 

    eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0NjQyNzUyOSwiZXhwIjoxNjQ3MjkxNTI5fQ.FSUXtbMOsN5Gvn9hnj6F1OICjNps9eXu6qTVJEkAE6sT9mU3VlMwnmxop9VFI2j3MxAYEh1Z1BkXYmSdPzbKEA


Fare Clic sul pulsante "Authorize" posto in alto prima dei servizi REST esposti sulla UI di Swagger.
Inserire il Token (che può essere il token dello "user" [che ha solo il ruolo "ROLE_USER"] o dell'"admin" [che ha entrambi i ruoli: "ROLE_USER" e "ROLE_ADMIN"] ) 
nel campo "value" e fare click sul pulsante di conferma.

A questo punto è possibile accedere ai vari servizi REST in base al "grado di autorizzazione" che progettualmente è impostato come segue:

Lo "user" può effettuare SOLO ed esclusivamente chiamate "GET" per consultare i dati presenti sul DB (Entities "Libro, Autore, Categoria" ed Entity "Utente").
Mentre l'"admin" può effettuare TUTTE le operazioni CRUD sia sulle Entities "Libro, Autore, Categoria" che sulla Entity "Utente".

TUTTI gli endpoint http://localhost:8080/api/** sono raggiungibili solo ed esclusivamente agli utenti che hanno effettuato il login tramite apposito endpoint (specificato sopra)
mentre l'endpoint http://localhost:8080/auth/login oppure http://localhost:8080/auth/signup (con il quale è possibile registrare un nuovo utente) sono raggiungibili pubblicamente.

Traccia di esempio inserimento Libro:
   
    {
     "id": 17,
     "titolo": "Il pendolo di Foucault",
     "anno": 1988,
     "prezzo": 9.90,
     "autori": [
      {
      "id": 9,
      "nome": "Umberto",
      "cognome": "Eco"
      }
     ],
    "categorie": [
    {
      "id": 5,
      "nomeCategoria": "Giallo"
      
          }
       ]
     }



