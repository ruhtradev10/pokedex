
package br.com.fiap.pokedex.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String inicio() {
        return """
                <!DOCTYPE html>
                <html lang="pt-br">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Pokédex API</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background: #202b38;
                            color: white;
                            text-align: center;
                            padding: 60px 20px;
                        }
                        .container {
                            max-width: 600px;
                            margin: auto;
                            background: #304256;
                            padding: 35px;
                            border-radius: 15px;
                        }
                        h1 { color: #ffcb05; }
                        a {
                            display: inline-block;
                            margin-top: 20px;
                            padding: 14px 25px;
                            background: #ffcb05;
                            color: #202b38;
                            text-decoration: none;
                            border-radius: 8px;
                            font-weight: bold;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Pokédex API</h1>
                        <h2>Bem-vindo à API Pokémon!</h2>
                        <p>API REST desenvolvida com Java e Spring Boot.</p>
                        <p>Consulte os Pokémon cadastrados no banco de dados.</p>
                        <a href="/pokemons">Ver Pokémon cadastrados</a>
                    </div>
                </body>
                </html>
                """;
    }
}