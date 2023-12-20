package com.br.project.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.project.form.RelatorioAtivoForm;
import com.br.project.models.Ativo;
import com.br.project.models.Usuario;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

@Service
public class RelatorioAtivoService {

	@Autowired
	private AtivoService ativoService;

	@Autowired
	private UsuarioService usuarioService;

	public byte[] relatorioDevolucao(RelatorioAtivoForm form) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Ativo ativo = ativoService.buscarAtivoPorId(form.getIdAtivo());
		Usuario usuario = usuarioService.buscarUsuarioPorId(form.getIdUsuario());
		// Cria o documento PDF
		PdfWriter writer = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(PageSize.A4); // Define a página como paisagem
		Document document = new Document(pdf);
		document.setMargins(20, 20, 20, 20);

		PdfFont font = PdfFontFactory.createFont("Times-Roman");

		document.setFont(font);

		String caminhoDaImagem = "C:\\Users\\Administrador.WIN-83ICR0J7US6\\Desktop\\logoCanto.jpg";

		// Criar um objeto Image
		Image imagem = new Image(ImageDataFactory.create(caminhoDaImagem)).setWidth(100).setHeight(100);

		// Adicionar a imagem ao documento
		document.add(imagem);

		// Configurações da tabela
		Table table = new Table(1);
		table.setWidth(UnitValue.createPercentValue(100));
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		table.setFontSize(8);
		// Definir a posição absoluta da tabela
		float xPosition = 290f; // ajuste conforme necessário
		float yPosition = 720; // ajuste conforme necessário
		table.setFixedPosition(xPosition, yPosition, xPosition);

		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("CNPJ: 21.326.756/0001-03"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold()
				.add(new Paragraph("Inscrição Estadual: 3420269570084").setTextAlignment(TextAlignment.RIGHT))
				.setBorder(Border.NO_BORDER));
		table.addCell(new Cell(1, 14).setBold()
				.add(new Paragraph("R. João Batista Mendes, 400, Distrito Industrial")
						.setTextAlignment(TextAlignment.RIGHT).setPaddingBottom(0.1f))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("CEP: 38308-194 – Ituiutaba-MG"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("Tel: (34) 3271-6700")).setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("www.cantodeminas.com.br"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(DeviceRgb.GREEN));

		document.add(table);

		document.add(new Paragraph("TERMO DE RESPONSABILIDADE INDIVIDUAL").setFontSize(14)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(50, 650, 500).setBold());
		/*------------------------------------------------- ATIVO ------------------------------------------------------------*/
		document.add(new Paragraph(
				"ATIVO: " + ativo.getTipo().getNome().toUpperCase() + " - TAG" + ativo.getTag().toUpperCase())
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(50, 580, 220).setBold());

		document.add(new Paragraph("MARCA: " + ativo.getMarca()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 550, 180).setBold());
		document.add(new Paragraph("IMEI: " + ativo.getImei()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(300, 550, 180).setBold());

		document.add(new Paragraph("MODELO: " + ativo.getModelo()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 530, 180).setBold());
		document.add(new Paragraph("CHIP CORPORATIVO: ").setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(300, 530, 180).setBold());

		document.add(new Paragraph("DATA DEVOLUÇÃO: __/__/____").setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 500, 300).setBold());

		/*------------------------------------------------- PERIFERICOS ------------------------------------------------------------*/
		document.add(new Paragraph("PERIFÉRICOS:").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(50, 460, 220).setBold());

		document.add(new Paragraph(form.getBateria().equals(true) ? "BATERIA ( X ) " : "BATERIA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 420, 180).setBold());
		document.add(new Paragraph(form.getPelicula().equals(true) ? "PELÍCULA ( X ) " : "PELÍCULA (  ) ")
				.setFontSize(12).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(150, 420, 180).setBold());
		document.add(new Paragraph(form.getCaixa().equals(true) ? "CAIXA ( X ) " : "CAIXA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(250, 420, 180).setBold());
		document.add(new Paragraph(form.getCarregador().equals(true) ? "CARREGADOR ( X ) " : "CARREGADOR (  ) ")
				.setFontSize(12).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(330, 420, 180).setBold());
		document.add(new Paragraph(form.getCapinha().equals(true) ? "CAPINHA ( X ) " : "CAPINHA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(455, 420, 180).setBold());

		document.add(new Paragraph("O Ativo se encontra em perfeito estado de funcionamento? ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 360, 500).setBold());
		document.add(new Paragraph(form.getConcervado().equals(true) ? "SIM ( X ) " : "SIM (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 340, 180).setBold());
		document.add(new Paragraph(!form.getConcervado().equals(true) ? "NÃO ( X ) " : "NÃO (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 340, 180).setBold());

		/*------------------------------------------------- USUARIO ------------------------------------------------------------*/
		document.add(new Paragraph("DADOS DO USUÁRIO:").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(50, 280, 220).setBold());

		document.add(new Paragraph("NOME: " + usuario.getNome().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 240, 180).setBold());
		document.add(new Paragraph("CPF: " + usuario.getCpf().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 240, 180).setBold());

		document.add(new Paragraph("CARGO: " + usuario.getCpf().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 215, 180).setBold());
		document.add(new Paragraph("TELEFONE: " + usuario.getCelular().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 215, 180).setBold());

		document.add(new Paragraph("________________________________________________").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(130, 130, 500).setBold());
		document.add(
				new Paragraph(usuario.getNome().toUpperCase()).setFontSize(12).setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(60, 110, 500));

		document.add(new AreaBreak());
		/*------------------------------------------------- NOVA PAGINA ------------------------------------------------------------*/

		document.add(imagem);
		document.add(table);

		document.add(new Paragraph("Observação sobre as condições do hardware: ").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 650, 400));
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 600, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 585, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 570, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 555, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 540, 500).setBold());

		document.add(new Paragraph("Parecer do T.I: ").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 480, 400));
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 450, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 435, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 420, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 405, 500).setBold());
		document.add(new Paragraph("____________________________________________________________________")
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 390, 500).setBold());

		document.add(new Paragraph("________________________________________________").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(130, 300, 500).setBold());
		document.add(
				new Paragraph(usuario.getNome().toUpperCase()).setFontSize(12).setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(60, 280, 500));
		document.add(new Paragraph("________________________________________________").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(130, 250, 500).setBold());

		document.add(new Paragraph("LATICÍNIO CANTO DE MINAS").setFontSize(12).setTextAlignment(TextAlignment.CENTER)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(60, 230, 500));

		document.add(new Paragraph("Ituiutaba,___ de ____________ de ______.").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(180, 120, 500));

		document.close();

		return outputStream.toByteArray();

	}

	public byte[] relatorioAquisicao(RelatorioAtivoForm form) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		Ativo ativo = ativoService.buscarAtivoPorId(form.getIdAtivo());
		Usuario usuario = usuarioService.buscarUsuarioPorId(form.getIdUsuario());

		// Cria o documento PDF
		PdfWriter writer = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(writer);
		pdf.setDefaultPageSize(PageSize.A4); // Define a página como paisagem
		Document document = new Document(pdf);
		document.setMargins(20, 20, 20, 20);

		PdfFont font = PdfFontFactory.createFont("Times-Roman");

		document.setFont(font);

		String caminhoDaImagem = "C:\\Users\\Administrador.WIN-83ICR0J7US6\\Desktop\\logoCanto.jpg";

		// Criar um objeto Image
		Image imagem = new Image(ImageDataFactory.create(caminhoDaImagem)).setWidth(100).setHeight(100);

		// Adicionar a imagem ao documento
		document.add(imagem);

		// Configurações da tabela
		Table table = new Table(1);
		table.setWidth(UnitValue.createPercentValue(100));
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		table.setFontSize(8);
		// Definir a posição absoluta da tabela
		float xPosition = 290f; // ajuste conforme necessário
		float yPosition = 720; // ajuste conforme necessário
		table.setFixedPosition(xPosition, yPosition, xPosition);

		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("CNPJ: 21.326.756/0001-03"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold()
				.add(new Paragraph("Inscrição Estadual: 3420269570084").setTextAlignment(TextAlignment.RIGHT))
				.setBorder(Border.NO_BORDER));
		table.addCell(new Cell(1, 14).setBold()
				.add(new Paragraph("R. João Batista Mendes, 400, Distrito Industrial")
						.setTextAlignment(TextAlignment.RIGHT).setPaddingBottom(0.1f))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("CEP: 38308-194 – Ituiutaba-MG"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("Tel: (34) 3271-6700")).setBorder(Border.NO_BORDER)
				.setTextAlignment(TextAlignment.RIGHT));
		table.addCell(new Cell(1, 14).setBold().add(new Paragraph("www.cantodeminas.com.br"))
				.setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontColor(DeviceRgb.GREEN));

		document.add(table);

		document.add(new Paragraph("TERMO DE RESPONSABILIDADE INDIVIDUAL").setFontSize(14)
				.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(50, 650, 500).setBold());
		/*------------------------------------------------- ATIVO ------------------------------------------------------------*/
		document.add(new Paragraph(
				"ATIVO: " + ativo.getTipo().getNome().toUpperCase() + " - TAG" + ativo.getTag().toUpperCase())
				.setFontSize(14).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(50, 580, 220).setBold());

		document.add(new Paragraph("MARCA: " + ativo.getMarca()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 550, 180).setBold());
		document.add(new Paragraph("IMEI: " + ativo.getImei()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(300, 550, 180).setBold());

		document.add(new Paragraph("MODELO: " + ativo.getModelo()).setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 530, 180).setBold());
		document.add(new Paragraph("CHIP CORPORATIVO: ").setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(300, 530, 180).setBold());

		document.add(new Paragraph("DATA AQUISIÇÃO: __/__/____").setFontSize(12).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 500, 300).setBold());

		/*------------------------------------------------- PERIFERICOS ------------------------------------------------------------*/
		document.add(new Paragraph("PERIFÉRICOS:").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(50, 460, 220).setBold());

		document.add(new Paragraph(form.getBateria().equals(true) ? "BATERIA ( X ) " : "BATERIA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 420, 180).setBold());
		document.add(new Paragraph(form.getPelicula().equals(true) ? "PELÍCULA ( X ) " : "PELÍCULA (  ) ")
				.setFontSize(12).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(150, 420, 180).setBold());
		document.add(new Paragraph(form.getCaixa().equals(true) ? "CAIXA ( X ) " : "CAIXA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(250, 420, 180).setBold());
		document.add(new Paragraph(form.getCarregador().equals(true) ? "CARREGADOR ( X ) " : "CARREGADOR (  ) ")
				.setFontSize(12).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(330, 420, 180).setBold());
		document.add(new Paragraph(form.getCapinha().equals(true) ? "CAPINHA ( X ) " : "CAPINHA (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(455, 420, 180).setBold());

		document.add(new Paragraph("O Ativo se encontra em perfeito estado de funcionamento? ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 360, 500).setBold());
		document.add(new Paragraph(form.getConcervado().equals(true) ? "SIM ( X ) " : "SIM (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 340, 180).setBold());
		document.add(new Paragraph(!form.getConcervado().equals(true) ? "NÃO ( X ) " : "NÃO (  ) ").setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 340, 180).setBold());

		/*------------------------------------------------- USUARIO ------------------------------------------------------------*/
		document.add(new Paragraph("DADOS DO USUÁRIO:").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(50, 280, 220).setBold());

		document.add(new Paragraph("NOME: " + usuario.getNome().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 240, 180).setBold());
		document.add(new Paragraph("CPF: " + usuario.getCpf().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 240, 180).setBold());

		document.add(new Paragraph("CARGO: " + usuario.getCpf().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(80, 215, 180).setBold());
		document.add(new Paragraph("TELEFONE: " + usuario.getCelular().toUpperCase()).setFontSize(12)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(300, 215, 180).setBold());

		document.add(new Paragraph("________________________________________________").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(130, 130, 500).setBold());
		document.add(
				new Paragraph(usuario.getNome().toUpperCase()).setFontSize(11).setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(60, 110, 500));

		document.add(new AreaBreak());
		/*------------------------------------------------- NOVA PAGINA ------------------------------------------------------------*/

		document.add(imagem);
		document.add(table);

		document.add(new Paragraph(
				"\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Declaro para todos os fins de direito, que o(s) bem(ns) relacionado(s) neste termo, que recebi\r\n"
						+ "da empresa o aparelho identificado neste documento, que conferi e observei, constatando\r\n"
						+ "que encontra-se sem nenhum defeito aparente, funcionando perfeitamente, e que ficará em\r\n"
						+ "meu poder, para uso exclusivo a serviço da empresa, vedado expressamente o uso para assuntos ou negócios pessoais, responsabilizando-me pessoalmente pela manutenção, guarda\r\n"
						+ "e cuidados necessários, ciente de que poderei ser responsabilizado com advertência/Suspensão/Demissão por justa causa, além de ser obrigado a ressarcir os danos, conforme disposto\r\n"
						+ "no artigo 462 da CLT, salvo ocorrências pelo uso normal.")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(70, 570, 470));

		document.add(new Paragraph("Observações: ").setFontSize(14).setTextAlignment(TextAlignment.LEFT)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(80, 535, 400).setBold());
		document.add(new Paragraph(
				"\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Em caso de extravio por sinistro (furto, roubo, incêndio, desmoronamento e outros) deverei informar ao meu gestor imediatamente e providenciar o boletim de ocorrência reconhecido como idôneo, e imediatamente enviá-lo ao RH da Empresa."
						+ "\n \n \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Em caso que seja comprovado que o equipamento (ou parte dele) foi extraviado por motivos de mau uso ou uso indevido, (como, por exemplo, deixar o aparelho no interior de veículo) estou ciente de que deverei reembolsar a empresa no valor que o equipamento portátil foi adquirido, de acordo com a nota fiscal do mesmo, aplicada nesse caso a depreciação contábil do equipamento. "
						+ "\n \n \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0É expressamente proibido o remanejamento de qualquer item constante desta relação, sem a prévia autorização da área de RH da empresa. Fisicamente, o equipamento está sem danos, conforme observação constante na descrição do equipamento acima, e estou ciente de que tenho a obrigação de cuidá-lo para que permaneça em perfeito estado de funcionamento. Caso o equipamento seja danificado propositalmente, deverei arcar com as despesas que a Empresa incorrerá para repará-lo. "
						+ "\n \n \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Assumo, neste ato, todas as responsabilidades pela conservação, zelo, danos, quebra, perda ou extravio do equipamento."
						+ "\n \n \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Assumo toda e qualquer responsabilidade pelo uso incorreto ou fraudulento do equipamento, a qualquer título que possa causar danos a terceiros, por minha culpa, dolo, omissão, imperícia, imprudência ou negligência, bem como aqueles decorrentes de atos praticados por meus dependentes, sucessores ou prepostos a qualquer título, sejam estes de ordem material ou moral, respondendo pelos mesmos em caráter incontinente, com exclusão de qualquer responsabilidade, seja solidária ou subsidiária da Empresa. "
						+ "\n \n \u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Comprometo-me a devolver imediatamente o equipamento, no mesmo estado e na mesma condição em que o recebi, caso eu venha a perder a condição de empregado da Empresa, seja por rescisão do meu contrato de trabalho, ou por qualquer outro motivo, autorizando desde já a Empresa a quitar qualquer débito que eu tenha, oriundo do presente instrumento, diretamente em minha rescisão ou folha de pagamento salarial.")
				.setFontSize(11).setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(60, 70, 500));

		document.add(new AreaBreak());
		document.add(imagem);
		document.add(table);

		document.add(new Paragraph(
				"\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Todos os serviços de manutenção do aparelho que se façam necessários, seja pelo uso\r\n"
						+ "ou desgaste normal do aparelho, deverão ser executados pela Empresa, ou por empresa\r\n"
						+ "por esta credenciada, cabendo a mim, sempre que necessário, apresentar o aparelho para\r\n"
						+ "manutenção.\r\n"
						+ "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Verificando-se a necessidade de manutenção por mau uso ou uso indevido, estou ciente\r\n"
						+ "de que deverei reembolsar a empresa dos valores despendidos para referida manutenção,\r\n"
						+ "ficando desde já autorizado o desconto em folha de pagamento ou rescisão do contrato de\r\n"
						+ "trabalho.\r\n"
						+ "\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0\u00A0Este termo é confeccionado em uma via, sendo que deverei assinar devolvê-la a área de RH.")
				.setFontSize(11).setTextAlignment(TextAlignment.JUSTIFIED)
				.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(70, 570, 500));

		document.add(new Paragraph("________________________________________________").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(130, 250, 500).setBold());

		document.add(
				new Paragraph(usuario.getNome().toUpperCase()).setFontSize(11).setTextAlignment(TextAlignment.CENTER)
						.setVerticalAlignment(VerticalAlignment.MIDDLE).setFixedPosition(60, 230, 500));

		document.add(new Paragraph("Ituiutaba,___ de ____________ de ______.").setFontSize(14)
				.setTextAlignment(TextAlignment.LEFT).setVerticalAlignment(VerticalAlignment.MIDDLE)
				.setFixedPosition(180, 120, 500));
		document.close();
		return outputStream.toByteArray();

	}
}
