package br.com.fiap.pokedex.dao;

import br.com.fiap.pokedex.to.PokemonTO;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

public class PokemonDAO {

    private Connection conectar() throws SQLException {
        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        if (url == null || usuario == null || senha == null) {
            Dotenv dotenv = Dotenv.load();

            url = dotenv.get("DB_URL");
            usuario = dotenv.get("DB_USER");
            senha = dotenv.get("DB_PASSWORD");
        }

        return DriverManager.getConnection(url, usuario, senha);
    }

    public ArrayList<PokemonTO> listar() throws SQLException {
        ArrayList<PokemonTO> pokemons = new ArrayList<>();

        String sql = "SELECT * FROM POKEMON ORDER BY CODIGO";

        try (Connection conexao = conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {
                PokemonTO pokemon = new PokemonTO();

                pokemon.setCodigo(resultado.getInt("CODIGO"));
                pokemon.setNome(resultado.getString("NOME"));
                pokemon.setTipo(resultado.getString("TIPO"));
                pokemon.setAltura(resultado.getDouble("ALTURA"));
                pokemon.setPeso(resultado.getDouble("PESO"));

                Date data = resultado.getDate("DATA_DE_CAPTURA");
                if (data != null) {
                    pokemon.setDataDeCaptura(data.toLocalDate());
                }

                pokemons.add(pokemon);
            }
        }

        return pokemons;
    }

    public PokemonTO buscarPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM POKEMON WHERE CODIGO = ?";

        try (Connection conexao = conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, codigo);

            try (ResultSet resultado = comando.executeQuery()) {
                if (resultado.next()) {
                    PokemonTO pokemon = new PokemonTO();

                    pokemon.setCodigo(resultado.getInt("CODIGO"));
                    pokemon.setNome(resultado.getString("NOME"));
                    pokemon.setTipo(resultado.getString("TIPO"));
                    pokemon.setAltura(resultado.getDouble("ALTURA"));
                    pokemon.setPeso(resultado.getDouble("PESO"));

                    Date data = resultado.getDate("DATA_DE_CAPTURA");
                    if (data != null) {
                        pokemon.setDataDeCaptura(data.toLocalDate());
                    }

                    return pokemon;
                }
            }
        }

        return null;
    }

    public void cadastrar(PokemonTO pokemon) throws SQLException {
        String sql = "INSERT INTO POKEMON (NOME, TIPO, ALTURA, PESO, DATA_DE_CAPTURA) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexao = conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, pokemon.getNome());
            comando.setString(2, pokemon.getTipo());
            comando.setDouble(3, pokemon.getAltura());
            comando.setDouble(4, pokemon.getPeso());

            if (pokemon.getDataDeCaptura() != null) {
                comando.setDate(5, Date.valueOf(pokemon.getDataDeCaptura()));
            } else {
                comando.setNull(5, java.sql.Types.DATE);
            }

            comando.executeUpdate();
        }
    }

    public boolean atualizar(int codigo, PokemonTO pokemon) throws SQLException {
        String sql = "UPDATE POKEMON SET NOME = ?, TIPO = ?, ALTURA = ?, PESO = ?, "
                + "DATA_DE_CAPTURA = ? WHERE CODIGO = ?";

        try (Connection conexao = conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, pokemon.getNome());
            comando.setString(2, pokemon.getTipo());
            comando.setDouble(3, pokemon.getAltura());
            comando.setDouble(4, pokemon.getPeso());

            if (pokemon.getDataDeCaptura() != null) {
                comando.setDate(5, Date.valueOf(pokemon.getDataDeCaptura()));
            } else {
                comando.setNull(5, java.sql.Types.DATE);
            }

            comando.setInt(6, codigo);

            return comando.executeUpdate() > 0;
        }
    }

    public boolean excluir(int codigo) throws SQLException {
        String sql = "DELETE FROM POKEMON WHERE CODIGO = ?";

        try (Connection conexao = conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, codigo);

            return comando.executeUpdate() > 0;
        }
    }
}