package com.infostretch.labs.plugins;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.infostretch.labs.transformers.Transformer;

public class CIDV3Deployer extends Plugins {

    public CIDV3Deployer(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformPublisher() {
	String categories = getElementByTag("categories").getTextContent();
	String environments = getElementByTag("environments").getTextContent();
	String project = getElementByTag("project").getTextContent();

	String step = "step([$class: 'CIDV3Deployer', categories: \'%s\', environments: \'%s\', project: \'%s\'])";
	
	step = String.format(step, categories, environments, project);

	transformer.setOnlyBuildTrigger(false);
	appendPublishSteps("\n\t\t" + step + "\n");
    }
}





