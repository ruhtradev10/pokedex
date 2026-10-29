
package br.com.fiap.pokedex.resource;

import br.com.fiap.pokedex.bo.PokemonBO;
import br.com.fiap.pokedex.to.PokemonTO;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/pokemons")
public class PokemonResource {

    private PokemonBO pokemonBO = new PokemonBO();

    @GetMapping
    public ResponseEntity<ArrayList<PokemonTO>> listar() {
        try {
            return ResponseEntity.ok(pokemonBO.listar());
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<PokemonTO> buscarPorCodigo(@PathVariable int codigo) {
        try {
            PokemonTO pokemon = pokemonBO.buscarPorCodigo(codigo);

            if (pokemon == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(pokemon);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody PokemonTO pokemon) {
        try {
            pokemonBO.cadastrar(pokemon);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Pokemon cadastrado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao cadastrar Pokemon.");
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<String> atualizar(
            @PathVariable int codigo,
            @Valid @RequestBody PokemonTO pokemon) {
        try {
            boolean atualizado = pokemonBO.atualizar(codigo, pokemon);

            if (!atualizado) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Pokemon atualizado com sucesso!");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar Pokemon.");
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> excluir(@PathVariable int codigo) {
        try {
            boolean excluido = pokemonBO.excluir(codigo);

            if (!excluido) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.noContent().build();
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
