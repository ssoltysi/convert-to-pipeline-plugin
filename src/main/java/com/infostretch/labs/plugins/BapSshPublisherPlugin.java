package com.infostretch.labs.plugins;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.infostretch.labs.transformers.Transformer;

public class BapSshPublisherPlugin extends Plugins {

    public BapSshPublisherPlugin(Transformer transformer, Node node) {
        super(transformer, node);
    }

    @Override
    public void transformPublisher() {
	Element delegate = getElementByTag("jenkins.plugins.publish__over__ssh.BapSshPublisher");
	String configName = getElementByTag(delegate, "configName").getTextContent();
	String sourceFiles = getElementByTag(delegate, "sourceFiles").getTextContent();
	String flatten = getElementByTag(delegate, "flatten").getTextContent();

	String step = "sshPublisher(publishers: [sshPublisherDesc(configName: \'%s\', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '', execTimeout: 120000, flatten: %s, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '${JOB_NAME}/lastSuccessfulBuild', remoteDirectorySDF: false, removePrefix: '', sourceFiles: \'%s\')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])";
	
	step = String.format(step, flatten, configName, sourceFiles);

	transformer.setOnlyBuildTrigger(false);
	appendPublishSteps("\n\t\t" + step + "\n");
    }
}
