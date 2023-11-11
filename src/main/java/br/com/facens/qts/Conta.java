package br.com.facens.qts;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma conta bancária e possui
 * os testes com métodos baseados no BDD.
 * @author Nicolas Frederico
 * @version 0.1.0
 * @since 0.1.0
 */
public class Conta {
    private int saldo;
    private boolean isEspecial;
    private List<Integer> transacoes = new ArrayList<Integer>();

    /**
     * Método que define o usuário como não especial e seu
     * respectivo saldo.
     * @param saldo Valor do saldo da conta.
     */
    @Given("Um cliente especial com saldo de {int} reais")
    public void um_cliente_especial_com_saldo_de_reais(Integer saldo) {
        this.isEspecial = true;
        this.saldo = saldo;
    }

    /**
     * Método que chama a operação de sacar.
     * @param saque Valor a ser sacado.
     * @see Conta#sacar(int)
     */
    @When("for solicitado um saque no valor de {int} reais")
    public void for_solicitado_um_saque_no_valor_de_reais(Integer saque) {
        this.sacar(saque);
    }

    /**
     * Método que verifica se o saldo atualizado está correto
     * após a chamada do método responsável por sacar.
     * @param saldo Valor do saldo da conta.
     */
    @Then("deve efetuar o saque e atualizar o saldo da conta para {int} reais")
    public void deve_efetuar_o_saque_e_atualizar_o_saldo_da_conta_para_reais(Integer saldo) {
        assert this.saldo == saldo;
    }

    /**
     * Método que verifica se o usuário e cliente especial.
     */
    @Then("check more outcomes")
    public void check_more_outcomes() {
        assert this.isEspecial;
    }

    /**
     * Método que define um cliente comum e seu respectivo saldo na conta.
     * @param saldo Valor do saldo da conta.
     */
    @Given("Um cliente comum com saldo atual de {int} reais")
    public void um_cliente_comum_com_saldo_atual_de_reais(Integer saldo) {
        this.isEspecial = false;
        this.saldo = saldo;
    }

    /**
     * Método que chama a operação de sacar.
     * @param saque Valor a ser sacado.
     * @see Conta#sacar(int)
     */
    @When("solicitar um saque de {int} reais")
    public void solicitar_um_saque_de_reais(Integer saque) {
        this.sacar(saque);
    }

    /**
     * Método que assegura que o saque não foi realizado
     * devido à regra de negócio.
     * @see Conta#transacoes
     */
    @Then("não deve efetuar o saque e deve retornar a mensagem de Saldo Insuficiente")
    public void não_deve_efetuar_o_saque_e_deve_retornar_a_mensagem_de_saldo_insuficiente() {
        assert this.transacoes.isEmpty();
    }

    /**
     * Método que saca o valor solicitado da conta.
     * @param valor O valor do saque a ser realizado.
     */
    public void sacar(int valor) {
        if (this.podeRealizarSaque(valor)) {
            this.saldo -= valor;
            this.transacoes.add(valor);
        } else {
            System.out.println("Saldo insuficiente");
        }
    }

    /**
     * Método que verifica se o usuário está habilitado a
     * sacar a quantia solicitada.
     * @param valor
     * @return
     */
    public boolean podeRealizarSaque(int valor) {
        return this.isEspecial || this.saldo >= valor;
    }
}
