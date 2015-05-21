package spring.corp.framework.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import spring.corp.framework.configuracao.GerenciadorConfiguracao;
import spring.corp.framework.i18n.GerenciadorMensagem;
import spring.corp.framework.io.SerializableInputStream;

public class EmailMarketing {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String folder = GerenciadorConfiguracao.getConfiguracao("diretorio.arquivos");
		File file = new File(folder + "html-file.html"); // Arquivo html formatado...
		SerializableInputStream s = new SerializableInputStream(new FileInputStream(file));
		
		try {
			String nameSender = GerenciadorConfiguracao.getConfiguracao("name.user");
			String sender = GerenciadorConfiguracao.getConfiguracao("mail.user");
			Map<String, String> recipients = new HashMap<String, String>();
			recipients.put("alberto.cerqueira1990@gmail.com", "Alberto Cerqueira");
			GerenciadorEmail ge = GerenciadorEmail.builderInstance()
				.recipients(recipients)
				.subject("Teste - Email Marketing")
				.html(new String(s.getByte()))
				.name(nameSender)
				.from(sender)
				.build();
			Thread t = new Thread(ge);
			t.start();
			
			System.out.println(GerenciadorMensagem.getMessage("view.mensagem.enviada.com.sucesso"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(GerenciadorMensagem.getMessage(GerenciadorMensagem.ERRO_GERAL));
		}
	}
}