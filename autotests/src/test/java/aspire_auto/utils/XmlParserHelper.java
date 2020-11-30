package aspire_auto.utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class XmlParserHelper {

    public static Document getXMLDocumentFromString(String inputXml) {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(inputXml)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getXmlElementValueByXpath(Document doc, String xpath) throws XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nl = (NodeList) xPath.evaluate(xpath, doc, XPathConstants.NODESET);
        if (nl.getLength() > 0) {
            return nl.item(0).getTextContent();
        } else {
            return null;
        }
    }

}
