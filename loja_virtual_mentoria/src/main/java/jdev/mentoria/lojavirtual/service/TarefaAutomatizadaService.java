package jdev.mentoria.lojavirtual.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.repository.UsuarioRepository;

@Service
public class TarefaAutomatizadaService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ServiceSendEmail serviceSendEmail;
	
	
	@Scheduled(initialDelay = 2000, fixedDelay = 86400000 )
	
	
	
	
	
	public void notificarUserTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida();
		
		for(Usuario usuario : usuarios) {
			StringBuilder msg = new StringBuilder();
			
			msg.append("Ola, ").append(usuario.getPessoa().getNome()).append("<br />");
			msg.append("Esta na hora de trocar a sua senha, ja passou 90 dias de validade").append("<br/>");
			msg.append("Troque sua senha a loja virtual do Rodrigo - JDev treinamento");
			
			
			
			serviceSendEmail.enviarEmailHtml(
					"Troca de senha", msg.toString(), usuario.getLogin());
			
			
			Thread.sleep(3000);
			
		}
		
		
	}
	
	
	
}
