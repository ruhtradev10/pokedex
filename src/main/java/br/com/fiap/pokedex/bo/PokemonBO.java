
package br.com.fiap.pokedex.bo;

import br.com.fiap.pokedex.dao.PokemonDAO;
import br.com.fiap.pokedex.to.PokemonTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PokemonBO {

    private PokemonDAO pokemonDAO = new PokemonDAO();

    public ArrayList<PokemonTO> listar() throws SQLException {
        return pokemonDAO.listar();
    }

    public PokemonTO buscarPorCodigo(int codigo) throws SQLException {
        return pokemonDAO.buscarPorCodigo(codigo);
    }

    public void cadastrar(PokemonTO pokemon) throws SQLException {
        pokemonDAO.cadastrar(pokemon);
    }

    public boolean atualizar(int codigo, PokemonTO pokemon) throws SQLException {
        return pokemonDAO.atualizar(codigo, pokemon);
    }

    public boolean excluir(int codigo) throws SQLException {
        return pokemonDAO.excluir(codigo);
    }
}
