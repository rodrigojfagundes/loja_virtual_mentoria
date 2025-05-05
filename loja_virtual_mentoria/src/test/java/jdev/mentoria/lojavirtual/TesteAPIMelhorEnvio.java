package jdev.mentoria.lojavirtual;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jdev.mentoria.lojavirtual.enums.ApiTokenIntegracao;
import jdev.mentoria.lojavirtual.model.dto.EmpresaTransporteDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class TesteAPIMelhorEnvio {

	public static void main(String[] args) throws Exception {
		
		
		
		
		
		
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/agencies?company=2&country=BR&state=SC&city=Joinville")
		  .get()
		  .addHeader("accept", "application/json")
		  .addHeader("User-Agent", "rodrigojosefagundes@gmail.com")
		  .build();

		Response response = client.newCall(request).execute();
		
		System.out.println(response.body().string());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		  
		  
		  
		  
		  
		  
		  

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
