package jdev.mentoria.lojavirtual.model.dto;

public class AsaasApiPagamentoStatus {

	public static String BOLETO = "BOLETO";
	public static String CREDIT_CARD = "CREDIT_CARD";
	public static String PIX = "PIX";
	public static String BOLETO_PIX = "UNDEFINED"; /* conbrança que pode ser paga por pir, boleto e cartão */

	public static String URL_API_ASAAS = "https://api-sandbox.asaas.com/v3/";

	public static String API_KEY = "Token_API_Asaas";

}
