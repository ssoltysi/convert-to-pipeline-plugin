package com.infostretch.labs.plugins;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.infostretch.labs.transformers.Transformer;

public class HtmlPublisher extends Plugins {

    public HtmlPublisher(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformPublisher() {
	String allowMissing = getElementByTag("allowMissing").getTextContent();
	String alwaysLinkToLastBuild = getElementByTag("alwaysLinkToLastBuild").getTextContent();
	String keepAll = getElementByTag("keepAll").getTextContent();
	String reportDir = getElementByTag("reportDir").getTextContent();
	String reportFiles = getElementByTag("reportFiles").getTextContent();
	String reportName = getElementByTag("reportName").getTextContent();
	String reportTitles = getElementByTag("reportTitles").getTextContent();

	String publishHTML = "publishHTML([allowMissing: %s, alwaysLinkToLastBuild: %s, keepAll: %s, reportDir: \'%s\', reportFiles: \'%s\', reportName: \'%s\', reportTitles: \'%s\'])";
	publishHTML = String.format(publishHTML, allowMissing, alwaysLinkToLastBuild, keepAll, reportDir, reportFiles, reportName, reportTitles);
	
	appendPublishSteps("\n\t\t" + publishHTML + "\n");
    }
}





