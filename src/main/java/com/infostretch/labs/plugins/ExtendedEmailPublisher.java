package com.infostretch.labs.plugins;

import com.infostretch.labs.transformers.Transformer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Handles transformation of MsBuildBuilder properties
 *
 */
public class ExtendedEmailPublisher extends Plugins{

    public ExtendedEmailPublisher(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformPublisher() {
        appendBuildSteps("\t\t// ExtendedEmailPublisher build step");

	String attachBuildLog = getContentFromElement("attachBuildLog", "false");
	String attachmentsPattern = getContentFromElement("attachmentsPattern", "");
	String defaultContent = getContentFromElement("defaultContent", "$DEFAULT_CONTENT");
	String compressBuildLog = getContentFromElement("compressBuildLog", "false");
	String contentType = getContentFromElement("contentType", "default");
	String postsendScript = getContentFromElement("postsendScript", "");
	String presendScript = getContentFromElement("presendScript", "");
	String replyTo = getContentFromElement("replyTo", "");
	String defaultSubject = getContentFromElement("defaultSubject", "");
	String recipientList = getContentFromElement("recipientList", "");
	recipientList = recipientList.replace(',', ' ');

	String buildStep = "emailext attachLog: %s, attachmentsPattern: \'%s\', body: \'%s\', compressLog: %s, mimeType: \'%s\', postsendScript: \'%s\', presendScript: \'%s\', recipientProviders: [developers(), requestor()], replyTo: \'%s\', subject: \'%s\', to: \'%s\'";

	buildStep = String.format(buildStep, attachBuildLog, attachmentsPattern, defaultContent, compressBuildLog, contentType, postsendScript, presendScript, replyTo, defaultSubject, recipientList);

	appendNotificationSteps("\n\t" + buildStep + "\n");
    }

    private String getContentFromElement(String tagName, String defaultVal) {
	Element element = getChildElementByName(tagName);

	String content = defaultVal;
	
	if (element != null) {
	    content = element.getTextContent();
	}

	return content;
    }

    private Element getChildElementByName(String name) {
	NodeList nodes = node.getChildNodes();

	for (int i = 0; i < nodes.getLength(); i++) {
	    Node child = nodes.item(i);

	    if (child.getNodeType() == Node.ELEMENT_NODE) {
		Element el = (Element)child;
		String tag = el.getTagName();
		if (name.equals(tag)) {
		    return el;
		}
	    }
	}

	return null;
    }
	
}
