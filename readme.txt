Zasady takie jak tu: https://terazpoker.pl/poker-pieciokartowy-dobierany-5-card-draw/
ale bez wznoszenia ante

Aby uruchomić trzeba:
    - uruchomić poker-server (Poker\poker-server\target\poker-server-1.0-jar-with-dependencies.jar)
    - urochomić odpowiednią liczbę server-client (Poker\poker-client\target\poker-client-1.0-jar-with-dependencies.jar)

Server komunikuje się z clientem za pomocą haseł:
    - "Your turn" - zmienia tryb clienta z biernego na aktywny, czyli server wysyła pytanie i oczekuje odpowiedzi. Client zamiast odpowiedzi może użyć komendy "pass"
    - "end" - kończy tryb aktywny clienta
    - "start" - w trybie aktywnym rozpoczyna wielolinijkową wiadomość do clienta
    - "stop" - kończy wielolinijkową wiadomość