
package br.com.fiap.pokedex.to;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PastOrPresent;

public class PokemonTO {

    private Integer codigo;

    @NotBlank(message = "O nome do Pokemon e obrigatorio")
    private String nome;

    @NotBlank(message = "O tipo do Pokemon e obrigatorio")
    private String tipo;

    @Positive(message = "A altura deve ser maior que zero")
    private double altura;

    @Positive(message = "O peso deve ser maior que zero")
    private double peso;

    @PastOrPresent(message = "A data de captura nao pode ser futura")
    private LocalDate dataDeCaptura;

    public PokemonTO() {
    }

    public PokemonTO(Integer codigo, String nome, String tipo, double altura, double peso, LocalDate dataDeCaptura) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.altura = altura;
        this.peso = peso;
        this.dataDeCaptura = dataDeCaptura;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getDataDeCaptura() {
        return dataDeCaptura;
    }

    public void setDataDeCaptura(LocalDate dataDeCaptura) {
        this.dataDeCaptura = dataDeCaptura;
    }
}