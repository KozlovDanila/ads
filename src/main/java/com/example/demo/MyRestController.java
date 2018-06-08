package com.example.demo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@RestController
@EnableAutoConfiguration
public class MyRestController {

	@GetMapping
	@RequestMapping(value = "/currencies/{currencies}", method = RequestMethod.GET)
	ModelAndView getCurrencies(@PathVariable String currencies) {
		RestTemplate restTemplate = new RestTemplate();
		Document doc = stringToDocument(restTemplate.getForObject("https://www.cbr-xml-daily.ru/daily.xml", String.class));
		List<Currencies> curr = filter(getCurrenciesw(doc.getDocumentElement().getChildNodes()), currencies);
		ModelAndView result = new ModelAndView("currencies");
		result.addObject("curr", curr);
		return result;
	}


	public static void main(String[] args) {
		SpringApplication.run(MyRestController.class, args);
	}

	private static Document stringToDocument(String xmlStr) {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = builderFactory.newDocumentBuilder();
			return docBuilder.parse(new InputSource(new StringReader(xmlStr)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Currencies> getCurrenciesw(NodeList valute) {
		List<Currencies> result = new ArrayList<>();
		for (int i = 0; i < valute.getLength(); i++) {
			Currencies curr = new Currencies();
			NodeList nodes = valute.item(i).getChildNodes();
			for (int j = 0; j < nodes.getLength(); j++) {
				setCurrenciesValue(curr, nodes.item(j).getNodeName().toLowerCase(), nodes.item(j).getChildNodes().item(0).getNodeValue());
			}
			result.add(curr);
		}
		return result;
	}

	private void setCurrenciesValue(Currencies curr, String name, String value) {
		switch (name) {
			case "numcode":
				curr.setNumCode(value);
				break;
			case "charcode":
				curr.setCharCode(value);
				break;
			case "nominal":
				curr.setNominal(value);
				break;
			case "name":
				curr.setName(value);
				break;
			case "value":
				curr.setValue(value);
				break;
		}
	}

	private List<Currencies> filter(List<Currencies> currList, String currencies) {
		List<Currencies> result = new ArrayList<>();
		for (Currencies curr : currList) {
			if (currencies.contains(curr.getCharCode().toUpperCase())) {
				result.add(curr);
			}
		}
		return result;
	}
}
